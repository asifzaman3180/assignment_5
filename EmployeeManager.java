import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Error: Exactly one argument is required.");
            return;
        }

        String command = args[0];

        try {
            if (command.equals("l")) {
                loadEmployees();
            } 
            else if (command.equals("s")) {
                showRandomEmployee();
            } 
            else if (command.startsWith("+")) {
                addEmployee(command.substring(1));
            } 
            else if (command.startsWith("?")) {
                searchEmployee(command.substring(1));
            } 
            else if (command.equals("c")) {
                countEmployees();
            }
            else if (command.startsWith("u")) {
                updateEmployee(command.substring(1));
            } 
            else if (command.startsWith("d")) {
                deleteEmployee(command.substring(1));
            } 
            else {
                System.err.println("Error: Unsupported command '" + command + "'");
            }
        } catch (IOException e) {
            System.err.println("I/O Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
        }
    }

    private static void loadEmployees() throws IOException {
        System.out.println(Constants.LOADING_MSG);
        String[] employees = readEmployees();
        if (employees != null) {
            for (String emp : employees) System.out.println(emp);
        } else {
            System.out.println("No employee data found.");
        }
        System.out.println(Constants.DATA_LOADED_MSG);
    }

    private static void showRandomEmployee() throws IOException {
        System.out.println(Constants.LOADING_MSG);
        String[] employees = readEmployees();
        if (employees != null && employees.length > 0) {
            Random rand = new Random();
            System.out.println(employees[rand.nextInt(employees.length)]);
        } else {
            System.out.println("No employee data found.");
        }
        System.out.println(Constants.DATA_LOADED_MSG);
    }

    private static void addEmployee(String employee) throws IOException {
        System.out.println(Constants.LOADING_MSG);
        appendFile(employee.startsWith(",") ? employee : "," + employee);
        System.out.println(Constants.DATA_LOADED_MSG);
    }

    private static void searchEmployee(String employee) throws IOException {
        System.out.println(Constants.LOADING_MSG);
        String[] employees = readEmployees();
        if (employees != null) {
            boolean found = Arrays.stream(employees).anyMatch(emp -> emp.equals(employee));
            System.out.println(found ? "Employee found!" : "Employee not found.");
        } else {
            System.out.println("No employee data found.");
        }
        System.out.println(Constants.DATA_LOADED_MSG);
    }

    private static void countEmployees() throws IOException {
        System.out.println(Constants.LOADING_MSG);
        String[] employees = readEmployees();
        if (employees != null) {
            int wordCount = employees.length;
            int charCount = Arrays.stream(employees).mapToInt(String::length).sum();
            System.out.println(wordCount + " employee(s), " + charCount + " character(s)");
        } else {
            System.out.println("No employee data found.");
        }
        System.out.println(Constants.DATA_LOADED_MSG);
    }

    private static void updateEmployee(String target) throws IOException {
        System.out.println(Constants.LOADING_MSG);
        String[] employees = readEmployees();
        if (employees != null) {
            boolean updated = false;
            for (int i = 0; i < employees.length; i++) {
                if (employees[i].equals(target)) {
                    employees[i] = "Updated";
                    updated = true;
                }
            }
            if (updated) {
                writeFile(String.join(",", employees));
                System.out.println("Data Updated.");
            } else {
                System.out.println("Employee not found.");
            }
        } else {
            System.out.println("No employee data found.");
        }
    }

    private static void deleteEmployee(String target) throws IOException {
        System.out.println(Constants.LOADING_MSG);
        String[] employees = readEmployees();
        if (employees != null) {
            List<String> list = new ArrayList<>(Arrays.asList(employees));
            boolean removed = list.remove(target);
            if (removed) {
                writeFile(String.join(",", list));
                System.out.println("Data Deleted.");
            } else {
                System.out.println("Employee not found.");
            }
        } else {
            System.out.println("No employee data found.");
        }
    }

    private static String[] readEmployees() throws IOException {
        File file = new File(Constants.EMPLOYEE_FILE);
        if (!file.exists()) return null;

        try (BufferedReader read = new BufferedReader(new FileReader(file))) {
            String line = read.readLine();
            return (line != null && !line.isEmpty()) ? line.split(",") : null;
        }
    }

    private static void writeFile(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE))) {
            writer.write(content);
        }
    }

    private static void appendFile(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE, true))) {
            writer.write(content);
        }
    }
}
