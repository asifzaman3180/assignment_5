// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    // 🔹 Task #4: Reusable file operations

    // Read employee data from file
    private static String[] readEmployeeData() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("employees.txt")));
        String line = reader.readLine();
        reader.close();
        return line.split(",");
    }

    // Write employee data to file
    private static void writeEmployeeData(String[] employees, boolean append) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter("employees.txt", append));
        writer.write(String.join(",", employees));
        writer.close();
    }

    public static void main(String[] args) {

        // Validate arguments (from Task #2)
        if (args.length != 1) {
            System.out.println("Error: Invalid number of arguments.");
            System.out.println("Usage: java EmployeeManager [command]");
            System.out.println("Commands:");
            System.out.println("  l   -> List all employees");
            System.out.println("  s   -> Show random employee");
            System.out.println("  +X  -> Add employee named X");
            System.out.println("  ?X  -> Search for employee X");
            System.out.println("  c   -> Count words in file");
            System.out.println("  uX  -> Update employee X");
            System.out.println("  dX  -> Delete employee X");
            return;
        }

        String command = args[0];

        // List all employees
        if (command.equals("l")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeeData();
                for (String emp : employees) {
                    System.out.println(emp);
                }
            } catch (Exception e) {
                System.out.println("Error reading employee data.");
            }
            System.out.println("Data Loaded.");

        // Show random employee
        } else if (command.equals("s")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeeData();
                Random rand = new Random();
                System.out.println(employees[rand.nextInt(employees.length)]);
            } catch (Exception e) {
                System.out.println("Error reading employee data.");
            }
            System.out.println("Data Loaded.");

        // Add new employee
        } else if (command.contains("+")) {
            System.out.println("Loading data ...");
            try {
                String name = command.substring(1);
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt", true));
                writer.write(", " + name);
                writer.close();
            } catch (Exception e) {
                System.out.println("Error writing to file.");
            }
            System.out.println("Data Loaded.");

        // Search employee
        } else if (command.contains("?")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeeData();
                String searchName = command.substring(1);
                boolean found = false;

                for (String emp : employees) {
                    if (emp.equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Employee not found.");
                }
            } catch (Exception e) {
                System.out.println("Error reading employee data.");
            }
            System.out.println("Data Loaded.");

        // Count words
        } else if (command.contains("c")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeeData();
                String allData = String.join(",", employees);
                int count = allData.split(",").length;
                System.out.println(count + " employee(s) found.");
            } catch (Exception e) {
                System.out.println("Error reading employee data.");
            }
            System.out.println("Data Loaded.");

        // Update employee
        } else if (command.contains("u")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeeData();
                String name = command.substring(1);

                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(name)) {
                        employees[i] = "Updated";
                    }
                }

                writeEmployeeData(employees, false);
            } catch (Exception e) {
                System.out.println("Error updating employee data.");
            }
            System.out.println("Data Updated.");

        // Delete employee
        } else if (command.contains("d")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeeData();
                String name = command.substring(1);
                List<String> list = new ArrayList<>(Arrays.asList(employees));

                list.remove(name);
                writeEmployeeData(list.toArray(new String[0]), false);
            } catch (Exception e) {
                System.out.println("Error deleting employee.");
            }
            System.out.println("Data Deleted.");

        } else {
            System.out.println("Error: Unknown command '" + command + "'");
        }
    }
}
