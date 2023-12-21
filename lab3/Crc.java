//Write a program for error detecting code using CRC-CCITT(16-bits)


import java.util.Scanner;

public class CRCCode {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        System.out.println("Enter the data and key values");
        String data = in.next();
        String key = in.next();
        System.out.println("At Sender side");
        EncodeData(data, key);
        System.out.println("At Receiver side");
        System.out.println("Enter the code word received");
        String rec=in.next();
         int i= data.length() + key.length() - 1;
        if(rec.length()> i ||rec.length()< i) {
            System.out.println("Enter a valid received codeword");
        }else Receiver(rec,key);
    }
    private static void EncodeData(String data, String key) {
        int len = key.length();
        String str=new String(new char[len - 1]);
        String append = (data + str.replace("\0", "0"));
        String remainder = Mod2Division(append, key);
        String codeword = data + remainder;
        System.out.println("Remainder : " + remainder);
        System.out.println("CodeWord encoded is : " + codeword);
    }

    private static void Receiver(String data, String key) {
        String curXor = Mod2Division(data.substring(0, key.length()), key);
        int curr = key.length();
        while (curr != data.length()) {
            if (curXor.length() != key.length())
                curXor += data.charAt(curr++);
            else curXor = Mod2Division(curXor, key);
        }
        if (curXor.length() == key.length())
            curXor = Mod2Division(curXor, key);
        if (curXor.contains("1"))
            System.out.println("Error has occurred. Incorrect codeword received");
        else System.out.println("No error. Correct message received");
    }

    private static String Mod2Division(String dividend, String divisor) {
        int pick = divisor.length();
        String temp = dividend.substring(0, pick);
        int n = dividend.length();
        while (pick < n) {
            if (temp.charAt(0) == '1')
                temp = Xor(divisor, temp) + dividend.charAt(pick);
            else {
                String str=new String(new char[pick]);
                temp = Xor(str.replace("\0", "0"), temp) + dividend.charAt(pick);
            }
            pick += 1;
        }
        if (temp.charAt(0) == '1')
            temp = Xor(divisor, temp);
        else {
            String str=new String(new char[pick]);
            temp = Xor(str.replace("\0", "0"), temp);
        }
        return temp;
    }

    private static String Xor(String divisor, String temp) {
        StringBuilder result = new StringBuilder();
        int n = temp.length();
        for (int i = 1; i < n; i++) {
            if (divisor.charAt(i) == temp.charAt(i))
                result.append("0");
            else result.append("1");
        }
        return result.toString();
    }
}
