import java.util.*;
import java.io.*;

public class main{
    public static void main(String args[]) throws IOException {
        int n;
        Scanner sc = new Scanner(System.in);
        System.out.println("ENTER THE NO OF PASSENGERS");
        n = sc.nextInt();
        PassengerBooking p[] = new PassengerBooking[n];
        for(int i=0;i<n;i++){
            System.out.println("ENTER DATA FOR PASSENGER- "+i+1);
            p[i] = new PassengerBooking();
        }
        fileInput(p);

    }
    public static void fileInput(PassengerBooking p[]) throws  IOException{
        FileWriter fw = new FileWriter("output.txt");
        String input = " ";
        for(int i=0;i<p.length;i++){
            input += p[i].toString();
            input += "\n";
        }
        fw.write(input);
        fw.close();
    }
}