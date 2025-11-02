
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {
        if (args.length == 0) return; 

        String command = args[0];

        try {
            if (command.equals("l")) { 
                BufferedReader r = new BufferedReader(new FileReader("employees.txt"));
                String[] employees = r.readLine().split(",");
                r.close();
                for (String emp : employees) {
                    System.out.println(emp.trim());
                }
                System.out.println("Data Loaded.");
            } 
            else if (command.equals("s")) { 
                BufferedReader r = new BufferedReader(new FileReader("employees.txt"));
                String[] employees = r.readLine().split(",");
                r.close();
                Random rand = new Random();
                int idx = rand.nextInt(employees.length);
                System.out.println(employees[idx].trim());
                System.out.println("Data Loaded.");
            } 
            else if (command.startsWith("+")) { 
                String name = command.substring(1);
                BufferedWriter w = new BufferedWriter(new FileWriter("employees.txt", true));
                w.write(", " + name);
                w.close();
                System.out.println("Data Loaded.");
            } 
            else if (command.startsWith("?")) { 
                String name = command.substring(1);
                BufferedReader r = new BufferedReader(new FileReader("employees.txt"));
                String[] employees = r.readLine().split(",");
                r.close();
                boolean found = Arrays.stream(employees).anyMatch(emp -> emp.equals(name));
                if (found) System.out.println("Employee found!");
                System.out.println("Data Loaded.");
            } 
            else if (command.contains("c")) { 
                BufferedReader r = new BufferedReader(new FileReader("employees.txt"));
                String line = r.readLine();
                r.close();
                int wordCount = line.split("\\s+").length;
                System.out.println(wordCount + " word(s), " + line.length() + " characters");
                System.out.println("Data Loaded.");
            } 
            else if (command.startsWith("u")) { 
                String name = command.substring(1);
                BufferedReader r = new BufferedReader(new FileReader("employees.txt"));
                String[] employees = r.readLine().split(",");
                r.close();
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(name)) employees[i] = "Updated";
                }
                BufferedWriter w = new BufferedWriter(new FileWriter("employees.txt"));
                w.write(String.join(",", employees));
                w.close();
                System.out.println("Data Updated.");
            } 
            else if (command.startsWith("d")) { // Delete employee
                String name = command.substring(1);
                BufferedReader r = new BufferedReader(new FileReader("employees.txt"));
                List<String> employees = new ArrayList<>(Arrays.asList(r.readLine().split(",")));
                r.close();
                employees.remove(name);
                BufferedWriter w = new BufferedWriter(new FileWriter("employees.txt"));
                w.write(String.join(",", employees));
                w.close();
                System.out.println("Data Deleted.");
            }
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }
}
