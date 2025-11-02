//File Name EmployeeManager.java
import java.io.*;
import java.util.*;

/**
 * Employee Management System
 * 
 * A command-line application for managing employee data stored in a text file.
 * Supports operations for listing, adding, searching, updating, and deleting employees.
 * 
 * Usage: java EmployeeManager <command>
 * 
 * Supported Commands:
 *   l        - List all employees in the system
 *   s        - Select and display a random employee
 *   +<name>  - Add a new employee with the specified name
 *   ?<name>  - Search for an employee by name
 *   c        - Count the total number of employees
 *   u<name>  - Update an existing employee's record
 *   d<name>  - Delete an employee from the system
 */
public class EmployeeManager {
    
    /**
     * Reads all employee records from the data file
     * 
     * @return Array of employee names, or empty array if no employees exist
     * @throws IOException If there is an error reading the file
     */
    private static String[] readAllEmployeesFromFile() throws IOException {
        BufferedReader fileReader = new BufferedReader(
            new InputStreamReader(new FileInputStream(Constants.EMPLOYEE_DATA_FILE))
        );
        String fileContent = fileReader.readLine();
        fileReader.close();
        
        if (fileContent == null || fileContent.trim().isEmpty()) {
            return new String[0];
        }
        return fileContent.split(",");
    }
    
