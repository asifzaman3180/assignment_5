//File Name Constants.java
public class Constants {
    // File paths
    public static final String EMPLOYEE_DATA_FILE = "employees.txt";
    
    // Command prefixes and values
    public static final String LIST_COMMAND = "l";
    public static final String RANDOM_COMMAND = "s";
    public static final String ADD_COMMAND_PREFIX = "+";
    public static final String SEARCH_COMMAND_PREFIX = "?";
    public static final String COUNT_COMMAND = "c";
    public static final String UPDATE_COMMAND_PREFIX = "u";
    public static final String DELETE_COMMAND_PREFIX = "d";
    
    // Messages
    public static final String LOADING_DATA_MESSAGE = "Loading data ...";
    public static final String DATA_LOADED_MESSAGE = "Data Loaded.";
    public static final String DATA_UPDATED_MESSAGE = "Data Updated.";
    public static final String DATA_DELETED_MESSAGE = "Data Deleted.";
    public static final String EMPLOYEE_FOUND_MESSAGE = "Employee found!";
    public static final String UPDATED_VALUE = "Updated";
    
    // Error messages
    public static final String INVALID_ARGUMENT_ERROR = "Error: Please provide exactly one command argument.";
    public static final String USAGE_MESSAGE = "Usage: java EmployeeManager <command>";
    public static final String COMMANDS_LIST = "Commands: l, s, +<name>, ?<name>, c, u<name>, d<name>";
    public static final String INVALID_COMMAND_ERROR = "Error: Invalid command '";
}