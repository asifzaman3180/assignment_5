//File Name EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {    // Check arguments


        if (args[0].equals("l")) {
            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")));

                String list = reader.readLine();
                String e[] = list.split(",");

                for (String emp : e) {
                    System.out.println(emp);
                }

            } catch (Exception e) {
                   System.out.println("Data Loaded.");


             }else if (args[0].equals("s")) {
                       System.out.println("Loading data ...");


             }try {
                   BufferedReader reader = new BufferedReader(new InputStreamReader( new FileInputStream("employees.txt")));


                   String list = reader.readLine();
                   System.out.println(list);
                   String e[] = list.split(",");
                   Random rand = new Random();


                  int index = rand.nextInt(e.length);
                  System.out.println(e[index]);

             } catch (Exception e) {}
                 System.out.println("Data Loaded.");
 
             } else if (args[0].contains("+")) {
                System.out.println("Loading data ...");

              try {
   
                   BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt", true));

                   String employeename = args[0].substring(1);
                   w.write(", " + employeename);
                   w.close();

              } catch (Exception e) {
                   System.out.println("Data Loaded.");

              } else if (args[0].contains("?")) {

                    System.out.println("Loading data ...");
              try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")));


                    String list = reader.readLine();
                    String e[] = list.split(",");
                    boolean found = false;
                    String search = args[0].substring(1);

                   for (int i = 0; i < e.length && !found; i++) {

                      if (e[i].equals(s)) {
                            System.out.println("Employee found!");
                            found = true;
                       }
                    }
                  
                    catch (Exception e) {
                            System.out.println("Data Loaded.");

                    } else if (args[0].contains("c")) {
                            System.out.println("Loading data ...");

                 try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")));

                            String list = reader.readLine();
                            char[] chars = list.toCharArray();
                            boolean inWord = false;
                            int count = 0;

                            for (char ch : chars) {
                                if (ch == ' ') {
                                    if (!inWord) {
                                        count++;
                                        inWord = true;
                                     } else {
                                         inWord = false;
                                 }
                             }
                   }

                           System.out.println(count + " word(s) found " + chars.length);

                   catch (Exception e) {
                           System.out.println("Data Loaded.");

                   } else if (args[0].contains("u")) {
                           System.out.println("Loading data ...");

                   } try {
                           BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")));

                           String list = reader.readLine();
                           String e[] = list.split(",");
                           String employeename = args[0].substring(1);

                           for (int i = 0; i < e.length; i++) {
                                if (e[i].equals(n)) {
                                      e[i] = "Updated";
                                  }
                            }

                            BufferedWriter w = new BufferedWriter(new FileWriter("employees.txt"));

                            w.write(String.join(",", e));
                            w.close();

                   } catch (Exception e) {

                         System.out.println("Data Updated.");

                   } else if (args[0].contains("d")) {

                         System.out.println("Loading data ...");

                   try {
                          BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")));

                          String list = reader.readLine();
                          String e[] = list.split(",");
                          String employeename = args[0].substring(1);
                          List<String> list = new ArrayList<>(Arrays.asList(e));
                          list.remove(employeename);

                          BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"));

                          w.write(String.join(",", list));
                          w.close();
                   } catch (Exception e) {
                              System.out.println("Data Deleted.");
                   }
     }
}
