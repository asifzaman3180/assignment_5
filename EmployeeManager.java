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
    
    public static void main(String[] args) {
        // Validate command-line arguments
        if (args.length != 1) {
            System.out.println(Constants.INVALID_ARGUMENT_ERROR);
            System.out.println(Constants.USAGE_MESSAGE);
            System.out.println(Constants.COMMANDS_LIST);
            return;
        }
        
        // Check arguments
        if (args[0].equals(Constants.LIST_COMMAND)) {
            System.out.println(Constants.LOADING_DATA_MESSAGE);
            try {
                for (String employeeName : readAllEmployeesFromFile()) {
                    System.out.println(employeeName);
                }
            } catch (Exception exception) {
            }
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } else if (args[0].equals(Constants.RANDOM_COMMAND)) {
            System.out.println(Constants.LOADING_DATA_MESSAGE);
            try {
                String[] employeeList = readAllEmployeesFromFile();
                System.out.println(String.join(",", employeeList));
                System.out.println(employeeList[new Random().nextInt(employeeList.length)]);
            } catch (Exception exception) {
            }
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } else if (args[0].contains(Constants.ADD_COMMAND_PREFIX)) {
            System.out.println(Constants.LOADING_DATA_MESSAGE);
            try {
                appendEmployeeToFile(args[0].substring(1));
            } catch (Exception exception) {
            }
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } else if (args[0].contains(Constants.SEARCH_COMMAND_PREFIX)) {
            System.out.println(Constants.LOADING_DATA_MESSAGE);
            try {
                String[] employeeList = readAllEmployeesFromFile();
                String targetEmployeeName = args[0].substring(1);
                for (String employee : employeeList) {
                    if (employee.equals(targetEmployeeName)) {
                        System.out.println(Constants.EMPLOYEE_FOUND_MESSAGE);
                        System.out.println(Constants.DATA_LOADED_MESSAGE);
                        return;
                    }
                }
                System.out.println("Employee not found.");
            } catch (Exception exception) {
            }
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } else if (args[0].contains(Constants.COUNT_COMMAND)) {
            System.out.println(Constants.LOADING_DATA_MESSAGE);
            try {
                String[] employeeList = readAllEmployeesFromFile();
                System.out.println(employeeList.length + " employee(s) found");
            } catch (Exception exception) {
            }
            System.out.println(Constants.DATA_LOADED_MESSAGE);
        } else if (args[0].contains(Constants.UPDATE_COMMAND_PREFIX)) {
            System.out.println(Constants.LOADING_DATA_MESSAGE);
            try {
                String[] employeeList = readAllEmployeesFromFile();
                for (int employeeIndex = 0; employeeIndex < employeeList.length; employeeIndex++) {
                    if (employeeList[employeeIndex].equals(args[0].substring(1))) {
                        employeeList[employeeIndex] = Constants.UPDATED_VALUE;
                    }
                }
                writeEmployeesToFile(employeeList);
            } catch (Exception exception) {
            }
            System.out.println(Constants.DATA_UPDATED_MESSAGE);
        } else if (args[0].contains(Constants.DELETE_COMMAND_PREFIX)) {
            System.out.println(Constants.LOADING_DATA_MESSAGE);
            try {
                String[] employeeList = readAllEmployeesFromFile();
                List<String> updatedEmployeeList = new ArrayList<>(Arrays.asList(employeeList));
                updatedEmployeeList.remove(args[0].substring(1));
                writeEmployeesToFile(updatedEmployeeList.toArray(new String[0]));
            } catch (Exception exception) {
            }
            System.out.println(Constants.DATA_DELETED_MESSAGE);
        } else {
            System.out.println(Constants.INVALID_COMMAND_ERROR + args[0] + "'");
            System.out.println(Constants.COMMANDS_LIST);
        }
    }
}