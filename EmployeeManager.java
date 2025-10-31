// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        // Task 2 & 3: Validate arguments & Improve Variable Naming
        if (args.length == 0) {
            System.out.println("Error: No arguments provided.");
            System.out.println("Usage: java EmployeeManager <command>");
            System.out.println("Commands: l, s, +<name>, ?<name>, c, u<name>, d<name>");
            return; // exit safely
        }

        String command = args[0];

        if (command.equals("l")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader employeeReader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt"))
                );
                String employeeLine = employeeReader.readLine();
                String[] employeeNames = employeeLine.split(",");

                for (String employee : employeeNames) {
                    System.out.println(employee);
                }

                employeeReader.close();
            } catch (Exception e) {
                // Handle exception silently
            }
            System.out.println("Data Loaded.");

        } else if (command.equals("s")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader employeeReader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt"))
                );
                String employeeLine = employeeReader.readLine();
                String[] employeeNames = employeeLine.split(",");

                Random random = new Random();
                int randomIndex = random.nextInt(employeeNames.length);
                System.out.println(employeeNames[randomIndex]);

                employeeReader.close();
            } catch (Exception e) {
                // Ignore exception
            }
            System.out.println("Data Loaded.");

        } else if (command.startsWith("+")) {
            if (command.length() == 1) {
                System.out.println("Error: No employee name provided to add.");
                return;
            }
            System.out.println("Loading data ...");
            try {
                BufferedWriter employeeWriter = new BufferedWriter(
                        new FileWriter("employees.txt", true)
                );
                String newEmployee = command.substring(1);
                employeeWriter.write(", " + newEmployee);
                employeeWriter.close();
            } catch (Exception e) {
                // Ignore exception
            }
            System.out.println("Data Loaded.");

        } else if (command.startsWith("?")) {
            if (command.length() == 1) {
                System.out.println("Error: No employee name provided to search.");
                return;
            }
            System.out.println("Loading data ...");
            try {
                BufferedReader employeeReader = new BufferedReader(
                        new Inpu