    /**
     * Writes the complete list of employees to the data file
     * 
     * @param employeeArray Array of employee names to write to file
     * @throws IOException If there is an error writing to the file
     */
    private static void writeEmployeesToFile(String[] employeeArray) throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_DATA_FILE));
        fileWriter.write(String.join(",", employeeArray));
        fileWriter.close();
    }
    
    /**
     * Appends a new employee to the end of the data file
     * 
     * @param newEmployeeName Name of the employee to add
     * @throws IOException If there is an error writing to the file
     */
    private static void appendEmployeeToFile(String newEmployeeName) throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_DATA_FILE, true));
        fileWriter.write(", " + newEmployeeName);
        fileWriter.close();
    }
    
    /**
     * Validates that a command requiring an employee name has proper format
     * 
     * @param userCommand The full command string from user input
     * @param commandPrefix The expected prefix for this command type
     * @return true if the command is valid, false otherwise
     */
    private static boolean isValidNameCommand(String userCommand, String commandPrefix) {
        if (userCommand.length() <= commandPrefix.length()) {
            System.out.println("Error: Missing employee name after '" + commandPrefix + "' prefix");
            return false;
        }
        String employeeName = userCommand.substring(commandPrefix.length());
        if (employeeName.trim().isEmpty()) {
            System.out.println("Error: Employee name cannot be empty");
            return false;
        }
        return true;
    }
    
    /**
     * Main method - Entry point for the Employee Management System
     * Processes command-line arguments and executes requested operations
     * 
     * @param args Command-line arguments - expects exactly one command argument
     */
    public static void main(String[] args) {
        // Validate that exactly one command argument is provided
        if (args.length != 1) {
            System.out.println(Constants.INVALID_ARGUMENT_ERROR);
            System.out.println(Constants.USAGE_MESSAGE);
            System.out.println(Constants.COMMANDS_LIST);
            return;
        }
        
        String userCommand = args[0];
        
        // Process the user command based on the first character or exact match
        if (userCommand.equals(Constants.LIST_COMMAND)) {
            executeListEmployeesOperation();
        } else if (userCommand.equals(Constants.RANDOM_COMMAND)) {
            executeRandomEmployeeOperation();
        } else if (userCommand.contains(Constants.ADD_COMMAND_PREFIX)) {
            executeAddEmployeeOperation(userCommand);
        } else if (userCommand.contains(Constants.SEARCH_COMMAND_PREFIX)) {
            executeSearchEmployeeOperation(userCommand);
        } else if (userCommand.contains(Constants.COUNT_COMMAND)) {
            executeCountEmployeesOperation();
        } else if (userCommand.contains(Constants.UPDATE_COMMAND_PREFIX)) {
            executeUpdateEmployeeOperation(userCommand);
        } else if (userCommand.contains(Constants.DELETE_COMMAND_PREFIX)) {
            executeDeleteEmployeeOperation(userCommand);
        } else {
            displayInvalidCommandMessage(userCommand);
        }
    }
    
    /**
     * Executes the list employees operation - displays all employees in the system
     */
    private static void executeListEmployeesOperation() {
        System.out.println(Constants.LOADING_DATA_MESSAGE);
        try {
            String[] allEmployees = readAllEmployeesFromFile();
            if (allEmployees.length == 0) {
                System.out.println("No employees found in the system.");
            } else {
                System.out.println("Current Employees:");
                for (String employeeName : allEmployees) {
                    System.out.println("  - " + employeeName.trim());
                }
            }
        } catch (FileNotFoundException fileNotFound) {
            System.out.println("Error: Employee data file not found. Please ensure 'employees.txt' exists.");
        } catch (Exception unexpectedError) {
            System.out.println("Error: Unable to read employee data: " + unexpectedError.getMessage());
        }
        System.out.println(Constants.DATA_LOADED_MESSAGE);
    }
    
    /**
     * Executes the random employee selection operation - displays a random employee
     */
    private static void executeRandomEmployeeOperation() {
        System.out.println(Constants.LOADING_DATA_MESSAGE);
        try {
            String[] allEmployees = readAllEmployeesFromFile();
            if (allEmployees.length == 0) {
                System.out.println("No employees available to select randomly.");
            } else {
                System.out.println("All Employees: " + String.join(", ", allEmployees));
                String randomEmployee = allEmployees[new Random().nextInt(allEmployees.length)].trim();
                System.out.println("Randomly Selected Employee: " + randomEmployee);
            }
        } catch (FileNotFoundException fileNotFound) {
            System.out.println("Error: Employee data file not found. Please ensure 'employees.txt' exists.");
        } catch (Exception unexpectedError) {
            System.out.println("Error: Unable to read employee data: " + unexpectedError.getMessage());
        }
        System.out.println(Constants.DATA_LOADED_MESSAGE);
    }
    
    /**
     * Executes the add employee operation - adds a new employee to the system
     * 
     * @param userCommand The full add command including the + prefix and employee name
     */
    private static void executeAddEmployeeOperation(String userCommand) {
        if (!isValidNameCommand(userCommand, Constants.ADD_COMMAND_PREFIX)) {
            return;
        }
        System.out.println(Constants.LOADING_DATA_MESSAGE);
        try {
            String newEmployeeName = userCommand.substring(1).trim();
            appendEmployeeToFile(newEmployeeName);
            System.out.println("Employee '" + newEmployeeName + "' added successfully.");
        } catch (Exception unexpectedError) {
            System.out.println("Error: Unable to add employee: " + unexpectedError.getMessage());
        }
        System.out.println(Constants.DATA_LOADED_MESSAGE);
    }
    
    /**
     * Executes the search employee operation - searches for an employee by name
     * 
     * @param userCommand The full search command including the ? prefix and employee name
     */
    private static void executeSearchEmployeeOperation(String userCommand) {
        if (!isValidNameCommand(userCommand, Constants.SEARCH_COMMAND_PREFIX)) {
            return;
        }
        System.out.println(Constants.LOADING_DATA_MESSAGE);
        try {
            String[] allEmployees = readAllEmployeesFromFile();
            String targetEmployeeName = userCommand.substring(1).trim();
            boolean employeeExists = false;
            for (String currentEmployee : allEmployees) {
                if (currentEmployee.trim().equals(targetEmployeeName)) {
                    System.out.println(Constants.EMPLOYEE_FOUND_MESSAGE);
                    employeeExists = true;
                    break;
                }
            }
            if (!employeeExists) {
                System.out.println("Employee '" + targetEmployeeName + "' not found in the system.");
            }
        } catch (FileNotFoundException fileNotFound) {
            System.out.println("Error: Employee data file not found. Please ensure 'employees.txt' exists.");
        } catch (Exception unexpectedError) {
            System.out.println("Error: Unable to search for employee: " + unexpectedError.getMessage());
        }
        System.out.println(Constants.DATA_LOADED_MESSAGE);
    }
    
    /**
     * Executes the count employees operation - displays total number of employees
     */
    private static void executeCountEmployeesOperation() {
        System.out.println(Constants.LOADING_DATA_MESSAGE);
        try {
            String[] allEmployees = readAllEmployeesFromFile();
            if (allEmployees.length == 0) {
                System.out.println("No employees found in the system.");
            } else {
                System.out.println(allEmployees.length + " employee(s) found in the system");
            }
        } catch (FileNotFoundException fileNotFound) {
            System.out.println("Error: Employee data file not found. Please ensure 'employees.txt' exists.");
        } catch (Exception unexpectedError) {
            System.out.println("Error: Unable to count employees: " + unexpectedError.getMessage());
        }
        System.out.println(Constants.DATA_LOADED_MESSAGE);
    }
    
    /**
     * Executes the update employee operation - updates an existing employee's record
     * 
     * @param userCommand The full update command including the u prefix and employee name
     */
    private static void executeUpdateEmployeeOperation(String userCommand) {
        if (!isValidNameCommand(userCommand, Constants.UPDATE_COMMAND_PREFIX)) {
            return;
        }
        System.out.println(Constants.LOADING_DATA_MESSAGE);
        try {
            String[] allEmployees = readAllEmployeesFromFile();
            String employeeToUpdate = userCommand.substring(1).trim();
            boolean updateSuccessful = false;
            for (int employeeIndex = 0; employeeIndex < allEmployees.length; employeeIndex++) {
                if (allEmployees[employeeIndex].trim().equals(employeeToUpdate)) {
                    allEmployees[employeeIndex] = Constants.UPDATED_VALUE;
                    updateSuccessful = true;
                    break;
                }
            }
            if (updateSuccessful) {
                writeEmployeesToFile(allEmployees);
                System.out.println("Employee '" + employeeToUpdate + "' updated successfully.");
            } else {
                System.out.println("Error: Employee '" + employeeToUpdate + "' not found for update.");
            }
        } catch (FileNotFoundException fileNotFound) {
            System.out.println("Error: Employee data file not found. Please ensure 'employees.txt' exists.");
        } catch (Exception unexpectedError) {
            System.out.println("Error: Unable to update employee: " + unexpectedError.getMessage());
        }
        System.out.println(Constants.DATA_UPDATED_MESSAGE);
    }
    
    /**
     * Executes the delete employee operation - removes an employee from the system
     * 
     * @param userCommand The full delete command including the d prefix and employee name
     */
    private static void executeDeleteEmployeeOperation(String userCommand) {
        if (!isValidNameCommand(userCommand, Constants.DELETE_COMMAND_PREFIX)) {
            return;
        }
        System.out.println(Constants.LOADING_DATA_MESSAGE);
        try {
            String[] allEmployees = readAllEmployeesFromFile();
            String employeeToDelete = userCommand.substring(1).trim();
            List<String> updatedEmployeeList = new ArrayList<>(Arrays.asList(allEmployees));
            boolean deletionSuccessful = updatedEmployeeList.removeIf(employee -> employee.trim().equals(employeeToDelete));
            if (deletionSuccessful) {
                writeEmployeesToFile(updatedEmployeeList.toArray(new String[0]));
                System.out.println("Employee '" + employeeToDelete + "' deleted successfully.");
            } else {
                System.out.println("Error: Employee '" + employeeToDelete + "' not found for deletion.");
            }
        } catch (FileNotFoundException fileNotFound) {
            System.out.println("Error: Employee data file not found. Please ensure 'employees.txt' exists.");
        } catch (Exception unexpectedError) {
            System.out.println("Error: Unable to delete employee: " + unexpectedError.getMessage());
        }
        System.out.println(Constants.DATA_DELETED_MESSAGE);
    }
    
    /**
     * Displays an error message for invalid or unsupported commands
     * 
     * @param invalidCommand The invalid command that was provided by the user
     */
    private static void displayInvalidCommandMessage(String invalidCommand) {
        System.out.println(Constants.INVALID_COMMAND_ERROR + invalidCommand + "'");
        System.out.println("Supported Commands:");
        System.out.println("  l        - List all employees in the system");
        System.out.println("  s        - Select and display a random employee");
        System.out.println("  +<name>  - Add a new employee with the specified name");
        System.out.println("  ?<name>  - Search for an employee by name");
        System.out.println("  c        - Count the total number of employees");
        System.out.println("  u<name>  - Update an existing employee's record");
        System.out.println("  d<name>  - Delete an employee from the system");
    }
}