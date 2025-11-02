import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {
        // Argument validation
        if (args == null || args.length != 1) {
            System.out.println("Invalid number of arguments!");
            System.out.println(Constants.USAGE_MESSAGE);
            return;
        }

        String rawCommand = args[0];

        // Determine command type and parameter (if any)
        String cmdType = getCommandType(rawCommand);
        String parameter = getParameter(rawCommand);

        switch (cmdType) {
            case "LIST":
                System.out.println("Loading data ...");
                listEmployees();
                System.out.println("Data Loaded.");
                break;

            case "SHOW":
                System.out.println("Loading data ...");
                showRandomEmployee();
                System.out.println("Data Loaded.");
                break;

            case "ADD":
                // Validate parameter for add
                if (!isValidName(parameter)) {
                    System.out.println("Error: missing or invalid name for add (+Name).");
                    System.out.println("Example: java EmployeeManager +John_Doe");
                } else {
                    System.out.println("Loading data ...");
                    appendEmployee(parameter);
                    System.out.println("Data Loaded.");
                }
                break;

            case "SEARCH":
                if (!isValidName(parameter)) {
                    System.out.println("Error: missing or invalid name for search (?Name).");
                    System.out.println("Example: java EmployeeManager ?John_Doe");
                } else {
                    System.out.println("Loading data ...");
                    searchEmployee(parameter);
                    System.out.println("Data Loaded.");
                }
                break;

            case "COUNT":
                System.out.println("Loading data ...");
                countEmployees();
                System.out.println("Data Loaded.");
                break;

            case "UPDATE":
                if (!isValidName(parameter)) {
                    System.out.println("Error: missing or invalid name for update (uName).");
                    System.out.println("Example: java EmployeeManager uJohn_Doe");
                } else {
                    System.out.println("Loading data ...");
                    updateEmployee(parameter);
                }
                break;

            case "DELETE":
                if (!isValidName(parameter)) {
                    System.out.println("Error: missing or invalid name for delete (dName).");
                    System.out.println("Example: java EmployeeManager dJohn_Doe");
                } else {
                    System.out.println("Loading data ...");
                    deleteEmployee(parameter);
                }
                break;

            default:
                System.out.println("Invalid command: " + rawCommand);
                System.out.println(Constants.USAGE_MESSAGE);
        }
    }

    // Return command type string based on raw input
    private static String getCommandType(String cmd) {
        if (cmd == null || cmd.isEmpty()) return "INVALID";
        if ("l".equals(cmd)) return "LIST";
        if ("s".equals(cmd)) return "SHOW";
        if (cmd.startsWith("+")) return "ADD";
        if (cmd.startsWith("?")) return "SEARCH";
        if ("c".equals(cmd)) return "COUNT";
        if (cmd.startsWith("u")) return "UPDATE";
        if (cmd.startsWith("d")) return "DELETE";
        return "INVALID";
    }

    // Extract parameter (part after prefix), trimmed; returns empty string if none
    private static String getParameter(String raw) {
        if (raw == null || raw.length() == 0) return "";
        if (raw.startsWith("+") || raw.startsWith("?")) {
            return raw.length() > 1 ? raw.substring(1).trim() : "";
        }
        if (raw.startsWith("u") || raw.startsWith("d")) {
            return raw.length() > 1 ? raw.substring(1).trim() : "";
        }
        return "";
    }

    // Basic validation for a name parameter (non-empty, not just spaces)
    private static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    // ---------------- File I/O helpers ----------------

    private static List<String> readEmployees() {
        List<String> employees = new ArrayList<>();
        File file = new File(Constants.EMPLOYEE_FILE);
        if (!file.exists()) return employees;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line != null && !line.trim().isEmpty()) {
                for (String p : line.split(",")) employees.add(p.trim());
            }
        } catch (IOException ioe) {
            System.out.println("Error reading file: " + ioe.getMessage());
        }
        return employees;
    }

    private static void writeEmployees(List<String> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE))) {
            writer.write(String.join(",", employees));
        } catch (IOException ioe) {
            System.out.println("Error writing file: " + ioe.getMessage());
        }
    }

    private static void appendEmployee(String name) {
        File file = new File(Constants.EMPLOYEE_FILE);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (file.exists() && file.length() > 0) writer.write("," + name);
            else writer.write(name);
        } catch (IOException ioe) {
            System.out.println("Error appending: " + ioe.getMessage());
        }
    }

    // ---------------- Command handlers ----------------

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
        List<String> employees = readEmployees();
        System.out.println(employees.contains(name) ? "Employee found!" : "Employee NOT found.");
    }

    private static void countEmployees() {
        List<String> employees = readEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        int totalEmployees = employees.size();
        double averageNameLength = employees.stream().mapToInt(String::length).average().orElse(0);
        System.out.printf("Total Employees: %d | Average Name Length: %.2f%n", totalEmployees, averageNameLength);
    }

    private static void updateEmployee(String name) {
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



