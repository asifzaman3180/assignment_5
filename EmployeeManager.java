// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        // Argument validation
        if (args.length != 1) {
            System.out.println(Constants.ERROR_INVALID_ARGS);
            System.out.println(Constants.USAGE_INFO);
            return;
        }

        String command = args[0];
        System.out.println(Constants.LOADING_DATA);

        try {
            if (command.equals("l")) {
                listEmployees();
            } 
            else if (command.equals("s")) {
                showRandomEmployee();
            } 
            else if (command.startsWith("+")) {
                addEmployee(command.substring(1));
            } 
            else if (command.startsWith("?")) {
                searchEmployee(command.substring(1));
            } 
            else if (command.equals("c")) {
                countWords();
            } 
            else if (command.startsWith("u")) {
                updateEmployee(command.substring(1));
            } 
            else if (command.startsWith("d")) {
                deleteEmployee(command.substring(1));
            } 
            else {
                System.out.println("Error: Unknown command.");
                System.out.println(Constants.USAGE_INFO);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while processing the command.");
        }

        System.out.println(Constants.DATA_LOADED);
    }

    // ✅ Simplified methods for each operation

    private static void listEmployees() throws IOException {
        for (String employee : readEmployees()) {
            System.out.println(employee.trim());
        }
    }

    private static void showRandomEmployee() throws IOException {
        String[] employees = readEmployees();
        System.out.println(employees[new Random().nextInt(employees.length)].trim());
    }

    private static void addEmployee(String name) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE, true))) {
            writer.write(", " + name);
        }
    }

    private static void searchEmployee(String name) throws IOException {
        String[] employees = readEmployees();
        for (String employee : employees) {
            if (employee.trim().equalsIgnoreCase(name)) {
                System.out.println("Employee found: " + employee.trim());
                return; // ✅ Early exit — no need for a flag
            }
        }
        System.out.println("Employee not found: " + name);
    }

    private static void countWords() throws IOException {
        String content = String.join(",", readEmployees());
        int wordCount = 0;
        boolean inWord = false;
        for (char ch : content.toCharArray()) {
            if (ch == ' ') {
                if (!inWord) {
                    wordCount++;
                    inWord = true;
                } else {
                    inWord = false;
                }
            }
        }
        System.out.println(wordCount + " word(s) found, " + content.length() + " characters total.");
    }

    private static void updateEmployee(String nameToUpdate) throws IOException {
        String[] employees = readEmployees();
        boolean updated = false;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].trim().equalsIgnoreCase(nameToUpdate)) {
                employees[i] = "Updated";
                updated = true;
                break;
            }
        }
        writeEmployees(employees, false);
        System.out.println(updated ? Constants.DATA_UPDATED : "No matching employee found to update.");
    }

    private static void deleteEmployee(String nameToDelete) throws IOException {
        List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployees()));
        boolean removed = employeeList.removeIf(emp -> emp.trim().equalsIgnoreCase(nameToDelete));
        writeEmployees(employeeList.toArray(new String[0]), false);
        System.out.println(removed ? Constants.DATA_DELETED : "No matching employee found to delete.");
    }

    // Helper method to read all employees from file
    private static String[] readEmployees() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(Constants.EMPLOYEE_FILE)))) {
            return reader.readLine().split(",");
        }
    }

    // Helper method to write employees to file
    private static void writeEmployees(String[] employees, boolean append) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE, append))) {
            writer.write(String.join(",", employees));
        }
    }
}
