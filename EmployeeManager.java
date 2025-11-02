// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    private static final String FILE_PATH = Constants.EMPLOYEE_FILE_PATH;

    // Read all employees from file
    private static List<String> readEmployeesFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String data = reader.readLine();
            if (data == null || data.isEmpty()) return new ArrayList<>();
            return new ArrayList<>(Arrays.asList(data.split(",\\s*")));
        }
    }

    // Write employees to file
    private static void writeEmployeesToFile(List<String> employees) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(String.join(", ", employees));
        }
    }

    public static void main(String[] args) {

        // Validate arguments
        if (args.length != 1) {
            System.out.println("Invalid number of arguments!\nUsage:");
            System.out.println("  java EmployeeManager l          -> List all employees");
            System.out.println("  java EmployeeManager s          -> Show random employee");
            System.out.println("  java EmployeeManager +Name      -> Add new employee");
            System.out.println("  java EmployeeManager ?Name      -> Search employee");
            System.out.println("  java EmployeeManager c          -> Count employees");
            System.out.println("  java EmployeeManager uName      -> Update employee");
            System.out.println("  java EmployeeManager dName      -> Delete employee");
            return;
        }

        String arg = args[0];

        try {
            // List all employees
            if (arg.equals("l")) {
                System.out.println(Constants.DATA_LOADING);
                readEmployeesFromFile().forEach(System.out::println);
                System.out.println(Constants.DATA_LOADED);
            }

            // Show random employee
            else if (arg.equals("s")) {
                List<String> employees = readEmployeesFromFile();
                if (employees.isEmpty()) System.out.println("No employees found.");
                else System.out.println("Random employee: " + employees.get(new Random().nextInt(employees.size())));
            }

            // Add employee
            else if (arg.startsWith("+")) {
                List<String> employees = new ArrayList<>(readEmployeesFromFile());
                String newName = arg.substring(1).trim();
                employees.add(newName);
                writeEmployeesToFile(employees);
                System.out.println("✅ Employee added: " + newName);
            }

            // Search employee
            else if (arg.startsWith("?")) {
                List<String> employees = readEmployeesFromFile();
                String searchName = arg.substring(1).trim();
                if (employees.contains(searchName))
                    System.out.println("✅ Employee found: " + searchName);
                else
                    System.out.println("❌ Employee not found: " + searchName);
            }

            // Count employees (simplified)
            else if (arg.equals("c")) {
                System.out.println(Constants.DATA_LOADING);
                List<String> employees = readEmployeesFromFile();
                int count = employees.size();
                if (count == 0)
                    System.out.println("No employees found.");
                else
                    System.out.println("Total employees: " + count);
                System.out.println(Constants.DATA_LOADED);
            }

            // Update employee
            else if (arg.startsWith("u")) {
                List<String> employees = new ArrayList<>(readEmployeesFromFile());
                String nameToUpdate = arg.substring(1).trim();
                int index = employees.indexOf(nameToUpdate);
                if (index != -1) {
                    employees.set(index, "Updated");
                    writeEmployeesToFile(employees);
                    System.out.println(Constants.DATA_UPDATED);
                } else {
                    System.out.println("Employee not found.");
                }
            }

            // Delete employee
            else if (arg.startsWith("d")) {
                List<String> employees = new ArrayList<>(readEmployeesFromFile());
                String nameToDelete = arg.substring(1).trim();
                if (employees.remove(nameToDelete)) {
                    writeEmployeesToFile(employees);
                    System.out.println(Constants.DATA_DELETED);
                } else {
                    System.out.println("Employee not found.");
                }
            }

            // Invalid command
            else {
                System.out.println("Invalid command. Please check usage.");
            }

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}
