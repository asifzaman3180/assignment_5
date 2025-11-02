// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        // Argument validation
        if (args.length != 1) {
            System.out.println("Error: Invalid number of arguments.");
            System.out.println("Usage: java EmployeeManager [l | s | +name | ?name | c | u | dname]");
            return;
        }

        // Check command arguments
        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployees();
                for (String employee : employees) {
                    System.out.println(employee.trim());
                }
            } catch (Exception exception) {}
            System.out.println("Data Loaded.");
        }

        else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployees();
                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);
                System.out.println(employees[randomIndex].trim());
            } catch (Exception exception) {}
            System.out.println("Data Loaded.");
        }

        else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                String nameToAdd = args[0].substring(1);
                BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt", true));
                writer.write(", " + nameToAdd);
                writer.close();
            } catch (Exception exception) {}
            System.out.println("Data Loaded.");
        }

        else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployees();
                String searchName = args[0].substring(1);
                boolean found = false;
                for (String employee : employees) {
                    if (employee.trim().equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                        break;
                    }
                }
            } catch (Exception exception) {}
            System.out.println("Data Loaded.");
        }

        else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployees();
                String fullLine = String.join(",", employees);
                char[] chars = fullLine.toCharArray();
                boolean inWord = false;
                int wordCount = 0;
                for (char character : chars) {
                    if (character == ' ') {
                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }
                System.out.println(wordCount + " word(s) found, " + chars.length + " characters total.");
            } catch (Exception exception) {}
            System.out.println("Data Loaded.");
        }

        else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployees();
                String nameToUpdate = args[0].substring(1);
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].trim().equals(nameToUpdate)) {
                        employees[i] = "Updated";
                    }
                }
                writeEmployees(employees, false);
            } catch (Exception exception) {}
            System.out.println("Data Updated.");
        }

        else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployees();
                String nameToDelete = args[0].substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.removeIf(emp -> emp.trim().equals(nameToDelete));
                writeEmployees(employeeList.toArray(new String[0]), false);
            } catch (Exception exception) {}
            System.out.println("Data Deleted.");
        }
    }

    //  Helper method to read all employees from file
    private static String[] readEmployees() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")));
        String line = reader.readLine();
        reader.close();
        return line.split(",");
    }

    //  Helper method to write employees to file
    private static void writeEmployees(String[] employees, boolean append) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt", append));
        writer.write(String.join(",", employees));
        writer.close();
    }
}
