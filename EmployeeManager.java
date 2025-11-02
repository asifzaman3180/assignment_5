// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {
    
        if (args.length == 0) {
            System.out.println("Error: No argument provided.");
            System.out.println("Usage:");
            System.out.println(" l            - List all employees");
            System.out.println(" s            - Show a random employee");
            System.out.println(" +<name>      - Add a new employee");
            System.out.println(" ?<name>      - Search for an employee");
            System.out.println(" c            - Count words in file");
            System.out.println(" u<name>      - Update an employee");
            System.out.println(" d<name>      - Delete an employee");
            return;
        }

        String command = args[0];
        try {
            if (command.equals("l")) {
                listEmployees();
            } else if (command.equals("s")) {
                showRandomEmployee();
            } else if (command.startsWith("+")) {
                addEmployee(command.substring(1));
            } else if (command.startsWith("?")) {
                searchEmployee(command.substring(1));
            } else if (command.equals("c")) {
                countWordsInFile();
            } else if (command.startsWith("u")) {
                updateEmployee(command.substring(1));
            } else if (command.startsWith("d")) {
                deleteEmployee(command.substring(1));
            } else {
                System.out.println("Error: Invalid argument '" + command + "'");
            }
        } catch (IOException e) {
            System.out.println("File Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        }
    }

    private static void listEmployees() throws IOException {
        System.out.println("Loading data ...");
        List<String> employees = readEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (String emp : employees) {
                System.out.println(emp);
            }
        }
        System.out.println("Data Loaded.");
    }

    private static void showRandomEmployee() throws IOException {
        System.out.println("Loading data ...");
        List<String> employees = readEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            Random rand = new Random();
            int idx = rand.nextInt(employees.size());
            System.out.println("Random Employee: " + employees.get(idx));
        }
        System.out.println("Data Loaded.");
    }

    private static void addEmployee(String name) throws IOException {
        if (name.isEmpty()) {
            System.out.println("Error: Employee name cannot be empty.");
            return;
        }
        System.out.println("Adding employee ...");
        BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt", true));
        List<String> employees = readEmployees();
        if (!employees.isEmpty()) writer.write(", ");
        writer.write(name);
        writer.close();
        System.out.println("Employee Added: " + name);
    }

    private static void searchEmployee(String name) throws IOException {
        System.out.println("Searching employee ...");
        List<String> employees = readEmployees();
        if (employees.contains(name)) {
            System.out.println("Employee found: " + name);
        } else {
            System.out.println("Employee not found: " + name);
        }
        System.out.println("Data Loaded.");
    }

    private static void countWordsInFile() throws IOException {
        System.out.println("Counting words ...");
        String content = readFileContent();
        if (content.isEmpty()) {
            System.out.println("File is empty.");
            return;
        }
        String[] words = content.split("\\s+|,\\s*");
        System.out.println(words.length + " word(s) found in the file.");
    }

    private static void updateEmployee(String name) throws IOException {
        System.out.println("Updating employee ...");
        List<String> employees = readEmployees();
        boolean found = false;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).equals(name)) {
                employees.set(i, "Updated");
                found = true;
            }
        }
        if (found) {
            writeEmployees(employees);
            System.out.println("Employee Updated: " + name);
        } else {
            System.out.println("Employee not found: " + name);
        }
    }

    private static void deleteEmployee(String name) throws IOException {
        System.out.println("Deleting employee ...");
        List<String> employees = readEmployees();
        if (employees.remove(name)) {
            writeEmployees(employees);
            System.out.println("Employee Deleted: " + name);
        } else {
            System.out.println("Employee not found: " + name);
        }
    }

    // Helper methods
    private static List<String> readEmployees() throws IOException {
        String content = readFileContent();
        if (content.isEmpty()) return new ArrayList<>();
        String[] arr = content.split("\\s*,\\s*");
        return new ArrayList<>(Arrays.asList(arr));
    }

    private static void writeEmployees(List<String> employees) throws IOException {
        BufferedWriter writer = new BuredWriter(new FileWriter("employees.txt"));
        writer.write(String.join(", ", employees));
        writer.close();
    }

    private static String readFileContent() throws IOException {
        File file = new File("employees.txt");
        if (!file.exists()) return "";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        reader.close();
        return line != null ? line.trim() : "";
    }
}
