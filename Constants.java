// File Name: Constants.java

public class Constants {
    // File paths
    public static final String EMPLOYEE_FILE = "employees.txt";

    // Messages
    public static final String LOADING_DATA = "Loading data ...";
    public static final String DATA_LOADED = "Data Loaded.";
    public static final String DATA_UPDATED = "Data Updated.";
    public static final String DATA_DELETED = "Data Deleted.";

    public static final String ERROR_READ = "Error reading employee data.";
    public static final String ERROR_WRITE = "Error writing to file.";
    public static final String ERROR_UPDATE = "Error updating employee data.";
    public static final String ERROR_DELETE = "Error deleting employee.";
    public static final String ERROR_UNKNOWN = "Error: Unknown command.";

    // Usage info
    public static final String USAGE_MESSAGE = """
Error: Invalid number of arguments.
Usage: java EmployeeManager [command]
Commands:
  l   -> List all employees
  s   -> Show random employee
  +X  -> Add employee named X
  ?X  -> Search for employee X
  c   -> Count employees
  uX  -> Update employee X
  dX  -> Delete employee X
""";
}
