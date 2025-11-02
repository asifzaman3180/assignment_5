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

     //Lists all employees in the file. 
    private static void listEmployees() throws IOException {
        System.out.println(Constants.LOADING_MSG);
        String[] employees = readEmployeeData();
        if (employees != null) {
            for (String employee : employees) {
                System.out.println(employee);
            }
        } else {
            System.out.println("No employee data found.");
        }
        System.out.println(Constants.DATA_LOADED_MSG);
    }

    //Prints a random employee from the file. 
    private static void showRandomEmployee() throws IOException {
        System.out.println(Constants.LOADING_MSG);
        String[] employees = readEmployeeData();
        if (employees != null && employees.length > 0) {
            Random random = new Random();
            System.out.println(employees[random.nextInt(employees.length)]);
        } else {
            System.out.println("No employee data found.");
        }
        System.out.println(Constants.DATA_LOADED_MSG);
    }

    //Adds a new employee to the file. 
    private static void addEmployee(String employeeName) throws IOException {
        System.out.println(Constants.LOADING_MSG);
        appendToFile(employeeName.startsWith(",") ? employeeName : "," + employeeName);
        System.out.println(Constants.DATA_LOADED_MSG);
    }

    //Searches for an employee in the file. 
    private static void searchEmployee(String employeeName) throws IOException {
        System.out.println(Constants.LOADING_MSG);
        String[] employees = readEmployeeData();
        if (employees != null) {
            boolean found = Arrays.stream(employees).anyMatch(emp -> emp.equals(employeeName));
            System.out.println(found ? "Employee found!" : "Employee not found.");
        } else {
            System.out.println("No employee data found.");
        }
        System.out.println(Constants.DATA_LOADED_MSG);
    }

    //Counts the number of employees and total characters in the file. 
    private static void countEmployees() throws IOException {
        System.out.println(Constants.LOADING_MSG);
        String[] employees = readEmployeeData();
        if (employees != null) {
            int employeeCount = employees.length;
            int totalCharacters = Arrays.stream(employees).mapToInt(String::length).sum();
            System.out.println(employeeCount + " employee(s), " + totalCharacters + " character(s)");
        } else {
            System.out.println("No employee data found.");
        }
        System.out.println(Constants.DATA_LOADED_MSG);
    }

    // Updates an employee name to "Updated" if it exists. 
    private static void updateEmployee(String employeeName) throws IOException {
        System.out.println(Constants.LOADING_MSG);
        String[] employees = readEmployeeData();
        if (employees != null) {
            boolean updated = false;
            for (int i = 0; i < employees.length; i++) {
                if (employees[i].equals(employeeName)) {
                    employees[i] = "Updated";
                    updated = true;
                }
            }
            if (updated) {
                writeToFile(String.join(",", employees));
                System.out.println("Data Updated.");
            } else {
                System.out.println("Employee not found.");
            }
        } else {
            System.out.println("No employee data found.");
        }
    }

    // Deletes an employee if it exists in the file. 
    private static void deleteEmployee(String employeeName) throws IOException {
        System.out.println(Constants.LOADING_MSG);
        String[] employees = readEmployeeData();
        if (employees != null) {
            List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
            boolean removed = employeeList.remove(employeeName);
            if (removed) {
                writeToFile(String.join(",", employeeList));
                System.out.println("Data Deleted.");
            } else {
                System.out.println("Employee not found.");
            }
        } else {
            System.out.println("No employee data found.");
        }
    }

    /*Reads employee data from the file.
     * @return Array of employee names, or null if file is missing/empty.
     * @throws IOException if file read fails.
     */
    private static String[] readEmployeeData() throws IOException {
        File file = new File(Constants.EMPLOYEE_FILE);
        if (!file.exists()) return null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            return (line != null && !line.isEmpty()) ? line.split(",") : null;
        }
    }

    //Writes content to the employee file, overwriting existing data. 
    private static void writeToFile(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE))) {
            writer.write(content);
        }
    }

    // Appends content to the employee file.
    private static void appendToFile(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE, true))) {
            writer.write(content);
        }
    }
}