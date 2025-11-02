// File Name: EmployeeManager.java
// Description: Improved and refactored version of the employee manager program

import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {
        // Basic argument presence (Task 2 will improve this)
        if (args.length != 1) {
            System.out.println("Error: Please provide exactly one command-line argument (l, s, +name, ?name, c, uName, or dName).");
            return;
        }

        String command = args[0];
        System.out.println("Loading data ...");

        try {
            switch (command.charAt(0)) {
                case 'l':
                    listEmployees();
                    break;
                case 's':
                    showRandomEmployee();
                    break;
                case '+':
                    addEmployee(command.substring(1).trim());
                    break;
                case '?':
                    searchEmployee(command.substring(1).trim());
                    break;
                case 'c':
                    countEmployees();
                    break;
                case 'u':
                    updateEmployee(command.substring(1).trim());
                    break;
                case 'd':
                    deleteEmployee(command.substring(1).trim());
                    break;
                default:
                    System.out.println("Invalid argument. Use: l, s, +name, ?name, c, uName, or dName.");
                    break;
            }
        } catch (IOException e) {
            System.out.println("Error handling file: " + e.getMessage());
        }

        System.out.println("Operation completed.");
    }

    private static String readEmployeeData() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.FILE_PATH))) {
            String line = reader.readLine();
            return line == null ? "" : line;
        } catch (FileNotFoundException e) {
            return "";
        }
    }

    private static void writeEmployeeData(String data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_PATH))) {
            writer.write(data);
        }
    }

    private static void appendEmployeeData(String data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_PATH, true))) {
            if (new File(Constants.FILE_PATH).length() > 0) writer.write(", " + data);
            else writer.write(data);
        }
    }

    private static void listEmployees() throws IOException {
        String line = readEmployeeData();
        if (line == null || line.trim().isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        for (String employee : line.split(",")) System.out.println(employee.trim());
    }

    private static void showRandomEmployee() throws IOException {
        String line = readEmployeeData();
        if (line == null || line.trim().isEmpty()) {
            System.out.println("No employees to show.");
            return;
        }
        String[] employees = line.split(",");
        Random random = new Random();
        System.out.println(employees[random.nextInt(employees.length)].trim());
    }

    private static void addEmployee(String name) throws IOException {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: Employee name cannot be empty.");
            return;
        }
        appendEmployeeData(name.trim());
        System.out.println("Employee added: " + name.trim());
    }

    private static void searchEmployee(String name) throws IOException {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: Search term cannot be empty.");
            return;
        }
        String line = readEmployeeData();
        if (line == null || line.trim().isEmpty()) {
            System.out.println("Employee not found: " + name);
            return;
        }
        for (String employee : line.split(",")) {
            if (employee.trim().equalsIgnoreCase(name.trim())) {
                System.out.println("Employee found: " + employee.trim());
                return;
            }
        }
        System.out.println("Employee not found: " + name);
    }

    private static void countEmployees() throws IOException {
        String line = readEmployeeData();
        if (line == null || line.trim().isEmpty()) {
            System.out.println("Total employees: 0");
            return;
        }
        int count = 0;
        for (String e : line.split(",")) if (!e.trim().isEmpty()) count++;
        System.out.println("Total employees: " + count);
    }

    private static void updateEmployee(String name) throws IOException {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: Update name cannot be empty.");
            return;
        }
        String line = readEmployeeData();
        if (line == null || line.trim().isEmpty()) {
            System.out.println("Employee not found: " + name);
            return;
        }
        String[] employees = line.split(",");
        boolean updated = false;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].trim().equalsIgnoreCase(name.trim())) {
                employees[i] = "Updated";
                updated = true;
            }
        }
        writeEmployeeData(String.join(",", employees));
        System.out.println(updated ? "Employee updated successfully." : "Employee not found: " + name);
    }

    private static void deleteEmployee(String name) throws IOException {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: Delete name cannot be empty.");
            return;
        }
        String line = readEmployeeData();
        if (line == null || line.trim().isEmpty()) {
            System.out.println("Employee not found: " + name);
            return;
        }
        List<String> list = new ArrayList<>(Arrays.asList(line.split(",")));
        boolean removed = list.removeIf(e -> e.trim().equalsIgnoreCase(name.trim()));
        if (removed) writeEmployeeData(String.join(",", list));
        System.out.println(removed ? "Employee deleted: " + name : "Employee not found: " + name);
    }
}

class Constants {
    public static final String FILE_PATH = "employees.txt";
}
