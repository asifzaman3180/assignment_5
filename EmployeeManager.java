// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    private static final Set<String> VALID_COMMANDS = Set.of("l", "s", "c");
    private static final String EMPLOYEE_FILE = "employees.txt";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: No arguments provided!");
            System.out.println("Usage: l | s | +name | ?name | c | uName | dName");
            return;
        }

        String command = args[0];

        if (!isValidCommand(command)) {
            System.out.println("Error: Invalid argument provided: " + command);
            System.out.println("Usage: l | s | +name | ?name | c | uName | dName");
            return;
        }

        try {
            if (command.equals("l")) {
                // List all employees
                String[] employees = readEmployees();
                System.out.println("Loading data ...");
                for (String employee : employees) {
                    System.out.println(employee);
                }
                System.out.println("Data Loaded.");
            } 
            else if (command.equals("s")) {
                // Show a random employee
                String[] employees = readEmployees();
                System.out.println("Loading data ...");
                System.out.println(String.join(",", employees));
                Random random = new Random();
                int index = random.nextInt(employees.length);
                System.out.println(employees[index]);
                System.out.println("Data Loaded.");
            } 
            else if (command.startsWith("+")) {
                // Add a new employee
                String newEmployee = command.substring(1);
                System.out.println("Loading data ...");
                appendEmployee(newEmployee);
                System.out.println("Data Loaded.");
            } 
            else if (command.startsWith("?")) {
                // Search an employee
                String searchName = command.substring(1);
                String[] employees = readEmployees();
                System.out.println("Loading data ...");
                boolean found = Arrays.stream(employees).anyMatch(emp -> emp.equals(searchName));
                System.out.println(found ? "Employee found!" : "Employee not found!");
                System.out.println("Data Loaded.");
            } 
            // other commands (c, u, d) will use similar refactored methods
        } catch (IOException e) {
            System.out.println("Error accessing employee file: " + e.getMessage());
        }
    }

    // Reusable method to read employees from file
    private static String[] readEmployees() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_FILE));
        String line = reader.readLine();
        reader.close();
        return line.split(",");
    }

    // Reusable method to write employees to file
    private static void writeEmployees(String[] employees) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEE_FILE));
        writer.write(String.join(",", employees));
        writer.close();
    }

    // Reusable method to append a new employee
    private static void appendEmployee(String employeeName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEE_FILE, true));
        writer.write(", " + employeeName);
        writer.close();
    }

    private static boolean isValidCommand(String command) {
        if (VALID_COMMANDS.contains(command)) return true;
        return command.startsWith("+") || command.startsWith("?") || command.startsWith("u") || command.startsWith("d");
    }
}