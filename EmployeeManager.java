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
                List<String> employees = readEmployees();
                for (String employee : employees) {
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
                Random random = new Random();
                int randomIndex = random.nextInt(employees.size());
                System.out.println(employees.get(randomIndex));
            } catch (Exception ex) {}
            System.out.println("Data Loaded.");
        } 
        
        // Add a new employee
        else if (command.startsWith("+")) {
            System.out.println("Loading data ...");
            try {
                String newEmployee = command.substring(1);
                appendEmployee(newEmployee);
            } catch (Exception ex) {}
            System.out.println("Data Loaded.");
        } 
        
        // Search for an employee
        else if (command.startsWith("?")) {
            System.out.println("Loading data ...");
            try {
                List<String> employees = readEmployees();
                String searchEmployee = command.substring(1);
                boolean found = employees.contains(searchEmployee);
                if (found) {
                    System.out.println("Employee found!");
                }
            } catch (Exception ex) {}
            System.out.println("Data Loaded.");
        } 
        
        // Count words and characters
        else if (command.contains("c")) {
            System.out.println("Loading data ...");
            try {
                List<String> employees = readEmployees();
                String line = String.join(",", employees);
                char[] characters = line.toCharArray();
                boolean inWord = false;
                int wordCount = 0;
                for (char character : characters) {
                    if (character == ' ') {
                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }
                System.out.println(wordCount + " word(s) found " + characters.length);
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
                String employeeToDelete = command.substring(1);
                employees.remove(employeeToDelete);
                writeEmployees(employees);
            } catch (Exception ex) {}
            System.out.println("Data Deleted.");
        }
    }

    //Helper Methods

    private static List<String> readEmployees() throws IOException {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                new FileInputStream("employees.txt")
            )
        );
        String line = reader.readLine();
        reader.close();
        if (line == null || line.isEmpty()) {
            return new ArrayList<>();
        }
        String[] employeesArray = line.split(",");
        List<String> employees = new ArrayList<>();
        for (String emp : employeesArray) {
            employees.add(emp.trim());
        }
        return employees;
    }

    private static void writeEmployees(List<String> employees) throws IOException {
        BufferedWriter writer = new BufferedWriter(
            new FileWriter("employees.txt")
        );
        writer.write(String.join(", ", employees));
        writer.close();
    }

    private static void appendEmployee(String newEmployee) throws IOException {
        BufferedWriter writer = new BufferedWriter(
            new FileWriter("employees.txt", true)
        );
        writer.write(", " + newEmployee);
        writer.close();
    }
}
