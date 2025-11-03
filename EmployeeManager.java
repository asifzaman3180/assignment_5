// EmployeeManager.java – Task #5
import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Reads the employees from the file and returns a String array
    private static String[] readEmployees() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(Constants.EMPLOYEE_FILE));
        String line = reader.readLine();
        reader.close();
        return line.split(",");
    }

    // Writes the employees array to the file
    private static void writeEmployees(String[] employees) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE));
        writer.write(String.join(",", employees));
        writer.close();
    }

    public static void main(String[] args) {
        // Check for missing arguments
        if (args.length == 0) {
            System.out.println("Error: No arguments provided. Please provide an operation argument.");
            return;
        }

        try {
            if (args[0].equals("l")) {
                System.out.println("Loading data ...");
                String[] employees = readEmployees();
                for (String employee : employees) {
                    System.out.println(employee);
                }
                System.out.println("Data Loaded.");
            } else if (args[0].equals("s")) {
                System.out.println("Loading data ...");
                String[] employees = readEmployees();
                Random rand = new Random();
                int randomIndex = rand.nextInt(employees.length);
                System.out.println(employees[randomIndex]);
                System.out.println("Data Loaded.");
            } else if (args[0].contains("+")) {
                System.out.println("Loading data ...");
                String employeeName = args[0].substring(1);
                String[] employees = readEmployees();
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.add(employeeName);
                writeEmployees(employeeList.toArray(new String[0]));
                System.out.println("Data Loaded.");
            } else if (args[0].contains("?")) {
                System.out.println("Loading data ...");
                String searchName = args[0].substring(1);
                String[] employees = readEmployees();
                boolean found = false;
                for (String employee : employees) {
                    if (employee.equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Employee not found.");
                }
                System.out.println("Data Loaded.");
            } else if (args[0].contains("c")) {
                System.out.println("Loading data ...");
                String[] employees = readEmployees();
                System.out.println(employees.length + " employee(s) found.");
                System.out.println("Data Loaded.");
            } else if (args[0].contains("u")) {
                System.out.println("Loading data ...");
                String employeeName = args[0].substring(1);
                String[] employees = readEmployees();
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(employeeName)) {
                        employees[i] = "Updated";
                    }
                }
                writeEmployees(employees);
                System.out.println("Data Updated.");
            } else if (args[0].contains("d")) {
                System.out.println("Loading data ...");
                String employeeName = args[0].substring(1);
                String[] employees = readEmployees();
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(employeeName);
                writeEmployees(employeeList.toArray(new String[0]));
                System.out.println("Data Deleted.");
            } else {
                System.out.println("Error: Unsupported argument '" + args[0] + "'");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
