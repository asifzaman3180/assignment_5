// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    // ✅ Utility method to read all employees from file
    private static List<String> readEmployees() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(Constants.EMPLOYEE_FILE)))) {
            String line = reader.readLine();
            if (line == null || line.isEmpty()) {
                return new ArrayList<>();
            }
            String[] employees = line.split(",");
            List<String> employeeList = new ArrayList<>();
            for (String emp : employees) {
                employeeList.add(emp.trim());
            }
            return employeeList;
        }
    }

    // ✅ Utility method to write all employees back to file
    private static void writeEmployees(List<String> employees) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE))) {
            writer.write(String.join(", ", employees));
        }
    }

    // ✅ Utility method to append new employee
    private static void appendEmployee(String employeeName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE, true))) {
            writer.write(", " + employeeName);
        }
    }

    public static void main(String[] args) {

        // ✅ Argument validation
        if (args.length != 1) {
            System.out.println("Usage:");
            System.out.println("  java EmployeeManager l         // List all employees");
            System.out.println("  java EmployeeManager s         // Show a random employee");
            System.out.println("  java EmployeeManager +Name     // Add a new employee");
            System.out.println("  java EmployeeManager ?Name     // Search for an employee");
            System.out.println("  java EmployeeManager c         // Count employees");
            System.out.println("  java EmployeeManager uName     // Update employee to 'Updated'");
            System.out.println("  java EmployeeManager dName     // Delete an employee");
            return;
        }

        String command = args[0];

        try {
            if (command.equals("l")) { // ✅ List employees
                System.out.println(Constants.LOADING_MSG);
                List<String> employees = readEmployees();
                employees.forEach(System.out::println);
                System.out.println(Constants.DATA_LOADED_MSG);

            } else if (command.equals("s")) { // ✅ Show random employee
                System.out.println(Constants.LOADING_MSG);
                List<String> employees = readEmployees();
                if (employees.isEmpty()) {
                    System.out.println(Constants.EMPLOYEE_NOT_FOUND_MSG);
                } else {
                    Random random = new Random();
                    System.out.println(employees.get(random.nextInt(employees.size())));
                }
                System.out.println(Constants.DATA_LOADED_MSG);

            } else if (command.startsWith("+")) { // ✅ Add new employee
                System.out.println(Constants.LOADING_MSG);
                String newEmployee = command.substring(1).trim();
                appendEmployee(newEmployee);
                System.out.println("Employee added successfully.");
                System.out.println(Constants.DATA_LOADED_MSG);

            } else if (command.startsWith("?")) { // ✅ Search employee
                System.out.println(Constants.LOADING_MSG);
                List<String> employees = readEmployees();
                String searchName = command.substring(1).trim();
                boolean found = employees.stream().anyMatch(emp -> emp.equalsIgnoreCase(searchName));
                System.out.println(found ? Constants.EMPLOYEE_FOUND_MSG : Constants.EMPLOYEE_NOT_FOUND_MSG);
                System.out.println(Constants.DATA_LOADED_MSG);

            } else if (command.equals("c")) { // ✅ Count employees
                System.out.println(Constants.LOADING_MSG);
                List<String> employees = readEmployees();
                System.out.println(employees.size() + " employee(s) found.");
                System.out.println(Constants.DATA_LOADED_MSG);

            } else if (command.startsWith("u")) { // ✅ Update employee
                System.out.println(Constants.LOADING_MSG);
                List<String> employees = readEmployees();
                String updateName = command.substring(1).trim();

                boolean updated = false;
                for (int i = 0; i < employees.size(); i++) {
                    if (employees.get(i).equalsIgnoreCase(updateName)) {
                        employees.set(i, "Updated");
                        updated = true;
                    }
                }

                if (updated) {
                    writeEmployees(employees);
                    System.out.println(Constants.DATA_UPDATED_MSG);
                } else {
                    System.out.println(Constants.EMPLOYEE_NOT_FOUND_MSG);
                }

            } else if (command.startsWith("d")) { // ✅ Delete employee
                System.out.println(Constants.LOADING_MSG);
                List<String> employees = readEmployees();
                String deleteName = command.substring(1).trim();

                boolean removed = employees.removeIf(emp -> emp.equalsIgnoreCase(deleteName));
                if (removed) {
                    writeEmployees(employees);
                    System.out.println(Constants.DATA_DELETED_MSG);
                } else {
                    System.out.println(Constants.EMPLOYEE_NOT_FOUND_MSG);
                }

            } else {
                System.out.println("Invalid command! Use: l, s, +Name, ?Name, c, uName, dName");
            }

        } catch (IOException ex) {
            System.out.println("An error occurred while processing the file:");
            ex.printStackTrace();
        }
    }
}
