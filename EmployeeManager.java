// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        // Task 2: Validate arguments
        if (args.length == 0) {
            System.out.println("Error: No arguments provided.");
            System.out.println("Usage: java EmployeeManager <command>");
            System.out.println("Commands: l, s, +<name>, ?<name>, c, u<name>, d<name>");
            return; // exit safely
        }

        String command = args[0];

        // Check arguments
        if (command.equals("l")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt"))
                );
                String line = reader.readLine();
                String[] employees = line.split(",");

                for (String emp : employees) {
                    System.out.println(emp);
                }

                reader.close();
            } catch (Exception e) {
                // Handle exception silently
            }
            System.out.println("Data Loaded.");

        } else if (command.equals("s")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt"))
                );
                String line = reader.readLine();
                String[] employees = line.split(",");

                Random rand = new Random();
                int idx = rand.nextInt(employees.length);
                System.out.println(employees[idx]);

                reader.close();
            } catch (Exception e) {
                // Ignore exception
            }
            System.out.println("Data Loaded.");

        } else if (command.startsWith("+")) {
            if (command.length() == 1) {
                System.out.println("Error: No name provided to add.");
                return;
            }
            System.out.println("Loading data ...");
            try {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt", true)
                );
                String name = command.substring(1);
                writer.write(", " + name);
                writer.close();
            } catch (Exception e) {
                // Ignore exception
            }
            System.out.println("Data Loaded.");

        } else if (command.startsWith("?")) {
            if (command.length() == 1) {
                System.out.println("Error: No name provided to search.");
                return;
            }
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt"))
                );
                String line = reader.readLine();
                String[] employees = line.split(",");

                boolean found = false;
                String searchName = command.substring(1);

                for (int i = 0; i < employees.length && !found; i++) {
                    if (employees[i].equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }

                reader.close();
            } catch (Exception e) {
                // Ignore exception
            }
            System.out.println("Data Loaded.");

        } else if (command.equals("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt"))
                );
                String line = reader.readLine();
                char[] chars = line.toCharArray();

                boolean inWord = false;
                int count = 0;

                for (char c : chars) {
                    if (c == ' ') {
                        if (!inWord) {
                            count++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }

                System.out.println(count + " word(s) found " + chars.length);
                reader.close();
            } catch (Exception e) {
                // Ignore exception
            }
            System.out.println("Data Loaded.");

        } else if (command.startsWith("u")) {
            if (command.length() == 1) {
                System.out.println("Error: No name provided to update.");
                return;
            }
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt"))
                );
                String line = reader.readLine();
                String[] employees = line.split(",");

                String name = command.substring(1);
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(name)) {
                        employees[i] = "Updated";
                    }
                }

                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt")
                );
                writer.write(String.join(",", employees));
                writer.close();
                reader.close();
            } catch (Exception e) {
                // Ignore exception
            }
            System.out.println("Data Updated.");

        } else if (command.startsWith("d")) {
            if (command.length() == 1) {
                System.out.println("Error: No name provided to delete.");
                return;
            }
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt"))
                );
                String line = reader.readLine();
                String[] employees = line.split(",");

                String name = command.substring(1);
                List<String> list = new ArrayList<>(Arrays.asList(employees));
                list.remove(name);

                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt")
                );
                writer.write(String.join(",", list));
                writer.close();
                reader.close();
            } catch (Exception e) {
                // Ignore exception
            }
            System.out.println("Data Deleted.");
        } else {
            System.out.println("Error: Unsupported command '" + command + "'.");
            System.out.println("Usage: l, s, +<name>, ?<name>, c, u<name>, d<name>");
        }
    }
}
