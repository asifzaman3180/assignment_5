// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {

        // ✅ Argument validation check
        if (args.length != 1) {
            System.out.println("Error: Incorrect number of arguments.");
            System.out.println("Usage:");
            System.out.println("  java EmployeeManager l       --> Load and display all employees");
            System.out.println("  java EmployeeManager s       --> Show a random employee");
            System.out.println("  java EmployeeManager +Name   --> Add a new employee");
            System.out.println("  java EmployeeManager ?Name   --> Search an employee");
            System.out.println("  java EmployeeManager c       --> Count words");
            System.out.println("  java EmployeeManager uName   --> Update employee to 'Updated'");
            System.out.println("  java EmployeeManager dName   --> Delete employee");
            return; // Prevents further execution
        }

        // ✅ Rest of your logic starts here
        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String l = r.readLine();
                String[] e = l.split(",");
                for (String emp : e) {
                    System.out.println(emp.trim());
                }
                r.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");

        } else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String l = r.readLine();
                String[] e = l.split(",");
                Random rand = new Random();
                int idx = rand.nextInt(e.length);
                System.out.println(e[idx].trim());
                r.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");

        } else if (args[0].startsWith("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter w = new BufferedWriter(
                        new FileWriter("employees.txt", true));
                String n = args[0].substring(1);
                w.write(", " + n);
                w.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Added.");

        } else if (args[0].startsWith("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String l = r.readLine();
                String[] e = l.split(",");
                String s = args[0].substring(1).trim();
                boolean found = false;

                for (String emp : e) {
                    if (emp.trim().equalsIgnoreCase(s)) {
                        System.out.println("Employee found!");
                        found = true;
                        break;
                    }
                }
                if (!found) System.out.println("Employee not found!");
                r.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");

        } else if (args[0].equals("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String l = r.readLine();
                String[] e = l.split(",");
                System.out.println(e.length + " employee(s) found.");
                r.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");

        } else if (args[0].startsWith("u")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String l = r.readLine();
                String[] e = l.split(",");
                String n = args[0].substring(1).trim();

                for (int i = 0; i < e.length; i++) {
                    if (e[i].trim().equalsIgnoreCase(n)) {
                        e[i] = "Updated";
                    }
                }

                BufferedWriter w = new BufferedWriter(
                        new FileWriter("employees.txt"));
                w.write(String.join(",", e));
                w.close();
                r.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Updated.");

        } else if (args[0].startsWith("d")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String l = r.readLine();
                String[] e = l.split(",");
                String n = args[0].substring(1).trim();

                List<String> list = new ArrayList<>();
                for (String emp : e) {
                    if (!emp.trim().equalsIgnoreCase(n)) {
                        list.add(emp.trim());
                    }
                }

                BufferedWriter w = new BufferedWriter(
                        new FileWriter("employees.txt"));
                w.write(String.join(",", list));
                w.close();
                r.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Deleted.");

        } else {
            System.out.println("Invalid command. Use 'l', 's', '+name', '?name', 'c', 'uname', or 'dname'.");
        }
    }
}
