//File Name EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {

        // Check arguments
        if (args.length != 1) {
            System.out.println("Error: Invalid number of arguments.");
            return;
        }

        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader read = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String line = read.readLine();
                String employee[] = line.split(",");
                for (String emp : employee) {
                    System.out.println(emp);
                }
            } 
            catch (Exception e) {}
            System.out.println("Data Loaded.");
        }
         else if (args[0].equals("s")) {
            System.out.println("Loading data ...");

            try {
                BufferedReader read = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String line = read.readLine();
                System.out.println(line);
                String employee[] = line.split(",");
                Random rand = new Random();
                int idx = rand.nextInt(employee.length);
                System.out.println(employee[idx]);
            } 
            catch (Exception e) {}
            System.out.println("Data Loaded.");
        } 
        else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt", true));
                String n = args[0].substring(1);
                writer.write(", " + n);
                writer.close();
            } 
            catch (Exception e) {}
            System.out.println("Data Loaded.");
        } 
        else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader read = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String line = read.readLine();
                String employee[] = line.split(",");
                boolean found = false;
                String s = args[0].substring(1);
                for (int i = 0; i < employee.length && !found; i++) {
                    if (employee[i].equals(s)) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }
            } 
            catch (Exception e) {}
            System.out.println("Data Loaded.");
        } 
        else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader read = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String line = read.readLine();
                char[] chars = line.toCharArray();
                boolean inWord = false;
                int count = 0;
                for (char c : chars) {
                    if (c == ' ') {
                        if (!inWord) {
                            count++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }
                System.out.println(count + " word(s) found " + chars.length);
            } 
            catch (Exception e) {}
            System.out.println("Data Loaded.");
        } 
        else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader read = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String line = read.readLine();
                String employee[] = line.split(",");
                String n = args[0].substring(1);
                for (int i = 0; i < employee.length; i++) {
                    if (employee[i].equals(n)) {
                        employee[i] = "Updated";
                    }
                }
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt"));
                writer.write(String.join(",", employee));
                writer.close();
            } 
            catch (Exception e) {}
            System.out.println("Data Updated.");
        } 
        else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader read = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String line = read.readLine();
                String employee[] = line.split(",");
                String n = args[0].substring(1);
                List<String> list = new ArrayList<>(Arrays.asList(employee));
                list.remove(n);
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt"));
                writer.write(String.join(",", list));
                writer.close();
            } 
            catch (Exception e) {}
            System.out.println("Data Deleted.");
        }
    }
}
