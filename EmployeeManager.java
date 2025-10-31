import java.io.*;
import java.util.*;

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

    private static void listEmployees() {
        System.out.println("Loading data ...");
        List<String> employees = readEmployees();
        if (employees != null) employees.forEach(System.out::println);
        System.out.println("Data Loaded.");
    }

    private static void showRandomEmployee() {
        System.out.println("Loading data ...");
        List<String> employees = readEmployees();
        if (employees != null && !employees.isEmpty()) {
            System.out.println(String.join(",", employees));
            System.out.println(employees.get(new Random().nextInt(employees.size())));
        }
        System.out.println("Data Loaded.");
    }

    private static void addEmployee(String command) {
        System.out.println("Loading data ...");
        appendEmployee(command.substring(1));
        System.out.println("Data Loaded.");
    }

    private static void searchEmployee(String command) {
        System.out.println("Loading data ...");
        List<String> employees = readEmployees();
        if (employees != null) {
            String searchName = command.substring(1);
            System.out.println(employees.contains(searchName)
                    ? "Employee found!"
                    : "Employee not found.");
        }
        System.out.println("Data Loaded.");
    }

    private static void countEmployees() {
        System.out.println("Loading data ...");
        List<String> employees = readEmployees();
        if (employees != null && !employees.isEmpty()) {
            System.out.println(employees.size() + " word(s) found, total characters: " +
                    employees.stream().mapToInt(String::length).sum() + ".");
        }
        System.out.println("Data Loaded.");
    }

    private static void updateEmployee(String command) {
        System.out.println("Loading data ...");
        List<String> employees = readEmployees();
        if (employees != null) {
            String target = command.substring(1);
            if (employees.contains(target)) {
                employees.replaceAll(e -> e.equals(target) ? "Updated" : e);
                writeEmployees(employees);
                System.out.println("Data Updated.");
            } else {
                System.out.println("Error: Employee '" + target + "' not found.");
            }
        }
    }

    private static void deleteEmployee(String command) {
        System.out.println("Loading data ...");
        List<String> employees = readEmployees();
        if (employees != null) {
            String target = command.substring(1);
            if (employees.remove(target)) {
                writeEmployees(employees);
                System.out.println("Data Deleted.");
            } else {
                System.out.println("Error: Employee '" + target + "' not found.");
            }
        }
    }

    private static List<String> readEmployees() {
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.EMPLOYEE_FILE))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                return new ArrayList<>(Arrays.asList(line.split(",")));
            }
        } catch (Exception e) {}
        return new ArrayList<>();
    }

    private static void writeEmployees(List<String> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE))) {
            writer.write(String.join(",", employees));
        } catch (Exception e) {}
    }

    private static void appendEmployee(String employee) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE, true))) {
            writer.write(", " + employee.substring(1));
        } catch (Exception e) {}
    }
}
