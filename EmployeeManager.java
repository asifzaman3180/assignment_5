
// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;
// File Name: Constants.java
public class Constants {

    public static final String EMPLOYEE_FILE_PATH = "employees.txt";

    public static final String DATE_FORMAT = "dd/MM/yyyy";

    public static final String DATA_LOADING = "Loading data ...";
    public static final String DATA_LOADED = "Data Loaded.";
    public static final String DATA_UPDATED = "Data Updated.";
    public static final String DATA_DELETED = "Data Deleted.";

}
public class EmployeeManager {

    private static final String FILE_PATH = Constants.EMPLOYEE_FILE_PATH;

    private static List<String> readEmployeesFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String fileContent = reader.readLine();
            if (fileContent == null || fileContent.trim().isEmpty()) {
                return new ArrayList<>();
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(String.join(", ", employees));
        }
    }

    public static void main(String[] args) {
        // Argument validation
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
                System.out.println(Constants.DATA_LOADING);
                List<String> employees = readEmployeesFromFile();
                employees.forEach(System.out::println);
                System.out.println(Constants.DATA_LOADED);
            }

            else if (command.equals("s")) {
                System.out.println(Constants.DATA_LOADING);
                List<String> employees = readEmployeesFromFile();
                if (employees.isEmpty()) {
                    System.out.println("No employees found.");
                } else {
                    Random random = new Random();
                    int randomIndex = random.nextInt(employees.size());
                    System.out.println("Random employee: " + employees.get(randomIndex));
                }
                System.out.println(Constants.DATA_LOADED);
            }

            else if (command.startsWith("+")) {
                System.out.println(Constants.DATA_LOADING);
                String newEmployee = command.substring(1).trim();
                List<String> employees = readEmployeesFromFile();
                employees.add(newEmployee);
                writeEmployeesToFile(employees);
                System.out.println("Employee added: " + newEmployee);
                System.out.println(Constants.DATA_LOADED);
            }

            else if (command.startsWith("?")) {
                System.out.println(Constants.DATA_LOADING);
                String searchName = command.substring(1).trim();
                List<String> employees = readEmployeesFromFile();
                if (employees.contains(searchName)) {
                    System.out.println("Employee found: " + searchName);
                } else {
                    System.out.println("Employee not found.");
                }
                System.out.println(Constants.DATA_LOADED);
            }

            else if (command.equals("c")) {
                System.out.println(Constants.DATA_LOADING);
                List<String> employees = readEmployeesFromFile();
                System.out.println("Total employees: " + employees.size());
                System.out.println(Constants.DATA_LOADED);
            }

            else if (command.startsWith("u")) {
                System.out.println(Constants.DATA_LOADING);
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
                    System.out.println(Constants.DATA_UPDATED);
                } else {
                    System.out.println("Employee not found.");
                }
            }

            else if (command.startsWith("d")) {
                System.out.println(Constants.DATA_LOADING);
                String nameToDelete = command.substring(1).trim();
                List<String> employees = readEmployeesFromFile();
                boolean removed = employees.removeIf(emp -> emp.equals(nameToDelete));

                if (removed) {
                    writeEmployeesToFile(employees);
                    System.out.println(Constants.DATA_DELETED);
                } else {
                    System.out.println("Employee not found.");
                }
            }

            else {
                System.out.println("Invalid command. Please check usage.");
            }

        } catch (IOException ex) {
            System.out.println("Error accessing file: " + ex.getMessage());
        }
    }
}
