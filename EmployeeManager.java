// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    private static final String EMPLOYEE_FILE = "employees.txt";

    private static List<String> readEmployeesFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {
            String fileContent = reader.readLine();
            if (fileContent == null || fileContent.trim().isEmpty()) {
                return new ArrayList<>(); // Empty file
            }
            String[] employeeArray = fileContent.split(",");
            List<String> employees = new ArrayList<>();
            for (String emp : employeeArray) {
                employees.add(emp.trim());
            }
            return employees;
        }
    }

    private static void writeEmployeesToFile(List<String> employees) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEE_FILE))) {
            writer.write(String.join(", ", employees));
        }
    }

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

        String command = args[0];

        try {

            if (command.equals("l")) {
                System.out.println("Loading data ...");
                List<String> employees = readEmployeesFromFile();
                employees.forEach(System.out::println);
                System.out.println("Data Loaded.");
            }

            else if (command.equals("s")) {
                System.out.println("Loading data ...");
                List<String> employees = readEmployeesFromFile();
                if (employees.isEmpty()) {
                    System.out.println("No employees found.");
                } else {
                    Random random = new Random();
                    int randomIndex = random.nextInt(employees.size());
                    System.out.println("Random employee: " + employees.get(randomIndex));
                }
                System.out.println("Data Loaded.");
            }

            else if (command.startsWith("+")) {
                System.out.println("Loading data ...");
                String newEmployee = command.substring(1).trim();
                List<String> employees = readEmployeesFromFile();
                employees.add(newEmployee);
                writeEmployeesToFile(employees);
                System.out.println("Employee added: " + newEmployee);
                System.out.println("Data Saved.");
            }


            else if (command.startsWith("?")) {
                System.out.println("Loading data ...");
                String searchName = command.substring(1).trim();
                List<String> employees = readEmployeesFromFile();
                if (employees.contains(searchName)) {
                    System.out.println("Employee found: " + searchName);
                } else {
                    System.out.println("Employee not found.");
                }
                System.out.println("Data Loaded.");
            }


            else if (command.equals("c")) {
                System.out.println("Loading data ...");
                List<String> employees = readEmployeesFromFile();
                System.out.println("Total employees: " + employees.size());
                System.out.println("Data Loaded.");
            }


            else if (command.startsWith("u")) {
                System.out.println("Loading data ...");
                String nameToUpdate = command.substring(1).trim();
                List<String> employees = readEmployeesFromFile();
                boolean updated = false;

                for (int i = 0; i < employees.size(); i++) {
                    if (employees.get(i).equals(nameToUpdate)) {
                        employees.set(i, "Updated");
                        updated = true;
                        break;
                    }
                }

                if (updated) {
                    writeEmployeesToFile(employees);
                    System.out.println("Employee updated successfully.");
                } else {
                    System.out.println("Employee not found.");
                }
                System.out.println("Data Updated.");
            }

 
            else if (command.startsWith("d")) {
                System.out.println("Loading data ...");
                String nameToDelete = command.substring(1).trim();
                List<String> employees = readEmployeesFromFile();

                boolean removed = employees.removeIf(emp -> emp.equals(nameToDelete));

                if (removed) {
                    writeEmployeesToFile(employees);
                    System.out.println("Employee deleted: " + nameToDelete);
                } else {
                    System.out.println("Employee not found.");
                }
                System.out.println("Data Deleted.");
            }


            else {
                System.out.println("Invalid command. Please check usage:");
                System.out.println("  java EmployeeManager l / s / + / ? / c / u / d");
            }

        } catch (IOException ex) {
            System.out.println("Error accessing file: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
