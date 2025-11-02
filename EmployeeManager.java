import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {

        // ✅ Task 2: Argument Validation (Fix Early Termination)
        if (args == null || args.length != 1) {
            System.out.println("Invalid number of arguments!");
            System.out.println("Usage: java EmployeeManager <command>");
            System.out.println("Available commands:");
            System.out.println("  l            List all employees");
            System.out.println("  s            Show a random employee");
            System.out.println("  +Name        Add employee (use +Name, no space after +)");
            System.out.println("  ?Name        Search employee (use ?Name, exact match)");
            System.out.println("  c            Count employees");
            System.out.println("  uName        Update matching employee to 'Updated' (use uName)");
            System.out.println("  dName        Delete employee by exact name (use dName)");
            return;
        }

        // ✅ Main Logic (same as original)
        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String l = r.readLine();
                String e[] = l.split(",");
                for (String emp : e) {
                    System.out.println(emp);
                }
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
                System.out.println(l);
                String e[] = l.split(",");
                Random rand = new Random();
                int idx = rand.nextInt(e.length);
                System.out.println(e[idx]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("+")) {
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
            System.out.println("Data Loaded.");
        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String l = r.readLine();
                String e[] = l.split(",");
                boolean found = false;
                String s = args[0].substring(1);
                for (int i = 0; i < e.length && !found; i++) {
                    if (e[i].equals(s)) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String l = r.readLine();
                char[] chars = l.toCharArray();
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
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String l = r.readLine();
                String e[] = l.split(",");
                String n = args[0].substring(1);
                for (int i = 0; i < e.length; i++) {
                    if (e[i].equals(n)) {
                        e[i] = "Updated";
                    }
                }
                BufferedWriter w = new BufferedWriter(
                        new FileWriter("employees.txt"));
                w.write(String.join(",", e));
                w.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Updated.");
        } else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String l = r.readLine();
                String e[] = l.split(",");
                String n = args[0].substring(1);
                List<String> list = new ArrayList<>(Arrays.asList(e));
                list.remove(n);
                BufferedWriter w = new BufferedWriter(
                        new FileWriter("employees.txt"));
                w.write(String.join(",", list));
                w.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Deleted.");
        } else {
            // ✅ Optional improvement for invalid command
            System.out.println("Invalid command: " + args[0]);
            System.out.println("Use one of: l, s, +Name, ?Name, c, uName, dName");
        }
    }
}

