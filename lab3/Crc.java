// write a java code for 16 bit crc divsion error checking

import java.util.*;
import java.util.Scanner;

public class crc1{
     public static void main(String[] args){
                Scanner in=new Scanner(System.in);
                System..out.println("Enter the data word(in binary)");
               String data=in.next();
               System.out.println("Enter the key word(in binary)");
               String key=in.next();
               System.out.println("data is received at the sender side");
                Encode(data,key);
               System.out.println("At the receiver side");
               System.out.println("Enter the code word received at receiver side");
               String re=in.next();
              int  i=data.length()+key.length()-1;

             if(re.length()>i || re.length()<i){
                     System.out.println("Enter a valid recieved word"); 
                 }
              else{
                  Received(re,key);
               }
      
   }

private static void Encode(String data,String key)
{
      int len=key.length();
     String str= new String(new char[len-1]);
     Sting append=(data+str.replace("\0","0");
     String remainder=Mod2Division(append,key);
     String codeword=data+remainder;
     System.out.println("Remainder:"+remainder);
      System.out.println("CodeWord after encoding :"+codeword);
}

private static void Mod2Division(String dividend,String divisor){

    int pick=divisor.length();
   String temp=dividend.substring(0,pick);
  int n=dividend.length();
    while(pick
