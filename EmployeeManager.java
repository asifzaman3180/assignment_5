// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {
        // Check arguments
        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")))) {
                String line = reader.readLine();
                String[] employeeList = line.split(",");
                for (String employee : employeeList) {
                    System.out.println(employee);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } 
        else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")))) {
                String line = reader.readLine();
                System.out.println(line);
                String[] employeeList = line.split(",");
                Random random = new Random();
                int randomIndex = random.nextInt(employeeList.length);
                System.out.println(employeeList[randomIndex]);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } 
        else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt", true))) {
                String newEmployee = args[0].substring(1);
                writer.write(", " + newEmployee);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } 
        else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")))) {
                String line = reader.readLine();
                String[] employeeList = line.split(",");
                String searchEmployee = args[0].substring(1);
                boolean found = false;
                for (String employee : employeeList) {
                    if (employee.equals(searchEmployee)) {
                        System.out.println("Employee found!");
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Employee not found!");
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } 
        else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")))) {
                String line = reader.readLine();
                char[] characters = line.toCharArray();
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
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } 
        else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")))) {
                String line = reader.readLine();
                String[] employeeList = line.split(",");
                String employeeToUpdate = args[0].substring(1);
                for (int i = 0; i < employeeList.length; i++) {
                    if (employeeList[i].equals(employeeToUpdate)) {
                        employeeList[i] = "Updated";
                    }
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"))) {
                    writer.write(String.join(",", employeeList));
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            System.out.println("Data Updated.");
        } 
        else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")))) {
                String line = reader.readLine();
                String[] employeeList = line.split(",");
                String employeeToDelete = args[0].substring(1);
                List<String> employeeArrayList = new ArrayList<>(Arrays.asList(employeeList));
                employeeArrayList.remove(employeeToDelete);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"))) {
                    writer.write(String.join(",", employeeArrayList));
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            System.out.println("Data Deleted.");
        }
    }
}
