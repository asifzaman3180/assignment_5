// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

/**
 * EmployeeManager is a command-line application to manage a list of employees.
 * Supported operations:
 *   l      - List all employees
 *   s      - Show all employees and a randomly selected employee
 *   +name  - Add a new employee
 *   ?name  - Search for an employee
 *   c      - Count words and total characters in the employee list
 *   uname  - Update an employee's name to "Updated"
 *   dname  - Delete an employee
 */
public class EmployeeManager {

    public static void main(String[] args) {
        // Check if an argument was provided
        if (args.length == 0) {
            System.out.println("Error: No argument provided.");
            System.out.println("Usage: java EmployeeManager [l|s|+name|?name|c|uname|dname]");
            return;
        }

        String command = args[0]; // User-provided command

        if (command.equals("l")) {
            listEmployees();
        } 
        else if (command.equals("s")) {
            showRandomEmployee();
        } 
        else if (command.startsWith("+")) {
            addEmployee(command.substring(1));
        } 
        else if (command.startsWith("?")) {
            searchEmployee(command.substring(1));
        } 
        else if (command.equals("c")) {
            countWordsAndCharacters();
        } 
        else if (command.startsWith("u")) {
            updateEmployee(command.substring(1));
        } 
        else if (command.startsWith("d")) {
            deleteEmployee(command.substring(1));
        } 
        else {
            System.out.println("Error: Unsupported argument '" + command + "'.");
            System.out.println("Supported arguments: l, s, +name, ?name, c, uname, dname");
        }
    }

    /**
     * Reads employee names from the file.
     * @return array of employee names or null if file is empty or error occurs
     */
    private static String[] readEmployeeFile() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(Constants.EMPLOYEE_FILE)))) {
            String line = reader.readLine();
            return (line != null && !line.isEmpty()) ? line.split(",") : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Writes the given list of employees to the file.
     * @param employeeList array of employee names
     */
    private static void writeEmployeeFile(String[] employeeList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE))) {
            writer.write(String.join(",", employeeList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Appends a new employee to the employee file.
     * @param employeeName the name of the employee to add
     */
    private static void appendEmployee(String employeeName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE, true))) {
            writer.write(", " + employeeName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lists all employees in the file.
     */
    private static void listEmployees() {
        System.out.println("Loading data ...");
        String[] employeeList = readEmployeeFile();
        if (employeeList != null) {
            for (String employee : employeeList) {
                System.out.println(employee);
            }
        }
        System.out.println("Data Loaded.");
    }

    /**
     * Shows all employees and prints a randomly selected employee.
     */
    private static void showRandomEmployee() {
        System.out.println("Loading data ...");
        String[] employeeList = readEmployeeFile();
        if (employeeList != null) {
            System.out.println(String.join(", ", employeeList));
            System.out.println(employeeList[new Random().nextInt(employeeList.length)]);
        }
        System.out.println("Data Loaded.");
    }

    /**
     * Adds a new employee to the file.
     * @param employeeName the name of the employee to add
     */
    private static void addEmployee(String employeeName) {
        System.out.println("Loading data ...");
        appendEmployee(employeeName);
        System.out.println("Data Loaded.");
    }

    /**
     * Searches for an employee by name.
     * @param employeeName the name to search for
     */
    private static void searchEmployee(String employeeName) {
        System.out.println("Loading data ...");
        String[] employeeList = readEmployeeFile();
        if (employeeList != null && Arrays.asList(employeeList).contains(employeeName)) {
            System.out.println("Employee found!");
        } else {
            System.out.println("Employee not found!");
        }
        System.out.println("Data Loaded.");
    }

    /**
     * Counts and prints the number of words and characters in the employee list.
     */
    private static void countWordsAndCharacters() {
        System.out.println("Loading data ...");
        String[] employeeList = readEmployeeFile();
        if (employeeList != null) {
            String allEmployees = String.join(" ", employeeList);
            String[] words = allEmployees.trim().split("\\s+");
            int wordCount = (allEmployees.isEmpty()) ? 0 : words.length;
            int charCount = allEmployees.length();
            System.out.println(wordCount + " word(s) found, total characters: " + charCount);
        }
        System.out.println("Data Loaded.");
    }

    /**
     * Updates the given employee's name to "Updated".
     * @param employeeName the name of the employee to update
     */
    private static void updateEmployee(String employeeName) {
        System.out.println("Loading data ...");
        String[] employeeList = readEmployeeFile();
        if (employeeList != null) {
            for (int i = 0; i < employeeList.length; i++) {
                if (employeeList[i].equals(employeeName)) {
                    employeeList[i] = "Updated";
                }
            }
            writeEmployeeFile(employeeList);
        }
        System.out.println("Data Updated.");
    }

    /**
     * Deletes the specified employee from the file.
     * @param employeeName the name of the employee to delete
     */
    private static void deleteEmployee(String employeeName) {
        System.out.println("Loading data ...");
        String[] employeeList = readEmployeeFile();
        if (employeeList != null) {
            List<String> employeeArrayList = new ArrayList<>(Arrays.asList(employeeList));
            employeeArrayList.remove(employeeName);
            writeEmployeeFile(employeeArrayList.toArray(new String[0]));
        }
        System.out.println("Data Deleted.");
    }
}
