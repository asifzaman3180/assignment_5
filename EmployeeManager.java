// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    private static final String FILE_NAME = "employees.txt";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No argument provided.");
            return;
        }

        String command = args[0];
        System.out.println("Loading data ...");

        try {
            switch (command.charAt(0)) {
                case 'l' -> listEmployees();
                case 's' -> showRandomEmployee();
                case '+' -> addEmployee(command.substring(1));
                case '?' -> searchEmployee(command.substring(1));
                case 'c' -> countWords();
                case 'u' -> updateEmployee(command.substring(1));
                case 'd' -> deleteEmployee(command.substring(1));
                default -> System.out.println("Invalid command.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Operation completed.");
    }

    private static List<String> readEmployees() throws IOException {
        BufferedReader r = new BufferedReader(new FileReader(FILE_NAME));
        String line = r.readLine();
        r.close();
        if (line == null || line.isEmpty()) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(line.split(",")));
    }

    private static void writeEmployees(List<String> employees) throws IOException {
        BufferedWriter w = new BufferedWriter(new FileWriter(FILE_NAME));
        w.write(String.join(",", employees));
        w.close();
    }

    private static void listEmployees() throws IOException {
        readEmployees().forEach(emp -> System.out.println(emp.trim()));
    }

    private static void showRandomEmployee() throws IOException {
        List<String> employees = readEmployees();
        if (!employees.isEmpty()) {
            int idx = new Random().nextInt(employees.size());
            System.out.println(employees.get(idx).trim());
        }
    }

    private static void addEmployee(String name) throws IOException {
        List<String> employees = readEmployees();
        employees.add(name);
        writeEmployees(employees);
    }

    private static void searchEmployee(String name) throws IOException {
        List<String> employees = readEmployees();
        System.out.println(employees.contains(name) ? "Employee found!" : "Employee not found!");
    }

    private static void countWords() throws IOException {
        int totalWords = readEmployees().stream()
                .mapToInt(emp -> emp.trim().split("\\s+").length)
                .sum();
        System.out.println(totalWords + " word(s) found.");
    }

    private static void updateEmployee(String name) throws IOException {
        List<String> employees = readEmployees();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).equals(name)) employees.set(i, "Updated");
        }
        writeEmployees(employees);
    }

    private static void deleteEmployee(String name) throws IOException {
        List<String> employees = readEmployees();
        employees.removeIf(emp -> emp.equals(name));
        writeEmployees(employees);
    }
}
