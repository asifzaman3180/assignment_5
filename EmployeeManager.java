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

    private static List<String> readEmployeesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                return new ArrayList<>(Arrays.asList(line.split(",")));
            }
        } catch (IOException e) {
            // Handle silently
        }
        return new ArrayList<>();
    }

    private static void writeEmployeesToFile(List<String> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEE_FILE))) {
            writer.write(String.join(",", employees));
        } catch (IOException e) {
            // Handle silently
        }
    }

    private static void appendEmployeeToFile(String employeeName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEE_FILE, true))) {
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

        switch (command.charAt(0)) {

            case 'l':
                System.out.println(LOADING_DATA);
                readEmployeesFromFile().forEach(System.out::println);
                System.out.println(DATA_LOADED);
                break;

            case 's':
                System.out.println(LOADING_DATA);
                List<String> employees = readEmployeesFromFile();
                if (!employees.isEmpty()) {
                    System.out.println(employees.get(new Random().nextInt(employees.size())));
                }
                System.out.println(DATA_LOADED);
                break;

            case '+':
                if (command.length() == 1) {
                    System.out.println(NO_NAME_ADD);
                    return;
                }
                System.out.println(LOADING_DATA);
                appendEmployeeToFile(command.substring(1));
                System.out.println(DATA_LOADED);
                break;

            case '?':
                if (command.length() == 1) {
                    System.out.println(NO_NAME_SEARCH);
                    return;
                }
                System.out.println(LOADING_DATA);
                if (readEmployeesFromFile().contains(command.substring(1))) {
                    System.out.println(EMPLOYEE_FOUND);
                } else {
                    System.out.println("Employee not found!");
                }
                System.out.println(DATA_LOADED);
                break;

            case 'c':
                System.out.println(LOADING_DATA);
                List<String> empList = readEmployeesFromFile();
                long wordCount = empList.stream().filter(e -> !e.isBlank()).count();
                int totalChars = empList.stream().mapToInt(String::length).sum();
                System.out.println(wordCount + " word(s) found, total characters: " + totalChars);
                System.out.println(DATA_LOADED);
                break;

            case 'u':
                if (command.length() == 1) {
                    System.out.println(NO_NAME_UPDATE);
                    return;
                }
                System.out.println(LOADING_DATA);
                List<String> empUpdateList = readEmployeesFromFile();
                String empToUpdate = command.substring(1);
                if (!empUpdateList.contains(empToUpdate)) {
                    System.out.println("Employee not found to update!");
                } else {
                    empUpdateList.replaceAll(e -> e.equals(empToUpdate) ? "Updated" : e);
                    writeEmployeesToFile(empUpdateList);
                    System.out.println(DATA_UPDATED);
                }
                break;

            case 'd':
                if (command.length() == 1) {
                    System.out.println(NO_NAME_DELETE);
                    return;
                }
                System.out.println(LOADING_DATA);
                List<String> empDeleteList = readEmployeesFromFile();
                String empToDelete = command.substring(1);
                if (!empDeleteList.contains(empToDelete)) {
                    System.out.println("Employee not found to delete!");
                } else {
                    empDeleteList.remove(empToDelete);
                    writeEmployeesToFile(empDeleteList);
                    System.out.println(DATA_DELETED);
                }
                break;

            default:
                System.out.println(String.format(UNSUPPORTED_COMMAND, command));
                System.out.println(USAGE);
        }
    }
}
