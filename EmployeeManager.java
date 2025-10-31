// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

/**
 * Employee Management System
 * Provides CRUD operations for employee data stored in a text file
 * Supports listing, searching, adding, updating, and deleting employee records
 */
public class EmployeeManager {
    
    /**
     * Reads all employee records from the data file
     * @return Array of employee names, or empty array if file error occurs
     */
    private static String[] loadAllEmployees() {
        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(Constants.EMPLOYEES_FILE_PATH)));
            String fileContent = fileReader.readLine();
            fileReader.close();
            return fileContent.split(",");
        } 
        catch (Exception fileException) {
            System.out.println(Constants.FILE_ERROR_MESSAGE);
            return new String[0];
        }
    }
    
    /**
     * Writes employee records back to the data file
     * @param employeeList Array of employee names to be saved
     */
    private static void saveEmployeeList(String[] employeeList) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(Constants.EMPLOYEES_FILE_PATH));
            fileWriter.write(String.join(",", employeeList));
            fileWriter.close();
        } 
        catch (Exception fileException) {
            System.out.println(Constants.FILE_ERROR_MESSAGE);
        }
    }
    
    /**
     * Appends a new employee to the end of the data file
     * @param newEmployeeName Name of the employee to add
     */
    private static void addNewEmployee(String newEmployeeName) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(Constants.EMPLOYEES_FILE_PATH, true));
            fileWriter.write(", " + newEmployeeName);
            fileWriter.close();
        } 
        catch (Exception fileException) {
            System.out.println(Constants.FILE_ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        
        // Validate command line arguments before processing
        if (args.length != 1) {
            System.out.println(Constants.USAGE_MESSAGE);
            System.out.println("Please provide exactly one command line argument.");
            return;
        }

        String userCommand = args[0];
        
        // List all employees operation
        if (userCommand.equals("l")) {
            System.out.println(Constants.LOADING_MESSAGE);
            String[] allEmployees = loadAllEmployees();
            for (String employee : allEmployees) {
                System.out.println(employee);
            }
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } 
        // Show random employee operation
        else if (userCommand.equals("s")) {
            System.out.println(Constants.LOADING_MESSAGE);
            String[] allEmployees = loadAllEmployees();
            if (allEmployees.length == 0) {
                System.out.println("No employees found in database.");
            } else {
                System.out.println(String.join(",", allEmployees));
                System.out.println(allEmployees[new Random().nextInt(allEmployees.length)]);
            }
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } 
        // Add new employee operation
        else if (userCommand.contains("+")) {
            if (userCommand.length() == 1) {
                System.out.println(Constants.MISSING_NAME_MESSAGE);
                return;
            }
            System.out.println(Constants.LOADING_MESSAGE);
            addNewEmployee(userCommand.substring(1));
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } 
        // Search for employee operation
        else if (userCommand.contains("?")) {
            if (userCommand.length() == 1) {
                System.out.println(Constants.MISSING_NAME_MESSAGE);
                return;
            }
            System.out.println(Constants.LOADING_MESSAGE);
            String employeeToFind = userCommand.substring(1);
            boolean employeeFound = false;
            
            for (String employee : loadAllEmployees()) {
                if (employee.equals(employeeToFind)) {
                    System.out.println(Constants.EMPLOYEE_FOUND_MESSAGE);
                    employeeFound = true;
                    break;
                }
            }
            
            if (!employeeFound) {
                System.out.println("Employee not found!");
            }
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } 
        // Count employees and characters operation
        else if (userCommand.contains("c")) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(Constants.EMPLOYEES_FILE_PATH)));
                String fileContent = fileReader.readLine();
                String[] employeeList = fileContent.split(",");
                int totalCharacters = fileContent.length();
                System.out.println(employeeList.length + " employee(s) found, " + totalCharacters + " character(s)");
            } 
            catch (Exception fileException) {
                System.out.println(Constants.FILE_ERROR_MESSAGE);
            }
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } 
        // Update employee operation
        else if (userCommand.contains("u")) {
            if (userCommand.length() == 1) {
                System.out.println(Constants.MISSING_NAME_MESSAGE);
                return;
            }
            System.out.println(Constants.LOADING_MESSAGE);
            String[] employeeList = loadAllEmployees();
            String employeeToUpdate = userCommand.substring(1);
            boolean employeeUpdated = false;
            
            for (int i = 0; i < employeeList.length; i++) {
                if (employeeList[i].equals(employeeToUpdate)) {
                    employeeList[i] = Constants.UPDATED_PLACEHOLDER;
                    employeeUpdated = true;
                }
            }
            
            if (employeeUpdated) {
                saveEmployeeList(employeeList);
                System.out.println(Constants.DATA_UPDATED_MESSAGE);
            } else {
                System.out.println("Employee not found for update.");
            }
        } 
        // Delete employee operation
        else if (userCommand.contains("d")) {
            if (userCommand.length() == 1) {
                System.out.println(Constants.MISSING_NAME_MESSAGE);
                return;
            }
            System.out.println(Constants.LOADING_MESSAGE);
            List<String> employeeList = new ArrayList<>(Arrays.asList(loadAllEmployees()));
            String employeeToRemove = userCommand.substring(1);
            boolean employeeRemoved = employeeList.remove(employeeToRemove);
            
            if (employeeRemoved) {
                saveEmployeeList(employeeList.toArray(new String[0]));
                System.out.println(Constants.DATA_DELETED_MESSAGE);
            } else {
                System.out.println("Employee not found for deletion.");
            }
        }
        // Handle invalid commands
        else {
            System.out.println(Constants.INVALID_ARGUMENT_MESSAGE);
            System.out.println(Constants.USAGE_MESSAGE);
            System.out.println("Valid operations:");
            System.out.println("  l - List all employees");
            System.out.println("  s - Show random employee");
            System.out.println("  +name - Add new employee");
            System.out.println("  ?name - Search for employee");
            System.out.println("  c - Count employees and characters");
            System.out.println("  uname - Update employee");
            System.out.println("  dname - Delete employee");
        }
    }
}