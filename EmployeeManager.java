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
                System.out.println(employeeList[new Random().nextInt(employeeList.length)]);
            }
            System.out.println("Data Loaded.");
        } 
        else if (command.startsWith("+")) {
            System.out.println("Loading data ...");
            appendEmployee(command.substring(1));
            System.out.println("Data Loaded.");
        } 
        else if (command.startsWith("?")) {
            System.out.println("Loading data ...");
            boolean found = false;
            String searchEmployee = command.substring(1);
            String[] employeeList = readEmployees();
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
                int wordCount = 0;
                boolean inWord = false;
                for (char character : String.join(" ", employeeList).toCharArray()) {
                    if (character == ' ') {
                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }
                System.out.println(wordCount + " word(s) found, total characters: " + String.join(" ", employeeList).length());
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
            String[] employeeList = readEmployees();
            if (employeeList != null) {
                List<String> employeeArrayList = new ArrayList<>(Arrays.asList(employeeList));
                employeeArrayList.remove(command.substring(1));
                writeEmployees(employeeArrayList.toArray(new String[0]));
            }
            System.out.println("Data Deleted.");
        }
    }

    /**
     * Reads the employee list from the file defined in Constants.
     * Returns null if an error occurs.
     */
    private static String[] readEmployees() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(Constants.EMPLOYEE_FILE)))) {
            String line = reader.readLine();
            return (line != null && !line.isEmpty()) ? line.split(",") : null;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Writes the given employee list to the file defined in Constants.
     */
    private static void writeEmployees(String[] employeeList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE))) {
            writer.write(String.join(",", employeeList));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Appends a new employee to the file defined in Constants.
     */
    private static void appendEmployee(String newEmployee) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE, true))) {
            writer.write(", " + newEmployee);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
