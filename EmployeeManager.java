// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {

        // ✅ Argument validation
        if (args.length != 1) {
            System.out.println("Usage:");
            System.out.println("  java EmployeeManager l         // List all employees");
            System.out.println("  java EmployeeManager s         // Show a random employee");
            System.out.println("  java EmployeeManager +Name     // Add a new employee");
            System.out.println("  java EmployeeManager ?Name     // Search for an employee");
            System.out.println("  java EmployeeManager c         // Count employees");
            System.out.println("  java EmployeeManager uName     // Update employee to 'Updated'");
            System.out.println("  java EmployeeManager dName     // Delete an employee");
            return;
        }

        String command = args[0];
        String fileName = "employees.txt";

        if (command.equals("l")) { // List all employees
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
                String line = reader.readLine();
                String[] employees = line.split(",");
                for (String employee : employees) {
                    System.out.println(employee.trim());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Loaded.");

        } else if (command.equals("s")) { // Show random employee
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
                String line = reader.readLine();
                String[] employees = line.split(",");
                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);
                System.out.println(employees[randomIndex].trim());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Loaded.");

        } else if (command.startsWith("+")) { // Add new employee
            System.out.println("Loading data ...");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                String newEmployee = command.substring(1);
                writer.write(", " + newEmployee);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Loaded.");

        } else if (command.startsWith("?")) { // Search employee
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
                String line = reader.readLine();
                String[] employees = line.split(",");
                String searchName = command.substring(1).trim();
                boolean found = false;

                for (String employee : employees) {
                    if (employee.trim().equalsIgnoreCase(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Employee not found.");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Loaded.");

        } else if (command.equals("c")) { // Count employees
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
                String line = reader.readLine();
                String[] employees = line.split(",");
                System.out.println(employees.length + " employee(s) found. Total characters: " + line.length());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Loaded.");

        } else if (command.startsWith("u")) { // Update employee
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
                String line = reader.readLine();
                String[] employees = line.split(",");
                String updateName = command.substring(1).trim();

                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].trim().equalsIgnoreCase(updateName)) {
                        employees[i] = "Updated";
                    }
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                    writer.write(String.join(",", employees));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Updated.");

        } else if (command.startsWith("d")) { // Delete employee
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
                String line = reader.readLine();
                String[] employees = line.split(",");
                String deleteName = command.substring(1).trim();

                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.removeIf(emp -> emp.trim().equalsIgnoreCase(deleteName));

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                    writer.write(String.join(",", employeeList));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Deleted.");

        } else {
            System.out.println("Invalid command! Use one of: l, s, +name, ?name, c, uname, dname");
        }
    }
}
