//File Name EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
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
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String employees[] = line.split(",");
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
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                System.out.println(line);
                String employees[] = line.split(",");
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
                BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt", true));
                String newEmployeeName = args[0].substring(1);
                writer.write(", " + newEmployeeName);
                writer.close();
            } 
            catch (Exception exception) {

            }
            System.out.println("Data Loaded.");
        } 
        else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String employees[] = line.split(",");
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
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
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
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String employees[] = line.split(",");
                String employeeNameToUpdate = args[0].substring(1);
                for (int index = 0; index < employees.length; index++) {
                    if (employees[index].equals(employeeNameToUpdate)) {
                        employees[index] = "Updated";
                    }
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"));
                writer.write(String.join(",", employees));
                writer.close();
            } 
            catch (Exception exception) {

            }
            System.out.println("Data Updated.");
        } 
        else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String employees[] = line.split(",");
                String employeeNameToDelete = args[0].substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(employeeNameToDelete);
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt"));
                writer.write(String.join(",", employeeList));
                writer.close();
            } 
            catch (Exception exception) {
                
            }
            System.out.println("Data Deleted.");
        }
    }
}