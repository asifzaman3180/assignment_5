import java.io.*;
import java.util.*;

/**
 * EmployeeManager.java
 *
 * Handles employee records from employees.txt
 * Supports: list, search, add, count, update, and help operations.
 */
public class EmployeeManager
{
    public static void main(String[] args)
    {
        // ✅ Validate command-line arguments
        if (args.length != 1)
        {
            System.out.println("Usage: java EmployeeManager <option>");
            System.out.println("Options: l (list), s (search), + (add), ? (help), c (count), u (update)");
            System.exit(1);
        }

        String userOption = args[0];

        // ✅ Use constant for file path
        switch (userOption)
        {
            case Constants.OPTION_LIST:
                listEmployees();
                break;

            case Constants.OPTION_SEARCH:
                searchEmployee();
                break;

            case Constants.OPTION_ADD:
                addEmployee();
                break;

            case Constants.OPTION_COUNT:
                countEmployees();
                break;

            case Constants.OPTION_UPDATE:
                updateEmployee();
                break;

            case Constants.OPTION_HELP:
                showHelp();
                break;

            default:
                System.out.println(Constants.INVALID_OPTION_MESSAGE);
                break;
        }
    }

    // ============================================================
    // 🧾 1. LIST EMPLOYEES
    // ============================================================
    private static void listEmployees()
    {
        for (String employeeName : readEmployeesFromFile())
        {
            System.out.println(employeeName);
        }
    }

    // ============================================================
    // 🔍 2. SEARCH EMPLOYEE
    // ============================================================
    private static void searchEmployee()
    {
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Enter name to search: ");
        String searchQuery = inputScanner.nextLine().toLowerCase();

        boolean found = false;
        for (String employeeName : readEmployeesFromFile())
        {
            if (employeeName.toLowerCase().contains(searchQuery))
            {
                System.out.println("Found: " + employeeName);
                found = true;
            }
        }

        if (!found)
        {
            System.out.println("No match found.");
        }
    }

    // ============================================================
    // ➕ 3. ADD EMPLOYEE
    // ============================================================
    private static void addEmployee()
    {
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Enter new employee name: ");
        String newEmployeeName = inputScanner.nextLine().trim();

        if (newEmployeeName.isEmpty())
        {
            System.out.println("Invalid name.");
            return;
        }

        List<String> employeeList = readEmployeesFromFile();
        employeeList.add(newEmployeeName);
        writeEmployeesToFile(employeeList);

        System.out.println("Employee added successfully.");
    }

    // ============================================================
    // 🔢 4. COUNT EMPLOYEES
    // ============================================================
    private static void countEmployees()
    {
        System.out.println("Total employees: " + readEmployeesFromFile().size());
    }

    // ============================================================
    // ✏️ 5. UPDATE EMPLOYEE
    // ============================================================
    private static void updateEmployee()
    {
        Scanner inputScanner = new Scanner(System.in);
        List<String> employeeList = readEmployeesFromFile();

        System.out.print("Enter the employee name to update: ");
        String oldEmployeeName = inputScanner.nextLine();

        if (!employeeList.contains(oldEmployeeName))
        {
            System.out.println("Employee not found.");
            return;
        }

        System.out.print("Enter new name: ");
        String newEmployeeName = inputScanner.nextLine();

        employeeList.set(employeeList.indexOf(oldEmployeeName), newEmployeeName);
        writeEmployeesToFile(employeeList);

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
    // 🗂️ 7. FILE OPERATIONS (Refactored & Simplified)
    // ============================================================
    private static List<String> readEmployeesFromFile()
    {
        List<String> employeeList = new ArrayList<>();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(Constants.EMPLOYEE_FILE_PATH)))
        {
            String line = fileReader.readLine();
            if (line != null)
            {
                for (String name : line.split(","))
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

    private static void writeEmployeesToFile(List<String> employeeList)
    {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(Constants.EMPLOYEE_FILE_PATH)))
        {
            fileWriter.write(String.join(",", employeeList));
        }
        catch (IOException ioException)
        {
            System.out.println("Error writing file: " + ioException.getMessage());
        }
    }
}
