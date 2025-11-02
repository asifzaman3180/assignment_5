/**
 * Constants class to store all application constants
 * Task #5 – Replace String Literals with Constants
 */
public class Constants {
    // File paths
    public static final String EMPLOYEE_FILE = "employees.txt";
    
    // Command strings
    public static final String COMMAND_LIST          = "l";
    public static final String COMMAND_RANDOM        = "s";
    public static final String COMMAND_ADD_PREFIX    = "+";
    public static final String COMMAND_SEARCH_PREFIX = "?";
    public static final String COMMAND_COUNT         = "c";
    public static final String COMMAND_UPDATE_PREFIX = "u";
    public static final String COMMAND_DELETE_PREFIX = "d";
    
    // Messages
    public static final String LOADING_DATA_MESSAGE       = "Loading data ...";
    public static final String DATA_LOADED_MESSAGE        = "Data Loaded.";
    public static final String DATA_UPDATED_MESSAGE       = "Data Updated.";
    public static final String DATA_DELETED_MESSAGE       = "Data Deleted.";
    public static final String EMPLOYEE_FOUND_MESSAGE     = "Employee found!";
    public static final String EMPLOYEE_NOT_FOUND_MESSAGE = "Employee not found!";
    public static final String UPDATED_EMPLOYEE_VALUE     = "Updated";
    
    // Error and usage messages
    public static final String INVALID_ARGUMENTS_MESSAGE   = "Invalid number of arguments. Please provide exactly one argument.";
    public static final String UNSUPPORTED_COMMAND_MESSAGE = "Invalid or unsupported argument: \"";
    public static final String FILE_ERROR_MESSAGE          = "Error processing file: ";
    
    // Usage examples
    public static final String USAGE_HEADER = "Usage examples:";
    public static final String USAGE_LIST   = "  l        - List all employees";
    public static final String USAGE_RANDOM = "  s        - Show a random employee";
    public static final String USAGE_ADD    = "  +<name>  - Add a new employee";
    public static final String USAGE_SEARCH = "  ?<name>  - Search for an employee";
    public static final String USAGE_UPDATE = "  u<name>  - Update an employee";
    public static final String USAGE_DELETE = "  d<name>  - Delete an employee";
    public static final String USAGE_COUNT  = "  c        - Count words and characters";
    
    // Formatting constants
    public static final String DATA_SEPARATOR            = ",";
    public static final String DATA_SEPARATOR_WITH_SPACE = ", ";
    public static final String COUNT_FORMAT              = " word(s) found ";
    public static final String CHARACTERS_FORMAT         = " character(s)";
    
}