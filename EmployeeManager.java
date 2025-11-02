import java.io.*;
import java.util.*;

/**
 * EmployeeManager.java
 * 
 * Manages employee records stored in employees.txt.
 * Supports listing, searching, adding, counting, and updating employees.
 */
public class EmployeeManager 
{
    public static void main(String[] args) 
    {
        // ✅ Task 2: Validate command-line arguments
        if (args.length != 1) 
        {
            System.out.println("Usage: java EmployeeManager <option>");
            System.out.println("Options: l (list), s (search), + (add), ? (help), c (count), u (update)");
            System.exit(1);
        }

        // ✅ Task 3: Improved variable naming
        String userOption = args[0];
        String employeeFilePath = "employees.txt";

        switch (userOption) 
        {
            case "l":   // List all employees
                listEmployees(employeeFilePath);
                break;

            case "s":   // Search employee
                searchEmployee(employeeFilePath);
                break;

            case "+":   // Add new employee
                addEmployee(employeeFilePath);
                break;

            case "c":   // Count total employees
                countEmployees(employeeFilePath);
                break;

            case "u":   // Update employee
                updateEmployee(employeeFilePath);
                break;

            case "?":   // Help
                showHelp();
                break;

            default:
                System.out.println("Invalid option. Try: l, s, +, ?, c, u");
                break;
        }
    }

    // ============================================================
    // 🧾 1. LIST EMPLOYEES
    // ============================================================
    private static void listEmployees(String employeeFilePath) 
    {
        List<String> employeeList = readEmployeesFromFile(employeeFilePath);

        System.out.println("=== Employee List ===");
        for (String employeeName : employeeList) 
        {
            System.out.println(employeeName);
        }
    }

    // ============================================================
    // 🔍 2. SEARCH EMPLOYEE
    // ============================================================
    private static void searchEmployee(String employeeFilePath) 
    {
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Enter name to search: ");
        String searchQuery = inputScanner.nextLine().toLowerCase();

        List<String> employeeList = readEmployeesFromFile(employeeFilePath);
        boolean isFound = false;

        for (String employeeName : employeeList) 
        {
            if (employeeName.toLowerCase().contains(searchQuery)) 
            {
                System.out.println("Found: " + employeeName);
                isFound = true;
            }
        }

        if (!isFound) 
        {
            System.out.println("No match found.");
        }
    }

    // ============================================================
    // ➕ 3. ADD EMPLOYEE
    // ============================================================
    private static void addEmployee(String employeeFilePath) 
    {
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Enter new employee name: ");
        String newEmployeeName = inputScanner.nextLine().trim();

        if (newEmployeeName.isEmpty()) 
        {
            System.out.println("Invalid name.");
            return;
        }

        List<String> employeeList = readEmployeesFromFile(employeeFilePath);
        employeeList.add(newEmployeeName);
        writeEmployeesToFile(employeeFilePath, employeeList);

        System.out.println("Employee added successfully.");
    }

    // ============================================================
    // 🔢 4. COUNT EMPLOYEES
    // ============================================================
    private static void countEmployees(String employeeFilePath) 
    {
        List<String> employeeList = readEmployeesFromFile(employeeFilePath);
        System.out.println("Total employees: " + employeeList.size());
    }

    // ============================================================
    // ✏️ 5. UPDATE EMPLOYEE
    // ============================================================
    private static void updateEmployee(String employeeFilePath) 
    {
        Scanner inputScanner = new Scanner(System.in);
        List<String> employeeList = readEmployeesFromFile(employeeFilePath);

        System.out.println("Enter the employee name to update:");
        String oldEmployeeName = inputScanner.nextLine();

        if (!employeeList.contains(oldEmployeeName)) 
        {
            System.out.println("Employee not found.");
            return;
        }

        System.out.println("Enter new name:");
        String newEmployeeName = inputScanner.nextLine();

        int employeeIndex = employeeList.indexOf(oldEmployeeName);
        employeeList.set(employeeIndex, newEmployeeName);

        writeEmployeesToFile(employeeFilePath, employeeList);
        System.out.println("Employee updated successfully.");
    }

    // ============================================================
    // 🧠 6. SHOW HELP
    // ============================================================
    private static void showHelp() 
    {
        System.out.println("Options:");
        System.out.println("l - List employees");
        System.out.println("s - Search employee");
        System.out.println("+ - Add employee");
        System.out.println("c - Count employees");
        System.out.println("u - Update employee");
        System.out.println("? - Show help");
    }

    // ============================================================
    // 🗂️ 7. FILE READ & WRITE HELPERS
    // ============================================================
    private static List<String> readEmployeesFromFile(String employeeFilePath) 
    {
        List<String> employeeList = new ArrayList<>();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(employeeFilePath))) 
        {
            String line = fileReader.readLine();
            if (line != null) 
            {
                String[] employeeNames = line.split(",");
                for (String name : employeeNames) 
                {
                    employeeList.add(name.trim());
                }
            }
        } 
        catch (IOException ioException) 
        {
            System.out.println("Error reading file: " + ioException.getMessage());
        }

        return employeeList;
    }

    private static void writeEmployeesToFile(String employeeFilePath, List<String> employeeList) 
    {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(employeeFilePath))) 
        {
            fileWriter.write(String.join(",", employeeList));
        } 
        catch (IOException ioException) 
        {
            System.out.println("Error writing file: " + ioException.getMessage());
        }
    }
}
