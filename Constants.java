// File Name: Constants.java
public class Constants {
    // File paths
    public static final String EMPLOYEES_FILE_PATH = "employees.txt";
    
    // Date formats (for future use)
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    // Operation messages
    public static final String LOADING_MESSAGE = "Loading data ...";
    public static final String DATA_LOADED_MESSAGE = "Data Loaded.";
    public static final String DATA_UPDATED_MESSAGE = "Data Updated.";
    public static final String DATA_DELETED_MESSAGE = "Data Deleted.";
    
    // Employee operations
    public static final String EMPLOYEE_FOUND_MESSAGE = "Employee found!";
    public static final String UPDATED_PLACEHOLDER = "Updated";
    
    // Error messages
    public static final String INVALID_ARGUMENT_MESSAGE = "Error: Invalid or unsupported argument.";
    public static final String MISSING_NAME_MESSAGE = "Error: Missing employee name for operation.";
    public static final String USAGE_MESSAGE = "Usage: java EmployeeManager [l|s|+name|?name|c|uname|dname]";
    public static final String FILE_ERROR_MESSAGE = "Error: Unable to access employee data file.";
}