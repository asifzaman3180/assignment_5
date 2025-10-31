//File Name EmployeeManager.java
import java.io.*;
import java.util.*;

// Main application class for managing employee data which provides functionality to list, search, add, update, delete, and count employees
public class EmployeeManager {
    
    // Reads all employees from the data file and returns as an array
    private static String[] readAllEmployees() throws IOException {
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(Constants.EMPLOYEES_FILE_PATH)));
        String fileContent = fileReader.readLine();
        fileReader.close();
        return fileContent.split(",");
    }
    
    // Writes the complete list of employees to the data file
    private static void writeAllEmployees(String[] employees) throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(Constants.EMPLOYEES_FILE_PATH));
        fileWriter.write(String.join(",", employees));
        fileWriter.close();
    }
    
    // Appends new content to the end of the employees file
    private static void appendToEmployeeFile(String contentToAppend) throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(Constants.EMPLOYEES_FILE_PATH, true));
        fileWriter.write(contentToAppend);
        fileWriter.close();
    }

    public static void main(String[] args) {
        // Validate command line arguments
        if (args.length != 1) {
            System.out.println("Error: Incorrect number of arguments.");
            System.out.println("Usage: java EmployeeManager <command>");
            System.out.println("Commands: l (list), s (show random), +name (add), ?name (search), c (count), uname (update), dname (delete)");
            return;
        }
        
        String userCommand = args[0];
        
        // Execute command based on user input
        if (userCommand.equals("l")) {
            // List all employees
            System.out.println("Loading data ...");
            try {
                for (String employeeName : readAllEmployees()) {
                    System.out.println(employeeName);
                }
            } 
            catch (FileNotFoundException e) {
                System.out.println("Error: Employees file not found. Please ensure 'employees.txt' exists.");
            }
            catch (IOException e) {
                System.out.println("Error: Unable to read employees file. Please check file permissions.");
            }
            catch (Exception e) {
                System.out.println("Error: An unexpected error occurred while loading employee data.");
            }
            System.out.println("Data Loaded.");
        } 
        else if (userCommand.equals("s")) {
            // Show a random employee
            System.out.println("Loading data ...");
            try {
                String[] allEmployees = readAllEmployees();
                if (allEmployees.length == 0) {
                    System.out.println("Error: No employees found in the file.");
                    return;
                }
                System.out.println(String.join(",", allEmployees));
                System.out.println(allEmployees[new Random().nextInt(allEmployees.length)]);
            } 
            catch (FileNotFoundException e) {
                System.out.println("Error: Employees file not found. Please ensure 'employees.txt' exists.");
            }
            catch (IOException e) {
                System.out.println("Error: Unable to read employees file. Please check file permissions.");
            }
            catch (Exception e) {
                System.out.println("Error: An unexpected error occurred while selecting random employee.");
            }
            System.out.println("Data Loaded.");
        } 
        else if (userCommand.contains("+")) {
            // Add a new employee
            System.out.println("Loading data ...");
            try {
                if (userCommand.length() == 1) {
                    System.out.println("Error: Add command requires an employee name after '+'");
                    return;
                }
                String newEmployee = userCommand.substring(1);
                appendToEmployeeFile(", " + newEmployee);
            } 
            catch (FileNotFoundException e) {
                System.out.println("Error: Employees file not found. Please ensure 'employees.txt' exists.");
            }
            catch (IOException e) {
                System.out.println("Error: Unable to write to employees file. Please check file permissions.");
            }
            catch (Exception e) {
                System.out.println("Error: An unexpected error occurred while adding employee.");
            }
            System.out.println("Data Loaded.");
        } 
        else if (userCommand.contains("?")) {
            // Search for an employee
            System.out.println("Loading data ...");
            try {
                if (userCommand.length() == 1) {
                    System.out.println("Error: Search command requires an employee name after '?'");
                    return;
                }
                String[] allEmployees = readAllEmployees();
                String targetEmployee = userCommand.substring(1);
                boolean isEmployeeFound = false;
                
                // Search through employee list
                for (int currentIndex = 0; currentIndex < allEmployees.length; currentIndex++) {
                    if (allEmployees[currentIndex].equals(targetEmployee)) {
                        isEmployeeFound = true;
                        break;
                    }
                }
                
                // Display search result
                if (isEmployeeFound) {
                    System.out.println("Employee found!");
                } else {
                    System.out.println("Employee not found!");
                }
            } 
            catch (FileNotFoundException e) {
                System.out.println("Error: Employees file not found. Please ensure 'employees.txt' exists.");
            }
            catch (IOException e) {
                System.out.println("Error: Unable to read employees file. Please check file permissions.");
            }
            catch (Exception e) {
                System.out.println("Error: An unexpected error occurred while searching for employee.");
            }
            System.out.println("Data Loaded.");
        } 
        else if (userCommand.contains("c")) {
            // Count employees and characters
            System.out.println("Loading data ...");
            try {
                String[] allEmployees = readAllEmployees();
                if (allEmployees.length == 0) {
                    System.out.println("Error: No employees found in the file.");
                    return;
                }
                int totalEmployees = allEmployees.length;
                int totalCharacterCount = String.join(",", allEmployees).length();
                System.out.println(totalEmployees + " employee(s) found with " + totalCharacterCount + " characters");
            } 
            catch (FileNotFoundException e) {
                System.out.println("Error: Employees file not found. Please ensure 'employees.txt' exists.");
            }
            catch (IOException e) {
                System.out.println("Error: Unable to read employees file. Please check file permissions.");
            }
            catch (Exception e) {
                System.out.println("Error: An unexpected error occurred while counting employees.");
            }
            System.out.println("Data Loaded.");
        } 
        else if (userCommand.contains("u")) {
            // Update an existing employee
            System.out.println("Loading data ...");
            try {
                if (userCommand.length() == 1) {
                    System.out.println("Error: Update command requires an employee name after 'u'");
                    return;
                }
                String[] allEmployees = readAllEmployees();
                String employeeToUpdate = userCommand.substring(1);
                boolean isUpdateSuccessful = false;
                
                // Find and update the employee
                for (int currentIndex = 0; currentIndex < allEmployees.length; currentIndex++) {
                    if (allEmployees[currentIndex].equals(employeeToUpdate)) {
                        allEmployees[currentIndex] = "Updated";
                        isUpdateSuccessful = true;
                    }
                }
                
                // Check if employee was found and updated
                if (!isUpdateSuccessful) {
                    System.out.println("Error: Employee '" + employeeToUpdate + "' not found for update.");
                    return;
                }
                
                writeAllEmployees(allEmployees);
            } 
            catch (FileNotFoundException e) {
                System.out.println("Error: Employees file not found. Please ensure 'employees.txt' exists.");
            }
            catch (IOException e) {
                System.out.println("Error: Unable to update employees file. Please check file permissions.");
            }
            catch (Exception e) {
                System.out.println("Error: An unexpected error occurred while updating employee.");
            }
            System.out.println("Data Updated.");
        } 
        else if (userCommand.contains("d")) {
            // Delete an employee
            System.out.println("Loading data ...");
            try {
                if (userCommand.length() == 1) {
                    System.out.println("Error: Delete command requires an employee name after 'd'");
                    return;
                }
                String[] allEmployees = readAllEmployees();
                String employeeToDelete = userCommand.substring(1);
                List<String> updatedEmployeeList = new ArrayList<>(Arrays.asList(allEmployees));
                
                // Attempt to remove the employee
                if (!updatedEmployeeList.remove(employeeToDelete)) {
                    System.out.println("Error: Employee '" + employeeToDelete + "' not found for deletion.");
                    return;
                }
                
                writeAllEmployees(updatedEmployeeList.toArray(new String[0]));
            } 
            catch (FileNotFoundException e) {
                System.out.println("Error: Employees file not found. Please ensure 'employees.txt' exists.");
            }
            catch (IOException e) {
                System.out.println("Error: Unable to delete from employees file. Please check file permissions.");
            }
            catch (Exception e) {
                System.out.println("Error: An unexpected error occurred while deleting employee.");
            }
            System.out.println("Data Deleted.");
        }
        else {
            // Handle unsupported commands
            System.out.println("Error: Unsupported command '" + userCommand + "'");
            System.out.println("Supported commands: l (list), s (show random), +name (add), ?name (search), c (count), uname (update), dname (delete)");
        }
    }
}