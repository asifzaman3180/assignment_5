// EmployeeManager.java – Task #9
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
            System.out.println("Error: No arguments provided. Supported arguments: l, s, +<name>, ?<name>, c, u<name>, d<name>");
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
                if (employeeName.isEmpty()) {
                    System.out.println("Error: Please provide a name to add.");
                } else {
                    List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployees()));
                    employeeList.add(employeeName);
                    writeEmployees(employeeList.toArray(new String[0]));
                    System.out.println("Data Loaded.");
                }
            } else if (arg.startsWith("?")) {
                System.out.println("Loading data ...");
                String searchName = arg.substring(1);
                if (searchName.isEmpty()) {
                    System.out.println("Error: Please provide a name to search.");
                } else {
                    boolean found = Arrays.asList(readEmployees()).contains(searchName);
                    System.out.println(found ? "Employee found!" : "Employee not found.");
                }
                System.out.println("Data Loaded.");
            } else if (arg.startsWith("c")) {
                System.out.println("Loading data ...");
                System.out.println("Total employees: " + readEmployees().length);
                System.out.println("Data Loaded.");
            } else if (arg.startsWith("u")) {
                System.out.println("Loading data ...");
                String employeeName = arg.substring(1);
                if (employeeName.isEmpty()) {
                    System.out.println("Error: Please provide a name to update.");
                } else {
                    String[] employees = readEmployees();
                    boolean updated = false;
                    for (int i = 0; i < employees.length; i++) {
                        if (employees[i].equals(employeeName)) {
                            employees[i] = "Updated";
                            updated = true;
                        }
                    }
                    writeEmployees(employees);
                    System.out.println(updated ? "Data Updated." : "Employee not found.");
                }
            } else if (arg.startsWith("d")) {
                System.out.println("Loading data ...");
                String employeeName = arg.substring(1);
                if (employeeName.isEmpty()) {
                    System.out.println("Error: Please provide a name to delete.");
                } else {
                    List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployees()));
                    if (employeeList.remove(employeeName)) {
                        writeEmployees(employeeList.toArray(new String[0]));
                        System.out.println("Data Deleted.");
                    } else {
                        System.out.println("Employee not found.");
                    }
                }
            } else {
                System.out.println("Error: Unsupported argument '" + arg + "'. Supported arguments: l, s, +<name>, ?<name>, c, u<name>, d<name>");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
