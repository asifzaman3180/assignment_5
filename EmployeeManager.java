import java.io.*;
import java.util.*;

public class EmployeeManager {


// Read all employees from file
private static List<String> readEmployees() throws IOException {
    File file = new File(Constants.EMPLOYEE_FILE);
    if (!file.exists() || file.length() == 0) {
        return new ArrayList<>();
    }
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line = reader.readLine();
        if (line == null || line.isEmpty()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(line.split(",")));
    }
}

// Write all employees to file
private static void writeEmployees(List<String> employees) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE))) {
        writer.write(String.join(",", employees));
    }
}

//  Append a new employee
private static void appendEmployee(String employeeName) throws IOException {
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

    if (args.length != 1) {
        System.out.println("Error: Invalid number of arguments.");
        printUsage();
        return;
    }

    String command = args[0].trim();
    if (command.isEmpty()) {
        System.out.println("Error: Command cannot be empty.");
        printUsage();
        return;
    }

    try {
        switch (command.charAt(0)) {

            case 'l': // List all employees
                System.out.println(Constants.LOADING_MSG);
                List<String> employees = readEmployees();
                if (employees.isEmpty()) {
                    System.out.println("No employees found.");
                } else {
                    employees.forEach(System.out::println);
                }
                System.out.println(Constants.DATA_LOADED_MSG);
                break;

            case 's': // Show a random employee
                System.out.println(Constants.LOADING_MSG);
                employees = readEmployees();
                if (employees.isEmpty()) {
                    System.out.println(Constants.EMPLOYEE_NOT_FOUND_MSG);
                } else {
                    String randomEmp = employees.get(new Random().nextInt(employees.size()));
                    System.out.println("Random employee: " + randomEmp.trim());
                }
                System.out.println(Constants.DATA_LOADED_MSG);
                break;

            case '+': // Add new employee
                String newEmp = command.substring(1).trim();
                if (newEmp.isEmpty()) {
                    System.out.println("Error: Employee name to add cannot be empty.");
                } else {
                    System.out.println(Constants.LOADING_MSG);
                    appendEmployee(newEmp);
                    System.out.println("Employee '" + newEmp + "' added successfully.");
                    System.out.println(Constants.DATA_LOADED_MSG);
                }
                break;

            case '?': // Search employee
                String searchName = command.substring(1).trim();
                if (searchName.isEmpty()) {
                    System.out.println("Error: Employee name to search cannot be empty.");
                } else {
                    System.out.println(Constants.LOADING_MSG);
                    employees = readEmployees();
                    boolean employeeExists = employees.stream()
                            .map(String::trim)
                            .anyMatch(emp -> emp.equalsIgnoreCase(searchName));
                    if (employeeExists) {
                        System.out.println(Constants.EMPLOYEE_FOUND_MSG + " (" + searchName + ")");
                    } else {
                        System.out.println(Constants.EMPLOYEE_NOT_FOUND_MSG + " (" + searchName + ")");
                    }
                    System.out.println(Constants.DATA_LOADED_MSG);
                }
                break;

            case 'c': // Count employees
                System.out.println(Constants.LOADING_MSG);
                int count = readEmployees().size();
                System.out.println(count + " employee(s) found.");
                System.out.println(Constants.DATA_LOADED_MSG);
                break;

            case 'u': // Update employee
                String updateName = command.substring(1).trim();
                if (updateName.isEmpty()) {
                    System.out.println("Error: Employee name to update cannot be empty.");
                } else {
                    System.out.println(Constants.LOADING_MSG);
                    employees = readEmployees();
                    if (employees.contains(updateName)) {
                        Collections.replaceAll(employees, updateName, "Updated");
                        writeEmployees(employees);
                        System.out.println(Constants.DATA_UPDATED_MSG + " (" + updateName + ")");
                    } else {
                        System.out.println(Constants.EMPLOYEE_NOT_FOUND_MSG + " (" + updateName + ")");
                    }
                }
                break;

            case 'd': // Delete employee
                String deleteName = command.substring(1).trim();
                if (deleteName.isEmpty()) {
                    System.out.println("Error: Employee name to delete cannot be empty.");
                } else {
                    System.out.println(Constants.LOADING_MSG);
                    employees = readEmployees();
                    if (employees.removeIf(emp -> emp.trim().equalsIgnoreCase(deleteName))) {
                        writeEmployees(employees);
                        System.out.println(Constants.DATA_DELETED_MSG + " (" + deleteName + ")");
                    } else {
                        System.out.println(Constants.EMPLOYEE_NOT_FOUND_MSG + " (" + deleteName + ")");
                    }
                }
                break;

            default:
                System.out.println("Error: Unsupported command '" + command + "'");
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
