import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error: Invalid number of arguments.");
            return;
        }

        String command = args[0];

        if (command.equals("l")) {
            System.out.println(Constants.LOADING_MSG);
            String[] employees = readEmployees();
            if (employees != null) {
                for (String emp : employees) System.out.println(emp);
            }
            System.out.println(Constants.DATA_LOADED_MSG);
        } 
        else if (command.equals("s")) {
            System.out.println(Constants.LOADING_MSG);
            String[] employees = readEmployees();
            if (employees != null) {
                Random rand = new Random();
                System.out.println(employees[rand.nextInt(employees.length)]);
            }
            System.out.println(Constants.DATA_LOADED_MSG);
        } 
        else if (command.startsWith("+")) {
            System.out.println(Constants.LOADING_MSG);
            appendFile(", " + command.substring(1));
            System.out.println(Constants.DATA_LOADED_MSG);
        } 
        else if (command.startsWith("?")) {
            System.out.println(Constants.LOADING_MSG);
            String[] employees = readEmployees();
            if (employees != null) {
                for (String emp : employees) { 
                    if (emp.equals(command.substring(1))) {
                        System.out.println("Employee found!"); 
                        break;
                    }
                }
            }
            System.out.println(Constants.DATA_LOADED_MSG);
        } 
        else if (command.equals("c")) {
            System.out.println(Constants.LOADING_MSG);
            String[] employees = readEmployees();
        if (employees != null) {
            int wordCount = employees.length;
            int charCount = Arrays.stream(employees)
                              .mapToInt(String::length)
                              .sum(); 
        System.out.println(wordCount + " employee(s), " + charCount + " character(s)");
    } else {
        System.out.println("No employee data found.");
    }
        System.out.println(Constants.DATA_LOADED_MSG);
}

        else if (command.startsWith("u")) {
            System.out.println(Constants.LOADING_MSG);
            String[] employees = readEmployees();
            if (employees != null) {
                String target = command.substring(1);
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(target)) employees[i] = "Updated";
                }
                writeFile(String.join(",", employees));
            }
            System.out.println("Data Updated.");
        } 
        else if (command.startsWith("d")) {
            System.out.println(Constants.LOADING_MSG);
            String[] employees = readEmployees();
            if (employees != null) {
                List<String> list = new ArrayList<>(Arrays.asList(employees));
                list.remove(command.substring(1));
                writeFile(String.join(",", list));
            }
            System.out.println("Data Deleted.");
        }
    }

    private static String[] readEmployees() {
        try (BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(Constants.EMPLOYEE_FILE)))) {
            String line = read.readLine();
            return line != null ? line.split(",") : null;
        } catch (Exception e) { return null; }
    }

    private static void writeFile(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE))) {
            writer.write(content);
        } catch (Exception e) {}
    }

    private static void appendFile(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE, true))) {
            writer.write(content);
        } catch (Exception e) {}
    }
}
