//to check whether the given number is prime or not

import java.util.*;
import java.util.Scanner;

public class Main{
    public static void main(String x[]){
        int n;
        Scanner in = new Scanner(System.in);
        System.out.println("enter the number");
        n=in.nextInt();
        if(is_prime(n)){
            System.out.println(n +" is a prime number");
        }
        else{
             System.out.println(n + " is not a prime number");
        }
    }


 public static boolean is_prime(int n)
    {
        if(n<=1)
        {
            return false;
        }
       for(int i=2;i<=n/2;i++)
       {
           if((n%i)==0)
               return  false;
       }
       return true;
    }
}
