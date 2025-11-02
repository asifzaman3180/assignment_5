import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {

        // Task 2: Argument Validation
        if (args == null || args.length != 1) {
            System.out.println("Invalid number of arguments!");
            System.out.println("Usage: java EmployeeManager <command>");
            System.out.println("Available commands:");
            System.out.println("  l            List all employees");
            System.out.println("  s            Show a random employee");
            System.out.println("  +Name        Add employee");
            System.out.println("  ?Name        Search employee");
            System.out.println("  c            Count employees");
            System.out.println("  uName        Update matching employee");
            System.out.println("  dName        Delete employee");
            return;
        }

        //  Task 3: Improved Variable Naming
        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                for (String employee : employees) {
                    System.out.println(employee.trim());
                }
                reader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Loaded.");
        }

        else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);
                System.out.println("Random Employee: " + employees[randomIndex].trim());
                reader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Loaded.");
        }

        else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt", true));
                String employeeName = args[0].substring(1);
                writer.write(", " + employeeName);
                writer.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Loaded.");
        }

        else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                boolean found = false;
                String searchName = args[0].substring(1);
                for (String employee : employees) {
                    if (employee.trim().equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Employee not found!");
                }
                reader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Loaded.");
        }

        else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                System.out.println("Total Employees: " + employees.length);
                reader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Loaded.");
        }

        else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                String employeeName = args[0].substring(1);
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].trim().equals(employeeName)) {
                        employees[i] = "Updated";
                    }
                }
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt"));
                writer.write(String.join(",", employees));
                writer.close();
                reader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Updated.");
        }

        else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                String employeeName = args[0].substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(employeeName);
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt"));
                writer.write(String.join(",", employeeList));
                writer.close();
                reader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Deleted.");
        }

        else {
            System.out.println("Invalid command: " + args[0]);
            System.out.println("Use one of: l, s, +Name, ?Name, c, uName, dName");
        }
    }
}

