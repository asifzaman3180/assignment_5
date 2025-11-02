// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        // Argument validation
        if (args.length != 1) {
            System.out.println(Constants.ERROR_INVALID_ARGS);
            System.out.println(Constants.USAGE_INFO);
            return;
        }

        // Check command arguments
        if (args[0].equals("l")) {
            System.out.println(Constants.LOADING_DATA);
            try {
                for (String employee : readEmployees()) {
                    System.out.println(employee.trim());
                }
            } catch (Exception exception) {}
            System.out.println(Constants.DATA_LOADED);
        }

        else if (args[0].equals("s")) {
            System.out.println(Constants.LOADING_DATA);
            try {
                String[] employees = readEmployees();
                System.out.println(employees[new Random().nextInt(employees.length)].trim());
            } catch (Exception exception) {}
            System.out.println(Constants.DATA_LOADED);
        }

        else if (args[0].contains("+")) {
            System.out.println(Constants.LOADING_DATA);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE, true))) {
                writer.write(", " + args[0].substring(1));
            } catch (Exception exception) {}
            System.out.println(Constants.DATA_LOADED);
        }

        else if (args[0].contains("?")) {
            System.out.println(Constants.LOADING_DATA);
            try {
                String searchName = args[0].substring(1);
                for (String employee : readEmployees()) {
                    if (employee.trim().equals(searchName)) {
                        System.out.println("Employee found!");
                        break;
                    }
                }
            } catch (Exception exception) {}
            System.out.println(Constants.DATA_LOADED);
        }

        else if (args[0].contains("c")) {
            System.out.println(Constants.LOADING_DATA);
            try {
                String content = String.join(",", readEmployees());
                int wordCount = 0;
                boolean inWord = false;
                for (char ch : content.toCharArray()) {
                    if (ch == ' ') {
                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }
                System.out.println(wordCount + " word(s) found, " + content.length() + " characters total.");
            } catch (Exception exception) {}
            System.out.println(Constants.DATA_LOADED);
        }

        else if (args[0].contains("u")) {
            System.out.println(Constants.LOADING_DATA);
            try {
                String[] employees = readEmployees();
                String nameToUpdate = args[0].substring(1);
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].trim().equals(nameToUpdate)) {
                        employees[i] = "Updated";
                    }
                }
                writeEmployees(employees, false);
            } catch (Exception exception) {}
            System.out.println(Constants.DATA_UPDATED);
        }

        else if (args[0].contains("d")) {
            System.out.println(Constants.LOADING_DATA);
            try {
                List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployees()));
                employeeList.removeIf(emp -> emp.trim().equals(args[0].substring(1)));
                writeEmployees(employeeList.toArray(new String[0]), false);
            } catch (Exception exception) {}
            System.out.println(Constants.DATA_DELETED);
        }
    }

    // Helper method to read all employees from file
    private static String[] readEmployees() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(Constants.EMPLOYEE_FILE)))) {
            return reader.readLine().split(",");
        }
    }

    // Helper method to write employees to file
    private static void writeEmployees(String[] employees, boolean append) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE, append))) {
            writer.write(String.join(",", employees));
        }
    }
}
