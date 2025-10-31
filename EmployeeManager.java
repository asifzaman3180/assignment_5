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
            catch (Exception exception) {

            }
            System.out.println("Data Loaded.");
        } 
        else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                System.out.println(String.join(",", employees));
                System.out.println(employees[new Random().nextInt(employees.length)]);
            } 
            catch (Exception exception) {

            }
            System.out.println("Data Loaded.");
        } 
        else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                appendToEmployeesFile(", " + args[0].substring(1));
            } 
            catch (Exception exception) {

            }
            System.out.println("Data Loaded.");
        } 
        else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
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
            catch (Exception exception) {

            }
            System.out.println("Data Loaded.");
        } 
        else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                int employeeCount = employees.length;
                int totalCharacters = String.join(",", employees).length();
                System.out.println(employeeCount + " employee(s) found with " + totalCharacters + " characters");
            } 
            catch (Exception exception) {

            }
            System.out.println("Data Loaded.");
        } 
        else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                String employeeNameToUpdate = args[0].substring(1);
                for (int index = 0; index < employees.length; index++) {
                    if (employees[index].equals(employeeNameToUpdate)) {
                        employees[index] = "Updated";
                    }
                }
                writeEmployeesToFile(employees);
            } 
            catch (Exception exception) {

            }
            System.out.println("Data Updated.");
        } 
        else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(args[0].substring(1));
                writeEmployeesToFile(employeeList.toArray(new String[0]));
            } 
            catch (Exception exception) {
                
            }
            System.out.println("Data Deleted.");
        }
    }
}