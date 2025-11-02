//File Name EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    
    public static void main(String[] args) {
        // Validate command-line arguments
        if (args.length != 1) {
            System.out.println("Error: Please provide exactly one command argument.");
            System.out.println("Usage: java EmployeeManager <command>");
            System.out.println("Commands: l, s, +<name>, ?<name>, c, u<name>, d<name>");
            return;
        }
        
        String userCommand = args[0];
        
        // Check arguments
        if (userCommand.equals("l")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader fileReader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String fileContent = fileReader.readLine();
                String employeeList[] = fileContent.split(",");
                for (String employeeName : employeeList) {
                    System.out.println(employeeName);
                }
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } else if (userCommand.equals("s")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader fileReader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String fileContent = fileReader.readLine();
                System.out.println(fileContent);
                String employeeList[] = fileContent.split(",");
                Random randomGenerator = new Random();
                int randomEmployeeIndex = randomGenerator.nextInt(employeeList.length);
                System.out.println(employeeList[randomEmployeeIndex]);
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } else if (userCommand.contains("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter fileWriter = new BufferedWriter(
                    new FileWriter("employees.txt", true)
                );
                String newEmployeeName = userCommand.substring(1);
                fileWriter.write(", " + newEmployeeName);
                fileWriter.close();
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } else if (userCommand.contains("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader fileReader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String fileContent = fileReader.readLine();
                String employeeList[] = fileContent.split(",");
                boolean employeeFound = false;
                String targetEmployeeName = userCommand.substring(1);
                for (int employeeIndex = 0; employeeIndex < employeeList.length && !employeeFound; employeeIndex++) {
                    if (employeeList[employeeIndex].equals(targetEmployeeName)) {
                        System.out.println("Employee found!");
                        employeeFound = true;
                    }
                }
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } else if (userCommand.contains("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader fileReader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String fileContent = fileReader.readLine();
                char[] characterArray = fileContent.toCharArray();
                boolean currentlyInWord = false;
                int wordCount = 0;
                for (char currentCharacter : characterArray) {
                    if (currentCharacter == ' ') {
                        if (!currentlyInWord) {
                            wordCount++;
                            currentlyInWord = true;
                        } else {
                            currentlyInWord = false;
                        }
                    }
                }
                System.out.println(wordCount + " word(s) found " + characterArray.length);
            } catch (Exception exception) {
            }
            System.out.println("Data Loaded.");
        } else if (userCommand.contains("u")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader fileReader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String fileContent = fileReader.readLine();
                String employeeList[] = fileContent.split(",");
                String employeeToUpdate = userCommand.substring(1);
                for (int employeeIndex = 0; employeeIndex < employeeList.length; employeeIndex++) {
                    if (employeeList[employeeIndex].equals(employeeToUpdate)) {
                        employeeList[employeeIndex] = "Updated";
                    }
                }
                BufferedWriter fileWriter = new BufferedWriter(
                    new FileWriter("employees.txt")
                );
                fileWriter.write(String.join(",", employeeList));
                fileWriter.close();
            } catch (Exception exception) {
            }
            System.out.println("Data Updated.");
        } else if (userCommand.contains("d")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader fileReader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String fileContent = fileReader.readLine();
                String employeeList[] = fileContent.split(",");
                String employeeToDelete = userCommand.substring(1);
                List<String> updatedEmployeeList = new ArrayList<>(Arrays.asList(employeeList));
                updatedEmployeeList.remove(employeeToDelete);
                BufferedWriter fileWriter = new BufferedWriter(
                    new FileWriter("employees.txt")
                );
                fileWriter.write(String.join(",", updatedEmployeeList));
                fileWriter.close();
            } catch (Exception exception) {
            }
            System.out.println("Data Deleted.");
        } else {
            System.out.println("Error: Invalid command '" + userCommand + "'");
            System.out.println("Valid commands: l, s, +<name>, ?<name>, c, u<name>, d<name>");
        }
    }
}