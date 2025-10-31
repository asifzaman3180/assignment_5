// File Name: EmployeeManager.java
// Description: This program manages employee records stored in a text file. 
// Supports commands: list, random select, add, search, count, update, delete.

import java.io.*;
import java.util.*;

public class EmployeeManager {

    // -------------------- Constants --------------------
    private static final String EMPLOYEE_FILE = "employees.txt";
    private static final String ERROR_NO_ARGUMENTS = "Error: No arguments provided.";
    private static final String USAGE = "Usage: java EmployeeManager <command>";
    private static final String COMMANDS = "Commands: l, s, +<name>, ?<name>, c, u<name>, d<name>";
    private static final String ERROR_NO_NAME_ADD = "Error: No employee name provided to add.";
    private static final String ERROR_NO_NAME_SEARCH = "Error: No employee name provided to search.";
    private static final String ERROR_NO_NAME_UPDATE = "Error: No employee name provided to update.";
    private static final String ERROR_NO_NAME_DELETE = "Error: No employee name provided to delete.";
    private static final String ERROR_UNSUPPORTED_COMMAND = "Error: Unsupported command '%s'.";
    private static final String LOADING_DATA_MSG = "Loading data ...";
    private static final String DATA_LOADED_MSG = "Data Loaded.";
    private static final String DATA_UPDATED_MSG = "Data Updated.";
    private static final String DATA_DELETED_MSG = "Data Deleted.";
    private static final String EMPLOYEE_FOUND_MSG = "Employee found!";
    // ---------------------------------------------------

    // -------------------- File Operations --------------------
    /** Read all employees from the file and return as a List<String> */
    private static List<String> readEmployeesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                return new ArrayList<>(Arrays.asList(line.split(",")));
            }
        } catch (IOException e) {
            // Handle silently
        }
        return new ArrayList<>();
    }

    /** Write a list of employees back to the file */
    private static void writeEmployeesToFile(List<String> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEE_FILE))) {
            writer.write(String.join(",", employees));
        } catch (IOException e) {
            // Handle silently
        }
    }

    /** Append a single employee name to the file */
    private static void appendEmployeeToFile(String employeeName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEE_FILE, true))) {
            writer.write(", " + employeeName);
        } catch (IOException e) {
            // Handle silently
        }
    }
    // ---------------------------------------------------

    public static void main(String[] args) {

        // -------------------- Argument Validation --------------------
        if (args.length == 0) {
            System.out.println(ERROR_NO_ARGUMENTS);
            System.out.println(USAGE);
            System.out.println(COMMANDS);
            return;
        }
        String command = args[0];
        // -------------------------------------------------------------

        // -------------------- Command Handling --------------------
        switch (command.charAt(0)) {

            case 'l': // List all employees
                System.out.println(LOADING_DATA_MSG);
                readEmployeesFromFile().forEach(System.out::println);
                System.out.println(DATA_LOADED_MSG);
                break;

            case 's': // Randomly select an employee
                System.out.println(LOADING_DATA_MSG);
                List<String> employees = readEmployeesFromFile();
                if (!employees.isEmpty()) {
                    System.out.println(employees.get(new Random().nextInt(employees.size())));
                }
                System.out.println(DATA_LOADED_MSG);
                break;

            case '+': // Add a new employee
                if (command.length() == 1) {
                    System.out.println(ERROR_NO_NAME_ADD);
                    return;
                }
                System.out.println(LOADING_DATA_MSG);
                appendEmployeeToFile(command.substring(1));
                System.out.println(DATA_LOADED_MSG);
                break;

            case '?': // Search for an employee
                if (command.length() == 1) {
                    System.out.println(ERROR_NO_NAME_SEARCH);
                    return;
                }
                System.out.println(LOADING_DATA_MSG);
                String searchName = command.substring(1);
                if (readEmployeesFromFile().contains(searchName)) {
                    System.out.println(EMPLOYEE_FOUND_MSG);
                } else {
                    System.out.println("Employee not found!");
                }
                System.out.println(DATA_LOADED_MSG);
                break;

            case 'c': // Count total employees and characters
                System.out.println(LOADING_DATA_MSG);
                List<String> employeeList = readEmployeesFromFile();
                long totalEmployees = employeeList.stream().filter(e -> !e.isBlank()).count();
                int totalCharacters = employeeList.stream().mapToInt(String::length).sum();
                System.out.println(totalEmployees + " employee(s) found, total characters: " + totalCharacters);
                System.out.println(DATA_LOADED_MSG);
                break;

            case 'u': // Update an employee to "Updated"
                if (command.length() == 1) {
                    System.out.println(ERROR_NO_NAME_UPDATE);
                    return;
                }
                System.out.println(LOADING_DATA_MSG);
                String nameToUpdate = command.substring(1);
                List<String> updateList = readEmployeesFromFile();
                if (!updateList.contains(nameToUpdate)) {
                    System.out.println("Employee not found to update!");
                } else {
                    updateList.replaceAll(e -> e.equals(nameToUpdate) ? "Updated" : e);
                    writeEmployeesToFile(updateList);
                    System.out.println(DATA_UPDATED_MSG);
                }
                break;

            case 'd': // Delete an employee
                if (command.length() == 1) {
                    System.out.println(ERROR_NO_NAME_DELETE);
                    return;
                }
                System.out.println(LOADING_DATA_MSG);
                String nameToDelete = command.substring(1);
                List<String> deleteList = readEmployeesFromFile();
                if (!deleteList.contains(nameToDelete)) {
                    System.out.println("Employee not found to delete!");
                } else {
                    deleteList.remove(nameToDelete);
                    writeEmployeesToFile(deleteList);
                    System.out.println(DATA_DELETED_MSG);
                }
                break;

            default: // Unsupported command
                System.out.println(String.format(ERROR_UNSUPPORTED_COMMAND, command));
                System.out.println(USAGE);
        }
        // -------------------------------------------------------------
    }
}
