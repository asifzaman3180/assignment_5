import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {
        if (args == null || args.length != 1) {
            System.out.println("Invalid number of arguments!");
            System.out.println(Constants.USAGE_MESSAGE);
            return;
        }

        // 
        if ("l".equals(args[0])) {
            System.out.println("Loading data ...");
            listEmployees();
            System.out.println("Data Loaded.");
        } else if ("s".equals(args[0])) {
            System.out.println("Loading data ...");
            showRandomEmployee();
            System.out.println("Data Loaded.");
        } else if (args[0].startsWith("+")) {
            System.out.println("Loading data ...");
            String nameToAdd = args[0].substring(1).trim();
            if (!nameToAdd.isEmpty()) appendEmployee(nameToAdd);
            else System.out.println("Invalid name to add.");
            System.out.println("Data Loaded.");
        } else if (args[0].startsWith("?")) {
            System.out.println("Loading data ...");
            searchEmployee(args[0].substring(1).trim());
            System.out.println("Data Loaded.");
        } else if ("c".equals(args[0])) {
            System.out.println("Loading data ...");
            countEmployees();
            System.out.println("Data Loaded.");
        } else if (args[0].startsWith("u")) {
            System.out.println("Loading data ...");
            updateEmployee(args[0].substring(1).trim());
        } else if (args[0].startsWith("d")) {
            System.out.println("Loading data ...");
            deleteEmployee(args[0].substring(1).trim());
        } else {
            System.out.println("Invalid command: " + args[0]);
            System.out.println(Constants.USAGE_MESSAGE);
        }
    }

    // Read employees (no unnecessary vars)
    private static List<String> readEmployees() {
        List<String> employees = new ArrayList<>();
        File file = new File(Constants.EMPLOYEE_FILE);
        if (!file.exists()) return employees;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            if ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                for (String p : line.split(",")) employees.add(p.trim());
            }
        } catch (IOException ioe) {
            System.out.println("Error reading file: " + ioe.getMessage());
        }
        return employees;
    }

    // Write employees list (no redundant vars)
    private static void writeEmployees(List<String> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE))) {
            writer.write(String.join(",", employees));
        } catch (IOException ioe) {
            System.out.println("Error writing file: " + ioe.getMessage());
        }
    }

    // Append employee
    private static void appendEmployee(String name) {
        File file = new File(Constants.EMPLOYEE_FILE);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (file.exists() && file.length() > 0) writer.write("," + name);
            else writer.write(name);
        } catch (IOException ioe) {
            System.out.println("Error appending: " + ioe.getMessage());
        }
    }

    private static void listEmployees() {
        List<String> employees = readEmployees();
        if (employees.isEmpty()) System.out.println("(No employees found)");
        else employees.forEach(System.out::println);
    }

    private static void showRandomEmployee() {
        List<String> employees = readEmployees();
        if (employees.isEmpty()) System.out.println("(No employees to show)");
        else System.out.println(employees.get(new Random().nextInt(employees.size())));
    }

    private static void searchEmployee(String name) {
        if (name.isEmpty()) {
            System.out.println("Invalid name to search.");
            return;
        }
        System.out.println(readEmployees().contains(name) ? "Employee found!" : "Employee NOT found.");
    }

    private static void countEmployees() {
        List<String> employees = readEmployees();
        int totalChars = employees.stream().mapToInt(String::length).sum();
        System.out.println(employees.size() + " employee(s) found; total characters: " + totalChars);
    }

    private static void updateEmployee(String name) {
        if (name.isEmpty()) {
            System.out.println("Invalid name to update.");
            return;
        }
        List<String> employees = readEmployees();
        boolean updated = false;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).equals(name)) {
                employees.set(i, "Updated");
                updated = true;
            }
        }
        if (updated) {
            writeEmployees(employees);
            System.out.println("Data Updated.");
        } else {
            System.out.println("No matching employee to update.");
        }
    }

    private static void deleteEmployee(String name) {
        if (name.isEmpty()) {
            System.out.println("Invalid name to delete.");
            return;
        }
        List<String> employees = readEmployees();
        if (employees.removeIf(e -> e.equals(name))) {
            writeEmployees(employees);
            System.out.println("Data Deleted.");
        } else {
            System.out.println("No matching employee to delete.");
        }
    }
}

