//To find the ASCII value of a given character

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the character:");
        input = in.nextLine();
        
        if (input.length() == 1) {
            char character = input.charAt(0);
            int asciiValue = (int) character;
            System.out.println("The ASCII value of the given character is " + asciiValue);
        }
        
    }
}
