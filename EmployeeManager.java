import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        // Argument Validation
        if (args.length != 6) {
            System.out.println("Error: Invalid number of arguments!");
            System.out.println("Usage: java EmployeeManager l s + ? c u");
            return;
        }

        for (String command : args) {
            boolean isValidCommand = false;
            for (String valid : Constants.VALID_COMMANDS) {
                if (command.equals(valid)) {
                    isValidCommand = true;
                    break;
                }
            }
            if (!isValidCommand) {
                System.out.println("Error: Invalid command '" + command + "'");
                return;
            }
        }

        // Read employee list
        List<String> employeeList = readEmployees();

        // Handle commands
        for (String command : args) {

            switch (command) {

                case "+" -> {  // Add employee
                    String newEmployee = "David Green";
                    employeeList.add(newEmployee);
                    writeEmployees(employeeList);
                    System.out.println(newEmployee + " added to employee list.");
                }

                case "s" -> {  // Search employee
                    String searchQuery = "Alice Johnson";
                    boolean found = employeeList.stream().anyMatch(emp -> emp.equalsIgnoreCase(searchQuery));
                    System.out.println(found ? searchQuery + " found." : searchQuery + " not found.");
                }

                case "l" -> {  // List employees
                    System.out.println("Employee List: " + String.join(", ", employeeList));
                }

                case "c" -> {  // Count employees (Task #8)
                    System.out.println("Total employees: " + employeeList.size());
                }

                default -> System.out.println("Command " + command + " not handled.");
            }
        }
    }

    // File operations
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
