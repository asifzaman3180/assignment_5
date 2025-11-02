import java.io.*;
import java.util.*;

/**
 * EmployeeManager Program
 * Handles employee data: list, search, add, count, update (not implemented)
 * Uses a file 'employees.txt' to store employee names
 * 
 * Commands:
 * l - List employees
 * s - Search employee
 * + - Add employee
 * ? - Help
 * c - Count total employees
 * u - Update (not implemented)
 */
public class EmployeeManager {

    public static void main(String[] args) {

        // ------------------------------
        // Argument Validation
        // ------------------------------
        if (args.length != Constants.VALID_COMMANDS.length) {
            System.out.println("Error: Invalid number of arguments!");
            System.out.println("Usage: java EmployeeManager l s + ? c u");
            return;
        }

        // Check if all commands are valid
        for (String command : args) {
            boolean isValid = Arrays.stream(Constants.VALID_COMMANDS)
                                    .anyMatch(valid -> valid.equals(command));
            if (!isValid) {
                System.out.println("Error: Invalid command '" + command + "'");
                System.out.println("Valid commands are: " + String.join(", ", Constants.VALID_COMMANDS));
                return;
            }
        }

        // ------------------------------
        // Read employee list from file
        // ------------------------------
        List<String> employeeList = readEmployees();

        // ------------------------------
        // Process each command
        // ------------------------------
        for (String command : args) {
            switch (command) {

                case "+" -> {  // Add new employee
                    String newEmployee = "David Green";  // Example employee
                    employeeList.add(newEmployee);
                    writeEmployees(employeeList);
                    System.out.println(newEmployee + " added to employee list.");
                }

                case "s" -> {  // Search employee
                    String searchQuery = "Alice Johnson";
                    boolean found = employeeList.stream()
                                                .anyMatch(emp -> emp.equalsIgnoreCase(searchQuery));
                    System.out.println(found ? searchQuery + " found." : searchQuery + " not found.");
                }

                case "l" -> System.out.println("Employee List: " + String.join(", ", employeeList));

                case "c" -> System.out.println("Total employees: " + employeeList.size());

                case "?" -> System.out.println("Search help: use 's' command to search an employee");

                case "u" -> System.out.println("Update operation not implemented yet");

                default -> System.out.println("Unknown command: " + command);
            }
        }
    }

    /**
     * Read employee list from file
     * @return List of employee names
     */
    public static List<String> readEmployees() {
        List<String> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.EMPLOYEE_FILE))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                employees = new ArrayList<>(Arrays.asList(line.split(",")));
            }
        } catch (IOException e) {
            System.out.println("Error reading " + Constants.EMPLOYEE_FILE + ": " + e.getMessage());
        }
        return employees;
    }

    /**
     * Write employee list to file
     * @param employees List of employee names
     */
    public static void writeEmployees(List<String> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE))) {
            writer.write(String.join(",", employees));
        } catch (IOException e) {
            System.out.println("Error writing " + Constants.EMPLOYEE_FILE + ": " + e.getMessage());
        }
    }
}
