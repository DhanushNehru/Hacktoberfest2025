import json
import os
from datetime import datetime
from typing import List, Dict

class Task:
    """Represents a single task"""
    
    def __init__(self, task_id: int, title: str, description: str = "", 
                 priority: str = "medium", completed: bool = False):
        self.id = task_id
        self.title = title
        self.description = description
        self.priority = priority
        self.completed = completed
        self.created_at = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        self.completed_at = None
    
    def mark_complete(self):
        """Mark the task as completed"""
        self.completed = True
        self.completed_at = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    
    def mark_incomplete(self):
        """Mark the task as incomplete"""
        self.completed = False
        self.completed_at = None
    
    def to_dict(self) -> Dict:
        """Convert task to dictionary"""
        return {
            'id': self.id,
            'title': self.title,
            'description': self.description,
            'priority': self.priority,
            'completed': self.completed,
            'created_at': self.created_at,
            'completed_at': self.completed_at
        }
    
    @staticmethod
    def from_dict(data: Dict) -> 'Task':
        """Create task from dictionary"""
        task = Task(data['id'], data['title'], data['description'], data['priority'])
        task.completed = data['completed']
        task.created_at = data['created_at']
        task.completed_at = data.get('completed_at')
        return task


class TaskManager:
    """Task Manager CLI Application"""
    
    def __init__(self, data_file: str = "tasks.json"):
        self.data_file = data_file
        self.tasks: List[Task] = []
        self.load_tasks()
    
    def load_tasks(self):
        """Load tasks from JSON file"""
        if os.path.exists(self.data_file):
            try:
                with open(self.data_file, 'r') as f:
                    data = json.load(f)
                    self.tasks = [Task.from_dict(task_data) for task_data in data]
            except (json.JSONDecodeError, KeyError):
                print(f"Warning: Could not load tasks from {self.data_file}")
                self.tasks = []
        else:
            self.tasks = []
    
    def save_tasks(self):
        """Save tasks to JSON file"""
        try:
            with open(self.data_file, 'w') as f:
                data = [task.to_dict() for task in self.tasks]
                json.dump(data, f, indent=2)
        except Exception as e:
            print(f"Error saving tasks: {e}")
    
    def get_next_id(self) -> int:
        """Get the next available task ID"""
        if not self.tasks:
            return 1
        return max(task.id for task in self.tasks) + 1
    
    def add_task(self, title: str, description: str = "", priority: str = "medium"):
        """Add a new task"""
        task_id = self.get_next_id()
        task = Task(task_id, title, description, priority)
        self.tasks.append(task)
        self.save_tasks()
        print(f"\n✓ Task added successfully! (ID: {task_id})")
    
    def remove_task(self, task_id: int) -> bool:
        """Remove a task by ID"""
        for i, task in enumerate(self.tasks):
            if task.id == task_id:
                self.tasks.pop(i)
                self.save_tasks()
                print(f"\n✓ Task {task_id} removed successfully!")
                return True
        print(f"\n✗ Task {task_id} not found!")
        return False
    
    def complete_task(self, task_id: int) -> bool:
        """Mark a task as completed"""
        for task in self.tasks:
            if task.id == task_id:
                task.mark_complete()
                self.save_tasks()
                print(f"\n✓ Task {task_id} marked as complete!")
                return True
        print(f"\n✗ Task {task_id} not found!")
        return False
    
    def uncomplete_task(self, task_id: int) -> bool:
        """Mark a task as incomplete"""
        for task in self.tasks:
            if task.id == task_id:
                task.mark_incomplete()
                self.save_tasks()
                print(f"\n✓ Task {task_id} marked as incomplete!")
                return True
        print(f"\n✗ Task {task_id} not found!")
        return False
    
    def update_task(self, task_id: int, title: str = None, 
                   description: str = None, priority: str = None):
        """Update task details"""
        for task in self.tasks:
            if task.id == task_id:
                if title:
                    task.title = title
                if description is not None:
                    task.description = description
                if priority:
                    task.priority = priority
                self.save_tasks()
                print(f"\n✓ Task {task_id} updated successfully!")
                return True
        print(f"\n✗ Task {task_id} not found!")
        return False
    
    def list_tasks(self, show_completed: bool = True, filter_priority: str = None):
        """List all tasks"""
        if not self.tasks:
            print("\nNo tasks found!")
            return
        
        # Filter tasks
        filtered_tasks = self.tasks
        if not show_completed:
            filtered_tasks = [t for t in filtered_tasks if not t.completed]
        if filter_priority:
            filtered_tasks = [t for t in filtered_tasks if t.priority == filter_priority]
        
        if not filtered_tasks:
            print("\nNo tasks match the filter!")
            return
        
        # Sort: incomplete first, then by priority
        priority_order = {'high': 0, 'medium': 1, 'low': 2}
        filtered_tasks.sort(key=lambda x: (x.completed, priority_order.get(x.priority, 1)))
        
        print("\n" + "=" * 80)
        print("TASKS".center(80))
        print("=" * 80)
        
        for task in filtered_tasks:
            self._print_task(task)
    
    def _print_task(self, task: Task):
        """Print a single task with formatting"""
        status = "✓" if task.completed else "○"
        priority_symbol = {
            'high': '🔴',
            'medium': '🟡',
            'low': '🟢'
        }.get(task.priority, '⚪')
        
        print(f"\n{status} [{task.id}] {priority_symbol} {task.title}")
        if task.description:
            print(f"   Description: {task.description}")
        print(f"   Priority: {task.priority.upper()} | Created: {task.created_at}")
        if task.completed and task.completed_at:
            print(f"   Completed: {task.completed_at}")
        print("-" * 80)
    
    def search_tasks(self, keyword: str):
        """Search tasks by keyword"""
        results = [t for t in self.tasks if keyword.lower() in t.title.lower() 
                   or keyword.lower() in t.description.lower()]
        
        if not results:
            print(f"\nNo tasks found matching '{keyword}'")
            return
        
        print(f"\n{len(results)} task(s) found matching '{keyword}':")
        for task in results:
            self._print_task(task)
    
    def get_statistics(self):
        """Display task statistics"""
        total = len(self.tasks)
        completed = sum(1 for t in self.tasks if t.completed)
        incomplete = total - completed
        high_priority = sum(1 for t in self.tasks if t.priority == 'high' and not t.completed)
        
        print("\n" + "=" * 50)
        print("STATISTICS".center(50))
        print("=" * 50)
        print(f"Total Tasks: {total}")
        print(f"Completed: {completed}")
        print(f"Incomplete: {incomplete}")
        print(f"High Priority (Incomplete): {high_priority}")
        if total > 0:
            print(f"Completion Rate: {(completed/total)*100:.1f}%")
        print("=" * 50)


