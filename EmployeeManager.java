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
            // ✅ START: Task 9 Validation
            else if (command.startsWith("+")) {
                String name = command.substring(1);
                if (name.isEmpty()) {
                    System.out.println("Error: Employee name cannot be empty for 'add' operation.");
                    System.out.println(Constants.USAGE_INFO);
                } else {
                    addEmployee(name);
                }
            } 
            else if (command.startsWith("?")) {
                String name = command.substring(1);
                if (name.isEmpty()) {
                    System.out.println("Error: Employee name cannot be empty for 'search' operation.");
                    System.out.println(Constants.USAGE_INFO);
                } else {
                    searchEmployee(name);
                }
            } 
            // ✅ END: Task 9 Validation
            else if (command.equals("c")) {
                countEmployees();
            } 
            // ✅ START: Task 9 Validation
            else if (command.startsWith("u")) {
                String name = command.substring(1);
                if (name.isEmpty()) {
                    System.out.println("Error: Employee name cannot be empty for 'update' operation.");
                    System.out.println(Constants.USAGE_INFO);
                } else {
                    updateEmployee(name);
                }
            } 
            else if (command.startsWith("d")) {
                String name = command.substring(1);
                if (name.isEmpty()) {
                    System.out.println("Error: Employee name cannot be empty for 'delete' operation.");
                    System.out.println(Constants.USAGE_INFO);
                } else {
                    deleteEmployee(name);
                }
            } 
            // ✅ END: Task 9 Validation
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
                return;
            }
        }
        System.out.println("Employee not found: " + name);
    }

    // ✅ Simplified count method
    private static void countEmployees() throws IOException {
        String[] employees = readEmployees();
        int totalEmployees = employees.length;

        // Count total characters (excluding commas and spaces)
        int totalCharacters = String.join("", employees).replace(",", "").replace(" ", "").length();

        System.out.println("Total employees: " + totalEmployees);
        System.out.println("Total characters (excluding commas/spaces): " + totalCharacters);
    }

    private static void updateEmployee(String nameToUpdate) throws IOException {
        String[] employees = readEmployees();
        boolean updated = false;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].trim().equalsIgnoreCase(nameToUpdate)) {
                employees[i] = "Updated"; // Note: This is a placeholder update logic
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