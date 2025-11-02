public class Constants {
    // Data file name (use single source of truth)
    public static final String EMPLOYEE_FILE = "employees.txt";

    // Usage / help message for the program
    public static final String USAGE_MESSAGE = "Usage: java EmployeeManager <command>\n"
            + "Commands:\n"
            + "  l            List all employees\n"
            + "  s            Show a random employee\n"
            + "  +Name        Add employee (use +Name, no space after +)\n"
            + "  ?Name        Search employee (use ?Name, exact match)\n"
            + "  c            Count employees\n"
            + "  uName        Update matching employee to 'Updated' (use uName)\n"
            + "  dName        Delete employee by exact name (use dName)\n";
}