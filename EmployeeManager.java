//File Name EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    
    // Reusable method to read all employees from file
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
    
    // Reusable method to write employees to file
    private static void writeEmployeesToFile(String[] employeeList) throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_DATA_FILE));
        fileWriter.write(String.join(",", employeeList));
        fileWriter.close();
    }
    
    // Reusable method to append employee to file
    private static void appendEmployeeToFile(String newEmployeeName) throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_DATA_FILE, true));
        fileWriter.write(", " + newEmployeeName);
        fileWriter.close();
    }
    
    // Validate command format for operations that require a name
    private static boolean isValidCommandWithName(String command, String prefix) {
        if (command.length() <= prefix.length()) {
            System.out.println("Error: Missing employee name after '" + prefix + "' prefix");
            return false;
        }
        String employeeName = command.substring(prefix.length());
        if (employeeName.trim().isEmpty()) {
            System.out.println("Error: Employee name cannot be empty");
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        // Validate command-line arguments
        if (args.length != 1) {
            System.out.println(Constants.INVALID_ARGUMENT_ERROR);
            System.out.println(Constants.USAGE_MESSAGE);
            System.out.println(Constants.COMMANDS_LIST);
            return;
        }
        
        String command = args[0];
        
        // Check arguments with comprehensive validation
        if (command.equals(Constants.LIST_COMMAND)) {
            System.out.println(Constants.LOADING_DATA_MESSAGE);
            try {
                String[] employeeList = readAllEmployeesFromFile();
                if (employeeList.length == 0) {
                    System.out.println("No employees found in the system.");
                } else {
                    for (String employeeName : employeeList) {
                        System.out.println(employeeName.trim());
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: Employee data file not found. Please ensure 'employees.txt' exists.");
            } catch (Exception exception) {
                System.out.println("Error: Unable to read employee data: " + exception.getMessage());
            }
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } else if (command.equals(Constants.RANDOM_COMMAND)) {
            System.out.println(Constants.LOADING_DATA_MESSAGE);
            try {
                String[] employeeList = readAllEmployeesFromFile();
                if (employeeList.length == 0) {
                    System.out.println("No employees available to select randomly.");
                } else {
                    System.out.println(String.join(", ", employeeList));
                    System.out.println("Random employee: " + employeeList[new Random().nextInt(employeeList.length)].trim());
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: Employee data file not found. Please ensure 'employees.txt' exists.");
            } catch (Exception exception) {
                System.out.println("Error: Unable to read employee data: " + exception.getMessage());
            }
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } else if (command.contains(Constants.ADD_COMMAND_PREFIX)) {
            if (!isValidCommandWithName(command, Constants.ADD_COMMAND_PREFIX)) {
                return;
            }
            System.out.println(Constants.LOADING_DATA_MESSAGE);
            try {
                appendEmployeeToFile(command.substring(1).trim());
                System.out.println("Employee '" + command.substring(1).trim() + "' added successfully.");
            } catch (Exception exception) {
                System.out.println("Error: Unable to add employee: " + exception.getMessage());
            }
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } else if (command.contains(Constants.SEARCH_COMMAND_PREFIX)) {
            if (!isValidCommandWithName(command, Constants.SEARCH_COMMAND_PREFIX)) {
                return;
            }
            System.out.println(Constants.LOADING_DATA_MESSAGE);
            try {
                String[] employeeList = readAllEmployeesFromFile();
                String targetEmployeeName = command.substring(1).trim();
                boolean found = false;
                for (String employee : employeeList) {
                    if (employee.trim().equals(targetEmployeeName)) {
                        System.out.println(Constants.EMPLOYEE_FOUND_MESSAGE);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Employee '" + targetEmployeeName + "' not found in the system.");
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: Employee data file not found. Please ensure 'employees.txt' exists.");
            } catch (Exception exception) {
                System.out.println("Error: Unable to search for employee: " + exception.getMessage());
            }
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } else if (command.contains(Constants.COUNT_COMMAND)) {
            System.out.println(Constants.LOADING_DATA_MESSAGE);
            try {
                String[] employeeList = readAllEmployeesFromFile();
                if (employeeList.length == 0) {
                    System.out.println("No employees found in the system.");
                } else {
                    System.out.println(employeeList.length + " employee(s) found in the system");
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: Employee data file not found. Please ensure 'employees.txt' exists.");
            } catch (Exception exception) {
                System.out.println("Error: Unable to count employees: " + exception.getMessage());
            }
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } else if (command.contains(Constants.UPDATE_COMMAND_PREFIX)) {
            if (!isValidCommandWithName(command, Constants.UPDATE_COMMAND_PREFIX)) {
                return;
            }
            System.out.println(Constants.LOADING_DATA_MESSAGE);
            try {
                String[] employeeList = readAllEmployeesFromFile();
                String employeeToUpdate = command.substring(1).trim();
                boolean updated = false;
                for (int i = 0; i < employeeList.length; i++) {
                    if (employeeList[i].trim().equals(employeeToUpdate)) {
                        employeeList[i] = Constants.UPDATED_VALUE;
                        updated = true;
                        break;
                    }
                }
                if (updated) {
                    writeEmployeesToFile(employeeList);
                    System.out.println("Employee '" + employeeToUpdate + "' updated successfully.");
                } else {
                    System.out.println("Error: Employee '" + employeeToUpdate + "' not found for update.");
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: Employee data file not found. Please ensure 'employees.txt' exists.");
            } catch (Exception exception) {
                System.out.println("Error: Unable to update employee: " + exception.getMessage());
            }
            System.out.println(Constants.DATA_UPDATED_MESSAGE);
        } else if (command.contains(Constants.DELETE_COMMAND_PREFIX)) {
            if (!isValidCommandWithName(command, Constants.DELETE_COMMAND_PREFIX)) {
                return;
            }
            System.out.println(Constants.LOADING_DATA_MESSAGE);
            try {
                String[] employeeList = readAllEmployeesFromFile();
                String employeeToDelete = command.substring(1).trim();
                List<String> updatedEmployeeList = new ArrayList<>(Arrays.asList(employeeList));
                boolean removed = updatedEmployeeList.removeIf(emp -> emp.trim().equals(employeeToDelete));
                if (removed) {
                    writeEmployeesToFile(updatedEmployeeList.toArray(new String[0]));
                    System.out.println("Employee '" + employeeToDelete + "' deleted successfully.");
                } else {
                    System.out.println("Error: Employee '" + employeeToDelete + "' not found for deletion.");
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: Employee data file not found. Please ensure 'employees.txt' exists.");
            } catch (Exception exception) {
                System.out.println("Error: Unable to delete employee: " + exception.getMessage());
            }
            System.out.println(Constants.DATA_DELETED_MESSAGE);
        } else {
            System.out.println(Constants.INVALID_COMMAND_ERROR + command + "'");
            System.out.println("Supported commands:");
            System.out.println("  l  - List all employees");
            System.out.println("  s  - Show random employee");
            System.out.println("  +name - Add new employee");
            System.out.println("  ?name - Search for employee");
            System.out.println("  c  - Count employees");
            System.out.println("  uname - Update employee");
            System.out.println("  dname - Delete employee");
        }
    }
}