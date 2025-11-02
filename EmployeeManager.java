// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    // ===== Constants =====
    private static final String FILE_NAME = "employees.txt";

    // Command constants
    private static final String CMD_LIST = "l";
    private static final String CMD_SHOW = "s";
    private static final String CMD_ADD = "+";
    private static final String CMD_SEARCH = "?";
    private static final String CMD_COUNT = "c";
    private static final String CMD_UPDATE = "u";
    private static final String CMD_DELETE = "d";

    // Message constants
    private static final String MSG_LOADING = "Loading data ...";
    private static final String MSG_DATA_LOADED = "Data Loaded.";
    private static final String MSG_DATA_UPDATED = "Data Updated.";
    private static final String MSG_DATA_DELETED = "Data Deleted.";
    private static final String MSG_EMPLOYEE_FOUND = "Employee found!";
    private static final String MSG_INVALID_ARG = "Invalid argument!";
    private static final String MSG_NO_ARGS = "No arguments provided!";
    private static final String MSG_USAGE = """
            Usage:
              l  -> List employees
              s  -> Show random employee
              +X -> Add employee
              ?X -> Search employee
              c  -> Count words/chars
              uX -> Update employee
              dX -> Delete employee
            """;

    // ===== Helper Methods =====
    private static String[] readEmployeesFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            if (line == null || line.isEmpty()) {
                return new String[0];
            }
            return line.split(",");
        }
    }

    private static void writeEmployeesToFile(String[] employees) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(String.join(",", employees));
        }
    }

    private static void appendEmployeeToFile(String newEmployee) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(", " + newEmployee);
        }
    }

    // ===== Main Logic =====
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(MSG_NO_ARGS);
            System.out.println(MSG_USAGE);
            return;
        }

        String command = args[0];

        if (command.equals(CMD_LIST)) {
            System.out.println(MSG_LOADING);
            try {
                String[] employees = readEmployeesFromFile();
                for (String employee : employees) {
                    System.out.println(employee.trim());
                }
            } catch (Exception e) {}
            System.out.println(MSG_DATA_LOADED);

        } else if (command.equals(CMD_SHOW)) {
            System.out.println(MSG_LOADING);
            try {
                String[] employees = readEmployeesFromFile();
                if (employees.length > 0) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(employees.length);
                    System.out.println(employees[randomIndex].trim());
                }
            } catch (Exception e) {}
            System.out.println(MSG_DATA_LOADED);

        } else if (command.contains(CMD_ADD)) {
            System.out.println(MSG_LOADING);
            try {
                String newEmployee = command.substring(1);
                appendEmployeeToFile(newEmployee);
            } catch (Exception e) {}
            System.out.println(MSG_DATA_LOADED);

        } else if (command.contains(CMD_SEARCH)) {
            System.out.println(MSG_LOADING);
            try {
                String[] employees = readEmployeesFromFile();
                String searchName = command.substring(1);
                boolean found = false;
                for (String employee : employees) {
                    if (employee.trim().equals(searchName)) {
                        System.out.println(MSG_EMPLOYEE_FOUND);
                        found = true;
                        break;
                    }
                }
            } catch (Exception e) {}
            System.out.println(MSG_DATA_LOADED);

        } else if (command.contains(CMD_COUNT)) {
            System.out.println(MSG_LOADING);
            try {
                String[] employees = readEmployeesFromFile();
                int charCount = String.join(",", employees).length();
                System.out.println(employees.length + " word(s) found " + charCount);
            } catch (Exception e) {}
            System.out.println(MSG_DATA_LOADED);

        } else if (command.contains(CMD_UPDATE)) {
            System.out.println(MSG_LOADING);
            try {
                String[] employees = readEmployeesFromFile();
                String nameToUpdate = command.substring(1);
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].trim().equals(nameToUpdate)) {
                        employees[i] = "Updated";
                    }
                }
                writeEmployeesToFile(employees);
            } catch (Exception e) {}
            System.out.println(MSG_DATA_UPDATED);

        } else if (command.contains(CMD_DELETE)) {
            System.out.println(MSG_LOADING);
            try {
                String[] employees = readEmployeesFromFile();
                String nameToDelete = command.substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.removeIf(e -> e.trim().equals(nameToDelete));
                writeEmployeesToFile(employeeList.toArray(new String[0]));
            } catch (Exception e) {}
            System.out.println(MSG_DATA_DELETED);

        } else {
            System.out.println(MSG_INVALID_ARG);
            System.out.println(MSG_USAGE);
        }
    }
}
