// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Reusable file operations
    private static String[] readEmployeeData() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(Constants.EMPLOYEE_FILE)));
        String line = reader.readLine();
        reader.close();
        return line.split(",");
    }

    private static void writeEmployeeData(String[] employees, boolean append) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.EMPLOYEE_FILE, append));
        writer.write(String.join(",", employees));
        writer.close();
    }

    public static void main(String[] args) {

        // Argument validation
        if (args.length != 1) {
            System.out.println(Constants.USAGE_MESSAGE);
            return;
        }

        String command = args[0];
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
                    System.out.println(employees[new Random().nextInt(employees.length)]);
                    System.out.println(Constants.DATA_LOADED);
                }

                // Add new employee
                case '+' -> {
                    try (BufferedWriter writer = new BufferedWriter(
                            new FileWriter(Constants.EMPLOYEE_FILE, true))) {
                        writer.write(", " + command.substring(1));
                    }
                    System.out.println(Constants.DATA_LOADED);
                }

                // Search employee
                case '?' -> {
                    String searchName = command.substring(1);
                    boolean found = Arrays.asList(readEmployeeData()).contains(searchName);
                    System.out.println(found ? "Employee found!" : "Employee not found.");
                    System.out.println(Constants.DATA_LOADED);
                }

                // Count employees
                case 'c' -> {
                    int count = readEmployeeData().length;
                    System.out.println(count + " employee(s) found.");
                    System.out.println(Constants.DATA_LOADED);
                }

                // Update employee
                case 'u' -> {
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
                    System.out.println(updated ? Constants.DATA_UPDATED : "Employee not found.");
                }

                // Delete employee
                case 'd' -> {
                    List<String> list = new ArrayList<>(Arrays.asList(readEmployeeData()));
                    boolean removed = list.remove(command.substring(1));

                    writeEmployeeData(list.toArray(new String[0]), false);
                    System.out.println(removed ? Constants.DATA_DELETED : "Employee not found.");
                }

                // Invalid command
                default -> System.out.println(Constants.ERROR_UNKNOWN + " '" + command + "'");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
