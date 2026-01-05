def add_task(task):
    with open('todo.txt', 'a') as f:
        f.write(f'{task}\n')

def show_tasks():
    with open('todo.txt', 'r') as f:
        tasks = f.readlines()
    if tasks:
        for i, task in enumerate(tasks, 1):
            print(f'{i}. {task.strip()}')
    else:
        print("No tasks yet!")


add_task("Learn Git and GitHub")


show_tasks()
