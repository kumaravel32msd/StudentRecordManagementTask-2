package Task2;

import java.util.*;

// Student class
class Student {
    private final int id;
    private String name;
    private double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getMarks() { return marks; }

    public void setName(String name) { this.name = name; }
    public void setMarks(double marks) { this.marks = marks; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Marks: %.2f", id, name, marks);
    }
}

// Management class (handles operations)
class StudentManager {
    private final ArrayList<Student> studentList = new ArrayList<>();

    // Add Student
    public void addStudent(Student s) {
        for (Student st : studentList) {
            if (st.getId() == s.getId()) {
                System.out.println(" ID already exists. Try a different one!");
                return;
            }
        }
        studentList.add(s);
        System.out.println("✅ Student added successfully!");
    }

    // View All Students
    public void viewStudents() {
        if (studentList.isEmpty()) {
            System.out.println(" No students found.");
            return;
        }
        System.out.println("\n---- Student Records ----");
        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    // Update Student by ID
    public void updateStudent(int id, String newName, double newMarks) {
        for (Student s : studentList) {
            if (s.getId() == id) {
                s.setName(newName);
                s.setMarks(newMarks);
                System.out.println("✅ Student updated successfully!");
                return;
            }
        }
        System.out.println(" Student not found!");
    }

    // Delete Student by ID
    public void deleteStudent(int id) {
        Iterator<Student> it = studentList.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getId() == id) {
                it.remove();
                System.out.println("🗑️ Student deleted successfully!");
                return;
            }
        }
        System.out.println(" Student not found!");
    }

    // Sort students by marks (extra feature)
    public void sortByMarks() {
        studentList.sort(Comparator.comparingDouble(Student::getMarks).reversed());
        System.out.println(" Students sorted by marks (High → Low):");
        viewStudents();
    }
}

// Main class (user interaction)
public class StudentRecordManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        int choice;
        do {
            System.out.println("\n====== Student Management System ======");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Sort by Marks");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks = sc.nextDouble();
                    manager.addStudent(new Student(id, name, marks));
                    break;

                case 2:
                    manager.viewStudents();
                    break;

                case 3:
                    System.out.print("Enter ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter new marks: ");
                    double newMarks = sc.nextDouble();
                    manager.updateStudent(updateId, newName, newMarks);
                    break;

                case 4:
                    System.out.print("Enter ID to delete: ");
                    int deleteId = sc.nextInt();
                    manager.deleteStudent(deleteId);
                    break;

                case 5:
                    manager.sortByMarks();
                    break;

                case 6:
                    System.out.println(" Exiting program... Goodbye!");
                    break;

                default:
                    System.out.println(" Invalid choice. Try again!");
            }
        } while (choice != 6);

        sc.close();
    }
}
