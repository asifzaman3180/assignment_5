// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Reusable file operations
    private static String[] readEmployeeData() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(Constants.EMPLOYEE_FILE)))) {
            String line = reader.readLine();
            if (line == null || line.isEmpty()) return new String[0];
            return line.split(",");
        }
    }

    private static void writeEmployeeData(String[] employees, boolean append) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.EMPLOYEE_FILE, append))) {
            writer.write(String.join(",", employees));
        }
    }

    public static void main(String[] args) {

        // Argument validation
        if (args.length != 1) {
            System.out.println("❌ Invalid number of arguments.");
            System.out.println(Constants.USAGE_MESSAGE);
            return;
        }

        String command = args[0].trim();

        if (command.isEmpty()) {
            System.out.println("❌ Error: Command cannot be empty.");
            System.out.println(Constants.USAGE_MESSAGE);
            return;
        }

        System.out.println(Constants.LOADING_DATA);

        try {
            switch (command.charAt(0)) {

                // List all employees
                case 'l' -> {
                    for (String emp : readEmployeeData()) {
                        System.out.println(emp);
                    }
                    System.out.println(Constants.DATA_LOADED);
                }

                // Show random employee
                case 's' -> {
                    String[] employees = readEmployeeData();
                    if (employees.length == 0) {
                        System.out.println("⚠️ No employees found.");
                    } else {
                        System.out.println(employees[new Random().nextInt(employees.length)]);
                    }
                    System.out.println(Constants.DATA_LOADED);
                }

                // Add new employee
                case '+' -> {
                    if (command.length() <= 1) {
                        System.out.println("❌ Error: No employee name provided to add.");
                        return;
                    }
                    try (BufferedWriter writer = new BufferedWriter(
                            new FileWriter(Constants.EMPLOYEE_FILE, true))) {
                        writer.write(", " + command.substring(1));
                    }
                    System.out.println("✅ Employee added successfully.");
                    System.out.println(Constants.DATA_LOADED);
                }

                // Search employee
                case '?' -> {
                    if (command.length() <= 1) {
                        System.out.println("❌ Error: No employee name provided for search.");
                        return;
                    }
                    String searchName = command.substring(1);
                    boolean found = Arrays.asList(readEmployeeData()).contains(searchName);
                    System.out.println(found ? "✅ Employee found!" : "❌ Employee not found.");
                    System.out.println(Constants.DATA_LOADED);
                }

                // Count employees
                case 'c' -> {
                    int count = readEmployeeData().length;
                    System.out.printf("📊 There are %d employee(s) in the file.%n", count);
                    System.out.println(Constants.DATA_LOADED);
                }

                // Update employee
                case 'u' -> {
                    if (command.length() <= 1) {
                        System.out.println("❌ Error: No employee name provided for update.");
                        return;
                    }
                    String[] employees = readEmployeeData();
                    String updateName = command.substring(1);
                    boolean updated = false;

                    for (int i = 0; i < employees.length; i++) {
                        if (employees[i].equals(updateName)) {
                            employees[i] = "Updated";
                            updated = true;
                        }
                    }

                    writeEmployeeData(employees, false);
                    System.out.println(updated ? Constants.DATA_UPDATED : "❌ Employee not found.");
                }

                // Delete employee
                case 'd' -> {
                    if (command.length() <= 1) {
                        System.out.println("❌ Error: No employee name provided for deletion.");
                        return;
                    }
                    List<String> list = new ArrayList<>(Arrays.asList(readEmployeeData()));
                    boolean removed = list.remove(command.substring(1));

                    writeEmployeeData(list.toArray(new String[0]), false);
                    System.out.println(removed ? Constants.DATA_DELETED : "❌ Employee not found.");
                }

                // Invalid command
                default -> {
                    System.out.println("❌ Error: Unknown command '" + command + "'");
                    System.out.println(Constants.USAGE_MESSAGE);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("❌ Error: Employee data file not found: " + Constants.EMPLOYEE_FILE);
        } catch (IOException e) {
            System.out.println("❌ Error accessing the employee file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("⚠️ Unexpected error: " + e.getMessage());
        }
    }
}
// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Reusable file operations
    private static String[] readEmployeeData() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(Constants.EMPLOYEE_FILE)))) {
            String line = reader.readLine();
            if (line == null || line.isEmpty()) return new String[0];
            return line.split(",");
        }
    }

    private static void writeEmployeeData(String[] employees, boolean append) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.EMPLOYEE_FILE, append))) {
            writer.write(String.join(",", employees));
        }
    }

    public static void main(String[] args) {

        // Argument validation
        if (args.length != 1) {
            System.out.println("❌ Invalid number of arguments.");
            System.out.println(Constants.USAGE_MESSAGE);
            return;
        }

        String command = args[0].trim();

        if (command.isEmpty()) {
            System.out.println("❌ Error: Command cannot be empty.");
            System.out.println(Constants.USAGE_MESSAGE);
            return;
        }

        System.out.println(Constants.LOADING_DATA);

        try {
            switch (command.charAt(0)) {

                // List all employees
                case 'l' -> {
                    for (String emp : readEmployeeData()) {
                        System.out.println(emp);
                    }
                    System.out.println(Constants.DATA_LOADED);
                }

                // Show random employee
                case 's' -> {
                    String[] employees = readEmployeeData();
                    if (employees.length == 0) {
                        System.out.println("⚠️ No employees found.");
                    } else {
                        System.out.println(employees[new Random().nextInt(employees.length)]);
                    }
                    System.out.println(Constants.DATA_LOADED);
                }

                // Add new employee
                case '+' -> {
                    if (command.length() <= 1) {
                        System.out.println("❌ Error: No employee name provided to add.");
                        return;
                    }
                    try (BufferedWriter writer = new BufferedWriter(
                            new FileWriter(Constants.EMPLOYEE_FILE, true))) {
                        writer.write(", " + command.substring(1));
                    }
                    System.out.println("✅ Employee added successfully.");
                    System.out.println(Constants.DATA_LOADED);
                }

                // Search employee
                case '?' -> {
                    if (command.length() <= 1) {
                        System.out.println("❌ Error: No employee name provided for search.");
                        return;
                    }
                    String searchName = command.substring(1);
                    boolean found = Arrays.asList(readEmployeeData()).contains(searchName);
                    System.out.println(found ? "✅ Employee found!" : "❌ Employee not found.");
                    System.out.println(Constants.DATA_LOADED);
                }

                // Count employees
                case 'c' -> {
                    int count = readEmployeeData().length;
                    System.out.printf("📊 There are %d employee(s) in the file.%n", count);
                    System.out.println(Constants.DATA_LOADED);
                }

                // Update employee
                case 'u' -> {
                    if (command.length() <= 1) {
                        System.out.println("❌ Error: No employee name provided for update.");
                        return;
                    }
                    String[] employees = readEmployeeData();
                    String updateName = command.substring(1);
                    boolean updated = false;

                    for (int i = 0; i < employees.length; i++) {
                        if (employees[i].equals(updateName)) {
                            employees[i] = "Updated";
                            updated = true;
                        }
                    }

                    writeEmployeeData(employees, false);
                    System.out.println(updated ? Constants.DATA_UPDATED : "❌ Employee not found.");
                }

                // Delete employee
                case 'd' -> {
                    if (command.length() <= 1) {
                        System.out.println("❌ Error: No employee name provided for deletion.");
                        return;
                    }
                    List<String> list = new ArrayList<>(Arrays.asList(readEmployeeData()));
                    boolean removed = list.remove(command.substring(1));

                    writeEmployeeData(list.toArray(new String[0]), false);
                    System.out.println(removed ? Constants.DATA_DELETED : "❌ Employee not found.");
                }

                // Invalid command
                default -> {
                    System.out.println("❌ Error: Unknown command '" + command + "'");
                    System.out.println(Constants.USAGE_MESSAGE);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("❌ Error: Employee data file not found: " + Constants.EMPLOYEE_FILE);
        } catch (IOException e) {
            System.out.println("❌ Error accessing the employee file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("⚠️ Unexpected error: " + e.getMessage());
        }
    }
}
