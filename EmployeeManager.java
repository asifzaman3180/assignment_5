// File: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Please provide a command (l, s, +Name, ?Name, c, uName, dName).");
            return;
        }

        String command = args[0];

        // single "Loading data" message kept where appropriate
        System.out.println("Loading data ...");

        switch (command.charAt(0)) {

            case 'l': 
                for (String employee : readEmployees()) {
                    System.out.println(employee);
                }
                System.out.println("Data Loaded.");
                break;

            case 's': 
                String[] all = readEmployees();
                if (all.length == 0) {
                    System.out.println("No employees found.");
                } else {
                    System.out.println(String.join(",", all));
                    System.out.println(all[new Random().nextInt(all.length)]);
                }
                System.out.println("Data Loaded.");
                break;

            case '+': 
                String toAdd = command.substring(1);
                if (!toAdd.isBlank()) {
                    String[] current = readEmployees();
                    String[] extended = Arrays.copyOf(current, current.length + 1);
                    extended[extended.length - 1] = toAdd;
                    writeEmployees(extended);
                    System.out.println("Employee added: \"" + toAdd + "\"");
                } else {
                    System.out.println("Error: no name provided to add.");
                }
                System.out.println("Data Loaded.");
                break;

            case '?': 
                String toSearch = command.substring(1).trim();
                if (toSearch.isEmpty()) {
                    System.out.println("Error: please provide a name to search (example: ?Jane Smith).");
                } else {
                    String[] list = readEmployees();
                    boolean found = Arrays.stream(list).anyMatch(name -> name.equals(toSearch));
                    if (found) {
                        System.out.println("Employee \"" + toSearch + "\" found.");
                    } else {
                        System.out.println("Employee \"" + toSearch + "\" not found.");
                    }
                }
                System.out.println("Data Loaded.");
                break;

            case 'c': 
                String[] employeesForCount = readEmployees();
                long count = Arrays.stream(employeesForCount).filter(s -> !s.isBlank()).count();
                int chars = Arrays.stream(employeesForCount).mapToInt(String::length).sum();
                System.out.println(count + " employee(s) found, total characters: " + chars);
                System.out.println("Data Loaded.");
                break;

            case 'u': 
                String toUpdate = command.substring(1);
                if (toUpdate.isBlank()) {
                    System.out.println("Error: please provide a name to update.");
                } else {
                    String[] current = readEmployees();
                    boolean updated = false;
                    for (int i = 0; i < current.length; i++) {
                        if (current[i].equals(toUpdate)) {
                            current[i] = "Updated";
                            updated = true;
                        }
                    }
                    writeEmployees(current);
                    System.out.println(updated ? "Employee updated: \"" + toUpdate + "\"" : "No such employee: \"" + toUpdate + "\"");
                }
                System.out.println("Data Updated.");
                break;

            case 'd':
                String toDelete = command.substring(1).trim();
                if (toDelete.isEmpty()) {
                    System.out.println("Error: please provide a name to delete.");
                } else {
                    List<String> list = new ArrayList<>(Arrays.asList(readEmployees()));
                    boolean removed = list.remove(toDelete);
                    writeEmployees(list.toArray(new String[0]));
                    System.out.println(removed ? "Employee deleted: \"" + toDelete + "\"" : "No such employee: \"" + toDelete + "\"");
                }
                System.out.println("Data Deleted.");
                break;

            default:
                System.out.println("Error: Unsupported command \"" + command + "\". Try: l, s, +Name, ?Name, c, uName, dName.");
                break;
        }
    }

    // Reusable read method
    private static String[] readEmployees() {
        try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
            String line = reader.readLine();
            return (line == null || line.isBlank()) ? new String[0] : line.split(",");
        } catch (Exception e) {
            System.out.println("Error reading employees file.");
            return new String[0];
        }
    }

    // Reusable write method
    private static void writeEmployees(String[] employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"))) {
            writer.write(String.join(",", employees));
        } catch (Exception e) {
            System.out.println("Error writing employees file.");
        }
    }
}
