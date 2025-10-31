//File Name EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    
    // Reusable method to read employees from file
    private static String[] readEmployeesFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")));
        String line = reader.readLine();
        reader.close();
        return line.split(",");
    }
    
    // Reusable method to write employees to file
    private static void writeEmployeesToFile(String[] employees) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"));
        writer.write(String.join(",", employees));
        writer.close();
    }
    
    // Reusable method to append to employees file
    private static void appendToEmployeesFile(String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt", true));
        writer.write(content);
        writer.close();
    }

    public static void main(String[] args) {
        // Add validation check for command-line arguments
        if (args.length != 1) {
            System.out.println("Error: Incorrect number of arguments.");
            System.out.println("Usage: java EmployeeManager <command>");
            System.out.println("Commands: l (list), s (show random), +name (add), ?name (search), c (count), uname (update), dname (delete)");
            return;
        }
        
        // Check arguments
        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                for (String employee : employees) {
                    System.out.println(employee);
                }
            } 
            catch (Exception exception) {

            }
            System.out.println("Data Loaded.");
        } 
        else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                System.out.println(String.join(",", employees));
                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);
                System.out.println(employees[randomIndex]);
            } 
            catch (Exception exception) {

            }
            System.out.println("Data Loaded.");
        } 
        else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                String newEmployeeName = args[0].substring(1);
                appendToEmployeesFile(", " + newEmployeeName);
            } 
            catch (Exception exception) {

            }
            System.out.println("Data Loaded.");
        } 
        else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                boolean employeeFound = false;
                String searchName = args[0].substring(1);
                for (int index = 0; index < employees.length && !employeeFound; index++) {
                    if (employees[index].equals(searchName)) {
                        System.out.println("Employee found!");
                        employeeFound = true;
                    }
                }
            } 
            catch (Exception exception) {

            }
            System.out.println("Data Loaded.");
        } 
        else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                String line = String.join(",", employees);
                char[] characterArray = line.toCharArray();
                boolean inWord = false;
                int wordCount = 0;
                for (char character : characterArray) {
                    if (character == ' ') {
                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }
                System.out.println(wordCount + " word(s) found " + characterArray.length);
            } 
            catch (Exception exception) {

            }
            System.out.println("Data Loaded.");
        } 
        else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                String employeeNameToUpdate = args[0].substring(1);
                for (int index = 0; index < employees.length; index++) {
                    if (employees[index].equals(employeeNameToUpdate)) {
                        employees[index] = "Updated";
                    }
                }
                writeEmployeesToFile(employees);
            } 
            catch (Exception exception) {

            }
            System.out.println("Data Updated.");
        } 
        else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeesFromFile();
                String employeeNameToDelete = args[0].substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(employeeNameToDelete);
                writeEmployeesToFile(employeeList.toArray(new String[0]));
            } 
            catch (Exception exception) {
                
            }
            System.out.println("Data Deleted.");
        }
    }
}