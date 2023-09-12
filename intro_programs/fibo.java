import java.util.*;
import java.util.Scanner;

public class Main{
    public static void main(String x[]){
        Scanner in = new Scanner(System.in);
        int n1=0,n2=1,n,n3,i;
        System.out.println("Enter the number upto which fibonnacci number is to be printed");
        n=in.nextInt();
        System.out.print("The fibonnacci number are :\n"+n1+" "+n2+" ");
        for(i=2;i<n;i++){
            n3=n1+n2;
            System.out.print(n3+" ");
            n1=n2;
            n2=n3;
        }
        
    }
    
}
