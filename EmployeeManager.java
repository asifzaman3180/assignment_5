//File Name EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    
    // Reusable method to read employees from file
    private static String[] readEmployeesFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(Constants.EMPLOYEES_FILE_PATH)));
        String line = reader.readLine();
        reader.close();
        return line.split(",");
    }
    
    // Reusable method to write employees to file
    private static void writeEmployeesToFile(String[] employees) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEES_FILE_PATH));
        writer.write(String.join(",", employees));
        writer.close();
    }
    
    // Reusable method to append to employees file
    private static void appendToEmployeesFile(String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEES_FILE_PATH, true));
        writer.write(content);
        writer.close();
    }

    public static void main(String[] args) {
        // Add validation check for command-line arguments
        if (args.length != 1) {
            System.out.println("Error: Incorrect number of arguments.");
            System.out.println("Usage: java EmployeeManager <command>");
            System.out.println("Commands: l (list), s (show random), +name (add), ?name (search), c (count), uname (update), dname (delete)");
            return;
        }
        
        // Check arguments
        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            try {
                for (String employee : readEmployeesFromFile()) {
                    System.out.println(employee);
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
        else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                if (employees.length == 0) {
                    System.out.println("Error: No employees found in the file.");
                    return;
                }
                System.out.println(String.join(",", employees));
                System.out.println(employees[new Random().nextInt(employees.length)]);
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
        else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                if (args[0].length() == 1) {
                    System.out.println("Error: Add command requires an employee name after '+'");
                    return;
                }
                appendToEmployeesFile(", " + args[0].substring(1));
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
        else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                if (args[0].length() == 1) {
                    System.out.println("Error: Search command requires an employee name after '?'");
                    return;
                }
                String[] employees = readEmployeesFromFile();
                String searchName = args[0].substring(1);
                boolean employeeFound = false;
                
                for (int index = 0; index < employees.length; index++) {
                    if (employees[index].equals(searchName)) {
                        employeeFound = true;
                        break;
                    }
                }
                
                if (employeeFound) {
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
        else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                if (employees.length == 0) {
                    System.out.println("Error: No employees found in the file.");
                    return;
                }
                int employeeCount = employees.length;
                int totalCharacters = String.join(",", employees).length();
                System.out.println(employeeCount + " employee(s) found with " + totalCharacters + " characters");
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
        else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try {
                if (args[0].length() == 1) {
                    System.out.println("Error: Update command requires an employee name after 'u'");
                    return;
                }
                String[] employees = readEmployeesFromFile();
                String employeeNameToUpdate = args[0].substring(1);
                boolean employeeFound = false;
                
                for (int index = 0; index < employees.length; index++) {
                    if (employees[index].equals(employeeNameToUpdate)) {
                        employees[index] = "Updated";
                        employeeFound = true;
                    }
                }
                
                if (!employeeFound) {
                    System.out.println("Error: Employee '" + employeeNameToUpdate + "' not found for update.");
                    return;
                }
                
                writeEmployeesToFile(employees);
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
        else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            try {
                if (args[0].length() == 1) {
                    System.out.println("Error: Delete command requires an employee name after 'd'");
                    return;
                }
                String[] employees = readEmployeesFromFile();
                String employeeNameToDelete = args[0].substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                
                if (!employeeList.remove(employeeNameToDelete)) {
                    System.out.println("Error: Employee '" + employeeNameToDelete + "' not found for deletion.");
                    return;
                }
                
                writeEmployeesToFile(employeeList.toArray(new String[0]));
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
            System.out.println("Error: Unsupported command '" + args[0] + "'");
            System.out.println("Supported commands: l (list), s (show random), +name (add), ?name (search), c (count), uname (update), dname (delete)");
        }
    }
}