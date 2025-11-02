// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a command (l, s, +Name, ?Name, c, uName, dName).");
            return;
        }

        String command = args[0];
        System.out.println("Loading data ...");

        switch (command.charAt(0)) {
            case 'l':
                for (String emp : readEmployees()) System.out.println(emp);
                System.out.println("Data Loaded.");
                break;

            case 's':
                String[] all = readEmployees();
                if (all.length > 0) {
                    System.out.println(String.join(",", all));
                    System.out.println(all[new Random().nextInt(all.length)]);
                } else {
                    System.out.println("No employees found.");
                }
                System.out.println("Data Loaded.");
                break;

            case '+':
                String toAdd = command.substring(1);
                String[] updated = Arrays.copyOf(readEmployees(), readEmployees().length + 1);
                updated[updated.length - 1] = toAdd;
                writeEmployees(updated);
                System.out.println("Data Loaded.");
                break;

            case '?':
                String find = command.substring(1);
                boolean exists = Arrays.asList(readEmployees()).contains(find);
                System.out.println(exists ? "Employee found!" : "Employee not found.");
                System.out.println("Data Loaded.");
                break;

            case 'c':
                String[] employees = readEmployees();
                int totalWords = (int) Arrays.stream(employees).filter(s -> !s.isBlank()).count();
                int totalChars = Arrays.stream(employees).mapToInt(String::length).sum();
                System.out.println(totalWords + " word(s) found, total characters: " + totalChars);
                System.out.println("Data Loaded.");
                break;

            case 'u':
                String nameToUpdate = command.substring(1);
                String[] list = readEmployees();
                for (int i = 0; i < list.length; i++)
                    if (list[i].equals(nameToUpdate)) list[i] = "Updated";
                writeEmployees(list);
                System.out.println("Data Updated.");
                break;

            case 'd':
                String nameToDelete = command.substring(1);
                List<String> empList = new ArrayList<>(Arrays.asList(readEmployees()));
                empList.remove(nameToDelete);
                writeEmployees(empList.toArray(new String[0]));
                System.out.println("Data Deleted.");
                break;

            default:
                System.out.println("Invalid command.");
        }
    }

    private static String[] readEmployees() {
        try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
            String line = reader.readLine();
            return (line == null || line.isBlank()) ? new String[0] : line.split(",");
        } catch (Exception e) {
            System.out.println("Error reading employees file.");
            return new String[0];
        }
    }

    private static void writeEmployees(String[] employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"))) {
            writer.write(String.join(",", employees));
        } catch (Exception e) {
            System.out.println("Error writing employees file.");
        }
    }
}
