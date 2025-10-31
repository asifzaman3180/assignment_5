// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        // Check arguments
        if (args[0].equals("l")) {
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
                // Handle exception silently (no change in behavior)
            }
            System.out.println("Data Loaded.");

        } else if (args[0].equals("s")) {
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

        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt", true)
                );
                String name = args[0].substring(1);
                writer.write(", " + name);
                writer.close();
            } catch (Exception e) {
                // Ignore exception
            }
            System.out.println("Data Loaded.");

        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt"))
                );
                String line = reader.readLine();
                String[] employees = line.split(",");

                boolean found = false;
                String searchName = args[0].substring(1);

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

        } else if (args[0].contains("c")) {
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

        } else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt"))
                );
                String line = reader.readLine();
                String[] employees = line.split(",");

                String name = args[0].substring(1);
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

        } else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt"))
                );
                String line = reader.readLine();
                String[] employees = line.split(",");

                String name = args[0].substring(1);
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
        }
    }
}
