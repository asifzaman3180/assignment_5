// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Reusable file operations (from Task #4)
    private static String[] readEmployeeData() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(Constants.EMPLOYEE_FILE)));
        String line = reader.readLine();
        reader.close();
        return line.split(",");
    }

    private static void writeEmployeeData(String[] employees, boolean append) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.EMPLOYEE_FILE, append));
        writer.write(String.join(",", employees));
        writer.close();
    }

    public static void main(String[] args) {

        // Argument validation (Task #2)
        if (args.length != 1) {
            System.out.println(Constants.USAGE_MESSAGE);
            return;
        }

        String command = args[0];

        // List all employees
        if (command.equals("l")) {
            System.out.println(Constants.LOADING_DATA);
            try {
                for (String emp : readEmployeeData()) {
                    System.out.println(emp);
                }
            } catch (Exception e) {
                System.out.println(Constants.ERROR_READ);
            }
            System.out.println(Constants.DATA_LOADED);

        // Show random employee
        } else if (command.equals("s")) {
            System.out.println(Constants.LOADING_DATA);
            try {
                String[] employees = readEmployeeData();
                System.out.println(employees[new Random().nextInt(employees.length)]);
            } catch (Exception e) {
                System.out.println(Constants.ERROR_READ);
            }
            System.out.println(Constants.DATA_LOADED);

        // Add new employee
        } else if (command.contains("+")) {
            System.out.println(Constants.LOADING_DATA);
            try (BufferedWriter writer = new BufferedWriter(
                    new FileWriter(Constants.EMPLOYEE_FILE, true))) {
                writer.write(", " + command.substring(1));
            } catch (Exception e) {
                System.out.println(Constants.ERROR_WRITE);
            }
            System.out.println(Constants.DATA_LOADED);

        // Search employee
        } else if (command.contains("?")) {
            System.out.println(Constants.LOADING_DATA);
            try {
                String searchName = command.substring(1);
                boolean found = Arrays.asList(readEmployeeData()).contains(searchName);
                System.out.println(found ? "Employee found!" : "Employee not found.");
            } catch (Exception e) {
                System.out.println(Constants.ERROR_READ);
            }
            System.out.println(Constants.DATA_LOADED);

        // Count employees
        } else if (command.contains("c")) {
            System.out.println(Constants.LOADING_DATA);
            try {
                System.out.println(readEmployeeData().length + " employee(s) found.");
            } catch (Exception e) {
                System.out.println(Constants.ERROR_READ);
            }
            System.out.println(Constants.DATA_LOADED);

        // Update employee
        } else if (command.contains("u")) {
            System.out.println(Constants.LOADING_DATA);
            try {
                String[] employees = readEmployeeData();
                String updateName = command.substring(1);

                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(updateName)) {
                        employees[i] = "Updated";
                    }
                }
                writeEmployeeData(employees, false);
            } catch (Exception e) {
                System.out.println(Constants.ERROR_UPDATE);
            }
            System.out.println(Constants.DATA_UPDATED);

        // Delete employee
        } else if (command.contains("d")) {
            System.out.println(Constants.LOADING_DATA);
            try {
                List<String> list = new ArrayList<>(Arrays.asList(readEmployeeData()));
                list.remove(command.substring(1));
                writeEmployeeData(list.toArray(new String[0]), false);
            } catch (Exception e) {
                System.out.println(Constants.ERROR_DELETE);
            }
            System.out.println(Constants.DATA_DELETED);

        } else {
            System.out.println(Constants.ERROR_UNKNOWN + " '" + command + "'");
        }
    }
}
