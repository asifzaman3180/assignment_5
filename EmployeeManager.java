//File Name EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {

        // Task #2 – Validate arguments
        if (args.length != 1) {
            System.out.println("Invalid number of arguments. Please provide exactly one argument.");
            System.out.println("Usage examples:");
            System.out.println("  l   - List all employees");
            System.out.println("  s   - Show a random employee");
            System.out.println("  +<name> - Add a new employee");
            System.out.println("  ?<name> - Search for an employee");
            System.out.println("  u<name> - Update an employee");
            System.out.println("  d<name> - Delete an employee");
            System.out.println("  c   - Count words and characters");
            return; 
        }

        String command = args[0];

        // List all employees
        if (command.equals("l")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String line = reader.readLine();
                String[] employees = line.split(",");
                for (String employee : employees) {
                    System.out.println(employee);
                }
            } 
            catch (Exception ex) {
                
            }
            System.out.println("Data Loaded.");
        } 

        // Show a random employee
        else if (command.equals("s")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String line = reader.readLine();
                System.out.println(line);
                String[] employees = line.split(",");
                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);
                System.out.println(employees[randomIndex]);
            } 
            catch (Exception ex) {

            }
            System.out.println("Data Loaded.");
        } 
        
        // Add a new employee
        else if (command.contains("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter writer = new BufferedWriter(
                    new FileWriter("employees.txt", true)
                );
                String newEmployee = command.substring(1);
                writer.write(", " + newEmployee);
                writer.close();
            } 
            catch (Exception ex) {

            }
            System.out.println("Data Loaded.");
        } 
        
        // Search for an employee
        else if (command.contains("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String line = reader.readLine();
                String[] employees = line.split(",");
                boolean found = false;
                String searchEmployee = command.substring(1);
                for (int i = 0; i < employees.length && !found; i++) {
                    if (employees[i].equals(searchEmployee)) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }
            } 
            catch (Exception ex) {

            }
            System.out.println("Data Loaded.");
        } 
        
        // Count words and characters
        else if (command.contains("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                     new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String line = reader.readLine();
                char[] characters = line.toCharArray();
                boolean inWord = false;
                int wordCount = 0;
                for (char character : characters) {
                    if (character == ' ') {
                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        } 
                        else {
                            inWord = false;
                        }
                    }
                }
                System.out.println(wordCount + " word(s) found " + characters.length);
            }
            catch (Exception ex) {

            }
            System.out.println("Data Loaded.");
        } 
        
        // Update an employee
        else if (command.contains("u")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String line = reader.readLine();
                String[] employees = line.split(",");
                String employeeToUpdate = command.substring(1);
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(employeeToUpdate)) {
                        employees[i] = "Updated";
                    }
                }
                BufferedWriter writer = new BufferedWriter(
                    new FileWriter("employees.txt")
                );
                writer.write(String.join(",", employees));
                writer.close();
            } 
            catch (Exception ex) {

            }
            System.out.println("Data Updated.");
        } 

        // Delete an employee
        else if (command.contains("d")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String line = reader.readLine();
                String[] employees = line.split(",");
                String employeeToDelete = command.substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(employeeToDelete);
                BufferedWriter writer = new BufferedWriter(
                    new FileWriter("employees.txt")
                );
                writer.write(String.join(",", employeeList));
                writer.close();
            }
            catch (Exception ex) {

            }
            System.out.println("Data Deleted.");
        }
    }
}
