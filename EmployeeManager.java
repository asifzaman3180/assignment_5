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
            System.out.println(Constants.LOADING_MSG);
            String line = readFile();
            if (line != null) {
                String employee[] = line.split(",");
                for (String emp : employee) {
                    System.out.println(emp);
                }
            }  
            System.out.println(Constants.DATA_LOADED_MSG);
        }
         else if (args[0].equals("s")) {
            System.out.println(Constants.LOADING_MSG);

            String line = readFile();
            if (line != null) {
                System.out.println(line);
                String employee[] = line.split(",");
                Random rand = new Random();
                int idx = rand.nextInt(employee.length);
                System.out.println(employee[idx]);
            }
            System.out.println(Constants.DATA_LOADED_MSG);
        } 
        else if (args[0].contains("+")) {
            System.out.println(Constants.LOADING_MSG);
            String n = args[0].substring(1);
            appendFile(", " + n);
            System.out.println(Constants.DATA_LOADED_MSG);
        } 
        else if (args[0].contains("?")) {
            System.out.println(Constants.LOADING_MSG);
            String line = readFile();
            if (line != null) {
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
            
            System.out.println(Constants.DATA_LOADED_MSG);
        } 
       else if (args[0].contains("c")) {
            System.out.println(Constants.LOADING_MSG);
            String line = readFile();
            if (line != null) {
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
            System.out.println(Constants.DATA_LOADED_MSG);
        } 
        else if (args[0].contains("u")) {
            System.out.println(Constants.LOADING_MSG);
            String line = readFile();
            if (line != null) {
                String employee[] = line.split(",");
                String n = args[0].substring(1);
                for (int i = 0; i < employee.length; i++) {
                    if (employee[i].equals(n)) {
                        employee[i] = "Updated";
                    }
                }
                writeFile(String.join(",", employee));
            }
            System.out.println("Data Updated.");
        } 
        else if (args[0].contains("d")) {
            System.out.println(Constants.LOADING_MSG);
            String line = readFile();
            if (line != null) {
                String employee[] = line.split(",");
                String n = args[0].substring(1);
                List<String> list = new ArrayList<>(Arrays.asList(employee));
                list.remove(n);
                writeFile(String.join(",", list));
            }
            System.out.println("Data Deleted.");
        }
    }

    private static String readFile() {
        try {
            BufferedReader read = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(Constants.EMPLOYEE_FILE)));
            return read.readLine();
        } catch (Exception e) {
            return null;
        }
    }

    private static void writeFile(String content) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(Constants.EMPLOYEE_FILE));
            writer.write(content);
            writer.close();
        } catch (Exception e) {}
    }

    private static void appendFile(String content) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(Constants.EMPLOYEE_FILE, true));
            writer.write(content);
            writer.close();
        } catch (Exception e) {}
    }
}