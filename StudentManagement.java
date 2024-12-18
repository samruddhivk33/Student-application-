import java.util.HashMap;
import java.util.Scanner;

class Student {
    private String name;
    private int age;
    private String grade;

    Student(String name, int age, String grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Grade: " + grade;
    }
}

public class StudentManagement {
    private static HashMap<Integer, Student> studentRecords = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Student\n2. Update Student\n3. Remove Student\n4. View Student Details\n5. View All Students\n6. Exit");
            System.out.print("Enter your choice: ");
            int choice = getIntInput();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> updateStudent();
                case 3 -> removeStudent();
                case 4 -> viewStudentDetails();
                case 5 -> viewAllStudents();
                case 6 -> {
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter student ID: ");
        int id = getIntInput();

        if (studentRecords.containsKey(id)) {
            System.out.println("ID already exists.");
            return;
        }
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = getIntInput();
        System.out.print("Enter grade: ");
        String grade = scanner.nextLine();

        studentRecords.put(id, new Student(name, age, grade));
        System.out.println("Student added.");
    }

    private static void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = getIntInput();

        if (!studentRecords.containsKey(id)) {
            System.out.println("ID not found.");
            return;
        }
        Student student = studentRecords.get(id);
        System.out.print("Enter new name (current: " + student.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) student.setName(name);

        System.out.print("Enter new age (current: " + student.getAge() + "): ");
        int age = getIntInput();
        if (age > 0) student.setAge(age);

        System.out.print("Enter new grade (current: " + student.getGrade() + "): ");
        String grade = scanner.nextLine();
        if (!grade.isEmpty()) student.setGrade(grade);

        System.out.println("Student updated.");
    }

    private static void removeStudent() {
        System.out.print("Enter student ID to remove: ");
        int id = getIntInput();

        if (studentRecords.remove(id) != null) {
            System.out.println("Student removed.");
        } else {
            System.out.println("ID not found.");
        }
    }

    private static void viewStudentDetails() {
        System.out.print("Enter student ID to view: ");
        int id = getIntInput();

        Student student = studentRecords.get(id);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("ID not found.");
        }
    }

    private static void viewAllStudents() {
        if (studentRecords.isEmpty()) {
            System.out.println("No records found.");
        } else {
            studentRecords.forEach((id, student) -> System.out.println("ID: " + id + ", " + student));
        }
    }

    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }
}
