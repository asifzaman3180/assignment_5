// File Name: EmployeeManager.java
// Description: A simple employee manager that can load, search, add, update, delete, 
// and count employees from a text file.

import java.io.*;
import java.util.*;

public class EmployeeManager {

    // File where employee data is stored
    private static final String EMPLOYEE_FILE = "employees.txt";

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Please provide a valid command.");
            return;
        }

        String command = args[0];

        // Load and display all employees
        if (command.equals("l")) {
            loadAllEmployees();
        }
        // Load a random employee from the list
        else if (command.equals("s")) {
            displayRandomEmployee();
        }
        // Add a new employee
        else if (command.startsWith("+")) {
            String newEmployee = command.substring(1);
            addEmployee(newEmployee);
        }
        // Search for a specific employee
        else if (command.startsWith("?")) {
            String searchEmployee = command.substring(1);
            searchEmployee(searchEmployee);
        }
        // Count words/characters in the file
        else if (command.equals("c")) {
            countWordsAndChars();
        }
        // Update an existing employee
        else if (command.startsWith("u")) {
            String employeeToUpdate = command.substring(1);
            updateEmployee(employeeToUpdate);
        }
        // Delete an employee
        else if (command.startsWith("d")) {
            String employeeToDelete = command.substring(1);
            deleteEmployee(employeeToDelete);
        }
        else {
            System.out.println("Invalid command.");
        }
    }

    //  Methods

    // Load and print all employees
    private static void loadAllEmployees() {
        System.out.println("Loading employee data...");
        try (BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {
            String line = reader.readLine();
            if (line != null) {
                String[] employees = line.split(",");
                for (String emp : employees) {
                    System.out.println(emp.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
        System.out.println("Data loaded successfully.\n");
    }

    // Display a random employee
    private static void displayRandomEmployee() {
        System.out.println("Loading employee data...");
        try (BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {
            String line = reader.readLine();
            if (line != null) {
                String[] employees = line.split(",");
                Random rand = new Random();
                int randomIndex = rand.nextInt(employees.length);
                System.out.println("Random employee: " + employees[randomIndex].trim());
            }
        } catch (IOException e) {
            System.out.println("Error reading data: " + e.getMessage());
        }
        System.out.println("Operation completed.\n");
    }

    // Add a new employee
    private static void addEmployee(String newEmployee) {
        System.out.println("Adding new employee...");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEE_FILE, true))) {
            writer.write(", " + newEmployee.trim());
        } catch (IOException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
        System.out.println("Employee added successfully.\n");
    }

    // Search for an employee
    private static void searchEmployee(String employeeName) {
        System.out.println("Searching employee...");
        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {
            String line = reader.readLine();
            if (line != null) {
                String[] employees = line.split(",");
                for (String emp : employees) {
                    if (emp.trim().equals(employeeName)) {
                        found = true;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error searching employee: " + e.getMessage());
        }

        if (found) {
            System.out.println("Employee found: " + employeeName);
        } else {
            System.out.println("Employee not found: " + employeeName);
        }
        System.out.println("Search completed.\n");
    }

    // Count words and characters in the employee file
    private static void countWordsAndChars() {
        System.out.println("Counting words and characters...");
        try (BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {
            String line = reader.readLine();
            if (line != null) {
                String[] words = line.split(",");
                int charCount = line.length();
                System.out.println("Word count: " + words.length);
                System.out.println("Character count: " + charCount);
            }
        } catch (IOException e) {
            System.out.println("Error counting words/chars: " + e.getMessage());
        }
        System.out.println("Counting completed.\n");
    }

    // Update an employee name to "Updated"
    private static void updateEmployee(String employeeName) {
        System.out.println("Updating employee...");
        try (BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {
            String line = reader.readLine();
            if (line != null) {
                String[] employees = line.split(",");
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].trim().equals(employeeName)) {
                        employees[i] = "Updated";
                    }
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEE_FILE))) {
                    writer.write(String.join(",", employees));
                }
            }
        } catch (IOException e) {
            System.out.println("Error updating employee: " + e.getMessage());
        }
        System.out.println("Employee updated successfully.\n");
    }

    // Delete an employee from the file
    private static void deleteEmployee(String employeeName) {
        System.out.println("Deleting employee...");
        try (BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {
            String line = reader.readLine();
            if (line != null) {
                List<String> employeeList = new ArrayList<>(Arrays.asList(line.split(",")));
                employeeList.removeIf(emp -> emp.trim().equals(employeeName));
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEE_FILE))) {
                    writer.write(String.join(",", employeeList));
                }
            }
        } catch (IOException e) {
            System.out.println("Error deleting employee: " + e.getMessage());
        }
        System.out.println("Employee deleted successfully.\n");
    }
}
