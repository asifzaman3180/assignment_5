// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * EmployeeManager manages a list of employees stored in a text file.
 * Supported operations:
 * - l : List all employees
 * - s : Show a random employee
 * - +name : Add a new employee
 * - ?name : Search for an employee
 * - c : Count words and characters
 * - uName : Update an employee to "Updated"
 * - dName : Delete an employee
 *
 * All file I/O operations are refactored into reusable methods.
 * Invalid or unsupported arguments are handled gracefully.
 */
public class EmployeeManager {

    // Set of simple valid commands
    private static final Set<String> VALID_COMMANDS = Set.of("l", "s", "c");

    public static void main(String[] args) {

        // Task 2: Argument validation
        if (args.length == 0) {
            System.out.println("Error: No arguments provided!");
            System.out.println(Constants.USAGE_MESSAGE);
            return;
        }

        String command = args[0];

        // Task 9: Invalid argument handling
        if (!isValidCommand(command)) {
            System.out.println("Error: Unsupported or invalid argument: " + command);
            System.out.println(Constants.USAGE_MESSAGE);
            return;
        }

        try {
            if (command.equals("l")) {
                // Task 1,7: List all employees
                System.out.println("Loading data ...");
                String[] employees = readEmployees();
                for (String employee : employees) {
                    System.out.println(employee);
                }
                System.out.println("Data Loaded.");
            } 
            else if (command.equals("s")) {
                // Task 1,7: Show a random employee
                System.out.println("Loading data ...");
                String[] employees = readEmployees();
                System.out.println(String.join(",", employees));
                Random random = new Random();
                System.out.println(employees[random.nextInt(employees.length)]);
                System.out.println("Data Loaded.");
            } 
            else if (command.startsWith("+")) {
                // Task 4,5: Add a new employee
                System.out.println("Loading data ...");
                appendEmployee(command.substring(1));
                System.out.println("Data Loaded.");
            } 
            else if (command.startsWith("?")) {
                // Task 4,6,7: Search for an employee
                System.out.println("Loading data ...");
                String searchName = command.substring(1);
                boolean found = Arrays.stream(readEmployees()).anyMatch(emp -> emp.equals(searchName));
                System.out.println(found ? "Employee found!" : "Employee not found!");
                System.out.println("Data Loaded.");
            } 
            else if (command.equals("c")) {
                // Task 8: Count words and characters
                System.out.println("Loading data ...");
                String line = String.join(",", readEmployees()).trim();
                int wordCount = line.isEmpty() ? 0 : line.split("\\s+").length;
                System.out.println(wordCount + " word(s), " + line.length() + " character(s)");
                System.out.println("Data Loaded.");
            }
            else if (command.startsWith("u")) {
                // Task 4,6,7,9: Update employee
                System.out.println("Loading data ...");
                String employeeToUpdate = command.substring(1);
                String[] employees = readEmployees();
                if (!Arrays.asList(employees).contains(employeeToUpdate)) {
                    System.out.println("Employee not found. No update performed.");
                } else {
                    employees = Arrays.stream(employees)
                            .map(emp -> emp.equals(employeeToUpdate) ? "Updated" : emp)
                            .toArray(String[]::new);
                    writeEmployees(employees);
                    System.out.println("Data Updated.");
                }
            }
            else if (command.startsWith("d")) {
                // Task 4,6,7,9: Delete employee
                System.out.println("Loading data ...");
                String employeeToDelete = command.substring(1);
                List<String> employeesList = new ArrayList<>(Arrays.asList(readEmployees()));
                boolean removed = employeesList.removeIf(emp -> emp.equals(employeeToDelete));
                if (removed) {
                    writeEmployees(employeesList.toArray(new String[0]));
                    System.out.println("Data Deleted.");
                } else {
                    System.out.println("Employee not found. No deletion performed.");
                }
            }
            else {
                // Extra safety
                System.out.println("Error: Unknown command encountered.");
                System.out.println(Constants.USAGE_MESSAGE);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Employee file not found.");
        } catch (IOException e) {
            System.out.println("Error accessing employee file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    /**
     * Reads employees from the employee file
     * @return Array of employee names
     * @throws IOException if file cannot be read
     */
    private static String[] readEmployees() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(Constants.EMPLOYEE_FILE));
        String line = reader.readLine();
        reader.close();
        if (line == null || line.trim().isEmpty()) return new String[0];
        return line.split(",");
    }

    /**
     * Writes the employee array to the employee file
     * @param employees Array of employee names
     * @throws IOException if file cannot be written
     */
    private static void writeEmployees(String[] employees) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE));
        writer.write(String.join(",", employees));
        writer.close();
    }

    /**
     * Appends a new employee to the employee file
     * @param employeeName Name of the new employee
     * @throws IOException if file cannot be written
     */
    private static void appendEmployee(String employeeName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE, true));
        if (new File(Constants.EMPLOYEE_FILE).length() != 0) {
            writer.write(", " + employeeName);
        } else {
            writer.write(employeeName);
        }
        writer.close();
    }

    /**
     * Validates if the command provided is supported
     * @param command Command string
     * @return true if valid, false otherwise
     */
    private static boolean isValidCommand(String command) {
        if (VALID_COMMANDS.contains(command)) return true;
        return command.startsWith("+") || command.startsWith("?") || command.startsWith("u") || command.startsWith("d");
    }
}