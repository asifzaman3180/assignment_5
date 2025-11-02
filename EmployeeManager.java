// File Name: EmployeeManager.java
// Author: [Your Name]
// Description: A simple command-line employee management system that supports
// listing, searching, adding, updating, deleting, and counting employee records.

import java.io.*;
import java.util.*;

/**
 * EmployeeManager class handles various employee management operations such as
 * listing, searching, adding, updating, and deleting employees from a text file.
 */
public class EmployeeManager {

    /**
     * Reads employee data from the employee file.
     * 
     * @return An array of employee names.
     * @throws IOException if the file cannot be read.
     */
    private static String[] readEmployeeData() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(Constants.EMPLOYEE_FILE)))) {
            String line = reader.readLine();
            if (line == null || line.isEmpty()) return new String[0];
            return line.split(",");
        }
    }

    /**
     * Writes employee data to the employee file.
     * 
     * @param employees An array of employee names to write.
     * @param append    Whether to append or overwrite the file.
     * @throws IOException if the file cannot be written.
     */
    private static void writeEmployeeData(String[] employees, boolean append) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.EMPLOYEE_FILE, append))) {
            writer.write(String.join(",", employees));
        }
    }

    /**
     * Main entry point for EmployeeManager.
     * Handles argument validation and dispatches to the appropriate operation.
     * 
     * @param args Command-line arguments representing the operation to perform.
     */
    public static void main(String[] args) {

        // Validate command-line arguments
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

                /** -------------------- LIST EMPLOYEES -------------------- */
                case 'l' -> {
                    String[] employees = readEmployeeData();
                    for (String employee : employees) {
                        System.out.println(employee);
                    }
                    System.out.println(Constants.DATA_LOADED);
                }

                /** -------------------- RANDOM EMPLOYEE -------------------- */
                case 's' -> {
                    String[] employees = readEmployeeData();
                    if (employees.length == 0) {
                        System.out.println("⚠️ No employees found.");
                    } else {
                        int randomIndex = new Random().nextInt(employees.length);
                        System.out.println(employees[randomIndex]);
                    }
                    System.out.println(Constants.DATA_LOADED);
                }

                /** -------------------- ADD EMPLOYEE -------------------- */
                case '+' -> {
                    if (command.length() <= 1) {
                        System.out.println("❌ Error: No employee name provided to add.");
                        return;
                    }
                    String newEmployee = command.substring(1);
                    try (BufferedWriter writer = new BufferedWriter(
                            new FileWriter(Constants.EMPLOYEE_FILE, true))) {
                        writer.write(", " + newEmployee);
                    }
                    System.out.println("✅ Employee added successfully.");
                    System.out.println(Constants.DATA_LOADED);
                }

                /** -------------------- SEARCH EMPLOYEE -------------------- */
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

                /** -------------------- COUNT EMPLOYEES -------------------- */
                case 'c' -> {
                    int employeeCount = readEmployeeData().length;
                    System.out.printf("📊 There are %d employee(s) in the file.%n", employeeCount);
                    System.out.println(Constants.DATA_LOADED);
                }

                /** -------------------- UPDATE EMPLOYEE -------------------- */
                case 'u' -> {
                    if (command.length() <= 1) {
                        System.out.println("❌ Error: No employee name provided for update.");
                        return;
                    }
                    String updateName = command.substring(1);
                    String[] employees = readEmployeeData();
                    boolean isUpdated = false;

                    for (int i = 0; i < employees.length; i++) {
                        if (employees[i].equals(updateName)) {
                            employees[i] = "Updated";
                            isUpdated = true;
                        }
                    }

                    writeEmployeeData(employees, false);
                    System.out.println(isUpdated ? Constants.DATA_UPDATED : "❌ Employee not found.");
                }

                /** -------------------- DELETE EMPLOYEE -------------------- */
                case 'd' -> {
                    if (command.length() <= 1) {
                        System.out.println("❌ Error: No employee name provided for deletion.");
                        return;
                    }
                    String deleteName = command.substring(1);
                    List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployeeData()));
                    boolean isRemoved = employeeList.remove(deleteName);

                    writeEmployeeData(employeeList.toArray(new String[0]), false);
                    System.out.println(isRemoved ? Constants.DATA_DELETED : "❌ Employee not found.");
                }

                /** -------------------- INVALID COMMAND -------------------- */
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
