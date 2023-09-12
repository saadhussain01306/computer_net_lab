//to reverse the given number

import java.util.*;
import java.util.Scanner;

public class Main{
    public static void main(String x[]){
        int n;
        Scanner in = new Scanner(System.in);
        System.out.println("enter the number to be reversed");
        n=in.nextInt();
        int reverse_num=reverse(n);
        System.out.println("The reversed number is "+ reverse_num);
        
        
    }
  
 public static int reverse(int n)
    {
       int rev_num=0;
       while(n!=0){
           int p=n%10;
           rev_num=rev_num*10+p;
           n=n/10;
       }
       return rev_num;
    }
}
