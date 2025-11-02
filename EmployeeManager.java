// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Please provide a command (l, s, +Name, ?Name, c, uName, dName).");
            return;
        }
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
            System.out.println(String.join(",", employees));
            Random rand = new Random();
            int index = rand.nextInt(employees.length);
            System.out.println(employees[index]);
            System.out.println("Data Loaded.");

        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            String nameToAdd = args[0].substring(1);
            String[] employees = readEmployees();
            String[] newEmployees = Arrays.copyOf(employees, employees.length + 1);
            newEmployees[newEmployees.length - 1] = nameToAdd;
            writeEmployees(newEmployees);
            System.out.println("Data Loaded.");

        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            String[] employees = readEmployees();
            String searchName = args[0].substring(1);
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
            int wordCount = 0;
            for (String employee : employees) {
                if (!employee.isBlank()) wordCount++;
            }
            System.out.println(wordCount + " word(s) found, total characters: " +
                    Arrays.stream(employees).mapToInt(String::length).sum());
            System.out.println("Data Loaded.");

        } else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            String[] employees = readEmployees();
            String nameToUpdate = args[0].substring(1);
            for (int i = 0; i < employees.length; i++) {
                if (employees[i].equals(nameToUpdate)) {
                    employees[i] = "Updated";
                }
            }
            writeEmployees(employees);
            System.out.println("Data Updated.");

        } else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            String nameToDelete = args[0].substring(1);
            List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployees()));
            employeeList.remove(nameToDelete);
            writeEmployees(employeeList.toArray(new String[0]));
            System.out.println("Data Deleted.");
        }
    }

    //Reusable methods
    private static String[] readEmployees() {
        try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
            String line = reader.readLine();
            if (line == null || line.isBlank()) return new String[0];
            return line.split(",");
        } catch (Exception ex) {
            System.out.println("Error reading employees file.");
            return new String[0];
        }
    }

    private static void writeEmployees(String[] employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"))) {
            writer.write(String.join(",", employees));
        } catch (Exception ex) {
            System.out.println("Error writing employees file.");
        }
    }
}
