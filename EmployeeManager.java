// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println(Constants.NO_COMMAND);
            return;
        }

        String command = args[0];

        if (command.equals("l")) {
            System.out.println(Constants.LOADING_DATA);
            for (String employee : readEmployees()) {
                System.out.println(employee);
            }
            System.out.println(Constants.DATA_LOADED);

        } else if (command.equals("s")) {
            System.out.println(Constants.LOADING_DATA);
            String[] employees = readEmployees();
            System.out.println(String.join(",", employees));
            System.out.println(employees[new Random().nextInt(employees.length)]);
            System.out.println(Constants.DATA_LOADED);

        } else if (command.startsWith("+")) {
            System.out.println(Constants.LOADING_DATA);
            String nameToAdd = command.substring(1);
            String[] employees = readEmployees();
            String[] updated = Arrays.copyOf(employees, employees.length + 1);
            updated[updated.length - 1] = nameToAdd;
            writeEmployees(updated);
            System.out.println(Constants.DATA_LOADED);

        } else if (command.startsWith("?")) {
            System.out.println(Constants.LOADING_DATA);
            String searchName = command.substring(1);
            boolean found = Arrays.stream(readEmployees()).anyMatch(searchName::equals);
            System.out.println(found ? Constants.EMPLOYEE_FOUND : Constants.EMPLOYEE_NOT_FOUND);
            System.out.println(Constants.DATA_LOADED);

        } else if (command.equals("c")) {
            System.out.println(Constants.LOADING_DATA);
            String[] employees = readEmployees();
            long wordCount = Arrays.stream(employees).filter(s -> !s.isBlank()).count();
            int charCount = Arrays.stream(employees).mapToInt(String::length).sum();
            System.out.println(wordCount + " word(s) found, total characters: " + charCount);
            System.out.println(Constants.DATA_LOADED);

        } else if (command.startsWith("u")) {
            System.out.println(Constants.LOADING_DATA);
            String nameToUpdate = command.substring(1);
            String[] employees = readEmployees();
            for (int i = 0; i < employees.length; i++) {
                if (employees[i].equals(nameToUpdate)) employees[i] = "Updated";
            }
            writeEmployees(employees);
            System.out.println(Constants.DATA_UPDATED);

        } else if (command.startsWith("d")) {
            System.out.println(Constants.LOADING_DATA);
            String nameToDelete = command.substring(1);
            writeEmployees(Arrays.stream(readEmployees())
                    .filter(e -> !e.equals(nameToDelete))
                    .toArray(String[]::new));
            System.out.println(Constants.DATA_DELETED);
        }
    }

    // Reusable Methods
    private static String[] readEmployees() {
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.EMPLOYEE_FILE))) {
            String line = reader.readLine();
            if (line == null || line.isBlank()) return new String[0];
            return line.split(",");
        } catch (Exception ex) {
            System.out.println(Constants.ERROR_READING_FILE);
            return new String[0];
        }
    }

    private static void writeEmployees(String[] employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE))) {
            writer.write(String.join(",", employees));
        } catch (Exception ex) {
            System.out.println(Constants.ERROR_WRITING_FILE);
        }
    }
}
