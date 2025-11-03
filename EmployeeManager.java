// File Name: EmployeeManager.java
// Purpose: Manage employees - list, add, search, count, update, and delete

import java.io.*;
import java.util.*;

public class EmployeeManager {

    private static final String FILE_PATH = Constants.EMPLOYEE_FILE_PATH;

    /**
     * Reads all employees from the file.
     * @return List of employee names.
     * @throws IOException if file reading fails.
     */
    private static List<String> readEmployeesFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String data = reader.readLine();
            if (data == null || data.isEmpty()) return new ArrayList<>();
            return new ArrayList<>(Arrays.asList(data.split(",\\s*")));
        }
        
    }

    /**
     * Writes the list of employees to the file.
     * @param employees List of employee names to write.
     * @throws IOException if file writing fails.
     */
    private static void writeEmployeesToFile(List<String> employees) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(String.join(", ", employees));
        }
    }

    public static void main(String[] args) {

        // Validate number of arguments
        if (args.length != 1) {
            System.out.println("❌ Invalid number of arguments!");
            printUsage();
            return;
        }

        String command = args[0];

        // Validate argument value
        boolean validCommand = command.equals("l") ||
                               command.equals("s") ||
                               command.equals("c") ||
                               command.startsWith("+") ||
                               command.startsWith("?") ||
                               command.startsWith("u") ||
                               command.startsWith("d");

        if (!validCommand) {
            System.out.println("❌ Invalid or unsupported argument: " + command);
            printUsage();
            return;
        }

        // Execute command
        try {
            if (command.equals("l")) {
                listEmployees();
            } else if (command.equals("s")) {
                showRandomEmployee();
            } else if (command.startsWith("+")) {
                addEmployee(command.substring(1).trim());
            } else if (command.startsWith("?")) {
                searchEmployee(command.substring(1).trim());
            } else if (command.equals("c")) {
                countEmployees();
            } else if (command.startsWith("u")) {
                updateEmployee(command.substring(1).trim());
            } else if (command.startsWith("d")) {
                deleteEmployee(command.substring(1).trim());
            }
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    /** Prints program usage instructions */
    private static void printUsage() {
        System.out.println("Supported commands:");
        System.out.println("  l          -> List all employees");
        System.out.println("  s          -> Show random employee");
        System.out.println("  +Name      -> Add new employee");
        System.out.println("  ?Name      -> Search employee");
        System.out.println("  c          -> Count employees");
        System.out.println("  uName      -> Update employee");
        System.out.println("  dName      -> Delete employee");
    }

    /** Lists all employees in the file */
    private static void listEmployees() throws IOException {
        System.out.println(Constants.DATA_LOADING);
        readEmployeesFromFile().forEach(System.out::println);
        System.out.println(Constants.DATA_LOADED);
    }

    /** Shows a random employee */
    private static void showRandomEmployee() throws IOException {
        List<String> employees = readEmployeesFromFile();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            System.out.println("Random employee: " + employees.get(new Random().nextInt(employees.size())));
        }
    }

    /** Adds a new employee */
    private static void addEmployee(String name) throws IOException {
        List<String> employees = new ArrayList<>(readEmployeesFromFile());
        employees.add(name);
        writeEmployeesToFile(employees);
        System.out.println("✅ Employee added: " + name);
    }

    /** Searches for an employee */
    private static void searchEmployee(String name) throws IOException {
        List<String> employees = readEmployeesFromFile();
        if (employees.contains(name)) {
            System.out.println("✅ Employee found: " + name);
        } else {
            System.out.println("❌ Employee not found: " + name);
        }
    }

    /** Counts the total number of employees */
    private static void countEmployees() throws IOException {
        System.out.println(Constants.DATA_LOADING);
        List<String> employees = readEmployeesFromFile();
        int total = employees.size();
        if (total == 0) {
            System.out.println("No employees found.");
        } else {
            System.out.println("Total employees: " + total);
        }
        System.out.println(Constants.DATA_LOADED);
    }

    /** Updates an employee's name to "Updated" */
    private static void updateEmployee(String name) throws IOException {
        List<String> employees = new ArrayList<>(readEmployeesFromFile());
        int index = employees.indexOf(name);
        if (index != -1) {
            employees.set(index, "Updated");
            writeEmployeesToFile(employees);
            System.out.println(Constants.DATA_UPDATED);
        } else {
            System.out.println("Employee not found.");
        }
    }

    /** Deletes an employee */
    private static void deleteEmployee(String name) throws IOException {
        List<String> employees = new ArrayList<>(readEmployeesFromFile());
        if (employees.remove(name)) {
            writeEmployeesToFile(employees);
            System.out.println(Constants.DATA_DELETED);
        } else {
            System.out.println("Employee not found.");
        }
    }
}
