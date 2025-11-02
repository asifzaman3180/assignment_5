import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        if (args.length != 6) { 
            System.out.println("Error: Invalid number of arguments!");
            System.out.println("Usage: java EmployeeManager l s + ? c u");
            return; 
        }

        String[] validCommands = {"l", "s", "+", "?", "c", "u"};
        for (String cmd : args) {
            boolean isValid = false;
            for (String v : validCommands) {
                if (cmd.equals(v)) {
                    isValid = true;
                    break;
                }
            }
            if (!isValid) {
                System.out.println("Error: Invalid command '" + cmd + "'");
                System.out.println("Valid commands are: l, s, +, ?, c, u");
                return;  
            }
        }

       
        System.out.println("All arguments valid. Program continues...");
        
    }
}
