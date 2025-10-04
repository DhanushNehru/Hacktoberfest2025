package main

import (
	"encoding/json"
	"fmt"
	"net/http"
	"os"
	"strconv"
	"strings"
)

type Task struct {
	ID   int    `json:"id"`
	Name string `json:"name"`
}

const dataFile = "tasks.json"

// ---------- Helpers ----------
func loadTasks() ([]Task, error) {
	var tasks []Task
	file, err := os.ReadFile(dataFile)
	if err != nil {
		if os.IsNotExist(err) {
			return []Task{}, nil
		}
		return nil, err
	}
	json.Unmarshal(file, &tasks)
	return tasks, nil
}

func saveTasks(tasks []Task) error {
	data, err := json.MarshalIndent(tasks, "", "  ")
	if err != nil {
		return err
	}
	return os.WriteFile(dataFile, data, 0644)
}

// ---------- CORS Middleware ----------
func enableCORS(w http.ResponseWriter) {
	w.Header().Set("Access-Control-Allow-Origin", "*")
	w.Header().Set("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS")
	w.Header().Set("Access-Control-Allow-Headers", "Content-Type")
}

func withCORS(handler http.HandlerFunc) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		enableCORS(w)
		if r.Method == http.MethodOptions {
			w.WriteHeader(http.StatusOK)
			return
		}
		handler(w, r)
	}
}

// ---------- Handlers ----------
func getTasksHandler(w http.ResponseWriter, r *http.Request) {
	fmt.Printf("[%s] %s %s\n", r.RemoteAddr, r.Method, r.URL.Path)
	tasks, _ := loadTasks()
	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(tasks)
}

func addTaskHandler(w http.ResponseWriter, r *http.Request) {
	fmt.Printf("[%s] %s %s\n", r.RemoteAddr, r.Method, r.URL.Path)
	var newTask Task
	json.NewDecoder(r.Body).Decode(&newTask)

	if strings.TrimSpace(newTask.Name) == "" {
		http.Error(w, "Task name is required", http.StatusBadRequest)
		return
	}

	tasks, _ := loadTasks()

	// find max ID
	maxID := 0
	for _, t := range tasks {
		if t.ID > maxID {
			maxID = t.ID
		}
	}
	newTask.ID = maxID + 1

	tasks = append(tasks, newTask)
	saveTasks(tasks)

	w.WriteHeader(http.StatusCreated)
	json.NewEncoder(w).Encode(newTask)
}

func deleteTaskHandler(w http.ResponseWriter, r *http.Request) {
	fmt.Printf("[%s] %s %s\n", r.RemoteAddr, r.Method, r.URL.Path)

	parts := strings.Split(r.URL.Path, "/")
	if len(parts) < 3 {
		http.Error(w, "Task ID required", http.StatusBadRequest)
		return
	}
	id, err := strconv.Atoi(parts[2])
	if err != nil {
		http.Error(w, "Invalid ID", http.StatusBadRequest)
		return
	}

	tasks, _ := loadTasks()
	newTasks := []Task{}
	for _, t := range tasks {
		if t.ID != id {
			newTasks = append(newTasks, t)
		}
	}
	saveTasks(newTasks)
	w.WriteHeader(http.StatusNoContent)
}

// ---------- Main ----------
func main() {
	http.HandleFunc("/tasks", withCORS(func(w http.ResponseWriter, r *http.Request) {
		switch r.Method {
		case "GET":
			getTasksHandler(w, r)
		case "POST":
			addTaskHandler(w, r)
		default:
			http.Error(w, "Method not allowed", http.StatusMethodNotAllowed)
		}
	}))

	http.HandleFunc("/tasks/", withCORS(deleteTaskHandler)) // e.g., /tasks/3

	fmt.Println("Server running at http://localhost:8080")
	http.ListenAndServe(":8080", nil)
}
