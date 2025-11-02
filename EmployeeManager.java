import java.io.*;
import java.util.*;

/**
 * EmployeeManager.java
 *
 * Manages employee records stored in employees.txt.
 * Supports: list, search, add, count, update, and help operations.
 */
public class EmployeeManager {
    public static void main(String[] args) {
        // ✅ Validate command-line arguments
        if (args.length != 1) {
            System.out.println("Usage: java EmployeeManager <option>");
            System.out.println("Options: l (list), s (search), + (add), ? (help), c (count), u (update)");
            System.exit(1);
        }

        String userOption = args[0];

        switch (userOption) {
            case Constants.OPTION_LIST -> listEmployees();
            case Constants.OPTION_SEARCH -> searchEmployee();
            case Constants.OPTION_ADD -> addEmployee();
            case Constants.OPTION_COUNT -> countEmployees(); // Simplified in this task
            case Constants.OPTION_UPDATE -> updateEmployee();
            case Constants.OPTION_HELP -> showHelp();
            default -> System.out.println(Constants.INVALID_OPTION_MESSAGE);
        }
    }

    // ============================================================
    // 🧾 1. LIST EMPLOYEES
    // ============================================================
    private static void listEmployees() {
        List<String> employeeList = readEmployeesFromFile();

        if (employeeList.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println("=== Employee List ===");
        employeeList.forEach(System.out::println);
    }

    // ============================================================
    // 🔍 2. SEARCH EMPLOYEE
    // ============================================================
    private static void searchEmployee() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Enter name to search: ");
        String query = inputScanner.nextLine().trim().toLowerCase();

        List<String> employeeList = readEmployeesFromFile();

        List<String> matchedEmployees = employeeList.stream()
                .filter(name -> name.toLowerCase().contains(query))
                .toList();

        if (matchedEmployees.isEmpty()) {
            System.out.println("No match found.");
        } else {
            matchedEmployees.forEach(name -> System.out.println("Found: " + name));
        }
    }

    // ============================================================
    // ➕ 3. ADD EMPLOYEE
    // ============================================================
    private static void addEmployee() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Enter new employee name: ");
        String newName = inputScanner.nextLine().trim();

        if (newName.isEmpty()) {
            System.out.println("Invalid name.");
            return;
        }

        List<String> employeeList = readEmployeesFromFile();
        if (employeeList.contains(newName)) {
            System.out.println("Employee already exists.");
            return;
        }

        employeeList.add(newName);
        writeEmployeesToFile(employeeList);
        System.out.println("Employee added successfully.");
    }

    // ============================================================
    // 🔢 4. COUNT EMPLOYEES (Simplified for Task 8)
    // ============================================================
    private static void countEmployees() {
        int totalEmployees = readEmployeesFromFile().size();

        // ✅ Cleaner logic and better message
        if (totalEmployees == 0) {
            System.out.println("No employees found in the record.");
        } else if (totalEmployees == 1) {
            System.out.println("There is 1 employee in the record.");
        } else {
            System.out.printf("There are %d employees in the record.%n", totalEmployees);
        }
    }

    // ============================================================
    // ✏️ 5. UPDATE EMPLOYEE
    // ============================================================
    private static void updateEmployee() {
        Scanner inputScanner = new Scanner(System.in);
        List<String> employeeList = readEmployeesFromFile();

        if (employeeList.isEmpty()) {
            System.out.println("No employees to update.");
            return;
        }

        System.out.print("Enter the employee name to update: ");
        String oldName = inputScanner.nextLine().trim();

        if (!employeeList.contains(oldName)) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.print("Enter new name: ");
        String newName = inputScanner.nextLine().trim();

        if (newName.isEmpty()) {
            System.out.println("Invalid new name.");
            return;
        }

        employeeList.set(employeeList.indexOf(oldName), newName);
        writeEmployeesToFile(employeeList);
        System.out.println("Employee updated successfully.");
    }

    // ============================================================
    // 🧠 6. SHOW HELP
    // ============================================================
    private static void showHelp() {
        System.out.println("Available Options:");
        System.out.println("l - List employees");
        System.out.println("s - Search employee");
        System.out.println("+ - Add employee");
        System.out.println("c - Count employees");
        System.out.println("u - Update employee");
        System.out.println("? - Show help");
    }

    // ============================================================
    // 🗂️ 7. FILE OPERATIONS
    // ============================================================
    private static List<String> readEmployeesFromFile() {
        List<String> employeeList = new ArrayList<>();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(Constants.EMPLOYEE_FILE_PATH))) {
            String line = fileReader.readLine();
            if (line != null) {
                Arrays.stream(line.split(","))
                        .map(String::trim)
                        .filter(name -> !name.isEmpty())
                        .forEach(employeeList::add);
            }
        } catch (IOException ioException) {
            System.out.println("Error reading file: " + ioException.getMessage());
        }

        return employeeList;
    }

    private static void writeEmployeesToFile(List<String> employeeList) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE_PATH))) {
            fileWriter.write(String.join(",", employeeList));
        } catch (IOException ioException) {
            System.out.println("Error writing file: " + ioException.getMessage());
        }
    }
}
