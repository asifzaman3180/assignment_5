import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Constants Class 
    // Stores all file paths, command letters, and messages

    static class Constants {
        public static final String FILE_PATH = "employees.txt";

        // General messages
        public static final String MSG_LOADING = "Loading data ...";
        public static final String MSG_DATA_LOADED = "Data Loaded.";
        public static final String MSG_ERROR_READING = "Error reading file: ";
        public static final String MSG_ERROR_WRITING = "Error writing file: ";

        // Command letters
        public static final String CMD_LIST = "l";
        public static final String CMD_RANDOM = "s";
        public static final String CMD_ADD = "+";
        public static final String CMD_SEARCH = "?";
        public static final String CMD_COUNT = "c";
        public static final String CMD_UPDATE = "u";

        // Messages for operations
        public static final String MSG_EMP_FOUND = "Employee found!";
        public static final String MSG_EMP_NOT_FOUND = "Employee not found.";
        public static final String MSG_UPDATED = "Employee updated successfully.";
        public static final String MSG_NOT_UPDATED = "Employee not found to update.";
        public static final String MSG_INVALID_CMD = "Error: Invalid command. Try l, s, +name, ?name, c, or u.";
        public static final String MSG_NO_ARGS = "Error: No arguments provided. Try l, s, +name, ?name, c, or u.";
    }

    //Helper Methods

    
    private static List<String> readEmployeesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.FILE_PATH))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                return new ArrayList<>(Arrays.asList(line.split(",")));
            }
        } catch (IOException e) {
            System.out.println(Constants.MSG_ERROR_READING + e.getMessage());
        }
        return new ArrayList<>();
    }

    
    private static void writeEmployeesToFile(List<String> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_PATH))) {
            writer.write(String.join(",", employees));
        } catch (IOException e) {
            System.out.println(Constants.MSG_ERROR_WRITING + e.getMessage());
        }
    }

    // Main Program
    public static void main(String[] args) {

        // Check if user provided at least one command-line argument
        if (args.length == 0) {
            System.out.println(Constants.MSG_NO_ARGS);
            return;
        }

        String command = args[0];  // Full command input from user
        char cmd = command.charAt(0);  // Command type
        List<String> employees;

        // Validate command type
        if ("ls+?cu".indexOf(cmd) == -1) {
            System.out.println(Constants.MSG_INVALID_CMD);
            return;
        }

        switch (cmd) {

            case 'l':  // List all employees
                System.out.println(Constants.MSG_LOADING);
                employees = readEmployeesFromFile();
                employees.forEach(emp -> System.out.println(emp.trim()));
                System.out.println(Constants.MSG_DATA_LOADED);
                break;

            case 's': // Show a random employee
                System.out.println(Constants.MSG_LOADING);
                employees = readEmployeesFromFile();
                if (!employees.isEmpty()) {
                    System.out.println("Random Employee: " + employees.get(new Random().nextInt(employees.size())).trim());
                }
                System.out.println(Constants.MSG_DATA_LOADED);
                break;

            case '+': // Add a new employee
                if (command.length() < 2) {
                    System.out.println("Error: Employee name missing for add command.");
                    break;
                }
                System.out.println(Constants.MSG_LOADING);
                employees = readEmployeesFromFile();
                employees.add(command.substring(1));  // Add new employee
                writeEmployeesToFile(employees);
                System.out.println(Constants.MSG_DATA_LOADED);
                break;

            case '?': // Search for an employee
                if (command.length() < 2) {
                    System.out.println("Error: Employee name missing for search command.");
                    break;
                }
                System.out.println(Constants.MSG_LOADING);
                employees = readEmployeesFromFile();
                System.out.println(employees.contains(command.substring(1)) ? Constants.MSG_EMP_FOUND : Constants.MSG_EMP_NOT_FOUND);
                System.out.println(Constants.MSG_DATA_LOADED);
                break;

            case 'c': // Count total employees
                System.out.println(Constants.MSG_LOADING);
                employees = readEmployeesFromFile();
                System.out.println("Total Employees: " + employees.size());
                System.out.println(Constants.MSG_DATA_LOADED);
                break;

            case 'u': // Update an employee name to "Updated"
                if (command.length() < 2) {
                    System.out.println("Error: Employee name missing for update command.");
                    break;
                }
                System.out.println(Constants.MSG_LOADING);
                employees = readEmployeesFromFile();
                String target = command.substring(1);
                employees.replaceAll(emp -> emp.equals(target) ? "Updated" : emp);
                System.out.println(employees.contains("Updated") ? Constants.MSG_UPDATED : Constants.MSG_NOT_UPDATED);
                writeEmployeesToFile(employees);
                System.out.println(Constants.MSG_DATA_LOADED);
                break;

            default:
                System.out.println(Constants.MSG_INVALID_CMD);
                break;
        }
    }
}

