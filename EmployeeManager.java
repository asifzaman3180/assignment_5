import java.io.*;
import java.util.*;

public class EmployeeManager {


// Read all employees from file and return as a List of strings
private static List<String> loadEmployeesFromFile() throws IOException {
    File file = new File(Constants.EMPLOYEE_FILE);
    if (!file.exists() || file.length() == 0) {
        return new ArrayList<>(); // Return empty list if file does not exist or is empty
    }
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line = reader.readLine();
        if (line == null || line.isEmpty()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(line.split(",")));
    }
}

// Write the list of employees back to the file
private static void saveEmployeesToFile(List<String> employees) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE))) {
        writer.write(String.join(",", employees));
    }
}

// Append a new employee to the file
private static void addEmployeeToFile(String employeeName) throws IOException {
    if (employeeName.isEmpty()) {
        throw new IllegalArgumentException("Employee name cannot be empty.");
    }
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE, true))) {
        File file = new File(Constants.EMPLOYEE_FILE);
        if (file.exists() && file.length() > 0) {
            writer.write(", " + employeeName);
        } else {
            writer.write(employeeName);
        }
    }
}

public static void main(String[] args) {

    // Validate that exactly one argument is provided
    if (args.length != 1) {
        System.out.println("Error: Invalid number of arguments.");
        printUsage();
        return;
    }

    String userCommand = args[0].trim();
    if (userCommand.isEmpty()) {
        System.out.println("Error: Command cannot be empty.");
        printUsage();
        return;
    }

    try {
        switch (userCommand.charAt(0)) {

            case 'l': // List all employees
                System.out.println(Constants.LOADING_MSG);
                List<String> employees = loadEmployeesFromFile();
                if (employees.isEmpty()) {
                    System.out.println("No employees found.");
                } else {
                    employees.forEach(System.out::println);
                }
                System.out.println(Constants.DATA_LOADED_MSG);
                break;

            case 's': // Show a random employee
                System.out.println(Constants.LOADING_MSG);
                employees = loadEmployeesFromFile();
                if (employees.isEmpty()) {
                    System.out.println(Constants.EMPLOYEE_NOT_FOUND_MSG);
                } else {
                    String randomEmployee = employees.get(new Random().nextInt(employees.size()));
                    System.out.println("Random employee: " + randomEmployee.trim());
                }
                System.out.println(Constants.DATA_LOADED_MSG);
                break;

            case '+': // Add a new employee
                String newEmployee = userCommand.substring(1).trim();
                if (newEmployee.isEmpty()) {
                    System.out.println("Error: Employee name to add cannot be empty.");
                } else {
                    System.out.println(Constants.LOADING_MSG);
                    addEmployeeToFile(newEmployee);
                    System.out.println("Employee '" + newEmployee + "' added successfully.");
                    System.out.println(Constants.DATA_LOADED_MSG);
                }
                break;

            case '?': // Search for an employee
                String searchName = userCommand.substring(1).trim();
                if (searchName.isEmpty()) {
                    System.out.println("Error: Employee name to search cannot be empty.");
                } else {
                    System.out.println(Constants.LOADING_MSG);
                    employees = loadEmployeesFromFile();
                    boolean found = employees.stream()
                            .map(String::trim)
                            .anyMatch(emp -> emp.equalsIgnoreCase(searchName));
                    if (found) {
                        System.out.println(Constants.EMPLOYEE_FOUND_MSG + " (" + searchName + ")");
                    } else {
                        System.out.println(Constants.EMPLOYEE_NOT_FOUND_MSG + " (" + searchName + ")");
                    }
                    System.out.println(Constants.DATA_LOADED_MSG);
                }
                break;

            case 'c': // Count the total number of employees
                System.out.println(Constants.LOADING_MSG);
                int employeeCount = loadEmployeesFromFile().size();
                System.out.println(employeeCount + " employee(s) found.");
                System.out.println(Constants.DATA_LOADED_MSG);
                break;

            case 'u': // Update an existing employee
                String oldName = userCommand.substring(1).trim();
                if (oldName.isEmpty()) {
                    System.out.println("Error: Employee name to update cannot be empty.");
                } else {
                    System.out.println(Constants.LOADING_MSG);
                    employees = loadEmployeesFromFile();
                    if (employees.contains(oldName)) {
                        Collections.replaceAll(employees, oldName, "Updated");
                        saveEmployeesToFile(employees);
                        System.out.println(Constants.DATA_UPDATED_MSG + " (" + oldName + ")");
                    } else {
                        System.out.println(Constants.EMPLOYEE_NOT_FOUND_MSG + " (" + oldName + ")");
                    }
                }
                break;

            case 'd': // Delete an employee
                String deleteName = userCommand.substring(1).trim();
                if (deleteName.isEmpty()) {
                    System.out.println("Error: Employee name to delete cannot be empty.");
                } else {
                    System.out.println(Constants.LOADING_MSG);
                    employees = loadEmployeesFromFile();
                    if (employees.removeIf(emp -> emp.trim().equalsIgnoreCase(deleteName))) {
                        saveEmployeesToFile(employees);
                        System.out.println(Constants.DATA_DELETED_MSG + " (" + deleteName + ")");
                    } else {
                        System.out.println(Constants.EMPLOYEE_NOT_FOUND_MSG + " (" + deleteName + ")");
                    }
                }
                break;

            default: // Handle unsupported commands
                System.out.println("Error: Unsupported command '" + userCommand + "'");
                printUsage();
        }

    } catch (IOException e) {
        System.out.println("File access error: " + e.getMessage());
    } catch (IllegalArgumentException e) {
        System.out.println("Input error: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Unexpected error: " + e.getMessage());
    }
}

// Display usage instructions for the program
private static void printUsage() {
    System.out.println("Usage:");
    System.out.println("  java EmployeeManager l         // List all employees");
    System.out.println("  java EmployeeManager s         // Show a random employee");
    System.out.println("  java EmployeeManager +Name     // Add a new employee");
    System.out.println("  java EmployeeManager ?Name     // Search for an employee");
    System.out.println("  java EmployeeManager c         // Count employees");
    System.out.println("  java EmployeeManager uName     // Update employee");
    System.out.println("  java EmployeeManager dName     // Delete employee");
}


}
