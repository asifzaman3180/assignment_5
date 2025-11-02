// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

/**
 * EmployeeManager is a simple CLI application to manage a list of employees.
 * Supports the following operations:
 * - List all employees
 * - Show a random employee
 * - Add a new employee
 * - Search for an employee
 * - Count employees and total characters
 * - Update an employee's name
 * - Delete an employee
 *
 * Employees are stored in a text file ("employees.txt") as a comma-separated list.
 */
public class EmployeeManager {

    // ===== Constants =====
    private static final String EMPLOYEE_FILE = "employees.txt";

    // Command constants
    private static final String CMD_LIST = "l";
    private static final String CMD_SHOW = "s";
    private static final String CMD_ADD = "+";
    private static final String CMD_SEARCH = "?";
    private static final String CMD_COUNT = "c";
    private static final String CMD_UPDATE = "u";
    private static final String CMD_DELETE = "d";

    // Message constants
    private static final String MSG_LOADING = "Loading data ...";
    private static final String MSG_DATA_LOADED = "Data Loaded.";
    private static final String MSG_DATA_UPDATED = "Data Updated.";
    private static final String MSG_DATA_DELETED = "Data Deleted.";
    private static final String MSG_EMPLOYEE_FOUND = "Employee found!";
    private static final String MSG_INVALID_ARGUMENT = "Invalid argument!";
    private static final String MSG_NO_ARGUMENT = "No arguments provided!";
    private static final String MSG_USAGE = """
            Usage:
              l  -> List employees
              s  -> Show random employee
              +X -> Add employee
              ?X -> Search employee
              c  -> Count employees and characters
              uX -> Update employee
              dX -> Delete employee
            """;

    // ===== File Helper Methods =====

    /**
     * Reads employees from the file as an array of strings.
     *
     * @return array of employee names
     * @throws IOException if file cannot be read
     */
    private static String[] readEmployeesFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {
            String line = reader.readLine();
            return (line == null || line.isEmpty()) ? new String[0] : line.split(",");
        }
    }

    /**
     * Overwrites the employee file with the provided array of employees.
     *
     * @param employees array of employee names
     * @throws IOException if file cannot be written
     */
    private static void writeEmployeesToFile(String[] employees) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEE_FILE))) {
            writer.write(String.join(",", employees));
        }
    }

    /**
     * Appends a new employee to the employee file.
     *
     * @param employeeName the employee name to add
     * @throws IOException if file cannot be written
     */
    private static void appendEmployeeToFile(String employeeName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEE_FILE, true))) {
            writer.write(", " + employeeName);
        }
    }

    // ===== Main CLI Logic =====
    public static void main(String[] args) {

        // Validate arguments
        if (args.length == 0) {
            System.out.println(MSG_NO_ARGUMENT);
            System.out.println(MSG_USAGE);
            return;
        }

        String userInput = args[0].trim();

        // Check for empty input
        if (userInput.isEmpty()) {
            System.out.println(MSG_INVALID_ARGUMENT);
            System.out.println(MSG_USAGE);
            return;
        }

        // Validate commands that require a name argument
        if ((userInput.startsWith(CMD_ADD) || userInput.startsWith(CMD_SEARCH)
                || userInput.startsWith(CMD_UPDATE) || userInput.startsWith(CMD_DELETE))
                && userInput.length() < 2) {
            System.out.println("Command requires a name argument!");
            System.out.println(MSG_USAGE);
            return;
        }

        System.out.println(MSG_LOADING);

        try {
            switch (getCommandType(userInput)) {

                case CMD_LIST -> listAllEmployees();

                case CMD_SHOW -> showRandomEmployee();

                case CMD_ADD -> addEmployee(userInput.substring(1));

                case CMD_SEARCH -> searchEmployee(userInput.substring(1));

                case CMD_COUNT -> countEmployeesAndCharacters();

                case CMD_UPDATE -> updateEmployee(userInput.substring(1));

                case CMD_DELETE -> deleteEmployee(userInput.substring(1));

                default -> {
                    System.out.println(MSG_INVALID_ARGUMENT);
                    System.out.println(MSG_USAGE);
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ===== Command Implementations =====

    private static void listAllEmployees() throws IOException {
        Arrays.stream(readEmployeesFromFile())
                .map(String::trim)
                .forEach(System.out::println);
        System.out.println(MSG_DATA_LOADED);
    }

    private static void showRandomEmployee() throws IOException {
        String[] employees = readEmployeesFromFile();
        if (employees.length > 0) {
            System.out.println(employees[new Random().nextInt(employees.length)].trim());
        }
        System.out.println(MSG_DATA_LOADED);
    }

    private static void addEmployee(String name) throws IOException {
        appendEmployeeToFile(name);
        System.out.println(MSG_DATA_LOADED);
    }

    private static void searchEmployee(String name) throws IOException {
        if (Arrays.stream(readEmployeesFromFile())
                .map(String::trim)
                .anyMatch(emp -> emp.equals(name))) {
            System.out.println(MSG_EMPLOYEE_FOUND);
        }
        System.out.println(MSG_DATA_LOADED);
    }

    private static void countEmployeesAndCharacters() throws IOException {
        String[] employees = readEmployeesFromFile();
        int wordCount = employees.length;
        int charCount = String.join("", employees).length(); // letters only
        System.out.println(wordCount + " word(s) found, " + charCount + " character(s) total.");
        System.out.println(MSG_DATA_LOADED);
    }

    private static void updateEmployee(String name) throws IOException {
        String[] updated = Arrays.stream(readEmployeesFromFile())
                .map(emp -> emp.trim().equals(name) ? "Updated" : emp)
                .toArray(String[]::new);
        writeEmployeesToFile(updated);
        System.out.println(MSG_DATA_UPDATED);
    }

    private static void deleteEmployee(String name) throws IOException {
        String[] remaining = Arrays.stream(readEmployeesFromFile())
                .map(String::trim)
                .filter(emp -> !emp.equals(name))
                .toArray(String[]::new);
        writeEmployeesToFile(remaining);
        System.out.println(MSG_DATA_DELETED);
    }

    // ===== Command Resolver =====
    /**
     * Determines the command type based on user input.
     *
     * @param arg user input
     * @return one of the command constants or empty string if invalid
     */
    private static String getCommandType(String arg) {
        if (arg == null || arg.isEmpty()) return "";
        if (arg.equals(CMD_LIST)) return CMD_LIST;
        if (arg.equals(CMD_SHOW)) return CMD_SHOW;
        if (arg.startsWith(CMD_ADD)) return CMD_ADD;
        if (arg.startsWith(CMD_SEARCH)) return CMD_SEARCH;
        if (arg.equals(CMD_COUNT)) return CMD_COUNT;
        if (arg.startsWith(CMD_UPDATE)) return CMD_UPDATE;
        if (arg.startsWith(CMD_DELETE)) return CMD_DELETE;
        return "";
    }
}
