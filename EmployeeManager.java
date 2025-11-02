import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        if (args.length != 6) {
            System.out.println("Error: Invalid number of arguments!");
            System.out.println("Usage: java EmployeeManager l s + ? c u");
            return;
        }

        String[] validCommands = {"l", "s", "+", "?", "c", "u"};
        for (String command : args) {
            boolean isValidCommand = false;
            for (String valid : validCommands) {
                if (command.equals(valid)) {
                    isValidCommand = true;
                    break;
                }
            }
            if (!isValidCommand) {
                System.out.println("Error: Invalid command '" + command + "'");
                System.out.println("Valid commands are: l, s, +, ?, c, u");
                return;
            }
        }

        List<String> employeeList = new ArrayList<>();
        int employeeCount = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("employees.txt"));
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                employeeList = new ArrayList<>(Arrays.asList(line.split(",")));
                employeeCount = employeeList.size();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading employees.txt: " + e.getMessage());
        }

 
        for (String command : args) {
            if (command.equals("s")) {
                String searchQuery = "Alice Johnson"; 
                boolean isFound = false;
                for (int index = 0; index < employeeCount; index++) {
                    if (employeeList.get(index).equalsIgnoreCase(searchQuery)) {
                        isFound = true;
                        break;
                    }
                }
                if (isFound) {
                    System.out.println(searchQuery + " found in employee list.");
                } else {
                    System.out.println(searchQuery + " not found.");
                }
            }
        }
    }
}
