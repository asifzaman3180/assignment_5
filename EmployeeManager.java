
import java.io.*;
import java.util.*;

public class EmployeeManager {

    private static final String FILE_NAME = "employees.txt";

    // Reusable method for reading file
    private static List<String> readEmployees() {
        List<String> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                String[] arr = line.split(",");
                for (String emp : arr) {
                    employees.add(emp.trim());
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return employees;
    }

    //  Reusable method for writing file
    private static void writeEmployees(List<String> employees, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, append))) {
            String data = String.join(",", employees);
            writer.write(data);
        } catch (Exception e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No argument provided.");
            return;
        }

        String command = args[0];

        if (command.equals("l")) {
            System.out.println("Loading data ...");
            List<String> employees = readEmployees();
            for (String emp : employees) {
                System.out.println(emp);
            }
            System.out.println("Data Loaded.");

        } else if (command.equals("s")) {
            System.out.println("Loading data ...");
            List<String> employees = readEmployees();
            if (!employees.isEmpty()) {
                Random rand = new Random();
                System.out.println(employees.get(rand.nextInt(employees.size())));
            }
            System.out.println("Data Loaded.");

        } else if (command.startsWith("+")) {
            System.out.println("Loading data ...");
            List<String> employees = readEmployees();
            String newName = command.substring(1);
            employees.add(newName);
            writeEmployees(employees, false);
            System.out.println("Data Saved.");

        } else if (command.startsWith("?")) {
            System.out.println("Loading data ...");
            List<String> employees = readEmployees();
            String search = command.substring(1);
            if (employees.contains(search)) {
                System.out.println("Employee found!");
            } else {
                System.out.println("Employee not found!");
            }
            System.out.println("Data Loaded.");

        } else if (command.equals("c")) {
            System.out.println("Loading data ...");
            List<String> employees = readEmployees();
            System.out.println(employees.size() + " employee(s) found.");
            System.out.println("Data Loaded.");

        } else if (command.startsWith("u")) {
            System.out.println("Loading data ...");
            List<String> employees = readEmployees();
            String name = command.substring(1);
            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i).equals(name)) {
                    employees.set(i, "Updated");
                }
            }
            writeEmployees(employees, false);
            System.out.println("Data Updated.");

        } else if (command.startsWith("d")) {
            System.out.println("Loading data ...");
            List<String> employees = readEmployees();
            String name = command.substring(1);
            employees.removeIf(e -> e.equals(name));
            writeEmployees(employees, false);
            System.out.println("Data Deleted.");
        }
    }
}
