import java.io.*;
import java.util.*;

public class EmployeeManager {

    private static final String FILE_PATH = Constants.EMPLOYEE_FILE_PATH;

    private static List<String> readEmployeesFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String fileContent = reader.readLine();
            if (fileContent == null || fileContent.trim().isEmpty()) return new ArrayList<>();
            return Arrays.asList(fileContent.split(",\\s*"));
        }
    }

    private static void writeEmployeesToFile(List<String> employees) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
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

        try {
            String arg = args[0];

            if (arg.equals("l")) {
                System.out.println(Constants.DATA_LOADING);
                readEmployeesFromFile().forEach(System.out::println);
                System.out.println(Constants.DATA_LOADED);
            }

            else if (arg.equals("s")) {
                List<String> employees = readEmployeesFromFile();
                if (employees.isEmpty()) System.out.println("No employees found.");
                else System.out.println("Random employee: " + employees.get(new Random().nextInt(employees.size())));
            }

            else if (arg.startsWith("+")) {
                List<String> employees = new ArrayList<>(readEmployeesFromFile());
                employees.add(arg.substring(1).trim());
                writeEmployeesToFile(employees);
                System.out.println("Employee added: " + arg.substring(1).trim());
            }

            else if (arg.startsWith("?")) {
                String searchName = arg.substring(1).trim();
                if (readEmployeesFromFile().contains(searchName))
                    System.out.println("Employee found: " + searchName);
                else
                    System.out.println("Employee not found.");
            }

            else if (arg.equals("c")) {
                System.out.println("Total employees: " + readEmployeesFromFile().size());
            }

            else if (arg.startsWith("u")) {
                List<String> employees = new ArrayList<>(readEmployeesFromFile());
                String nameToUpdate = arg.substring(1).trim();
                if (employees.contains(nameToUpdate)) {
                    employees.set(employees.indexOf(nameToUpdate), "Updated");
                    writeEmployeesToFile(employees);
                    System.out.println(Constants.DATA_UPDATED);
                } else {
                    System.out.println("Employee not found.");
                }
            }

            else if (arg.startsWith("d")) {
                List<String> employees = new ArrayList<>(readEmployeesFromFile());
                String nameToDelete = arg.substring(1).trim();
                if (employees.remove(nameToDelete)) {
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
