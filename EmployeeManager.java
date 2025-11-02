// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Invalid number of arguments!");
            System.out.println("Usage:");
            System.out.println("  java EmployeeManager l          -> List all employees");
            System.out.println("  java EmployeeManager s          -> Show random employee");
            System.out.println("  java EmployeeManager +Name      -> Add new employee");
            System.out.println("  java EmployeeManager ?Name      -> Search employee");
            System.out.println("  java EmployeeManager c          -> Count employees");
            System.out.println("  java EmployeeManager uName      -> Update employee");
            System.out.println("  java EmployeeManager dName      -> Delete employee");
            return;
        }


        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
                String fileContent = reader.readLine();
                String[] employees = fileContent.split(",");
                for (String employee : employees) {
                    System.out.println(employee.trim());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Loaded.");
        }


        else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
                String fileContent = reader.readLine();
                String[] employees = fileContent.split(",");
                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);
                System.out.println(employees[randomIndex].trim());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Loaded.");
        }


        else if (args[0].startsWith("+")) {
            System.out.println("Loading data ...");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt", true))) {
                String newEmployee = args[0].substring(1);
                writer.write(", " + newEmployee);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Loaded.");
        }


        else if (args[0].startsWith("?")) {
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
                String fileContent = reader.readLine();
                String[] employees = fileContent.split(",");
                String searchName = args[0].substring(1);
                boolean found = false;

                for (String employee : employees) {
                    if (employee.trim().equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                        break;
                    }
                }

                if (!found)
                    System.out.println("Employee not found!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Loaded.");
        }

     
        else if (args[0].equals("c")) {
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
                String fileContent = reader.readLine();
                String[] employees = fileContent.split(",");
                System.out.println("Total employees: " + employees.length);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Loaded.");
        }

     
        else if (args[0].startsWith("u")) {
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
                String fileContent = reader.readLine();
                String[] employees = fileContent.split(",");
                String nameToUpdate = args[0].substring(1);

                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].trim().equals(nameToUpdate)) {
                        employees[i] = "Updated";
                    }
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"))) {
                    writer.write(String.join(",", employees));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Updated.");
        }

      
        else if (args[0].startsWith("d")) {
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
                String fileContent = reader.readLine();
                String[] employees = fileContent.split(",");
                String nameToDelete = args[0].substring(1);

                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.removeIf(emp -> emp.trim().equals(nameToDelete));

                try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"))) {
                    writer.write(String.join(",", employeeList));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Data Deleted.");
        }
    }
}
