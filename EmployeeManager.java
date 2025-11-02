import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {
        // Task #2 – Validate arguments
        if (args.length != 1) {
            System.out.println(Constants.INVALID_ARGUMENTS_MESSAGE);
            System.out.println(Constants.USAGE_HEADER);
            System.out.println(Constants.USAGE_LIST);
            System.out.println(Constants.USAGE_RANDOM);
            System.out.println(Constants.USAGE_ADD);
            System.out.println(Constants.USAGE_SEARCH);
            System.out.println(Constants.USAGE_UPDATE);
            System.out.println(Constants.USAGE_DELETE);
            System.out.println(Constants.USAGE_COUNT);
            return; 
        }

        String command = args[0];

        // List all employees
        if (command.equals(Constants.COMMAND_LIST)) {
            System.out.println(Constants.LOADING_DATA_MESSAGE);
            try {
                for (String employee : readEmployees()) {
                    System.out.println(employee);
                }
            } catch (Exception ex) {
                handleFileError(ex);
            }
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } 

        // Show a random employee
        else if (command.equals(Constants.COMMAND_RANDOM)) {
            System.out.println(Constants.LOADING_DATA_MESSAGE);
            try {
                List<String> employees = readEmployees();
                if (!employees.isEmpty()) {
                    System.out.println(String.join(Constants.DATA_SEPARATOR_WITH_SPACE, employees));
                    System.out.println(employees.get(new Random().nextInt(employees.size())));
                }
            } catch (Exception ex) {
                handleFileError(ex);
            }
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } 
        
        // Add a new employee
        else if (command.startsWith(Constants.COMMAND_ADD_PREFIX)) {
            System.out.println(Constants.LOADING_DATA_MESSAGE);
            try {
                String newEmployee = command.substring(1);
                appendEmployee(newEmployee);
            } catch (Exception ex) {
                handleFileError(ex);
            }
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } 
        
        // Search for an employee
        else if (command.startsWith(Constants.COMMAND_SEARCH_PREFIX)) {
            System.out.println(Constants.LOADING_DATA_MESSAGE);
            try {
                List<String> employees = readEmployees();
                String searchEmployee = command.substring(1);
                if (employees.contains(searchEmployee)) {
                    System.out.println(Constants.EMPLOYEE_FOUND_MESSAGE);
                } else {
                    System.out.println(Constants.EMPLOYEE_NOT_FOUND_MESSAGE);
                }
            } catch (Exception ex) {
                handleFileError(ex);
            }
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } 
        
        // Count words and characters
        else if (command.equals(Constants.COMMAND_COUNT)) {
            System.out.println(Constants.LOADING_DATA_MESSAGE);
            try {
                List<String> employees = readEmployees();
                int wordCount = employees.size();
                int charCount = String.join(Constants.DATA_SEPARATOR_WITH_SPACE, employees).length();
                System.out.println(wordCount + Constants.COUNT_FORMAT + charCount + Constants.CHARACTERS_FORMAT);
            } catch (Exception ex) {
                handleFileError(ex);
            }
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } 
        
        // Update an employee
        else if (command.startsWith(Constants.COMMAND_UPDATE_PREFIX)) {
            System.out.println(Constants.LOADING_DATA_MESSAGE);
            try {
                List<String> employees = readEmployees();
                String employeeToUpdate = command.substring(1);
                boolean updated = false;
                for (int i = 0; i < employees.size(); i++) {
                    if (employees.get(i).equals(employeeToUpdate)) {
                        employees.set(i, Constants.UPDATED_EMPLOYEE_VALUE);
                        updated = true;
                    }
                }
                if (updated) {
                    writeEmployees(employees);
                }
            } catch (Exception ex) {
                handleFileError(ex);
            }
            System.out.println(Constants.DATA_UPDATED_MESSAGE);
        } 

        // Delete an employee
        else if (command.startsWith(Constants.COMMAND_DELETE_PREFIX)) {
            System.out.println(Constants.LOADING_DATA_MESSAGE);
            try {
                List<String> employees = readEmployees();
                boolean removed = employees.remove(command.substring(1));
                if (removed) {
                    writeEmployees(employees);
                }
            } catch (Exception ex) {
                handleFileError(ex);
            }
            System.out.println(Constants.DATA_DELETED_MESSAGE);
        } 
        
        // Handle invalid arguments (Task #9)
        else {
            System.out.println(Constants.UNSUPPORTED_COMMAND_MESSAGE + command + "\"");
            System.out.println("Please use one of the following commands:");
            System.out.println(Constants.USAGE_LIST);
            System.out.println(Constants.USAGE_RANDOM);
            System.out.println(Constants.USAGE_ADD);
            System.out.println(Constants.USAGE_SEARCH);
            System.out.println(Constants.USAGE_UPDATE);
            System.out.println(Constants.USAGE_DELETE);
            System.out.println(Constants.USAGE_COUNT);
        }
    }

    private static void handleFileError(Exception exception) {
        System.err.println(Constants.FILE_ERROR_MESSAGE + exception.getMessage());
    }

    //Helper Methods 

    private static List<String> readEmployees() throws IOException {
        File file = new File(Constants.EMPLOYEE_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        reader.close();

        if (line == null || line.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<String> employees = new ArrayList<>();
        for (String emp : line.split(Constants.DATA_SEPARATOR)) {
            employees.add(emp.trim());
        }
        return employees;
    }

    private static void writeEmployees(List<String> employees) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE));
        writer.write(String.join(Constants.DATA_SEPARATOR_WITH_SPACE, employees));
        writer.close();
    }

    private static void appendEmployee(String newEmployee) throws IOException {
        List<String> employees = readEmployees();
        employees.add(newEmployee);
        writeEmployees(employees);
    }
}