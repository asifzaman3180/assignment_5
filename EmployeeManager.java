// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    
    // Reusable method to read employees from file
    private static String[] readEmployeesFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(Constants.EMPLOYEES_FILE_PATH)));
            String line = reader.readLine();
            reader.close();
            return line.split(",");
        } 
        catch (Exception exception) {
            System.out.println(Constants.FILE_ERROR_MESSAGE);
            return new String[0];
        }
    }
    
    // Reusable method to write employees to file
    private static void writeEmployeesToFile(String[] employees) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEES_FILE_PATH));
            writer.write(String.join(",", employees));
            writer.close();
        } 
        catch (Exception exception) {
            System.out.println(Constants.FILE_ERROR_MESSAGE);
        }
    }
    
    // Reusable method to append employee to file
    private static void appendEmployeeToFile(String newEmployee) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEES_FILE_PATH, true));
            writer.write(", " + newEmployee);
            writer.close();
        } 
        catch (Exception exception) {
            System.out.println(Constants.FILE_ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        
        // Validate command line arguments
        if (args.length != 1) {
            System.out.println(Constants.USAGE_MESSAGE);
            System.out.println("Please provide exactly one command line argument.");
            return;
        }

        String command = args[0];
        
        if (command.equals("l")) {
            System.out.println(Constants.LOADING_MESSAGE);
            String[] employees = readEmployeesFromFile();
            for (String employee : employees) {
                System.out.println(employee);
            }
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } 
        else if (command.equals("s")) {
            System.out.println(Constants.LOADING_MESSAGE);
            String[] employees = readEmployeesFromFile();
            if (employees.length == 0) {
                System.out.println("No employees found in database.");
            } else {
                System.out.println(String.join(",", employees));
                System.out.println(employees[new Random().nextInt(employees.length)]);
            }
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } 
        else if (command.contains("+")) {
            if (command.length() == 1) {
                System.out.println(Constants.MISSING_NAME_MESSAGE);
                return;
            }
            System.out.println(Constants.LOADING_MESSAGE);
            appendEmployeeToFile(command.substring(1));
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } 
        else if (command.contains("?")) {
            if (command.length() == 1) {
                System.out.println(Constants.MISSING_NAME_MESSAGE);
                return;
            }
            System.out.println(Constants.LOADING_MESSAGE);
            String searchName = command.substring(1);
            boolean employeeFound = false;
            
            for (String employee : readEmployeesFromFile()) {
                if (employee.equals(searchName)) {
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
        else if (command.contains("c")) {
            System.out.println(Constants.LOADING_MESSAGE);
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(Constants.EMPLOYEES_FILE_PATH)));
                String line = reader.readLine();
                String[] employees = line.split(",");
                int characterCount = line.length();
                System.out.println(employees.length + " employee(s) found, " + characterCount + " character(s)");
            } 
            catch (Exception exception) {
                System.out.println(Constants.FILE_ERROR_MESSAGE);
            }
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } 
        else if (command.contains("u")) {
            if (command.length() == 1) {
                System.out.println(Constants.MISSING_NAME_MESSAGE);
                return;
            }
            System.out.println(Constants.LOADING_MESSAGE);
            String[] employees = readEmployeesFromFile();
            String employeeName = command.substring(1);
            boolean employeeUpdated = false;
            
            for (int i = 0; i < employees.length; i++) {
                if (employees[i].equals(employeeName)) {
                    employees[i] = Constants.UPDATED_PLACEHOLDER;
                    employeeUpdated = true;
                }
            }
            
            if (employeeUpdated) {
                writeEmployeesToFile(employees);
                System.out.println(Constants.DATA_UPDATED_MESSAGE);
            } else {
                System.out.println("Employee not found for update.");
            }
        } 
        else if (command.contains("d")) {
            if (command.length() == 1) {
                System.out.println(Constants.MISSING_NAME_MESSAGE);
                return;
            }
            System.out.println(Constants.LOADING_MESSAGE);
            List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployeesFromFile()));
            String employeeToDelete = command.substring(1);
            boolean employeeRemoved = employeeList.remove(employeeToDelete);
            
            if (employeeRemoved) {
                writeEmployeesToFile(employeeList.toArray(new String[0]));
                System.out.println(Constants.DATA_DELETED_MESSAGE);
            } else {
                System.out.println("Employee not found for deletion.");
            }
        }
        else {
            // Handle invalid or unsupported arguments
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