//File Name EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error: Please provide exactly one argument.");
            System.out.println("Usage: java EmployeeManager <command>");
            System.out.println("Commands: l, s, +<name>, ?<name>, c, u<name>, d<name>");
            return;
        }

        String command = args[0];

        if (command.equals("l")) {
            System.out.println("Loading data ...");
            List<String> employees = readEmployees();
            if (employees != null) {
                for (String employee : employees) {
                    System.out.println(employee);
                }
            }
            System.out.println("Data Loaded.");
        } else if (command.equals("s")) {
            System.out.println("Loading data ...");
            List<String> employees = readEmployees();
            if (employees != null && !employees.isEmpty()) {
                System.out.println(String.join(",", employees));
                Random random = new Random();
                int randomIndex = random.nextInt(employees.size());
                System.out.println(employees.get(randomIndex));
            }
            System.out.println("Data Loaded.");
        } else if (command.contains("+")) {
            System.out.println("Loading data ...");
            String newEmployee = command.substring(1);
            appendEmployee(newEmployee);
            System.out.println("Data Loaded.");
        } else if (command.contains("?")) {
            System.out.println("Loading data ...");
            List<String> employees = readEmployees();
            if (employees != null) {
                String searchName = command.substring(1);
                boolean employeeFound = employees.contains(searchName);
                if (employeeFound) System.out.println("Employee found!");
            }
            System.out.println("Data Loaded.");
        } else if (command.contains("c")) {
            System.out.println("Loading data ...");
            List<String> employees = readEmployees();
            if (employees != null && !employees.isEmpty()) {
                String line = String.join(",", employees);
                char[] characters = line.toCharArray();
                boolean inWord = false;
                int wordCount = 0;
                for (char c : characters) {
                    if (c == ' ') {
                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }
                System.out.println(wordCount + " word(s) found " + characters.length);
            }
            System.out.println("Data Loaded.");
        } else if (command.contains("u")) {
            System.out.println("Loading data ...");
            List<String> employees = readEmployees();
            if (employees != null) {
                String employeeToUpdate = command.substring(1);
                for (int i = 0; i < employees.size(); i++) {
                    if (employees.get(i).equals(employeeToUpdate)) {
                        employees.set(i, "Updated");
                    }
                }
                writeEmployees(employees);
            }
            System.out.println("Data Updated.");
        } else if (command.contains("d")) {
            System.out.println("Loading data ...");
            List<String> employees = readEmployees();
            if (employees != null) {
                String employeeToDelete = command.substring(1);
                employees.remove(employeeToDelete);
                writeEmployees(employees);
            }
            System.out.println("Data Deleted.");
        }
    }

    private static List<String> readEmployees() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(Constants.EMPLOYEE_FILE)
                    )
            );
            String line = reader.readLine();
            reader.close();
            if (line != null && !line.isEmpty()) {
                return new ArrayList<>(Arrays.asList(line.split(",")));
            }
        } catch (Exception e) {
        }
        return null;
    }

    private static void writeEmployees(List<String> employees) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(Constants.EMPLOYEE_FILE)
            );
            writer.write(String.join(",", employees));
            writer.close();
        } catch (Exception e) {
        }
    }

    private static void appendEmployee(String employee) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(Constants.EMPLOYEE_FILE, true)
            );
            writer.write(", " + employee);
            writer.close();
        } catch (Exception e) {
        }
    }
}
