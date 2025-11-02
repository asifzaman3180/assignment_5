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
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                for (String employee : employees) {
                    System.out.println(employee.trim());
                }
                reader.close();
            } catch (Exception exception) {}
            System.out.println("Data Loaded.");
        }

        else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);
                System.out.println(employees[randomIndex].trim());
                reader.close();
            } catch (Exception exception) {}
            System.out.println("Data Loaded.");
        }

        else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt", true));
                String nameToAdd = args[0].substring(1);
                writer.write(", " + nameToAdd);
                writer.close();
            } catch (Exception exception) {}
            System.out.println("Data Loaded.");
        }

        else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                boolean found = false;
                String searchName = args[0].substring(1);
                for (int i = 0; i < employees.length && !found; i++) {
                    if (employees[i].trim().equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }
                reader.close();
            } catch (Exception exception) {}
            System.out.println("Data Loaded.");
        }

        else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                char[] chars = line.toCharArray();
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
                reader.close();
            } catch (Exception exception) {}
            System.out.println("Data Loaded.");
        }

        else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                String nameToUpdate = args[0].substring(1);
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].trim().equals(nameToUpdate)) {
                        employees[i] = "Updated";
                    }
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"));
                writer.write(String.join(",", employees));
                writer.close();
                reader.close();
            } catch (Exception exception) {}
            System.out.println("Data Updated.");
        }

        else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                String nameToDelete = args[0].substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.removeIf(emp -> emp.trim().equals(nameToDelete));
                BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"));
                writer.write(String.join(",", employeeList));
                writer.close();
                reader.close();
            } catch (Exception exception) {}
            System.out.println("Data Deleted.");
        }
    }
}
