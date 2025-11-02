//File Name EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    
    public static void main(String[] args) {
        // Check arguments
        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String line = reader.readLine();
                String employees[] = line.split(",");
                for (String employee : employees) {
                    System.out.println(employee);
                }
            } catch (Exception e) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String line = reader.readLine();
                System.out.println(line);
                String employees[] = line.split(",");
                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);
                System.out.println(employees[randomIndex]);
            } catch (Exception e) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter writer = new BufferedWriter(
                    new FileWriter("employees.txt", true)
                );
                String newEmployee = args[0].substring(1);
                writer.write(", " + newEmployee);
                writer.close();
            } catch (Exception e) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String line = reader.readLine();
                String employees[] = line.split(",");
                boolean found = false;
                String searchName = args[0].substring(1);
                for (int i = 0; i < employees.length && !found; i++) {
                    if (employees[i].equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }
            } catch (Exception e) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String line = reader.readLine();
                char[] characters = line.toCharArray();
                boolean inWord = false;
                int count = 0;
                for (char character : characters) {
                    if (character == ' ') {
                        if (!inWord) {
                            count++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }
                System.out.println(count + " word(s) found " + characters.length);
            } catch (Exception e) {
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String line = reader.readLine();
                String employees[] = line.split(",");
                String employeeName = args[0].substring(1);
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(employeeName)) {
                        employees[i] = "Updated";
                    }
                }
                BufferedWriter writer = new BufferedWriter(
                    new FileWriter("employees.txt")
                );
                writer.write(String.join(",", employees));
                writer.close();
            } catch (Exception e) {
            }
            System.out.println("Data Updated.");
        } else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String line = reader.readLine();
                String employees[] = line.split(",");
                String employeeName = args[0].substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(employeeName);
                BufferedWriter writer = new BufferedWriter(
                    new FileWriter("employees.txt")
                );
                writer.write(String.join(",", employeeList));
                writer.close();
            } catch (Exception e) {
            }
            System.out.println("Data Deleted.");
        }
    }
}