import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        // ------------------------------
        // Argument Count Validation
        // ------------------------------
        if (args.length != Constants.VALID_COMMANDS.length) {
            System.out.println("Error: Invalid number of arguments!");
            System.out.println("Usage: java EmployeeManager l s + ? c u");
            return;
        }

        // ------------------------------
        // Invalid Command Validation
        // ------------------------------
        for (String command : args) {
            boolean isValid = Arrays.stream(Constants.VALID_COMMANDS)
                                    .anyMatch(valid -> valid.equals(command));
            if (!isValid) {
                System.out.println("Error: Invalid command '" + command + "'");
                System.out.println("Valid commands are: " + String.join(", ", Constants.VALID_COMMANDS));
                return;  // terminate program on invalid command
            }
        }

        // ------------------------------
        // Read employee list
        // ------------------------------
        List<String> employeeList = readEmployees();

        // ------------------------------
        // Handle commands
        // ------------------------------
        for (String command : args) {
            switch (command) {

                case "+" -> {
                    String newEmployee = "David Green";
                    employeeList.add(newEmployee);
                    writeEmployees(employeeList);
                    System.out.println(newEmployee + " added to employee list.");
                }

                case "s" -> {
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

    // ------------------------------
    // File operations
    // ------------------------------
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

    public static void writeEmployees(List<String> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE))) {
            writer.write(String.join(",", employees));
        } catch (IOException e) {
            System.out.println("Error writing " + Constants.EMPLOYEE_FILE + ": " + e.getMessage());
        }
    }
}
