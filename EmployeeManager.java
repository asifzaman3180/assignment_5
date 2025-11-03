//File Name EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

  
   
    public static String readFromFile(String filepath){
        try(BufferedReader read = new BufferedReader( new FileReader(filepath))){
	    return read.readLine();
	}	
	catch(Exception e){
            System.out.println("error in file reading");
	    System.exit(0);
	    return ""; 
	}	
    }

    public static void writeToFile(String filepath,String data ,boolean append){
        try(BufferedWriter write = new BufferedWriter( new FileWriter(filepath,append))){
		write.write(data);
	}	
	catch(Exception e){
            System.out.println("error in file writing");
	    System.exit(0);
	 
	}	
    }
      public static void main(String[] args) {
       
        if(args.length<1){
		
           System.out.println(" we need comand line argument ");
	   System.exit(0);
	}
         
	    // Check arguments
        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            try {
                //BufferedReader read = new BufferedReader(
                       // new InputStreamReader(
                           //     new FileInputStream("employees.txt")));
			   

                //String line = read.readLine();
		String line =  readFromFile("employees.txt");
                String employees[] = line.split(",");
                for (String emp : employees) {
                    System.out.println(emp);
                }
            }

	    catch (Exception e) {}
            System.out.println("Data Loaded.");
        } 


	else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try {
                //BufferedReader read = new BufferedReader(
                  //      new InputStreamReader(
                    //            new FileInputStream("employees.txt")));

		String line =  readFromFile("employees.txt");
                //String line = read.readLine();
                System.out.println(line);
                String employees[] = line.split(",");
                Random rand = new Random();
                int index = rand.nextInt(employees.length);
                System.out.println(employees[index]);
            }

	    catch (Exception e) {}
            System.out.println("Data Loaded.");
        } 


	else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                //BufferedWriter write = new BufferedWriter(
                  //      new FileWriter("employees.txt", true));
                String next = args[0].substring(1);
                //write.write(", " + next);
		writeToFile("employees.txt",", "+next,true);
               // write.close();
            } 

	    catch (Exception e) {}
            System.out.println("Data Loaded.");
        } 

	else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                //BufferedReader read = new BufferedReader(
                   //     new InputStreamReader(
                     //           new FileInputStream("employees.txt")));
                //String line = read.readLine();

		String line =  readFromFile("employees.txt");
		
                String employees[] = line.split(",");
                boolean found = false;
                String str = args[0].substring(1);
                for (int i = 0; i < employees.length && !found; i++) {
                    if (employees[i].equals(str)) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }
            }

	    catch (Exception e) {}
            System.out.println("Data Loaded.");
        } 

	else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
               // BufferedReader read = new BufferedReader(
                 //       new InputStreamReader(
                   //             new FileInputStream("employees.txt")));
                //String line = read.readLine();
                 String line = readFromFile("employees.txt");
                char[] chars = line.toCharArray();
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
            }

	    catch (Exception e) {}
            System.out.println("Data Loaded.");
        }

	else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader read = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String line = read.readLine();
                String employees[] = line.split(",");
                String next = args[0].substring(1);
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(next)) {
                        employees[i] = "Updated";
                    }
                }
                BufferedWriter write = new BufferedWriter(
                        new FileWriter("employees.txt"));
                write.write(String.join(",", employees));
                write.close();
            } 

	    catch (Exception e) {}
            System.out.println("Data Updated.");
        } 

	else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader read = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")));
                String line = read.readLine();
                String employees[] = line.split(",");
                String next = args[0].substring(1);
                List<String> list = new ArrayList<>(Arrays.asList(employees));
                list.remove(next);
                BufferedWriter write = new BufferedWriter(
                        new FileWriter("employees.txt"));
                write.write(String.join(",", list));
                write.close();
            }

	    catch (Exception e) {}
            System.out.println("Data Deleted.");
        }
    }
}
