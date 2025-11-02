//File Name EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        // Task #2 – Validate arguments
        if (args.length != 1) {
            System.out.println("Invalid number of arguments. Please provide exactly one argument.");
            System.out.println("Usage examples:");
            System.out.println("  l   - List all employees");
            System.out.println("  s   - Show a random employee");
            System.out.println("  +<name> - Add a new employee");
            System.out.println("  ?<name> - Search for an employee");
            System.out.println("  u<name> - Update an employee");
            System.out.println("  d<name> - Delete an employee");
            System.out.println("  c   - Count words and characters");
            return; 
        }

        String command = args[0];

        // List all employees
        if (command.equals("l")) {
            System.out.println("Loading data ...");
            try {
                for (String employee : readEmployees()) {
                    System.out.println(employee);
                }
            } catch (Exception ex) {}
            System.out.println("Data Loaded.");
        } 

        // Show a random employee
        else if (command.equals("s")) {
            System.out.println("Loading data ...");
            try {
                List<String> employees = readEmployees();
                System.out.println(String.join(",", employees));
                System.out.println(employees.get(new Random().nextInt(employees.size())));
            } catch (Exception ex) {}
            System.out.println("Data Loaded.");
        } 
        
        // Add a new employee
        else if (command.startsWith("+")) {
            System.out.println("Loading data ...");
            try {
                appendEmployee(command.substring(1));
            } catch (Exception ex) {}
            System.out.println("Data Loaded.");
        } 
        
        // Search for an employee
        else if (command.startsWith("?")) {
            System.out.println("Loading data ...");
            try {
                if (readEmployees().contains(command.substring(1))) {
                    System.out.println("Employee found!");
                }
            } catch (Exception ex) {}
            System.out.println("Data Loaded.");
        } 
        
        // Count words and characters
        else if (command.contains("c")) {
            System.out.println("Loading data ...");
            try {
                String employeeLine = String.join(",", readEmployees());
                int wordCount = 0;
                boolean inWord = false;

                for (char c : employeeLine.toCharArray()) {
                    if (c == ' ') {
                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }
                System.out.println(wordCount + " word(s) found " + employeeLine.length());
            } catch (Exception ex) {}
            System.out.println("Data Loaded.");
        } 
        
        // Update an employee
        else if (command.startsWith("u")) {
            System.out.println("Loading data ...");
            try {
                List<String> employees = readEmployees();
                String employeeToUpdate = command.substring(1);
                for (int i = 0; i < employees.size(); i++) {
                    if (employees.get(i).equals(employeeToUpdate)) {
                        employees.set(i, "Updated");
                    }
                }
                writeEmployees(employees);
            } catch (Exception ex) {}
            System.out.println("Data Updated.");
        } 

        // Delete an employee
        else if (command.startsWith("d")) {
            System.out.println("Loading data ...");
            try {
                List<String> employees = readEmployees();
                employees.remove(command.substring(1));
                writeEmployees(employees);
            } catch (Exception ex) {}
            System.out.println("Data Deleted.");
        }
    }

    // Helper Methods

    private static List<String> readEmployees() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(Constants.EMPLOYEE_FILE)));
        String line = reader.readLine();
        reader.close();

        if (line == null || line.isEmpty()) return new ArrayList<>();
        List<String> employees = new ArrayList<>();
        for (String emp : line.split(",")) employees.add(emp.trim());
        return employees;
    }

    private static void writeEmployees(List<String> employees) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE));
        writer.write(String.join(", ", employees));
        writer.close();
    }

    private static void appendEmployee(String newEmployee) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE, true));
        writer.write(", " + newEmployee);
        writer.close();
    }
}
