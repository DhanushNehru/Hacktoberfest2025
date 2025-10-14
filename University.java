
class University {

    public String uniName;

    public University(String name) {
        this.uniName = name;
    }

    public University(University u1) {
        this.uniName = u1.uniName;
    }

    static class Library {

        public int libId;
        public static int totalBooks = 0;
        public String libName;
        public static int libCounter = 0;

        public Library(String name) {
            this.libName = name;
            this.libId = libCounter++;
        }

        public void addBooks(String title) {
            totalBooks++;
            System.out.println("Book added: " + title);
        }

        public void addBooks(String title, String author) {
            totalBooks++;
            System.out.println("Book added: " + title + " By" + author);
        }

        public void addBooks(String title, String author, int copies) {
            totalBooks += copies;
            System.out.println("Book added: " + title + " by" + author + " copies are: " + copies);
        }

        public void display() {
            System.out.println("Library : " + libName + "Books: " + totalBooks);
        }
    }

    class Department {

        public String deptName;
        public int facultyCount;

        public Department(String name) {
            this.deptName = name;
            this.facultyCount = 0;
        }

        public Department(String name, int facultyCount) {
            this.deptName = name;
            this.facultyCount = facultyCount;
        }

        public void printDept() {
            System.out.println("University: " + uniName + ", Department: " + deptName + ", Faculty: " + facultyCount);
        }
    }

    public static void main(String[] args) {
        University u = new University("IIITDMJ");
        University.Library lib = new University.Library("Central Library");
        lib.addBooks("Java", "James Gosling", 5);
        lib.addBooks("C++ Primer");
        lib.display();
        University.Department d1 = u.new Department("Computer Science", 40);
        d1.printDept();
    }
}
