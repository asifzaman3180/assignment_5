// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

// Main class to manage employee data from a file (employees.txt).
public class EmployeeManager {

    // Program entry point: handles command-line arguments and routes operations.
    public static void main(String[] args) {

        // Validate command-line argument count (must be exactly one).
        if (args.length != 1) {
            System.out.println(Constants.ERROR_INVALID_ARGS);
            System.out.println(Constants.USAGE_INFO);
            return;
        }

        String command = args[0];
        System.out.println(Constants.LOADING_DATA);

        try {
            // Check command and execute corresponding operation.
            if (command.equals("l")) {
                listEmployees();
            } 
            else if (command.equals("s")) {
                showRandomEmployee();
            } 
            // Add Employee: validate for empty name before adding.
            else if (command.startsWith("+")) {
                String employeeName = command.substring(1).trim();
                if (employeeName.isEmpty()) {
                    System.out.println("Error: Employee name cannot be empty for 'add' operation.");
                    System.out.println(Constants.USAGE_INFO);
                } else {
                    addEmployee(employeeName);
                }
            } 
            // Search Employee: validate for empty name before searching.
            else if (command.startsWith("?")) {
                String employeeName = command.substring(1).trim();
                if (employeeName.isEmpty()) {
                    System.out.println("Error: Employee name cannot be empty for 'search' operation.");
                    System.out.println(Constants.USAGE_INFO);
                } else {
                    searchEmployee(employeeName);
                }
            } 
            else if (command.equals("c")) {
                // Streamlined count operation.
                countEmployees();
            } 
            // Update Employee: validate for empty name before updating.
            else if (command.startsWith("u")) {
                String nameToUpdate = command.substring(1).trim();
                if (nameToUpdate.isEmpty()) {
                    System.out.println("Error: Employee name cannot be empty for 'update' operation.");
                    System.out.println(Constants.USAGE_INFO);
                } else {
                    updateEmployee(nameToUpdate);
                }
            } 
            // Delete Employee: validate for empty name before deleting.
            else if (command.startsWith("d")) {
                String nameToDelete = command.substring(1).trim();
                if (nameToDelete.isEmpty()) {
                    System.out.println("Error: Employee name cannot be empty for 'delete' operation.");
                    System.out.println(Constants.USAGE_INFO);
                } else {
                    deleteEmployee(nameToDelete);
                }
            } 
            // Handle unknown commands.
            else {
                System.out.println("Error: Unknown command.");
                System.out.println(Constants.USAGE_INFO);
            }
        } catch (IOException e) {
            // General I/O error handling for file operations.
            System.out.println("An error occurred while accessing the employee file.");
            e.printStackTrace();
        } catch (Exception e) {
            // Catch-all for unexpected runtime errors.
            System.out.println("An unexpected error occurred while processing the command.");
        }

        System.out.println(Constants.DATA_LOADED);
    }

    // Prints all employee names, one per line.
    private static void listEmployees() throws IOException {
        String[] employees = readEmployees();
        if (employees.length == 0 || (employees.length == 1 && employees[0].isEmpty())) {
            System.out.println("The employee list is empty.");
            return;
        }
        for (String employee : employees) {
            System.out.println(employee.trim());
        }
    }

    // Selects and prints one random employee name.
    private static void showRandomEmployee() throws IOException {
        String[] employees = readEmployees();
        // Check if list is not empty before attempting to get a random index.
        if (employees.length > 0 && !employees[0].isEmpty()) {
            System.out.println(employees[new Random().nextInt(employees.length)].trim());
        } else {
            System.out.println("Cannot show a random employee: The list is empty.");
        }
    }

    // Appends a new employee name to the file.
    private static void addEmployee(String employeeName) throws IOException {
        // Appends to the file, preceded by a comma.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE, true))) {
            writer.write(", " + employeeName);
            System.out.println("Added new employee: " + employeeName);
        }
    }

    // Searches for a specified employee name (case-insensitive).
    private static void searchEmployee(String nameToSearch) throws IOException {
        String[] employees = readEmployees();
        for (String employee : employees) {
            if (employee.trim().equalsIgnoreCase(nameToSearch)) {
                System.out.println("Employee found: " + employee.trim());
                return;
            }
        }
        System.out.println("Employee not found: " + nameToSearch);
    }

    // Prints the total number of employees and total non-delimiter characters.
    private static void countEmployees() throws IOException {
        String[] employees = readEmployees();
        int totalEmployees = employees.length;

        // Calculate total characters by concatenating and removing delimiters.
        int totalCharacters = String.join("", employees).replace(",", "").replace(" ", "").length();

        System.out.println("Total employees: " + totalEmployees);
        System.out.println("Total characters (excluding commas/spaces): " + totalCharacters);
    }

    // Finds an employee and replaces the name with "Updated".
    private static void updateEmployee(String nameToUpdate) throws IOException {
        String[] employees = readEmployees();
        boolean updated = false;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].trim().equalsIgnoreCase(nameToUpdate)) {
                employees[i] = "Updated"; // Placeholder update
                updated = true;
                break;
            }
        }
        writeEmployees(employees, false); // Overwrite the file with the updated list
        System.out.println(updated ? Constants.DATA_UPDATED : "No matching employee found to update.");
    }

    // Deletes a specified employee name from the list and rewrites the file.
    private static void deleteEmployee(String nameToDelete) throws IOException {
        List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployees()));
        // Use removeIf for efficient deletion.
        boolean removed = employeeList.removeIf(emp -> emp.trim().equalsIgnoreCase(nameToDelete));
        writeEmployees(employeeList.toArray(new String[0]), false); // Overwrite the file
        System.out.println(removed ? Constants.DATA_DELETED : "No matching employee found to delete.");
    }

    // Helper method: Reads file content and splits it into an array of names.
    private static String[] readEmployees() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(Constants.EMPLOYEE_FILE)))) {
            String line = reader.readLine();
            // Handle case where file is empty or contains only whitespace.
            return (line == null || line.trim().isEmpty()) ? new String[0] : line.split(",");
        }
    }

    // Helper method: Writes the array of employee names back to the file, joined by commas.
    private static void writeEmployees(String[] employees, boolean append) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE, append))) {
            writer.write(String.join(",", employees));
        }
    }
}