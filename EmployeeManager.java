import java.io.*;
import java.util.*;

/**
 * EmployeeManager
 * ----------------
 * This program manages a list of employees stored in a text file.
 * It supports operations like:
 *  - Listing all employees
 *  - Showing a random employee
 *  - Adding a new employee
 *  - Searching for an employee
 *  - Counting employees
 *  - Updating or deleting an employee
 *
 * File: employees.txt
 * Format: comma-separated names (e.g., John Doe,Jane Smith)
 */

public class EmployeeManager {

    public static void main(String[] args) {

        //  Validate command-line arguments
        if (args == null || args.length != 1) {
            System.out.println("Invalid number of arguments!");
            System.out.println(Constants.USAGE_MESSAGE);
            return;
        }

        String commandInput = args[0]; // e.g. "+John", "l", "?Alice"
        String commandType = getCommandType(commandInput);
        String argument = getCommandArgument(commandInput);

        //  Process command using switch-case
        switch (commandType) {

            case "LIST":
                System.out.println("Loading data ...");
                listEmployees();
                System.out.println("Data Loaded.");
                break;

            case "SHOW":
                System.out.println("Loading data ...");
                showRandomEmployee();
                System.out.println("Data Loaded.");
                break;

            case "ADD":
                if (!isValidName(argument)) {
                    System.out.println("Error: Missing name for add command (+Name).");
                } else {
                    System.out.println("Loading data ...");
                    appendEmployee(argument);
                    System.out.println("Data Loaded.");
                }
                break;

            case "SEARCH":
                if (!isValidName(argument)) {
                    System.out.println("Error: Missing name for search command (?Name).");
                } else {
                    System.out.println("Loading data ...");
                    searchEmployee(argument);
                    System.out.println("Data Loaded.");
                }
                break;

            case "COUNT":
                System.out.println("Loading data ...");
                countEmployees();
                System.out.println("Data Loaded.");
                break;

            case "UPDATE":
                if (!isValidName(argument)) {
                    System.out.println("Error: Missing name for update command (uName).");
                } else {
                    System.out.println("Loading data ...");
                    updateEmployee(argument);
                }
                break;

            case "DELETE":
                if (!isValidName(argument)) {
                    System.out.println("Error: Missing name for delete command (dName).");
                } else {
                    System.out.println("Loading data ...");
                    deleteEmployee(argument);
                }
                break;

            default:
                System.out.println("Invalid command: " + commandInput);
                System.out.println(Constants.USAGE_MESSAGE);
        }
    }

    // ------------------------------------------------------------
    // 🔹 Command Parsing Methods
    // ------------------------------------------------------------

    /** Returns the command type based on the first character of input */
    private static String getCommandType(String cmd) {
        if (cmd == null || cmd.isEmpty()) return "INVALID";
        if ("l".equals(cmd)) return "LIST";
        if ("s".equals(cmd)) return "SHOW";
        if (cmd.startsWith("+")) return "ADD";
        if (cmd.startsWith("?")) return "SEARCH";
        if ("c".equals(cmd)) return "COUNT";
        if (cmd.startsWith("u")) return "UPDATE";
        if (cmd.startsWith("d")) return "DELETE";
        return "INVALID";
    }

    /** Extracts the name/parameter from a command like "+John" or "?Alice" */
    private static String getCommandArgument(String raw) {
        if (raw == null || raw.length() <= 1) return "";
        return raw.substring(1).trim();
    }

    /** Checks whether a name is valid (non-empty) */
    private static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    // ------------------------------------------------------------
    // 🔹 File Handling Methods
    // ------------------------------------------------------------

    /** Reads the list of employees from file */
    private static List<String> readEmployeesFromFile() {
        List<String> employees = new ArrayList<>();
        File file = new File(Constants.EMPLOYEE_FILE);

        if (!file.exists()) return employees;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line != null && !line.trim().isEmpty()) {
                for (String emp : line.split(",")) {
                    employees.add(emp.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return employees;
    }

    /** Writes the updated list of employees to the file */
    private static void writeEmployeesToFile(List<String> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE))) {
            writer.write(String.join(",", employees));
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    /** Appends a new employee to the file */
    private static void appendEmployee(String name) {
        File file = new File(Constants.EMPLOYEE_FILE);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (file.exists() && file.length() > 0)
                writer.write("," + name);
            else
                writer.write(name);
        } catch (IOException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
    }

    // ------------------------------------------------------------
    // 🔹 Command Logic Methods
    // ------------------------------------------------------------

    /** Lists all employees */
    private static void listEmployees() {
        List<String> employees = readEmployeesFromFile();
        if (employees.isEmpty())
            System.out.println("(No employees found)");
        else
            employees.forEach(System.out::println);
    }

    /** Shows a random employee */
    private static void showRandomEmployee() {
        List<String> employees = readEmployeesFromFile();
        if (employees.isEmpty())
            System.out.println("(No employees to show)");
        else
            System.out.println("Random Employee: " + employees.get(new Random().nextInt(employees.size())));
    }

    /** Searches for an employee by name */
    private static void searchEmployee(String name) {
        List<String> employees = readEmployeesFromFile();
        if (employees.contains(name))
            System.out.println("Employee found!");
        else
            System.out.println("Employee NOT found.");
    }

    /** Counts total employees and shows statistics */
    private static void countEmployees() {
        List<String> employees = readEmployeesFromFile();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        int total = employees.size();
        double avgLength = employees.stream().mapToInt(String::length).average().orElse(0);
        System.out.printf("Total Employees: %d | Average Name Length: %.2f%n", total, avgLength);
    }

    /** Updates a specific employee to "Updated" */
    private static void updateEmployee(String name) {
        List<String> employees = readEmployeesFromFile();
        boolean updated = false;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).equals(name)) {
                employees.set(i, "Updated");
                updated = true;
            }
        }
        if (updated) {
            writeEmployeesToFile(employees);
            System.out.println("Employee updated successfully.");
        } else {
            System.out.println("No matching employee found for update.");
        }
    }

    /** Deletes an employee by name */
    private static void deleteEmployee(String name) {
        List<String> employees = readEmployeesFromFile();
        boolean removed = employees.removeIf(emp -> emp.equals(name));
        if (removed) {
            writeEmployeesToFile(employees);
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("No matching employee found to delete.");
        }
    }
}


