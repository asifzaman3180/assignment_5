<<<<<<< HEAD
=======

>>>>>>> task2
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {
<<<<<<< HEAD
        if (args.length == 0) {
            System.out.println("Please provide a command. Use ?, l, +, s, c, u, or d");
=======

        if (args.length == 0) {
            System.out.println("Please provide a command. Use ?, l, +, s, c, u, or d");

>>>>>>> task2
            return;
        }

        String command = args[0];

<<<<<<< HEAD
=======

>>>>>>> task2
        switch (command) {
            case "l":
                listEmployees();
                break;
            case "s":
                searchRandomEmployee();
                break;
            case "+":
                addEmployee();
                break;
            case "?":
                searchEmployeeByName();
                break;
            case "c":
                countEmployees();
                break;
            case "u":
                updateEmployee();
                break;
            case "d":
                deleteEmployee();
                break;
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }

    private static void listEmployees() {
        System.out.println("Loading data...");
        try (BufferedReader r = new BufferedReader(new FileReader("employees.txt"))) {
            String line = r.readLine();
            if (line == null) {
                System.out.println("No employees found.");
                return;
            }
            String[] employees = line.split(",");
            for (String emp : employees) {
                System.out.println(emp.trim());
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        System.out.println("Data loaded.");
    }

    private static void searchRandomEmployee() {
        System.out.println("Loading data...");
        try (BufferedReader r = new BufferedReader(new FileReader("employees.txt"))) {
            String line = r.readLine();
            String[] employees = line.split(",");
            Random rand = new Random();
            int idx = rand.nextInt(employees.length);
            System.out.println("Random employee: " + employees[idx]);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Data loaded.");
    }

    private static void addEmployee() {
        System.out.println("Loading data...");
        try (BufferedWriter w = new BufferedWriter(new FileWriter("employees.txt", true))) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter name to add: ");
            String name = sc.nextLine();
            w.write("," + name);
            System.out.println("Added employee: " + name);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Data saved.");
    }

    private static void searchEmployeeByName() {
        System.out.println("Loading data...");
        try (BufferedReader r = new BufferedReader(new FileReader("employees.txt"))) {
            String line = r.readLine();
            String[] employees = line.split(",");
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter name to search: ");
            String name = sc.nextLine();
            boolean found = false;
            for (String emp : employees) {
                if (emp.trim().equalsIgnoreCase(name)) {
                    found = true;
                    break;
                }
            }
            if (found)
                System.out.println("Employee found!");
            else
                System.out.println("Employee not found!");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Data loaded.");
    }

    private static void countEmployees() {
        System.out.println("Loading data...");
        try (BufferedReader r = new BufferedReader(new FileReader("employees.txt"))) {
            String line = r.readLine();
            String[] employees = line.split(",");
            System.out.println("Total employees: " + employees.length);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Data loaded.");
    }

    private static void updateEmployee() {
        System.out.println("Loading data...");
        try (BufferedReader r = new BufferedReader(new FileReader("employees.txt"))) {
            String line = r.readLine();
            String[] employees = line.split(",");
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter name to update: ");
            String oldName = sc.nextLine();
            System.out.print("Enter new name: ");
            String newName = sc.nextLine();

            for (int i = 0; i < employees.length; i++) {
                if (employees[i].trim().equalsIgnoreCase(oldName)) {
                    employees[i] = newName;
                }
            }
            BufferedWriter w = new BufferedWriter(new FileWriter("employees.txt"));
            w.write(String.join(",", employees));
            w.close();
            System.out.println("Updated employee: " + oldName + " to " + newName);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Data updated.");
    }

    private static void deleteEmployee() {
        System.out.println("Loading data...");
        try (BufferedReader r = new BufferedReader(new FileReader("employees.txt"))) {
            String line = r.readLine();
            List<String> list = new ArrayList<>(Arrays.asList(line.split(",")));
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter name to delete: ");
            String name = sc.nextLine();
            list.removeIf(e -> e.trim().equalsIgnoreCase(name));
            BufferedWriter w = new BufferedWriter(new FileWriter("employees.txt"));
            w.write(String.join(",", list));
            w.close();
            System.out.println("Deleted employee: " + name);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Data deleted.");
    }
<<<<<<< HEAD
=======

>>>>>>> task2
}
