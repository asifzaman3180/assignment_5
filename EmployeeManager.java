// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    // ===== Constants =====
    private static final String FILE_NAME = "employees.txt";

    // Command constants
    private static final String CMD_LIST = "l";
    private static final String CMD_SHOW = "s";
    private static final String CMD_ADD = "+";
    private static final String CMD_SEARCH = "?";
    private static final String CMD_COUNT = "c";
    private static final String CMD_UPDATE = "u";
    private static final String CMD_DELETE = "d";

    // Message constants
    private static final String MSG_LOADING = "Loading data ...";
    private static final String MSG_DATA_LOADED = "Data Loaded.";
    private static final String MSG_DATA_UPDATED = "Data Updated.";
    private static final String MSG_DATA_DELETED = "Data Deleted.";
    private static final String MSG_EMPLOYEE_FOUND = "Employee found!";
    private static final String MSG_INVALID_ARG = "Invalid argument!";
    private static final String MSG_NO_ARGS = "No arguments provided!";
    private static final String MSG_USAGE = """
            Usage:
              l  -> List employees
              s  -> Show random employee
              +X -> Add employee
              ?X -> Search employee
              c  -> Count words/chars
              uX -> Update employee
              dX -> Delete employee
            """;

    // ===== Helper Methods =====
    private static String[] readEmployeesFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new F
