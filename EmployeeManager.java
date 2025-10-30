import java.io.*;
import java.util.*;

public class EmployeeManager {

    
    static class Constants {
        public static final String FILE_PATH = "employees.txt";

        public static final String MSG_LOADING = "Loading data ...";
        public static final String MSG_DATA_LOADED = "Data Loaded.";
        public static final String MSG_ERROR_READING = "Error reading file: ";
        public static final String MSG_ERROR_WRITING = "Error writing file: ";

        public static final String CMD_LIST = "l";
        public static final String CMD_RANDOM = "s";
        public static final String CMD_ADD = "+";
        public static final String CMD_SEARCH = "?";
        public static final String CMD_COUNT = "c";
        public static final String CMD_UPDATE = "u";

        public static final String MSG_EMP_FOUND = "Employee found!";
        public static final String MSG_EMP_NOT_FOUND = "Employee not found.";
        public static final String MSG_UPDATED = "Employee updated successfully.";
        public static final String MSG_NOT_UPDATED = "Employee not found to update.";
        public static final String MSG_INVALID_CMD = "Error: Invalid command. Try l, s, +name, ?name, c, or u.";
        public static final String MSG_NO_ARGS = "Error: No arguments provided. Try l, s, +name, ?name, c, or u.";
    }

    
    private static List<String> readEmployeesFromFile() {
        List<String> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.FILE_PATH))) {
            String line = reader.readLine();
            if (line != null) {
                employees = new ArrayList<>(Arrays.asList(line.split(",")));
            }
        } catch (IOException e) {
            System.out.println(Constants.MSG_ERROR_READING + e.getMessage());
        }
        return employees;
    }

    private static void writeEmployeesToFile(List<String> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_PATH))) {
            writer.write(String.join(",", employees));
        } catch (IOException e) {
            System.out.println(Constants.MSG_ERROR_WRITING + e.getMessage());
        }
    }

    
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println(Constants.MSG_NO_ARGS);
            return;
        }

        String command = args[0];

        if (command.equals(Constants.CMD_LIST)) {
            System.out.println(Constants.MSG_LOADING);
            List<String> employees = readEmployeesFromFile();
            for (String emp : employees) {
                System.out.println(emp.trim());
            }
            System.out.println(Constants.MSG_DATA_LOADED);
        }

        else if (command.equals(Constants.CMD_RANDOM)) {
            System.out.println(Constants.MSG_LOADING);
            List<String> employees = readEmployeesFromFile();
            if (!employees.isEmpty()) {
                Random rand = new Random();
                int randomIndex = rand.nextInt(employees.size());
                System.out.println("Random Employee: " + employees.get(randomIndex).trim());
            }
            System.out.println(Constants.MSG_DATA_LOADED);
        }

        else if (command.startsWith(Constants.CMD_ADD)) {
            String newEmployee = command.substring(1);
            System.out.println(Constants.MSG_LOADING);
            List<String> employees = readEmployeesFromFile();
            employees.add(newEmployee);
            writeEmployeesToFile(employees);
            System.out.println(Constants.MSG_DATA_LOADED);
        }

        else if (command.startsWith(Constants.CMD_SEARCH)) {
            String searchName = command.substring(1);
            System.out.println(Constants.MSG_LOADING);
            List<String> employees = readEmployeesFromFile();
            if (employees.contains(searchName)) {
                System.out.println(Constants.MSG_EMP_FOUND);
            } else {
                System.out.println(Constants.MSG_EMP_NOT_FOUND);
            }
            System.out.println(Constants.MSG_DATA_LOADED);
        }

        else if (command.equals(Constants.CMD_COUNT)) {
            System.out.println(Constants.MSG_LOADING);
            List<String> employees = readEmployeesFromFile();
            System.out.println("Total Employees: " + employees.size());
            System.out.println(Constants.MSG_DATA_LOADED);
        }

        else if (command.startsWith(Constants.CMD_UPDATE)) {
            String targetName = command.substring(1);
            System.out.println(Constants.MSG_LOADING);
            List<String> employees = readEmployeesFromFile();

            boolean updated = false;
            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i).equals(targetName)) {
                    employees.set(i, "Updated");
                    updated = true;
                }
            }

            writeEmployeesToFile(employees);
            if (updated) {
                System.out.println(Constants.MSG_UPDATED);
            } else {
                System.out.println(Constants.MSG_NOT_UPDATED);
            }
            System.out.println(Constants.MSG_DATA_LOADED);
        }

        else {
            System.out.println(Constants.MSG_INVALID_CMD);
        }
    }
}
