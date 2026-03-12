import java.util.*;

class Student {
    int studentId;
    String name;
    String className;
    String section;
    int rollNo;
    Map<String, Integer> marks = new HashMap<>();

    Student(int studentId, String name, String className, String section, int rollNo) {
        this.studentId = studentId;
        this.name = name;
        this.className = className;
        this.section = section;
        this.rollNo = rollNo;
    }

    void addMark(String subject, int mark) {
        marks.put(subject, mark);
    }

    int calculateTotal() {
        int total = 0;
        for (int m : marks.values()) {
            total += m;
        }
        return total;
    }

    String calculateGrade() {
        int total = calculateTotal();
        int avg = total / marks.size();

        if (avg >= 90) return "A+";
        else if (avg >= 75) return "A";
        else if (avg >= 60) return "B";
        else if (avg >= 50) return "C";
        else return "Fail";
    }

    void displayReport() {
        System.out.println("\n--- Report Card ---");
        System.out.println("Name: " + name);
        System.out.println("Class: " + className + " Section: " + section);
        System.out.println("Roll No: " + rollNo);

        for (String subject : marks.keySet()) {
            System.out.println(subject + ": " + marks.get(subject));
        }

        System.out.println("Total: " + calculateTotal());
        System.out.println("Grade: " + calculateGrade());
    }
}

public class MarksManagementSystem {

    static Scanner sc = new Scanner(System.in);
    static List<Student> students = new ArrayList<>();
    static List<String> teachers = new ArrayList<>();
    static List<String> subjects = new ArrayList<>();

    // ---------------- ADMIN MODULE ----------------
    static void adminModule() {
        while (true) {
            System.out.println("\n--- Admin Module ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Teacher");
            System.out.println("3. Add Subject");
            System.out.println("4. View Students");
            System.out.println("5. Back");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Class: ");
                    String cls = sc.nextLine();
                    System.out.print("Enter Section: ");
                    String sec = sc.nextLine();
                    System.out.print("Enter Roll No: ");
                    int roll = sc.nextInt();

                    students.add(new Student(id, name, cls, sec, roll));
                    System.out.println("Student Added Successfully!");
                    break;

                case 2:
                    sc.nextLine();
                    System.out.print("Enter Teacher Name: ");
                    teachers.add(sc.nextLine());
                    System.out.println("Teacher Added!");
                    break;

                case 3:
                    sc.nextLine();
                    System.out.print("Enter Subject Name: ");
                    subjects.add(sc.nextLine());
                    System.out.println("Subject Added!");
                    break;

                case 4:
                    for (Student s : students) {
                        System.out.println("ID: " + s.studentId + " Name: " + s.name);
                    }
                    break;

                case 5:
                    return;
            }
        }
    }

    // ---------------- TEACHER MODULE ----------------
    static void teacherModule() {
        System.out.print("Enter Student ID to Enter Marks: ");
        int id = sc.nextInt();

        Student found = null;
        for (Student s : students) {
            if (s.studentId == id) {
                found = s;
                break;
            }
        }

        if (found == null) {
            System.out.println("Student Not Found!");
            return;
        }

        sc.nextLine();
        for (String sub : subjects) {
            System.out.print("Enter marks for " + sub + ": ");
            int mark = sc.nextInt();
            found.addMark(sub, mark);
        }

        System.out.println("Marks Updated Successfully!");
    }

    // ---------------- STUDENT MODULE ----------------
    static void studentModule() {
        System.out.print("Enter Student ID to View Report: ");
        int id = sc.nextInt();

        for (Student s : students) {
            if (s.studentId == id) {
                s.displayReport();
                return;
            }
        }

        System.out.println("Student Not Found!");
    }

    // ---------------- MAIN METHOD ----------------
    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== Online Student Marks Management System =====");
            System.out.println("1. Admin Login");
            System.out.println("2. Teacher Login");
            System.out.println("3. Student Login");
            System.out.println("4. Exit");
            System.out.print("Choose Option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    adminModule();
                    break;
                case 2:
                    teacherModule();
                    break;
                case 3:
                    studentModule();
                    break;
                case 4:
                    System.out.println("Exiting System...");
                    System.exit(0);
            }
        }
    }
}