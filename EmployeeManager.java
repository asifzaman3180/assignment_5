// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    private static final String FILE_NAME = "employees.txt";

    // Helper method: read employees from file
    private static String[] readEmployeesFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            if (line == null || line.isEmpty()) {
                return new String[0];
            }
            return line.split(",");
        }
    }

    // Helper method: write employees to file (overwrite)
    private static void writeEmployeesToFile(String[] employees) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(String.join(",", employees));
        }
    }

    // Helper method: append new employee to file
    private static void appendEmployeeToFile(String newEmployee) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(", " + newEmployee);
        }
    }

    public static void main(String[] args) {
        // Check arguments
        if (args.length == 0) {
            System.out.println("No arguments provided!");
            System.out.println("Usage:");
            System.out.println("  l  -> List employees");
            System.out.println("  s  -> Show random employee");
            System.out.println("  +X -> Add employee");
            System.out.println("  ?X -> Search employee");
            System.out.println("  c  -> Count words/chars");
            System.out.println("  uX -> Update employee");
            System.out.println("  dX -> Delete employee");
            return;
        }

        String command = args[0];

        if (command.equals("l")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                for (String employee : employees) {
                    System.out.println(employee.trim());
                }
            } catch (Exception e) {}
            System.out.println("Data Loaded.");

        } else if (command.equals("s")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                if (employees.length > 0) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(employees.length);
                    System.out.println(employees[randomIndex].trim());
                }
            } catch (Exception e) {}
            System.out.println("Data Loaded.");

        } else if (command.contains("+")) {
            System.out.println("Loading data ...");
            try {
                String newEmployee = command.substring(1);
                appendEmployeeToFile(newEmployee);
            } catch (Exception e) {}
            System.out.println("Data Loaded.");

        } else if (command.contains("?")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                String searchName = command.substring(1);
                boolean found = false;
                for (String employee : employees) {
                    if (employee.trim().equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                        break;
                    }
                }
            } catch (Exception e) {}
            System.out.println("Data Loaded.");

        } else if (command.contains("c")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                int charCount = String.join(",", employees).length();
                System.out.println(employees.length + " word(s) found " + charCount);
            } catch (Exception e) {}
            System.out.println("Data Loaded.");

        } else if (command.contains("u")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                String nameToUpdate = command.substring(1);
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].trim().equals(nameToUpdate)) {
                        employees[i] = "Updated";
                    }
                }
                writeEmployeesToFile(employees);
            } catch (Exception e) {}
            System.out.println("Data Updated.");

        } else if (command.contains("d")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                String nameToDelete = command.substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.removeIf(e -> e.trim().equals(nameToDelete));
                writeEmployeesToFile(employeeList.toArray(new String[0]));
            } catch (Exception e) {}
            System.out.println("Data Deleted.");

        } else {
            System.out.println("Invalid argument!");
            System.out.println("Use one of: l, s, +name, ?name, c, uname, dname");
        }
    }
}
