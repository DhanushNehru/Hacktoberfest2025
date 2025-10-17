emp = []

def add_employee():
    emp_id = int(input("Enter the employee id: "))
    emp_name = str(input("Enter the employee name: "))
    emp_dep = str(input("Enter the employee department: "))
    emp_age = int(input("Enter the employee age: "))
    emp_salary = int(input("Enter the employee salary: "))

    employee = {"id":emp_id,"name":emp_name,"department":emp_dep,"age":emp_age,"salary":emp_salary}
    emp.append(employee)
    print("Employee added successfully")

def view_employees():
    if  not  emp:
        print("There is no any employee found ")
        return
    print("-----------------------------")

    for employee in emp:
        print(f"ID: {employee["id"]}, Name: {employee["name"]}, Department: {employee["department"]}, Age: {employee["age"]}, Salary: {employee["salary"]}")
    print("-------------------------------------------------------------------------------------------------------")

def search_employee():
    emp_id = int(input("Enter employee id to search: "))
    for employee in emp:
        if emp_id == employee["id"]:
            print("Found ",emp)
        return
    print("Not found")

def update_employee():
    emp_id = int(input("Enter employee id to update: "))
    for employee in emp:
        if emp_id == employee["id"]:
            new_emp_name = str(input("Enter the employee name: ")) or employee["name"]
            new_emp_dep = str(input("Enter the employee department: ")) or employee["department"]
            new_emp_age = int(input("Enter the employee age: ")) or employee["age"]
            new_emp_salary = int(input("Enter the employee salary: ")) or employee["salary"]

            employee["name"] = new_emp_name
            employee["department"] = new_emp_dep
            employee["age"] = new_emp_age
            employee["salary"] = new_emp_salary

            print("Employee updated successfully")
            return
    print("Not found")


def delete_employee():
    emp_id = int(input("Enter the employee id to delete: "))

    for employee in emp:
        if emp_id == employee["id"]:
            emp.remove(employee)
            print("Employee deleted successfully ")
            return
    print("Employee not found")
