// File Name: EmployeeManager.java
// Description: Improved and refactored version of the employee manager program
// Author: [Your Name]
// Date: [Today's Date]

import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {
        // ===== Task #2: Validate command-line arguments =====
        if (args.length != 1) {
            System.out.println("Error: Please provide exactly one command-line argument (l, s, +name, ?name, c, uName, or dName).");
            return;
        }

        String command = args[0];
        System.out.println("Loading data ...");

        try {
            switch (command.charAt(0)) {
                case 'l':  // ===== Task #0: List all employees =====
                    listEmployees();
                    break;

                case 's':  // Show a random employee
                    showRandomEmployee();
                    break;

                case '+':  // Add a new employee
                    addEmployee(command.substring(1).trim());
                    break;

                case '?':  // Search for an employee
                    searchEmployee(command.substring(1).trim());
                    break;

                case 'c':  // Count employees
                    countEmployees();
                    break;

                case 'u':  // Update an employee (replace with "Updated")
                    updateEmployee(command.substring(1).trim());
                    break;

                case 'd':  // Delete an employee
                    deleteEmployee(command.substring(1).trim());
                    break;

                default:
                    // ===== Task #9: Handle invalid arguments =====
                    System.out.println("Invalid argument. Use: l, s, +name, ?name, c, uName, or dName.");
                    break;
            }
        } catch (IOException e) {
            System.out.println("Error handling file: " + e.getMessage());
        }

        System.out.println("Operation completed.");
    }

    // ===== Task #4: Refactor repetitive file operations =====

    private static String readEmployeeData() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.FILE_PATH))) {
            return reader.readLine();
        }
    }

    private static void writeEmployeeData(String data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_PATH))) {
            writer.write(data);
        }
    }

    private static void appendEmployeeData(String data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_PATH, true))) {
            writer.write(", " + data);
        }
    }

    // ===== Task #1, #3, #6, #7, #8, #10 =====
    // Improved readability, meaningful variable names, simplified logic, and added documentation

    private static void listEmployees() throws IOException {
        String[] employees = readEmployeeData().split(",");
        System.out.println("Employees:");
        for (String employee : employees) {
            System.out.println(employee.trim());
        }
    }

    private static void showRandomEmployee() throws IOException {
        String[] employees = readEmployeeData().split(",");
        Random random = new Random();
        int randomIndex = random.nextInt(employees.length);
        System.out.println("Random Employee: " + employees[randomIndex].trim());
    }

    private static void addEmployee(String name) throws IOException {
        if (name.isEmpty()) {
            System.out.println("Error: Employee name cannot be empty.");
            return;
        }
        appendEmployeeData(name);
        System.out.println("Employee added: " + name);
    }

    private static void searchEmployee(String name) throws IOException {
        String[] employees = readEmployeeData().split(",");
        boolean found = false;
        for (String employee : employees) {
            if (employee.trim().equalsIgnoreCase(name)) {
                System.out.println("Employee found: " + name);
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Employee not found: " + name);
    }

    private static void countEmployees() throws IOException {
        String[] employees = readEmployeeData().split(",");
        System.out.println("Total employees: " + employees.length);
    }

    private static void updateEmployee(String name) throws IOException {
        String[] employees = readEmployeeData().split(",");
        boolean updated = false;

        for (int i = 0; i < employees.length; i++) {
            if (employees[i].trim().equalsIgnoreCase(name)) {
                employees[i] = "Updated";
                updated = true;
            }
        }

        writeEmployeeData(String.join(",", employees));

        if (updated) System.out.println("Employee updated successfully.");
        else System.out.println("Employee not found: " + name);
    }

    private static void deleteEmployee(String name) throws IOException {
        List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployeeData().split(",")));

        if (employeeList.removeIf(e -> e.trim().equalsIgnoreCase(name))) {
            writeEmployeeData(String.join(",", employeeList));
            System.out.println("Employee deleted: " + name);
        } else {
            System.out.println("Employee not found: " + name);
        }
    }
}

// ===== Task #5: Create Constants.java =====
class Constants {
    public static final String FILE_PATH = "employees.txt";
}
