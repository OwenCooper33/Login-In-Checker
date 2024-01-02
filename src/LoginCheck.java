import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.HashMap;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Collections;

public class LoginCheck {

    public static void main(String[] files) throws FileNotFoundException  {

        Scanner file = new Scanner(new File(files[0]));
        HashMap<String, Integer> IPs = new HashMap<>();
        ArrayList<String> IPList = new ArrayList<>();
/*reads the .txt file and adds the IP addresses of the invalid
attempts to an ArrayList*/
        int count = 1;
        while (file.hasNextLine()) {

            String line = file.nextLine();
            String[] lineArray = line.split(" ");

            if (lineArray[5].equals("Invalid")) {
                IPList.add(lineArray[9]);
            }
        }
        /*sorts the arraylist of IP adresses */
        Collections.sort(IPList);
/*adds the IP address with the number of attempts
to a hashmap*/
        for (int i = 0; i < IPList.size()-1; i++) {
            if (IPList.get(i).equals(IPList.get(i+1))) {
                count++;
            } else {
                IPs.put(IPList.get(i), count);
                count = 1;
            }
        }
        /* checks to see it IPList is empty*/
        if (!IPList.isEmpty()) {
            IPs.put(IPList.get(IPList.size() - 1), count);
        }
/* creates a new files with the title of given in the commandline,
 checks for errors, and prints the IP addresses with more than 3
 attempts to the output file*/
        File myObj = new File(files[1]);
        try {
            FileWriter myWriter = new FileWriter(files[1]);

            for (String key : IPs.keySet()) {
                if(IPs.get(key) >= 3) {
                    myWriter.write(key + "\n");
                }
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}