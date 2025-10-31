import java.io.*;
import java.util.*;

/**
 * EmployeeManager is a simple CLI tool to manage employee records stored in a text file.
 * Supports listing, adding, searching, counting, updating, and deleting employees.
 */
public class EmployeeManager {

    private static final String USAGE = """
        Usage: java EmployeeManager <command>
        Commands:
        l       - List all employees
        s       - Show all employees and pick one at random
        +<name> - Add a new employee
        ?<name> - Search for an employee
        c       - Count employees and total characters
        u<name> - Update an employee
        d<name> - Delete an employee
        """;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error: Please provide exactly one argument.");
            System.out.println(USAGE);
            return;
        }

        String command = args[0];

        // Determine operation based on the first character of the command
        switch (command.charAt(0)) {
            case 'l' -> listEmployees();
            case 's' -> showRandomEmployee();
            case '+' -> addEmployee(command);
            case '?' -> searchEmployee(command);
            case 'c' -> countEmployees();
            case 'u' -> updateEmployee(command);
            case 'd' -> deleteEmployee(command);
            default -> System.out.println("Error: Unsupported command '" + command + "'.\n" + USAGE);
        }
    }

    // Lists all employees
    private static void listEmployees() {
        System.out.println("Loading data ...");
        List<String> employees = readEmployees();
        employees.forEach(System.out::println);
        System.out.println("Data Loaded.");
    }

    // Shows all employees and picks one at random
    private static void showRandomEmployee() {
        System.out.println("Loading data ...");
        List<String> employees = readEmployees();
        if (!employees.isEmpty()) {
            System.out.println(String.join(",", employees));
            System.out.println("Randomly selected: " + employees.get(new Random().nextInt(employees.size())));
        }
        System.out.println("Data Loaded.");
    }

    // Adds a new employee to the file
    private static void addEmployee(String command) {
        System.out.println("Loading data ...");
        String newEmployeeName = command.substring(1); // Remove '+' command
        appendEmployee(newEmployeeName);
        System.out.println("Data Loaded.");
    }

    // Searches for an employee by name
    private static void searchEmployee(String command) {
        System.out.println("Loading data ...");
        List<String> employees = readEmployees();
        String searchName = command.substring(1);
        System.out.println(employees.contains(searchName)
                ? "Employee found!"
                : "Employee not found.");
        System.out.println("Data Loaded.");
    }

    // Counts the number of employees and total characters
    private static void countEmployees() {
        System.out.println("Loading data ...");
        List<String> employees = readEmployees();
        System.out.println(employees.size() + " employee(s), total characters: " +
                employees.stream().mapToInt(String::length).sum());
        System.out.println("Data Loaded.");
    }

    // Updates an employee's name to "Updated"
    private static void updateEmployee(String command) {
        System.out.println("Loading data ...");
        List<String> employees = readEmployees();
        String target = command.substring(1);
        if (employees.contains(target)) {
            employees.replaceAll(e -> e.equals(target) ? "Updated" : e);
            writeEmployees(employees);
            System.out.println("Data Updated.");
        } else {
            System.out.println("Error: Employee '" + target + "' not found.");
        }
    }

    // Deletes an employee by name
    private static void deleteEmployee(String command) {
        System.out.println("Loading data ...");
        List<String> employees = readEmployees();
        String target = command.substring(1);
        if (employees.remove(target)) {
            writeEmployees(employees);
            System.out.println("Data Deleted.");
        } else {
            System.out.println("Error: Employee '" + target + "' not found.");
        }
    }

    // Reads employees from the file and trims whitespace
    private static List<String> readEmployees() {
        List<String> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.EMPLOYEE_FILE))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                Arrays.stream(line.split(","))
                        .map(String::trim)
                        .forEach(employees::add);
            }
        } catch (IOException e) {
            System.out.println("Error reading employees file: " + e.getMessage());
        }
        return employees;
    }

    // Writes the list of employees to the file
    private static void writeEmployees(List<String> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE))) {
            writer.write(String.join(",", employees));
        } catch (IOException e) {
            System.out.println("Error writing employees file: " + e.getMessage());
        }
    }

    // Appends a new employee to the file
    private static void appendEmployee(String employee) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE, true))) {
            writer.write(", " + employee);
        } catch (IOException e) {
            System.out.println("Error appending to employees file: " + e.getMessage());
        }
    }
}
