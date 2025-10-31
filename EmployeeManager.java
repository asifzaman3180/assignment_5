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
        }
    }

    public static void main(String[] args) {
        
        // Validate command line arguments
        if (args.length != 1) {
            System.out.println("Usage: java EmployeeManager [l|s|+name|?name|c|uname|dname]");
            System.out.println("Please provide exactly one command line argument.");
            return;
        }

        String command = args[0];
        
        if (command.equals("l")) {
            System.out.println(Constants.LOADING_MESSAGE);
            for (String employee : readEmployeesFromFile()) {
                System.out.println(employee);
            }
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } 
        else if (command.equals("s")) {
            System.out.println(Constants.LOADING_MESSAGE);
            String[] employees = readEmployeesFromFile();
            System.out.println(String.join(",", employees));
            System.out.println(employees[new Random().nextInt(employees.length)]);
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } 
        else if (command.contains("+")) {
            System.out.println(Constants.LOADING_MESSAGE);
            appendEmployeeToFile(command.substring(1));
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } 
        else if (command.contains("?")) {
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
            }
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } 
        else if (command.contains("u")) {
            System.out.println(Constants.LOADING_MESSAGE);
            String[] employees = readEmployeesFromFile();
            String employeeName = command.substring(1);
            for (int i = 0; i < employees.length; i++) {
                if (employees[i].equals(employeeName)) {
                    employees[i] = Constants.UPDATED_PLACEHOLDER;
                }
            }
            writeEmployeesToFile(employees);
            System.out.println(Constants.DATA_UPDATED_MESSAGE);
        } 
        else if (command.contains("d")) {
            System.out.println(Constants.LOADING_MESSAGE);
            List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployeesFromFile()));
            employeeList.remove(command.substring(1));
            writeEmployeesToFile(employeeList.toArray(new String[0]));
            System.out.println(Constants.DATA_DELETED_MESSAGE);
        }
    }
}