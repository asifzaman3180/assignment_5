// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeManager {

    private static final Set<String> VALID_COMMANDS = Set.of("l", "s", "c");

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: No arguments provided!");
            System.out.println(Constants.USAGE_MESSAGE);
            return;
        }

        String command = args[0];

        if (!isValidCommand(command)) {
            System.out.println("Error: Invalid argument provided: " + command);
            System.out.println(Constants.USAGE_MESSAGE);
            return;
        }

        try {
            if (command.equals("l")) {
                // List all employees
                System.out.println("Loading data ...");
                String[] employees = readEmployees();
                for (String employee : employees) {
                    System.out.println(employee);
                }
                System.out.println("Data Loaded.");
            } 
            else if (command.equals("s")) {
                // Show a random employee
                System.out.println("Loading data ...");
                String[] employees = readEmployees();
                System.out.println(String.join(",", employees));
                Random random = new Random();
                System.out.println(employees[random.nextInt(employees.length)]);
                System.out.println("Data Loaded.");
            } 
            else if (command.startsWith("+")) {
                // Add a new employee
                System.out.println("Loading data ...");
                appendEmployee(command.substring(1));
                System.out.println("Data Loaded.");
            } 
            else if (command.startsWith("?")) {
                // Search for an employee
                System.out.println("Loading data ...");
                String searchName = command.substring(1);
                boolean found = Arrays.stream(readEmployees()).anyMatch(emp -> emp.equals(searchName));
                System.out.println(found ? "Employee found!" : "Employee not found!");
                System.out.println("Data Loaded.");
            } 
            else if (command.equals("c")) {
                // Count words and characters
                System.out.println("Loading data ...");
                String line = String.join(",", readEmployees());
                int wordCount = line.trim().isEmpty() ? 0 : line.split("\\s+").length;
                System.out.println(wordCount + " word(s) found, total characters: " + line.length());
                System.out.println("Data Loaded.");
            }
            else if (command.startsWith("u")) {
                // Update employee
                System.out.println("Loading data ...");
                String employeeToUpdate = command.substring(1);
                String[] employees = readEmployees();
                employees = Arrays.stream(employees)
                        .map(emp -> emp.equals(employeeToUpdate) ? "Updated" : emp)
                        .toArray(String[]::new);
                writeEmployees(employees);
                System.out.println("Data Updated.");
            }
            else if (command.startsWith("d")) {
                // Delete employee
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
        } catch (FileNotFoundException e) {
            System.out.println("Error: Employee file not found.");
        } catch (IOException e) {
            System.out.println("Error accessing employee file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private static String[] readEmployees() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(Constants.EMPLOYEE_FILE));
        String line = reader.readLine();
        reader.close();
        if (line == null || line.trim().isEmpty()) return new String[0];
        return line.split(",");
    }

    private static void writeEmployees(String[] employees) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE));
        writer.write(String.join(",", employees));
        writer.close();
    }

    private static void appendEmployee(String employeeName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE, true));
        if (new File(Constants.EMPLOYEE_FILE).length() != 0) {
            writer.write(", " + employeeName);
        } else {
            writer.write(employeeName);
        }
        writer.close();
    }

    private static boolean isValidCommand(String command) {
        if (VALID_COMMANDS.contains(command)) return true;
        return command.startsWith("+") || command.startsWith("?") || command.startsWith("u") || command.startsWith("d");
    }
}