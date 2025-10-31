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

        switch (command.charAt(0)) {
            case 'l' -> {
                System.out.println("Loading data ...");
                List<String> employees = readEmployees();
                if (employees != null) employees.forEach(System.out::println);
                System.out.println("Data Loaded.");
            }
            case 's' -> {
                System.out.println("Loading data ...");
                List<String> employees = readEmployees();
                if (employees != null && !employees.isEmpty()) {
                    System.out.println(String.join(",", employees));
                    System.out.println(employees.get(new Random().nextInt(employees.size())));
                }
                System.out.println("Data Loaded.");
            }
            case '+' -> {
                System.out.println("Loading data ...");
                appendEmployee(command.substring(1));
                System.out.println("Data Loaded.");
            }
            case '?' -> {
                System.out.println("Loading data ...");
                List<String> employees = readEmployees();
                if (employees != null) {
                    String searchName = command.substring(1);
                    System.out.println(employees.contains(searchName)
                            ? "Employee found!"
                            : "Employee not found.");
                }
                System.out.println("Data Loaded.");
            }
            case 'c' -> {
                System.out.println("Loading data ...");
                List<String> employees = readEmployees();
                if (employees != null && !employees.isEmpty()) {
                    System.out.println(employees.size() + " word(s) found, total characters: " +
                            employees.stream().mapToInt(String::length).sum() + ".");
                }
                System.out.println("Data Loaded.");
            }
            case 'u' -> {
                System.out.println("Loading data ...");
                List<String> employees = readEmployees();
                if (employees != null) {
                    String target = command.substring(1);
                    employees.replaceAll(e -> e.equals(target) ? "Updated" : e);
                    writeEmployees(employees);
                }
                System.out.println("Data Updated.");
            }
            case 'd' -> {
                System.out.println("Loading data ...");
                List<String> employees = readEmployees();
                if (employees != null) {
                    employees.remove(command.substring(1));
                    writeEmployees(employees);
                }
                System.out.println("Data Deleted.");
            }
            default -> System.out.println("Invalid command.");
        }
    }

    private static List<String> readEmployees() {
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.EMPLOYEE_FILE))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                return new ArrayList<>(Arrays.asList(line.split(",")));
            }
        } catch (Exception e) {}
        return null;
    }

    private static void writeEmployees(List<String> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE))) {
            writer.write(String.join(",", employees));
        } catch (Exception e) {}
    }

    private static void appendEmployee(String employee) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE, true))) {
            writer.write(", " + employee);
        } catch (Exception e) {}
    }
}
