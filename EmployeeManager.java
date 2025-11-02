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
        // ✅ Validate command-line arguments (Task 9)
        if (args.length != 1) {
            System.out.println("❌ Invalid number of arguments.");
            printUsageGuide();
            System.exit(1);
        }

        String userOption = args[0].trim().toLowerCase();

        if (!isValidOption(userOption)) {
            System.out.println("❌ Unsupported option: " + userOption);
            printUsageGuide();
            System.exit(1);
        }

        // ✅ Safe switch structure
        switch (userOption) {
            case Constants.OPTION_LIST -> listEmployees();
            case Constants.OPTION_SEARCH -> searchEmployee();
            case Constants.OPTION_ADD -> addEmployee();
            case Constants.OPTION_COUNT -> countEmployees();
            case Constants.OPTION_UPDATE -> updateEmployee();
            case Constants.OPTION_HELP -> showHelp();
            default -> System.out.println(Constants.INVALID_OPTION_MESSAGE);
        }
    }

    // ============================================================
    // 🔒 Validation Helpers
    // ============================================================
    private static boolean isValidOption(String option) {
        return Set.of(
                Constants.OPTION_LIST,
                Constants.OPTION_SEARCH,
                Constants.OPTION_ADD,
                Constants.OPTION_COUNT,
                Constants.OPTION_UPDATE,
                Constants.OPTION_HELP).contains(option);
    }

    private static void printUsageGuide() {
        System.out.println("""
                ✅ Usage:
                java EmployeeManager <option>

                Available options:
                  l  - List all employees
                  s  - Search for an employee
                  +  - Add a new employee
                  c  - Count total employees
                  u  - Update an existing employee
                  ?  - Show help menu
                """);
    }

    // ============================================================
    // 🧾 1. LIST EMPLOYEES
    // ============================================================
    private static void listEmployees() {
        List<String> employeeList = readEmployeesFromFile();

        if (employeeList.isEmpty()) {
            System.out.println("⚠️ No employees found.");
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
        String query = inputScanner.nextLine().trim();

        if (query.isEmpty()) {
            System.out.println("⚠️ Search query cannot be empty.");
            return;
        }

        List<String> employeeList = readEmployeesFromFile();
        if (employeeList.isEmpty()) {
            System.out.println("⚠️ No employees available for searching.");
            return;
        }

        List<String> matchedEmployees = employeeList.stream()
                .filter(name -> name.toLowerCase().contains(query.toLowerCase()))
                .toList();

        if (matchedEmployees.isEmpty()) {
            System.out.println("❌ No match found for \"" + query + "\".");
        } else {
            matchedEmployees.forEach(name -> System.out.println("✅ Found: " + name));
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
            System.out.println("⚠️ Invalid name. Please enter a valid employee name.");
            return;
        }

        List<String> employeeList = readEmployeesFromFile();

        if (employeeList.contains(newName)) {
            System.out.println("⚠️ Employee \"" + newName + "\" already exists.");
            return;
        }

        employeeList.add(newName);
        writeEmployeesToFile(employeeList);
        System.out.println("✅ Employee \"" + newName + "\" added successfully.");
    }

    // ============================================================
    // 🔢 4. COUNT EMPLOYEES
    // ============================================================
    private static void countEmployees() {
        int totalEmployees = readEmployeesFromFile().size();

        if (totalEmployees == 0) {
            System.out.println("⚠️ No employees found in the record.");
        } else if (totalEmployees == 1) {
            System.out.println("📊 There is 1 employee in the record.");
        } else {
            System.out.printf("📊 There are %d employees in the record.%n", totalEmployees);
        }
    }

    // ============================================================
    // ✏️ 5. UPDATE EMPLOYEE
    // ============================================================
    private static void updateEmployee() {
        Scanner inputScanner = new Scanner(System.in);
        List<String> employeeList = readEmployeesFromFile();

        if (employeeList.isEmpty()) {
            System.out.println("⚠️ No employees available to update.");
            return;
        }

        System.out.print("Enter the employee name to update: ");
        String oldName = inputScanner.nextLine().trim();

        if (oldName.isEmpty()) {
            System.out.println("⚠️ Invalid input. Name cannot be empty.");
            return;
        }

        if (!employeeList.contains(oldName)) {
            System.out.println("❌ Employee \"" + oldName + "\" not found.");
            return;
        }

        System.out.print("Enter new name: ");
        String newName = inputScanner.nextLine().trim();

        if (newName.isEmpty()) {
            System.out.println("⚠️ Invalid new name. Please enter a valid one.");
            return;
        }

        employeeList.set(employeeList.indexOf(oldName), newName);
        writeEmployeesToFile(employeeList);
        System.out.println("✅ Employee updated successfully.");
    }

    // ============================================================
    // 🧠 6. SHOW HELP
    // ============================================================
    private static void showHelp() {
        printUsageGuide();
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
        } catch (FileNotFoundException e) {
            System.out.println("⚠️ Employee file not found. A new one will be created on next save.");
        } catch (IOException ioException) {
            System.out.println("❌ Error reading file: " + ioException.getMessage());
        }

        return employeeList;
    }

    private static void writeEmployeesToFile(List<String> employeeList) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE_PATH))) {
            fileWriter.write(String.join(",", employeeList));
        } catch (IOException ioException) {
            System.out.println("❌ Error writing to file: " + ioException.getMessage());
        }
    }
}
