// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No arguments provided!");
            return;
        }

        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            try (BufferedReader r = new BufferedReader(new FileReader("employees.txt"))) {
                for (String emp : r.readLine().split(",")) {
                    System.out.println(emp);
                }
            } catch (Exception e) {}
            System.out.println("Data Loaded.");
        }

        else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try (BufferedReader r = new BufferedReader(new FileReader("employees.txt"))) {
                String[] e = r.readLine().split(",");
                System.out.println(e[new Random().nextInt(e.length)]);
            } catch (Exception e) {}
            System.out.println("Data Loaded.");
        }

        else if (args[0].startsWith("+")) {
            System.out.println("Loading data ...");
            try (BufferedWriter w = new BufferedWriter(new FileWriter("employees.txt", true))) {
                w.write(", " + args[0].substring(1));
            } catch (Exception e) {}
            System.out.println("Data Loaded.");
        }

        else if (args[0].startsWith("?")) {
            System.out.println("Loading data ...");
            try (BufferedReader r = new BufferedReader(new FileReader("employees.txt"))) {
                String[] e = r.readLine().split(",");
                String s = args[0].substring(1);
                for (String emp : e) {
                    if (emp.equals(s)) {
                        System.out.println("Employee found!");
                        break;
                    }
                }
            } catch (Exception e) {}
            System.out.println("Data Loaded.");
        }

        else if (args[0].equals("c")) {
            System.out.println("Loading data ...");
            try (BufferedReader r = new BufferedReader(new FileReader("employees.txt"))) {
                int count = r.readLine().split(",").length;
                System.out.println(count + " record(s) found.");
            } catch (Exception e) {}
            System.out.println("Data Loaded.");
        }

        else if (args[0].startsWith("u")) {
            System.out.println("Loading data ...");
            try (BufferedReader r = new BufferedReader(new FileReader("employees.txt"))) {
                String[] e = r.readLine().split(",");
                String n = args[0].substring(1);
                for (int i = 0; i < e.length; i++) {
                    if (e[i].equals(n)) e[i] = "Updated";
                }
                try (BufferedWriter w = new BufferedWriter(new FileWriter("employees.txt"))) {
                    w.write(String.join(",", e));
                }
            } catch (Exception e) {}
            System.out.println("Data Updated.");
        }

        else if (args[0].startsWith("d")) {
            System.out.println("Loading data ...");
            try (BufferedReader r = new BufferedReader(new FileReader("employees.txt"))) {
                List<String> list = new ArrayList<>(Arrays.asList(r.readLine().split(",")));
                list.remove(args[0].substring(1));
                try (BufferedWriter w = new BufferedWriter(new FileWriter("employees.txt"))) {
                    w.write(String.join(",", list));
                }
            } catch (Exception e) {}
            System.out.println("Data Deleted.");
        }
    }
}
