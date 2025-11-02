// File: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a command: l, s, +Name, ?Name, c, uName, dName");
            return;
        }

        String command = args[0];
        System.out.println("Loading data ...");

        String[] employees = readEmployees();

        char action = command.charAt(0);
        String name = command.length() > 1 ? command.substring(1).trim() : "";

        switch (action) {
            case 'l':
                for (String e : employees) System.out.println(e);
                System.out.println("Data Loaded.");
                break;

            case 's':
                if (employees.length == 0) System.out.println("No employees found.");
                else System.out.println(employees[new Random().nextInt(employees.length)]);
                System.out.println("Data Loaded.");
                break;

            case '+':
                if (name.isEmpty()) System.out.println("Error: no name provided to add.");
                else {
                    String[] updated = Arrays.copyOf(employees, employees.length + 1);
                    updated[updated.length - 1] = name;
                    writeEmployees(updated);
                    System.out.println("Added: " + name);
                }
                System.out.println("Data Loaded.");
                break;

            case '?':
                if (name.isEmpty()) System.out.println("Error: no name provided to search.");
                else {
                    boolean found = false;
                    for (String e : employees) {
                        if (e.equals(name)) {
                            found = true;
                            break;
                        }
                    }
                    System.out.println(found ? "Found: " + name : "Not found: " + name);
                }
                System.out.println("Data Loaded.");
                break;

            case 'c':
                int count = 0, totalChars = 0;
                for (String e : employees) {
                    if (!e.isEmpty()) {
                        count++;
                        totalChars += e.length();
                    }
                }
                System.out.println(count + " employee(s), total characters: " + totalChars);
                System.out.println("Data Loaded.");
                break;

            case 'u':
                if (name.isEmpty()) System.out.println("Error: no name provided to update.");
                else {
                    boolean updated = false;
                    for (int i = 0; i < employees.length; i++) {
                        if (employees[i].equals(name)) {
                            employees[i] = "Updated";
                            updated = true;
                        }
                    }
                    writeEmployees(employees);
                    System.out.println(updated ? "Updated: " + name : "Not found: " + name);
                }
                System.out.println("Data Updated.");
                break;

            case 'd':
                if (name.isEmpty()) System.out.println("Error: no name provided to delete.");
                else {
                    List<String> list = new ArrayList<>(Arrays.asList(employees));
                    boolean removed = list.remove(name);
                    writeEmployees(list.toArray(new String[0]));
                    System.out.println(removed ? "Deleted: " + name : "Not found: " + name);
                }
                System.out.println("Data Deleted.");
                break;

            default:
                System.out.println("Error: Unknown command \"" + command + "\"");
        }
    }
    
    // Reusable methods:
    private static String[] readEmployees() {
        try (BufferedReader br = new BufferedReader(new FileReader("employees.txt"))) {
            String line = br.readLine();
            if (line == null || line.isEmpty()) return new String[0];
            return line.split(",");
        } catch (Exception e) {
            return new String[0];
        }
    }

    private static void writeEmployees(String[] data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("employees.txt"))) {
            bw.write(String.join(",", data));
        } catch (Exception e) {
            System.out.println("Error writing file.");
        }
    }
}
