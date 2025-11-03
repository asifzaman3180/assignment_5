// EmployeeManager.java – Task #10
import java.io.*;
import java.util.*;

public class EmployeeManager {

    /**
     * Reads the list of employees from the file.
     *
     * @return Array of employee names
     * @throws IOException if the file cannot be read
     */
    private static String[] readEmployees() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(Constants.EMPLOYEE_FILE));
        String line = reader.readLine();
        reader.close();
        return line.split(",");
    }

    /**
     * Writes the list of employees to the file.
     *
     * @param employees Array of employee names
     * @throws IOException if the file cannot be written
     */
    private static void writeEmployees(String[] employees) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE));
        writer.write(String.join(",", employees));
        writer.close();
    }

    /**
     * Entry point of the EmployeeManager program.
     * Supports operations: list, search, add, count, update, delete.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: No arguments provided.");
            System.out.println("Supported arguments: l, s, +<name>, ?<name>, c, u<name>, d<name>");
            return;
        }

        String operation = args[0];

        try {
            switch (operation.charAt(0)) {
                case 'l': // List all employees
                    System.out.println("Loading data ...");
                    for (String employee : readEmployees()) {
                        System.out.println(employee);
                    }
                    System.out.println("Data Loaded.");
                    break;

                case 's': // Show a random employee
                    System.out.println("Loading data ...");
                    String[] employees = readEmployees();
                    System.out.println(employees[new Random().nextInt(employees.length)]);
                    System.out.println("Data Loaded.");
                    break;

                case '+': // Add a new employee
                    System.out.println("Loading data ...");
                    addEmployee(operation.substring(1));
                    System.out.println("Data Loaded.");
                    break;

                case '?': // Search for an employee
                    System.out.println("Loading data ...");
                    searchEmployee(operation.substring(1));
                    System.out.println("Data Loaded.");
                    break;

                case 'c': // Count total employees
                    System.out.println("Loading data ...");
                    System.out.println("Total employees: " + readEmployees().length);
                    System.out.println("Data Loaded.");
                    break;

                case 'u': // Update an employee's name
                    System.out.println("Loading data ...");
                    updateEmployee(operation.substring(1));
                    break;

                case 'd': // Delete an employee
                    System.out.println("Loading data ...");
                    deleteEmployee(operation.substring(1));
                    break;

                default: // Invalid argument
                    System.out.println("Error: Unsupported argument '" + operation + "'");
                    System.out.println("Supported arguments: l, s, +<name>, ?<name>, c, u<name>, d<name>");
                    break;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Adds a new employee to the file
    private static void addEmployee(String employeeName) throws IOException {
        if (employeeName.isEmpty()) {
            System.out.println("Error: Please provide a name to add.");
            return;
        }
        List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployees()));
        employeeList.add(employeeName);
        writeEmployees(employeeList.toArray(new String[0]));
    }

    // Searches for an employee in the file
    private static void searchEmployee(String employeeName) throws IOException {
        if (employeeName.isEmpty()) {
            System.out.println("Error: Please provide a name to search.");
            return;
        }
        boolean found = Arrays.asList(readEmployees()).contains(employeeName);
        System.out.println(found ? "Employee found!" : "Employee not found.");
    }

    // Updates an employee's name to "Updated"
    private static void updateEmployee(String employeeName) throws IOException {
        if (employeeName.isEmpty()) {
            System.out.println("Error: Please provide a name to update.");
            return;
        }
        String[] employees = readEmployees();
        boolean updated = false;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].equals(employeeName)) {
                employees[i] = "Updated";
                updated = true;
            }
        }
        writeEmployees(employees);
        System.out.println(updated ? "Data Updated." : "Employee not found.");
    }

    // Deletes an employee from the file
    private static void deleteEmployee(String employeeName) throws IOException {
        if (employeeName.isEmpty()) {
            System.out.println("Error: Please provide a name to delete.");
            return;
        }
        List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployees()));
        if (employeeList.remove(employeeName)) {
            writeEmployees(employeeList.toArray(new String[0]));
            System.out.println("Data Deleted.");
        } else {
            System.out.println("Employee not found.");
        }
    }
}
