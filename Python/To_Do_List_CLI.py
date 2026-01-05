import json, os

FILE = "tasks.json"

def load_tasks():
    if os.path.exists(FILE):
        with open(FILE, "r") as f: return json.load(f)
    return []

def save_tasks(tasks):
    with open(FILE, "w") as f: json.dump(tasks, f, indent=2)

def add_task(task):
    tasks = load_tasks()
    tasks.append({"task": task, "done": False})
    save_tasks(tasks)

def show_tasks():
    for i, t in enumerate(load_tasks(), 1):
        status = "✔️" if t["done"] else "❌"
        print(f"{i}. {t['task']} [{status}]")

add_task("Finish homework")
show_tasks()
