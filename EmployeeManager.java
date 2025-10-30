// File: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    
    private static List<String> readEmployeesFromFile() {
        List<String> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
            String line = reader.readLine();
            if (line != null) {
                employees = new ArrayList<>(Arrays.asList(line.split(",")));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return employees;
    }

   
    private static void writeEmployeesToFile(List<String> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"))) {
            writer.write(String.join(",", employees));
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

   
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Error: No arguments provided. Try l, s, +name, ?name, c, or u.");
            return;
        }

        String command = args[0];

        // List employees
        if (command.equals("l")) {
            System.out.println("Loading data ...");
            List<String> employees = readEmployeesFromFile();
            for (String emp : employees) {
                System.out.println(emp.trim());
            }
            System.out.println("Data Loaded.");
        }

        // Show a random employee
        else if (command.equals("s")) {
            System.out.println("Loading data ...");
            List<String> employees = readEmployeesFromFile();
            if (!employees.isEmpty()) {
                Random rand = new Random();
                int randomIndex = rand.nextInt(employees.size());
                System.out.println("Random Employee: " + employees.get(randomIndex).trim());
            }
            System.out.println("Data Loaded.");
        }

        // Add new employee
        else if (command.startsWith("+")) {
            String newEmployee = command.substring(1);
            System.out.println("Loading data ...");
            List<String> employees = readEmployeesFromFile();
            employees.add(newEmployee);
            writeEmployeesToFile(employees);
            System.out.println("Data Loaded.");
        }

        // Search employee
        else if (command.startsWith("?")) {
            String searchName = command.substring(1);
            System.out.println("Loading data ...");
            List<String> employees = readEmployeesFromFile();
            if (employees.contains(searchName)) {
                System.out.println("Employee found!");
            } else {
                System.out.println("Employee not found.");
            }
            System.out.println("Data Loaded.");
        }

        // Count total employees
        else if (command.equals("c")) {
            System.out.println("Loading data ...");
            List<String> employees = readEmployeesFromFile();
            System.out.println("Total Employees: " + employees.size());
            System.out.println("Data Loaded.");
        }

        // Update an employee (replace old name with "Updated")
        else if (command.startsWith("u")) {
            String targetName = command.substring(1);
            System.out.println("Loading data ...");
            List<String> employees = readEmployeesFromFile();

            boolean updated = false;
            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i).equals(targetName)) {
                    employees.set(i, "Updated");
                    updated = true;
                }
            }

            writeEmployeesToFile(employees);
            if (updated) {
                System.out.println("Employee updated successfully.");
            } else {
                System.out.println("Employee not found to update.");
            }
            System.out.println("Data Updated.");
        }

        // Invalid command
        else {
            System.out.println("Error: Invalid command. Try l, s, +name, ?name, c, or u.");
        }
    }
}
