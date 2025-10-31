// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Task 4: Reusable method to read employees from file
    private static List<String> readEmployeesFromFile(String filePath) {
        List<String> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                employees = new ArrayList<>(Arrays.asList(line.split(",")));
            }
        } catch (IOException e) {
            // Handle silently
        }
        return employees;
    }

    // Task 4: Reusable method to write employees to file
    private static void writeEmployeesToFile(String filePath, List<String> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(String.join(",", employees));
        } catch (IOException e) {
            // Handle silently
        }
    }

    // Task 4: Reusable method to append a new employee
    private static void appendEmployeeToFile(String filePath, String employeeName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(", " + employeeName);
        } catch (IOException e) {
            // Handle silently
        }
    }

    public static void main(String[] args) {

        // Task 2 & 3: Validate arguments & Improve Variable Naming
        if (args.length == 0) {
            System.out.println("Error: No arguments provided.");
            System.out.println("Usage: java EmployeeManager <command>");
            System.out.println("Commands: l, s, +<name>, ?<name>, c, u<name>, d<name>");
            return; // exit safely
        }

        String command = args[0];
        String filePath = "employees.txt";

        if (command.equals("l")) {
            System.out.println("Loading data ...");
            List<String> employeeList = readEmployeesFromFile(filePath);
            for (String employee : employeeList) {
                System.out.println(employee);
            }
            System.out.println("Data Loaded.");

        } else if (command.equals("s")) {
            System.out.println("Loading data ...");
            List<String> employeeList = readEmployeesFromFile(filePath);
            if (!employeeList.isEmpty()) {
                Random random = new Random();
                int randomIndex = random.nextInt(employeeList.size());
                System.out.println(employeeList.get(randomIndex));
            }
            System.out.println("Data Loaded.");

        } else if (command.startsWith("+")) {
            if (command.length() == 1) {
                System.out.println("Error: No employee name provided to add.");
                return;
            }
            System.out.println("Loading data ...");
            String newEmployee = command.substring(1);
            appendEmployeeToFile(filePath, newEmployee);
            System.out.println("Data Loaded.");

        } else if (command.startsWith("?")) {
            if (command.length() == 1) {
                System.out.println("Error: No employee name provided to search.");
                return;
            }
            System.out.println("Loading data ...");
            List<String> employeeList = readEmployeesFromFile(filePath);
            String searchEmployee = command.substring(1);
            if (employeeList.contains(searchEmployee)) {
                System.out.println("Employee found!");
            }
            System.out.println("Data Loaded.");

        } else if (command.equals("c")) {
            System.out.println("Loading data ...");
            List<String> employeeList = readEmployeesFromFile(filePath);
            int totalChars = 0;
            int wordCount = 0;

            for (String emp : employeeList) {
                totalChars += emp.length();
                if (!emp.trim().isEmpty()) {
                    wordCount++;
                }
            }

            System.out.println(wordCount + " word(s) found, total characters: " + totalChars);
            System.out.println("Data Loaded.");

        } else if (command.startsWith("u")) {
            if (command.length() == 1) {
                System.out.println("Error: No employee name provided to update.");
                return;
            }
            System.out.println("Loading data ...");
            List<String> employeeList = readEmployeesFromFile(filePath);
            String employeeToUpdate = command.substring(1);

            for (int i = 0; i < employeeList.size(); i++) {
                if (employeeList.get(i).equals(employeeToUpdate)) {
                    employeeList.set(i, "Updated");
                }
            }

            writeEmployeesToFile(filePath, employeeList);
            System.out.println("Data Updated.");

        } else if (command.startsWith("d")) {
            if (command.length() == 1) {
                System.out.println("Error: No employee name provided to delete.");
                return;
            }
            System.out.println("Loading data ...");
            List<String> employeeList = readEmployeesFromFile(filePath);
            String employeeToDelete = command.substring(1);
            employeeList.remove(employeeToDelete);
            writeEmployeesToFile(filePath, employeeList);
            System.out.println("Data Deleted.");

        } else {
            System.out.println("Error: Unsupported command '" + command + "'.");
            System.out.println("Usage: l, s, +<name>, ?<name>, c, u<name>, d<name>");
        }
    }
}
