import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

  
        if (args.length != 6) {
            System.out.println("Error: Invalid number of arguments!");
            System.out.println("Usage: java EmployeeManager l s + ? c u");
            return;
        }

        // Task #5: Use Constants instead of string literals
        for (String command : args) {
            boolean isValidCommand = false;
            for (String valid : Constants.VALID_COMMANDS) {
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

   
        List<String> employeeList = readEmployees();

        for (String command : args) {
            if (command.equals("+")) {
                String newEmployee = "David Green"; 
                employeeList.add(newEmployee);
                writeEmployees(employeeList);
                System.out.println(newEmployee + " added to employee list.");
            }

            if (command.equals("s")) {
                String searchQuery = "Alice Johnson";
                boolean isFound = false;
                for (int index = 0; index < employeeList.size(); index++) {
                    if (employeeList.get(index).equalsIgnoreCase(searchQuery)) {
                        isFound = true;
                        break;
                    }
                }
                System.out.println(isFound ? searchQuery + " found." : searchQuery + " not found.");
            }

            if (command.equals("l")) {
                System.out.println("Employee List: " + String.join(", ", employeeList));
            }
        }
    }

    public static List<String> readEmployees() {
        List<String> employees = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Constants.EMPLOYEE_FILE));
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                employees = new ArrayList<>(Arrays.asList(line.split(",")));
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading " + Constants.EMPLOYEE_FILE + ": " + e.getMessage());
        }
        return employees;
    }

    // Method to write employees to file
    public static void writeEmployees(List<String> employees) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE));
            writer.write(String.join(",", employees));
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing " + Constants.EMPLOYEE_FILE + ": " + e.getMessage());
        }
    }
}
