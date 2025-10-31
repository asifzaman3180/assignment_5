// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    // -------------------- Constants --------------------
    private static final String EMPLOYEE_FILE = "employees.txt";
    private static final String NO_ARGUMENTS = "Error: No arguments provided.";
    private static final String USAGE = "Usage: java EmployeeManager <command>";
    private static final String COMMANDS = "Commands: l, s, +<name>, ?<name>, c, u<name>, d<name>";
    private static final String NO_NAME_ADD = "Error: No employee name provided to add.";
    private static final String NO_NAME_SEARCH = "Error: No employee name provided to search.";
    private static final String NO_NAME_UPDATE = "Error: No employee name provided to update.";
    private static final String NO_NAME_DELETE = "Error: No employee name provided to delete.";
    private static final String UNSUPPORTED_COMMAND = "Error: Unsupported command '%s'.";
    private static final String LOADING_DATA = "Loading data ...";
    private static final String DATA_LOADED = "Data Loaded.";
    private static final String DATA_UPDATED = "Data Updated.";
    private static final String DATA_DELETED = "Data Deleted.";
    private static final String EMPLOYEE_FOUND = "Employee found!";
    // ---------------------------------------------------

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

    private static void writeEmployeesToFile(String filePath, List<String> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(String.join(",", employees));
        } catch (IOException e) {
            // Handle silently
        }
    }

    private static void appendEmployeeToFile(String filePath, String employeeName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(", " + employeeName);
        } catch (IOException e) {
            // Handle silently
        }
    }

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println(NO_ARGUMENTS);
            System.out.println(USAGE);
            System.out.println(COMMANDS);
            return;
        }

        String command = args[0];
        String filePath = EMPLOYEE_FILE;

        if (command.equals("l")) {
            System.out.println(LOADING_DATA);
            List<String> employeeList = readEmployeesFromFile(filePath);
            for (String employee : employeeList) {
                System.out.println(employee);
            }
            System.out.println(DATA_LOADED);

        } else if (command.equals("s")) {
            System.out.println(LOADING_DATA);
            List<String> employeeList = readEmployeesFromFile(filePath);
            if (!employeeList.isEmpty()) {
                Random random = new Random();
                int randomIndex = random.nextInt(employeeList.size());
                System.out.println(employeeList.get(randomIndex));
            }
            System.out.println(DATA_LOADED);

        } else if (command.startsWith("+")) {
            if (command.length() == 1) {
                System.out.println(NO_NAME_ADD);
                return;
            }
            System.out.println(LOADING_DATA);
            String newEmployee = command.substring(1);
            appendEmployeeToFile(filePath, newEmployee);
            System.out.println(DATA_LOADED);

        } else if (command.startsWith("?")) {
            if (command.length() == 1) {
                System.out.println(NO_NAME_SEARCH);
                return;
            }
            System.out.println(LOADING_DATA);
            List<String> employeeList = readEmployeesFromFile(filePath);
            String searchEmployee = command.substring(1);
            if (employeeList.contains(searchEmployee)) {
                System.out.println(EMPLOYEE_FOUND);
            }
            System.out.println(DATA_LOADED);

        } else if (command.equals("c")) {
            System.out.println(LOADING_DATA);
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
            System.out.println(DATA_LOADED);

        } else if (command.startsWith("u")) {
            if (command.length() == 1) {
                System.out.println(NO_NAME_UPDATE);
                return;
            }
            System.out.println(LOADING_DATA);
            List<String> employeeList = readEmployeesFromFile(filePath);
            String employeeToUpdate = command.substring(1);

            for (int i = 0; i < employeeList.size(); i++) {
                if (employeeList.get(i).equals(employeeToUpdate)) {
                    employeeList.set(i, "Updated");
                }
            }

            writeEmployeesToFile(filePath, employeeList);
            System.out.println(DATA_UPDATED);

        } else if (command.startsWith("d")) {
            if (command.length() == 1) {
                System.out.println(NO_NAME_DELETE);
                return;
            }
            System.out.println(LOADING_DATA);
            List<String> employeeList = readEmployeesFromFile(filePath);
            String employeeToDelete = command.substring(1);
            employeeList.remove(employeeToDelete);
            writeEmployeesToFile(filePath, employeeList);
            System.out.println(DATA_DELETED);

        } else {
            System.out.println(String.format(UNSUPPORTED_COMMAND, command));
            System.out.println(USAGE);
        }
    }
}
