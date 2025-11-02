// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide an argument!");
            return;
        }

        String command = args[0];
        String fileName = "employees.txt";

        try {
            if (command.equals("l")) {  
                System.out.println("Loading data ...");
                BufferedReader r = new BufferedReader(new FileReader(fileName));
                String line = r.readLine();
                r.close();
                if (line != null) {
                    String[] employees = line.split(",");
                    for (String emp : employees) {
                        System.out.println(emp.trim());
                    }
                }
                System.out.println("Data Loaded.");

            } else if (command.equals("s")) { 
                System.out.println("Loading data ...");
                BufferedReader r = new BufferedReader(new FileReader(fileName));
                String line = r.readLine();
                r.close();
                if (line != null) {
                    String[] employees = line.split(",");
                    Random rand = new Random();
                    int idx = rand.nextInt(employees.length);
                    System.out.println("Random Employee: " + employees[idx].trim());
                }
                System.out.println("Data Loaded.");

            } else if (command.startsWith("+")) {  
                System.out.println("Adding data ...");
                String nameToAdd = command.substring(1).trim();
                BufferedWriter w = new BufferedWriter(new FileWriter(fileName, true));
                w.write(", " + nameToAdd);
                w.close();
                System.out.println("Data Added.");

            } else if (command.startsWith("?")) {  
                System.out.println("Searching data ...");
                String nameToSearch = command.substring(1).trim();
                BufferedReader r = new BufferedReader(new FileReader(fileName));
                String line = r.readLine();
                r.close();
                boolean found = false;
                if (line != null) {
                    String[] employees = line.split(",");
                    for (String emp : employees) {
                        if (emp.trim().equals(nameToSearch)) {
                            System.out.println("Employee found!");
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("Employee not found.");
                }

            } else if (command.startsWith("c")) {  
                System.out.println("Counting data ...");
                BufferedReader r = new BufferedReader(new FileReader(fileName));
                String line = r.readLine();
                r.close();
                if (line != null && !line.isEmpty()) {
                    String[] employees = line.split(",");
                    System.out.println(employees.length + " employee(s), " + line.length() + " characters total.");
                } else {
                    System.out.println("0 employee(s), 0 characters.");
                }

            } else if (command.startsWith("u")) {  
                System.out.println("Updating data ...");
                String nameToUpdate = command.substring(1).trim();
                BufferedReader r = new BufferedReader(new FileReader(fileName));
                String line = r.readLine();
                r.close();
                if (line != null) {
                    String[] employees = line.split(",");
                    for (int i = 0; i < employees.length; i++) {
                        if (employees[i].trim().equals(nameToUpdate)) {
                            employees[i] = "Updated";
                        }
                    }
                    BufferedWriter w = new BufferedWriter(new FileWriter(fileName));
                    w.write(String.join(", ", employees));
                    w.close();
                }
                System.out.println("Data Updated.");

            } else if (command.startsWith("d")) {  
                System.out.println("Deleting data ...");
                String nameToDelete = command.substring(1).trim();
                BufferedReader r = new BufferedReader(new FileReader(fileName));
                String line = r.readLine();
                r.close();
                if (line != null) {
                    List<String> employees = new ArrayList<>(Arrays.asList(line.split(",")));
                    employees.removeIf(emp -> emp.trim().equals(nameToDelete));
                    BufferedWriter w = new BufferedWriter(new FileWriter(fileName));
                    w.write(String.join(", ", employees));
                    w.close();
                }
                System.out.println("Data Deleted.");

            } else {
                System.out.println("Invalid command!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
