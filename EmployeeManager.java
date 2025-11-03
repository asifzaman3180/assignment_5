// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No arguments provided!");
            return;
        }

        String command = args[0];

        if (command.equals("l")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                for (String employee : employees) {
                    System.out.println(employee);
                }
            } catch (Exception e) {}
            System.out.println("Data Loaded.");
        } 
        else if (command.equals("s")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                System.out.println(line);
                String[] employees = line.split(",");
                Random rand = new Random();
                int idx = rand.nextInt(employees.length);
                System.out.println(employees[idx]);
            } catch (Exception e) {}
            System.out.println("Data Loaded.");
        } 
        else if (command.contains("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt", true));
                String newEmployee = command.substring(1);
                writer.write(", " + newEmployee);
                writer.close();
            } catch (Exception e) {}
            System.out.println("Data Loaded.");
        } 
        else if (command.contains("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
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
            } catch (Exception e) {}
            System.out.println("Data Loaded.");
        }
        // other commands (c, u, d) remain unchanged
    }
}