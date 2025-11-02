// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {
        String command = args[0];

        if (command.equals("l")) {
            System.out.println("Loading data ...");
            String[] employeeList = readEmployees();
            if (employeeList != null) {
                for (String employee : employeeList) {
                    System.out.println(employee);
                }
            }
            System.out.println("Data Loaded.");
        } 
        else if (command.equals("s")) {
            System.out.println("Loading data ...");
            String[] employeeList = readEmployees();
            if (employeeList != null) {
                System.out.println(String.join(", ", employeeList));
                Random random = new Random();
                int randomIndex = random.nextInt(employeeList.length);
                System.out.println(employeeList[randomIndex]);
            }
            System.out.println("Data Loaded.");
        } 
        else if (command.startsWith("+")) {
            System.out.println("Loading data ...");
            String newEmployee = command.substring(1);
            appendEmployee(newEmployee);
            System.out.println("Data Loaded.");
        } 
        else if (command.startsWith("?")) {
            System.out.println("Loading data ...");
            String searchEmployee = command.substring(1);
            String[] employeeList = readEmployees();
            boolean found = false;
            if (employeeList != null) {
                for (String employee : employeeList) {
                    if (employee.equals(searchEmployee)) {
                        System.out.println("Employee found!");
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                System.out.println("Employee not found!");
            }
            System.out.println("Data Loaded.");
        } 
        else if (command.contains("c")) {
            System.out.println("Loading data ...");
            String[] employeeList = readEmployees();
            if (employeeList != null) {
                String allEmployees = String.join(" ", employeeList);
                char[] characters = allEmployees.toCharArray();
                int wordCount = 0;
                boolean inWord = false;
                for (char character : characters) {
                    if (character == ' ') {
                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }
                System.out.println(wordCount + " word(s) found, total characters: " + characters.length);
            }
            System.out.println("Data Loaded.");
        } 
        else if (command.startsWith("u")) {
            System.out.println("Loading data ...");
            String employeeToUpdate = command.substring(1);
            String[] employeeList = readEmployees();
            if (employeeList != null) {
                for (int i = 0; i < employeeList.length; i++) {
                    if (employeeList[i].equals(employeeToUpdate)) {
                        employeeList[i] = "Updated";
                    }
                }
                writeEmployees(employeeList);
            }
            System.out.println("Data Updated.");
        } 
        else if (command.startsWith("d")) {
            System.out.println("Loading data ...");
            String employeeToDelete = command.substring(1);
            String[] employeeList = readEmployees();
            if (employeeList != null) {
                List<String> employeeArrayList = new ArrayList<>(Arrays.asList(employeeList));
                employeeArrayList.remove(employeeToDelete);
                writeEmployees(employeeArrayList.toArray(new String[0]));
            }
            System.out.println("Data Deleted.");
        }
    }

    /**
     * Reads the employee list from "employees.txt".
     * Returns null if an error occurs.
     */
    private static String[] readEmployees() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                return line.split(",");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Writes the given employee list to "employees.txt".
     */
    private static void writeEmployees(String[] employeeList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"))) {
            writer.write(String.join(",", employeeList));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Appends a new employee to "employees.txt".
     */
    private static void appendEmployee(String newEmployee) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt", true))) {
            writer.write(", " + newEmployee);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
