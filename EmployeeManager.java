// EmployeeManager.java – Task #7
import java.io.*;
import java.util.*;

public class EmployeeManager {

    private static String[] readEmployees() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(Constants.EMPLOYEE_FILE));
        String line = reader.readLine();
        reader.close();
        return line.split(",");
    }

    private static void writeEmployees(String[] employees) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE));
        writer.write(String.join(",", employees));
        writer.close();
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: No arguments provided. Please provide an operation argument.");
            return;
        }

        try {
            String arg = args[0];

            if (arg.equals("l")) {
                System.out.println("Loading data ...");
                for (String employee : readEmployees()) {
                    System.out.println(employee);
                }
                System.out.println("Data Loaded.");
            } else if (arg.equals("s")) {
                System.out.println("Loading data ...");
                String[] employees = readEmployees();
                System.out.println(employees[new Random().nextInt(employees.length)]);
                System.out.println("Data Loaded.");
            } else if (arg.startsWith("+")) {
                System.out.println("Loading data ...");
                String employeeName = arg.substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployees()));
                employeeList.add(employeeName);
                writeEmployees(employeeList.toArray(new String[0]));
                System.out.println("Data Loaded.");
            } else if (arg.startsWith("?")) {
                System.out.println("Loading data ...");
                String searchName = arg.substring(1);
                boolean found = Arrays.asList(readEmployees()).contains(searchName);
                System.out.println(found ? "Employee found!" : "Employee not found.");
                System.out.println("Data Loaded.");
            } else if (arg.startsWith("c")) {
                System.out.println("Loading data ...");
                System.out.println(readEmployees().length + " employee(s) found.");
                System.out.println("Data Loaded.");
            } else if (arg.startsWith("u")) {
                System.out.println("Loading data ...");
                String employeeName = arg.substring(1);
                String[] employees = readEmployees();
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(employeeName)) employees[i] = "Updated";
                }
                writeEmployees(employees);
                System.out.println("Data Updated.");
            } else if (arg.startsWith("d")) {
                System.out.println("Loading data ...");
                String employeeName = arg.substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployees()));
                employeeList.remove(employeeName);
                writeEmployees(employeeList.toArray(new String[0]));
                System.out.println("Data Deleted.");
            } else {
                System.out.println("Error: Unsupported argument '" + arg + "'");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
