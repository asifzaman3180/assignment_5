// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    private static final Set<String> VALID_COMMANDS = Set.of("l", "s", "c");

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: No arguments provided!");
            System.out.println("Usage: l | s | +name | ?name | c | uName | dName");
            return;
        }

        String command = args[0];

        if (!isValidCommand(command)) {
            System.out.println("Error: Invalid argument provided: " + command);
            System.out.println("Usage: l | s | +name | ?name | c | uName | dName");
            return;
        }

        if (command.equals("l")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(new FileReader("employees.txt"));
                String line = reader.readLine();
                String[] employees = line.split(",");
                for (String employee : employees) {
                    System.out.println(employee);
                }
            } catch (Exception e) {}
            System.out.println("Data Loaded.");
        }
        else if (command.equals("s")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(new FileReader("employees.txt"));
                String line = reader.readLine();
                System.out.println(line);
                String[] employees = line.split(",");
                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);
                System.out.println(employees[randomIndex]);
            } catch (Exception e) {}
            System.out.println("Data Loaded.");
        }
        // rest of the commands updated similarly with descriptive variable names
    }

    private static boolean isValidCommand(String command) {
        if (VALID_COMMANDS.contains(command)) {
            return true;
        }
        if (command.startsWith("+") || command.startsWith("?") || command.startsWith("u") || command.startsWith("d")) {
            return true;
        }
        return false;
    }
}