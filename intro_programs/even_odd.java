//to check whether the given number is even or odd

import java.util.*;
import java.util.Scanner;

public class Main{
    public static void main(String x[]){
        int n;
        Scanner in = new Scanner(System.in);
        System.out.println("enter the number");
        n=in.nextInt();
        
        if(is_even(n)){
            System.out.println(n +" is even");
        }
        else{
             System.out.println(n + " is odd");
        }
    }


 public static boolean is_even(int n)
    {
    
      if(n%2==0){
          return true;
      } 
      else{
          return false;
      }
    }
}
