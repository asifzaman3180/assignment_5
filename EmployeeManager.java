import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {

        // Step 1: Argument validation
        if (args.length == 0) {
            System.out.println("Error: No arguments provided.");
            printUsage();
            return;
        }

        String command = args[0];

        //  Step 2: Valid command list
        List<String> validCommands = Arrays.asList("l", "s", "c");
        boolean isValid = validCommands.contains(command)
                || command.startsWith("+")
                || command.startsWith("?")
                || command.startsWith("u")
                || command.startsWith("d");

        if (!isValid) {
            System.out.println("Error: Invalid command '" + command + "'");
            printUsage();
            return;
        }

        //  Step 3: Continue normal operation
        if (command.equals("l")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String l = r.readLine();
                String[] e = l.split(",");
                for (String emp : e) {
                    System.out.println(emp);
                }
            } catch (Exception e) {
                System.out.println("Error loading file: " + e.getMessage());
            }
            System.out.println("Data Loaded.");
        }

        else if (command.equals("s")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String l = r.readLine();
                String[] e = l.split(",");
                Random rand = new Random();
                int idx = rand.nextInt(e.length);
                System.out.println(e[idx]);
            } catch (Exception e) {
                System.out.println("Error loading file: " + e.getMessage());
            }
            System.out.println("Data Loaded.");
        }

        else if (command.startsWith("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter w = new BufferedWriter(
                        new FileWriter("employees.txt", true));
                String n = command.substring(1).trim();
                if (!n.isEmpty()) {
                    w.write(", " + n);
                } else {
                    System.out.println("Error: No name provided to add.");
                }
                w.close();
            } catch (Exception e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
            System.out.println("Data Loaded.");
        }

        else if (command.startsWith("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String l = r.readLine();
                String[] e = l.split(",");
                String s = command.substring(1).trim();
                boolean found = false;
                for (String emp : e) {
                    if (emp.trim().equalsIgnoreCase(s)) {
                        System.out.println("Employee found!");
                        found = true;
                        break;
                    }
                }
                if (!found) System.out.println("Employee not found.");
            } catch (Exception e) {
                System.out.println("Error loading file: " + e.getMessage());
            }
            System.out.println("Data Loaded.");
        }

        else if (command.equals("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String l = r.readLine();
                char[] chars = l.toCharArray();
                int count = 0;
                for (char c : chars) {
                    if (c == ',') count++;
                }
                System.out.println((count + 1) + " employee(s) found.");
            } catch (Exception e) {
                System.out.println("Error loading file: " + e.getMessage());
            }
            System.out.println("Data Loaded.");
        }

        else if (command.startsWith("u")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String l = r.readLine();
                String[] e = l.split(",");
                String n = command.substring(1).trim();
                boolean updated = false;
                for (int i = 0; i < e.length; i++) {
                    if (e[i].trim().equalsIgnoreCase(n)) {
                        e[i] = "Updated";
                        updated = true;
                    }
                }
                BufferedWriter w = new BufferedWriter(
                        new FileWriter("employees.txt"));
                w.write(String.join(",", e));
                w.close();
                if (updated) System.out.println("Employee updated successfully.");
                else System.out.println("Employee not found for update.");
            } catch (Exception e) {
                System.out.println("Error updating file: " + e.getMessage());
            }
            System.out.println("Data Updated.");
        }

        else if (command.startsWith("d")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String l = r.readLine();
                String[] e = l.split(",");
                String n = command.substring(1).trim();
                List<String> list = new ArrayList<>(Arrays.asList(e));
                if (list.removeIf(emp -> emp.trim().equalsIgnoreCase(n))) {
                    BufferedWriter w = new BufferedWriter(
                            new FileWriter("employees.txt"));
                    w.write(String.join(",", list));
                    w.close();
                    System.out.println("Employee deleted successfully.");
                } else {
                    System.out.println("Employee not found for deletion.");
                }
            } catch (Exception e) {
                System.out.println("Error deleting data: " + e.getMessage());
            }
            System.out.println("Data Deleted.");
        }
    }

    //  Helper: Print correct usage info
    private static void printUsage() {
        System.out.println("\nUsage: java EmployeeManager [option]");
        System.out.println("Valid options:");
        System.out.println("  l           - List all employees");
        System.out.println("  s           - Show random employee");
        System.out.println("  +name       - Add new employee");
        System.out.println("  ?name       - Search employee");
        System.out.println("  c           - Count total employees");
        System.out.println("  uname       - Update employee name");
        System.out.println("  dname       - Delete employee");
    }
}