def print_menu():
    """Display the main menu"""
    print("\n" + "=" * 50)
    print("TASK MANAGER CLI".center(50))
    print("=" * 50)
    print("1.  Add Task")
    print("2.  List All Tasks")
    print("3.  List Incomplete Tasks")
    print("4.  Complete Task")
    print("5.  Uncomplete Task")
    print("6.  Remove Task")
    print("7.  Update Task")
    print("8.  Search Tasks")
    print("9.  View Statistics")
    print("10. Clear Completed Tasks")
    print("0.  Exit")
    print("=" * 50)


def main():
    """Main function to run the Task Manager CLI"""
    manager = TaskManager()
    
    while True:
        print_menu()
        choice = input("\nEnter your choice: ").strip()
        
        if choice == '1':
            # Add Task
            title = input("Task title: ").strip()
            if not title:
                print("\n✗ Title cannot be empty!")
                continue
            description = input("Description (optional): ").strip()
            priority = input("Priority (high/medium/low) [medium]: ").strip().lower() or "medium"
            if priority not in ['high', 'medium', 'low']:
                print("\n✗ Invalid priority! Using 'medium'")
                priority = 'medium'
            manager.add_task(title, description, priority)
        
        elif choice == '2':
            # List All Tasks
            manager.list_tasks()
        
        elif choice == '3':
            # List Incomplete Tasks
            manager.list_tasks(show_completed=False)
        
        elif choice == '4':
            # Complete Task
            try:
                task_id = int(input("Enter task ID to complete: ").strip())
                manager.complete_task(task_id)
            except ValueError:
                print("\n✗ Invalid task ID!")
        
        elif choice == '5':
            # Uncomplete Task
            try:
                task_id = int(input("Enter task ID to mark incomplete: ").strip())
                manager.uncomplete_task(task_id)
            except ValueError:
                print("\n✗ Invalid task ID!")
        
        elif choice == '6':
            # Remove Task
            try:
                task_id = int(input("Enter task ID to remove: ").strip())
                confirm = input(f"Are you sure you want to remove task {task_id}? (y/n): ").lower()
                if confirm == 'y':
                    manager.remove_task(task_id)
            except ValueError:
                print("\n✗ Invalid task ID!")
        
        elif choice == '7':
            # Update Task
            try:
                task_id = int(input("Enter task ID to update: ").strip())
                print("Leave blank to keep current value")
                title = input("New title: ").strip() or None
                description = input("New description: ").strip()
                priority = input("New priority (high/medium/low): ").strip().lower() or None
                manager.update_task(task_id, title, description if description else None, priority)
            except ValueError:
                print("\n✗ Invalid task ID!")
        
        elif choice == '8':
            # Search Tasks
            keyword = input("Enter search keyword: ").strip()
            if keyword:
                manager.search_tasks(keyword)
            else:
                print("\n✗ Search keyword cannot be empty!")
        
        elif choice == '9':
            # View Statistics
            manager.get_statistics()
        
        elif choice == '10':
            # Clear Completed Tasks
            completed_count = sum(1 for t in manager.tasks if t.completed)
            if completed_count == 0:
                print("\nNo completed tasks to clear!")
            else:
                confirm = input(f"Remove {completed_count} completed task(s)? (y/n): ").lower()
                if confirm == 'y':
                    manager.tasks = [t for t in manager.tasks if not t.completed]
                    manager.save_tasks()
                    print(f"\n✓ {completed_count} completed task(s) removed!")
        
        elif choice == '0':
            # Exit
            print("\nGoodbye! Stay productive! 💪")
            break
        
        else:
            print("\n✗ Invalid choice! Please try again.")
        
        input("\nPress Enter to continue...")


if __name__ == "__main__":
    main()
