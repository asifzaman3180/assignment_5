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
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream("employees.txt")));
                String line = reader.readLine();
                String[] employees = line.split(",");
                for (String employee : employees) {
                    System.out.println(employee);
                }
            } catch (Exception e) {}
            System.out.println("Data Loaded.");
        }
        // rest of the commands remain same
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