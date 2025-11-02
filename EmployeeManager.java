import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {
        if (args.length == 0) return; // prevent errors

        try {
            if (args[0].equals("l")) {
                System.out.println(Constants.LOADING_DATA);
                BufferedReader r = new BufferedReader(new InputStreamReader(
                        new FileInputStream(Constants.EMPLOYEE_FILE)));
                String l = r.readLine();
                String e[] = l.split(",");
                for (String emp : e) {
                    System.out.println(emp);
                }
                r.close();
                System.out.println(Constants.DATA_LOADED);
            } 
            else if (args[0].equals("s")) {
                System.out.println(Constants.LOADING_DATA);
                BufferedReader r = new BufferedReader(new InputStreamReader(
                        new FileInputStream(Constants.EMPLOYEE_FILE)));
                String l = r.readLine();
                System.out.println(l);
                String e[] = l.split(",");
                Random rand = new Random();
                int idx = rand.nextInt(e.length);
                System.out.println(e[idx]);
                r.close();
                System.out.println(Constants.DATA_LOADED);
            } 
            else if (args[0].contains("+")) {
                System.out.println(Constants.LOADING_DATA);
                BufferedWriter w = new BufferedWriter(
                        new FileWriter(Constants.EMPLOYEE_FILE, true));
                String n = args[0].substring(1);
                w.write(", " + n);
                w.close();
                System.out.println(Constants.DATA_LOADED);
            } 
            else if (args[0].contains("?")) {
                System.out.println(Constants.LOADING_DATA);
                BufferedReader r = new BufferedReader(new InputStreamReader(
                        new FileInputStream(Constants.EMPLOYEE_FILE)));
                String l = r.readLine();
                String e[] = l.split(",");
                boolean found = false;
                String s = args[0].substring(1);
                for (int i = 0; i < e.length && !found; i++) {
                    if (e[i].equals(s)) {
                        System.out.println(Constants.EMPLOYEE_FOUND);
                        found = true;
                    }
                }
                r.close();
                System.out.println(Constants.DATA_LOADED);
            } 
            else if (args[0].contains("c")) {
                System.out.println(Constants.LOADING_DATA);
                BufferedReader r = new BufferedReader(new InputStreamReader(
                        new FileInputStream(Constants.EMPLOYEE_FILE)));
                String l = r.readLine();
                char[] chars = l.toCharArray();
                boolean inWord = false;
                int count = 0;
                for (char c : chars) {
                    if (c == ' ') {
                        if (!inWord) {
                            count++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }
                System.out.println(count + " word(s) found " + chars.length);
                r.close();
                System.out.println(Constants.DATA_LOADED);
            } 
            else if (args[0].contains("u")) {
                System.out.println(Constants.LOADING_DATA);
                BufferedReader r = new BufferedReader(new InputStreamReader(
                        new FileInputStream(Constants.EMPLOYEE_FILE)));
                String l = r.readLine();
                String e[] = l.split(",");
                String n = args[0].substring(1);
                for (int i = 0; i < e.length; i++) {
                    if (e[i].equals(n)) {
                        e[i] = "Updated";
                    }
                }
                BufferedWriter w = new BufferedWriter(
                        new FileWriter(Constants.EMPLOYEE_FILE));
                w.write(String.join(",", e));
                w.close();
                r.close();
                System.out.println(Constants.DATA_UPDATED);
            } 
            else if (args[0].contains("d")) {
                System.out.println(Constants.LOADING_DATA);
                BufferedReader r = new BufferedReader(new InputStreamReader(
                        new FileInputStream(Constants.EMPLOYEE_FILE)));
                String l = r.readLine();
                String e[] = l.split(",");
                String n = args[0].substring(1);
                List<String> list = new ArrayList<>(Arrays.asList(e));
                list.remove(n);
                BufferedWriter w = new BufferedWriter(
                        new FileWriter(Constants.EMPLOYEE_FILE));
                w.write(String.join(",", list));
                w.close();
                r.close();
                System.out.println(Constants.DATA_DELETED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
