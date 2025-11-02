import java.io.*;
import java.util.*;

/*
 * EmployeeManager (Task 4)
 * Duplicate file operations refactored into helper methods:
 *  - readEmployees()   -> List<String>
 *  - writeEmployees()  -> void
 *  - appendEmployee()  -> void
 *
 * This version keeps behavior same as original but centralizes file I/O.
 */

public class EmployeeManager {

    public static void main(String[] args) {
        // Basic argument validation (from Task 2)
        if (args == null || args.length != 1) {
            System.out.println("Invalid number of arguments!");
            System.out.println("Usage: java EmployeeManager <command>");
            return;
        }

        String command = args[0];

        if ("l".equals(command)) {
            System.out.println("Loading data ...");
            listEmployees();
            System.out.println("Data Loaded.");
        } else if ("s".equals(command)) {
            System.out.println("Loading data ...");
            showRandomEmployee();
            System.out.println("Data Loaded.");
        } else if (command.startsWith("+")) {
            System.out.println("Loading data ...");
            String nameToAdd = command.substring(1);
            if (!nameToAdd.trim().isEmpty()) {
                appendEmployee(nameToAdd.trim());
            } else {
                System.out.println("Invalid name to add.");
            }
            System.out.println("Data Loaded.");
        } else if (command.startsWith("?")) {
            System.out.println("Loading data ...");
            String nameToSearch = command.substring(1);
            searchEmployee(nameToSearch.trim());
            System.out.println("Data Loaded.");
        } else if ("c".equals(command)) {
            System.out.println("Loading data ...");
            countEmployees();
            System.out.println("Data Loaded.");
        } else if (command.startsWith("u")) {
            System.out.println("Loading data ...");
            String nameToUpdate = command.substring(1);
            updateEmployee(nameToUpdate.trim());
            // updateEmployee prints update status
        } else if (command.startsWith("d")) {
            System.out.println("Loading data ...");
            String nameToDelete = command.substring(1);
            deleteEmployee(nameToDelete.trim());
            // deleteEmployee prints delete status
        } else {
            System.out.println("Invalid command: " + command);
            System.out.println("Commands: l, s, +Name, ?Name, c, uName, dName");
        }
    }

    // ---------------- Helper file I/O methods ----------------

    // Read employees from file and return a list of trimmed names.
    private static List<String> readEmployees() {
        List<String> employees = new ArrayList<>();
        File file = new File("employees.txt");
        if (!file.exists()) {
            // No file -> return empty list
            return employees;
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line = reader.readLine();
            if (line != null && !line.trim().isEmpty()) {
                String[] parts = line.split(",");
                for (String p : parts) {
                    employees.add(p.trim());
                }
            }
        } catch (IOException ioe) {
            System.out.println("Error reading employees file: " + ioe.getMessage());
        }
        return employees;
    }

    // Overwrite employees file with the provided list.
    private static void writeEmployees(List<String> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"))) {
            writer.write(String.join(",", employees));
        } catch (IOException ioe) {
            System.out.println("Error writing employees file: " + ioe.getMessage());
        }
    }

    // Append a single employee to the file (adds comma if file non-empty).
    private static void appendEmployee(String name) {
        File file = new File("employees.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (file.exists() && file.length() > 0) {
                writer.write("," + name);
            } else {
                writer.write(name);
            }
        } catch (IOException ioe) {
            System.out.println("Error appending employee: " + ioe.getMessage());
        }
    }

    // ---------------- Command-specific helpers ----------------

    // List employees (l)
    private static void listEmployees() {
        List<String> employees = readEmployees();
        if (employees.isEmpty()) {
            System.out.println("(No employees found)");
            return;
        }
        for (String emp : employees) {
            System.out.println(emp);
        }
    }

    // Show random employee (s)
    private static void showRandomEmployee() {
        List<String> employees = readEmployees();
        if (employees.isEmpty()) {
            System.out.println("(No employees to show)");
            return;
        }
        Random rand = new Random();
        int idx = rand.nextInt(employees.size());
        System.out.println(employees.get(idx));
    }

    // Search employee ( ?Name )
    private static void searchEmployee(String name) {
        if (name == null || name.isEmpty()) {
            System.out.println("Invalid name to search.");
            return;
        }
        List<String> employees = readEmployees();
        if (employees.contains(name)) {
            System.out.println("Employee found!");
        } else {
            System.out.println("Employee NOT found.");
        }
    }

    // Count employees and total characters (c)
    private static void countEmployees() {
        List<String> employees = readEmployees();
        int count = employees.size();
        int totalChars = 0;
        for (String emp : employees) {
            totalChars += emp.length();
        }
        System.out.println(count + " employee(s) found; total characters: " + totalChars);
    }

    // Update matching employee(s) to "Updated" (uName)
    private static void updateEmployee(String name) {
        if (name == null || name.isEmpty()) {
            System.out.println("Invalid name to update.");
            return;
        }
        List<String> employees = readEmployees();
        boolean changed = false;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).equals(name)) {
                employees.set(i, "Updated");
                changed = true;
            }
        }
        if (changed) {
            writeEmployees(employees);
            System.out.println("Data Updated.");
        } else {
            System.out.println("No matching employee to update.");
        }
    }

    // Delete employee by exact name (dName)
    private static void deleteEmployee(String name) {
        if (name == null || name.isEmpty()) {
            System.out.println("Invalid name to delete.");
            return;
        }
        List<String> employees = readEmployees();
        boolean removed = employees.removeIf(e -> e.equals(name));
        if (removed) {
            writeEmployees(employees);
            System.out.println("Data Deleted.");
        } else {
            System.out.println("No matching employee to delete.");
        }
    }
}

