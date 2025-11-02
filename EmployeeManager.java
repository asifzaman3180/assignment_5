// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    // ✅ Read all employees from file
    private static List<String> readEmployees() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.EMPLOYEE_FILE))) {
            String line = reader.readLine();
            if (line == null || line.isEmpty()) {
                return new ArrayList<>();
            }
            return new ArrayList<>(Arrays.asList(line.split(",")));
        }
    }

    // ✅ Write all employees to file
    private static void writeEmployees(List<String> employees) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE))) {
            writer.write(String.join(",", employees));
        }
    }

    // ✅ Append a single employee
    private static void appendEmployee(String employeeName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE, true))) {
            writer.write(", " + employeeName);
        }
    }

    public static void main(String[] args) {

        // ✅ Validate argument count
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
            if (command.equals("l")) { // ✅ List all employees
                System.out.println(Constants.LOADING_MSG);
                readEmployees().forEach(System.out::println);
                System.out.println(Constants.DATA_LOADED_MSG);

            } else if (command.equals("s")) { // ✅ Show random employee
                System.out.println(Constants.LOADING_MSG);
                List<String> employees = readEmployees();
                if (employees.isEmpty()) {
                    System.out.println(Constants.EMPLOYEE_NOT_FOUND_MSG);
                } else {
                    System.out.println(employees.get(new Random().nextInt(employees.size())));
                }
                System.out.println(Constants.DATA_LOADED_MSG);

            } else if (command.startsWith("+")) { // ✅ Add new employee
                System.out.println(Constants.LOADING_MSG);
                appendEmployee(command.substring(1).trim());
                System.out.println("Employee added successfully.");
                System.out.println(Constants.DATA_LOADED_MSG);

            } else if (command.startsWith("?")) { // ✅ Search for employee
                System.out.println(Constants.LOADING_MSG);
                boolean found = readEmployees().stream()
                        .anyMatch(emp -> emp.trim().equalsIgnoreCase(command.substring(1).trim()));
                System.out.println(found ? Constants.EMPLOYEE_FOUND_MSG : Constants.EMPLOYEE_NOT_FOUND_MSG);
                System.out.println(Constants.DATA_LOADED_MSG);

            } else if (command.equals("c")) { // ✅ Count employees
                System.out.println(Constants.LOADING_MSG);
                System.out.println(readEmployees().size() + " employee(s) found.");
                System.out.println(Constants.DATA_LOADED_MSG);

            } else if (command.startsWith("u")) { // ✅ Update employee
                System.out.println(Constants.LOADING_MSG);
                List<String> employees = readEmployees();
                String updateName = command.substring(1).trim();

                if (employees.contains(updateName)) {
                    Collections.replaceAll(employees, updateName, "Updated");
                    writeEmployees(employees);
                    System.out.println(Constants.DATA_UPDATED_MSG);
                } else {
                    System.out.println(Constants.EMPLOYEE_NOT_FOUND_MSG);
                }

            } else if (command.startsWith("d")) { // ✅ Delete employee
                System.out.println(Constants.LOADING_MSG);
                List<String> employees = readEmployees();
                if (employees.remove(command.substring(1).trim())) {
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
