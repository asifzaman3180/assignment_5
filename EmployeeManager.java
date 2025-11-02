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

    // ===== File Helpers =====
    private static String[] readEmployees() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            return (line == null || line.isEmpty()) ? new String[0] : line.split(",");
        }
    }

    private static void writeEmployees(String[] employees) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(String.join(",", employees));
        }
    }

    private static void appendEmployee(String newEmployee) throws IOException {
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

        String input = args[0];
        System.out.println(MSG_LOADING);

        try {
            switch (getCommandType(input)) {
                case CMD_LIST -> {
                    Arrays.stream(readEmployees())
                            .map(String::trim)
                            .forEach(System.out::println);
                    System.out.println(MSG_DATA_LOADED);
                }

                case CMD_SHOW -> {
                    String[] employees = readEmployees();
                    if (employees.length > 0) {
                        System.out.println(employees[new Random().nextInt(employees.length)].trim());
                    }
                    System.out.println(MSG_DATA_LOADED);
                }

                case CMD_ADD -> {
                    appendEmployee(input.substring(1));
                    System.out.println(MSG_DATA_LOADED);
                }

                case CMD_SEARCH -> {
                    String target = input.substring(1);
                    if (Arrays.stream(readEmployees())
                            .map(String::trim)
                            .anyMatch(name -> name.equals(target))) {
                        System.out.println(MSG_EMPLOYEE_FOUND);
                    }
                    System.out.println(MSG_DATA_LOADED);
                }

                case CMD_COUNT -> {
                    String[] employees = readEmployees();
                    System.out.println(employees.length + " word(s) found " +
                            String.join(",", employees).length());
                    System.out.println(MSG_DATA_LOADED);
                }

                case CMD_UPDATE -> {
                    String name = input.substring(1);
                    String[] updated = Arrays.stream(readEmployees())
                            .map(emp -> emp.trim().equals(name) ? "Updated" : emp)
                            .toArray(String[]::new);
                    writeEmployees(updated);
                    System.out.println(MSG_DATA_UPDATED);
                }

                case CMD_DELETE -> {
                    String name = input.substring(1);
                    String[] remaining = Arrays.stream(readEmployees())
                            .map(String::trim)
                            .filter(emp -> !emp.equals(name))
                            .toArray(String[]::new);
                    writeEmployees(remaining);
                    System.out.println(MSG_DATA_DELETED);
                }

                default -> {
                    System.out.println(MSG_INVALID_ARG);
                    System.out.println(MSG_USAGE);
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ===== Command Type Resolver =====
    private static String getCommandType(String arg) {
        if (arg == null || arg.isEmpty()) return "";
        if (arg.equals(CMD_LIST)) return CMD_LIST;
        if (arg.equals(CMD_SHOW)) return CMD_SHOW;
        if (arg.startsWith(CMD_ADD)) return CMD_ADD;
        if (arg.startsWith(CMD_SEARCH)) return CMD_SEARCH;
        if (arg.equals(CMD_COUNT)) return CMD_COUNT;
        if (arg.startsWith(CMD_UPDATE)) return CMD_UPDATE;
        if (arg.startsWith(CMD_DELETE)) return CMD_DELETE;
        return "";
    }
}
