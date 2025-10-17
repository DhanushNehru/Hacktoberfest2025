from employee import add_employee,view_employees,update_employee,delete_employee,search_employee

def main():
    while True:
        print("==== Employee Management System ====")
        print("1. Add Employee")
        print("2. View All Employees")
        print("3. Search Employee")
        print("4. Update Employee")
        print("5. Delete Employee")
        print("6. Exit")

        choice = input("Enter your choice (1-6): ")

        if choice == '1':
            add_employee()
        elif choice == '2':
            view_employees()
        elif choice == '3':
            search_employee()
        elif choice == '4':
            update_employee()
        elif choice == '5':
            delete_employee()
        elif choice == '6':
            print(" Exiting system...")
            break
        else:
            print("❌ Invalid choice! Try again.\n")

if __name__ == '__main__':
    main()
