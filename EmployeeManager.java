// File Name: EmployeeManager.java
// Purpose: A simple command-line tool to manage employee records stored in a text file.
// Supports operations: list, show random, add, search, count, update, delete.

import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        // Check if any argument is provided
        if (args.length == 0) {
            System.out.println("Please provide a valid command.");
            return;
        }

        String command = args[0];

        // LIST all employees
        if (command.equals("l")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(new FileReader("employees.txt"));
                String line = reader.readLine();
                reader.close();

                if (line != null && !line.isEmpty()) {
                    String[] employees = line.split(",");
                    for (String emp : employees) {
                        System.out.println(emp.trim());
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading employee data.");
            }
            System.out.println("Data Loaded.");
        }

        // SHOW a random employee
        else if (command.equals("s")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(new FileReader("employees.txt"));
                String line = reader.readLine();
                reader.close();

                if (line != null && !line.isEmpty()) {
                    String[] employees = line.split(",");
                    Random rand = new Random();
                    int idx = rand.nextInt(employees.length);
                    System.out.println("Random Employee: " + employees[idx].trim());
                }
            } catch (IOException e) {
                System.out.println("Error reading employee data.");
            }
            System.out.println("Data Loaded.");
        }

        // ADD a new employee
        else if (command.startsWith("+")) {
            System.out.println("Loading data ...");
            try {
                String newEmployee = command.substring(1).trim();
                BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt", true));
                writer.write(", " + newEmployee);
                writer.close();
            } catch (IOException e) {
                System.out.println("Error writing employee data.");
            }
            System.out.println("Data Loaded.");
        }

        // SEARCH for an employee
        else if (command.startsWith("?")) {
            System.out.println("Loading data ...");
            try {
                String searchName = command.substring(1).trim();
                BufferedReader reader = new BufferedReader(new FileReader("employees.txt"));
                String line = reader.readLine();
                reader.close();

                boolean found = false;
                if (line != null && !line.isEmpty()) {
                    String[] employees = line.split(",");
                    for (String emp : employees) {
                        if (emp.trim().equals(searchName)) {
                            System.out.println("Employee found!");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Employee not found.");
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading employee data.");
            }
            System.out.println("Data Loaded.");
        }

        // COUNT words and characters in the file
        else if (command.contains("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(new FileReader("employees.txt"));
                String line = reader.readLine();
                reader.close();

                if (line != null) {
                    String[] words = line.split(",");
                    System.out.println(words.length + " employee(s) found, " + line.length() + " characters.");
                }
            } catch (IOException e) {
                System.out.println("Error reading employee data.");
            }
            System.out.println("Data Loaded.");
        }

        // UPDATE an employee
        else if (command.startsWith("u")) {
            System.out.println("Loading data ...");
            try {
                String updateName = command.substring(1).trim();
                BufferedReader reader = new BufferedReader(new FileReader("employees.txt"));
                String line = reader.readLine();
                reader.close();

                if (line != null) {
                    String[] employees = line.split(",");
                    for (int i = 0; i < employees.length; i++) {
                        if (employees[i].trim().equals(updateName)) {
                            employees[i] = "Updated";
                        }
                    }

                    BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"));
                    writer.write(String.join(",", employees));
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println("Error updating employee data.");
            }
            System.out.println("Data Updated.");
        }

        // DELETE an employee
        else if (command.startsWith("d")) {
            System.out.println("Loading data ...");
            try {
                String deleteName = command.substring(1).trim();
                BufferedReader reader = new BufferedReader(new FileReader("employees.txt"));
                String line = reader.readLine();
                reader.close();

                if (line != null) {
                    List<String> employees = new ArrayList<>(Arrays.asList(line.split(",")));
                    employees.removeIf(emp -> emp.trim().equals(deleteName));

                    BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"));
                    writer.write(String.join(",", employees));
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println("Error deleting employee data.");
            }
            System.out.println("Data Deleted.");
        }

        // Invalid command
        else {
            System.out.println(
                    "Invalid command. Use: l (list), s (show random), +name (add), ?name (search), c (count), uname (update), dname (delete).");
        }
    }
}
