// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    
    // Reusable method to read employees from file
    private static String[] readEmployeesFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")));
            String line = reader.readLine();
            reader.close();
            return line.split(",");
        } 
        catch (Exception exception) {
            return new String[0];
        }
    }
    
    // Reusable method to write employees to file
    private static void writeEmployeesToFile(String[] employees) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"));
            writer.write(String.join(",", employees));
            writer.close();
        } 
        catch (Exception exception) {
        }
    }
    
    // Reusable method to append employee to file
    private static void appendEmployeeToFile(String newEmployee) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt", true));
            writer.write(", " + newEmployee);
            writer.close();
        } 
        catch (Exception exception) {
        }
    }

    public static void main(String[] args) {
        
        // Validate command line arguments
        if (args.length != 1) {
            System.out.println("Usage: java EmployeeManager [l|s|+name|?name|c|uname|dname]");
            System.out.println("Please provide exactly one command line argument.");
            return;
        }

        // Check arguments
        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            String[] employees = readEmployeesFromFile();
            for (String employee : employees) {
                System.out.println(employee);
            }
            System.out.println("Data Loaded.");
        } 
        else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            String[] employees = readEmployeesFromFile();
            System.out.println(String.join(",", employees));
            Random random = new Random();
            int randomIndex = random.nextInt(employees.length);
            System.out.println(employees[randomIndex]);
            System.out.println("Data Loaded.");
        } 
        else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            String newEmployee = args[0].substring(1);
            appendEmployeeToFile(newEmployee);
            System.out.println("Data Loaded.");
        } 
        else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            String[] employees = readEmployeesFromFile();
            boolean found = false;
            String searchName = args[0].substring(1);
            for (int i = 0; i < employees.length && !found; i++) {
                if (employees[i].equals(searchName)) {
                    System.out.println("Employee found!");
                    found = true;
                }
            }
            System.out.println("Data Loaded.");
        } 
        else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                char[] characters = line.toCharArray();
                boolean inWord = false;
                int wordCount = 0;
                for (char character : characters) {
                    if (character == ' ') {
                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }
                System.out.println(wordCount + " word(s) found " + characters.length);
            } 
            catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } 
        else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            String[] employees = readEmployeesFromFile();
            String employeeName = args[0].substring(1);
            for (int i = 0; i < employees.length; i++) {
                if (employees[i].equals(employeeName)) {
                    employees[i] = "Updated";
                }
            }
            writeEmployeesToFile(employees);
            System.out.println("Data Updated.");
        } 
        else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            String[] employees = readEmployeesFromFile();
            String employeeToDelete = args[0].substring(1);
            List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
            employeeList.remove(employeeToDelete);
            writeEmployeesToFile(employeeList.toArray(new String[0]));
            System.out.println("Data Deleted.");
        }
    }
}