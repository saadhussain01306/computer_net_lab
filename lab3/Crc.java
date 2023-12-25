//Write a program for error detecting code using CRC-CCITT(16-bits)

import java.util.Scanner;

public class CRC1 {

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        System.out.println("At Sender Side: ");
        // Input Data Stream
        System.out.print("Enter message bits: ");
        String message = sc.nextLine();
        System.out.print("Enter generator: ");
        String generator = sc.nextLine();

        int data[] = new int[message.length() + generator.length() - 1];
        int divisor[] = new int[generator.length()];

        for (int i = 0; i < message.length(); i++)
            data[i] = Integer.parseInt(message.charAt(i) + "");

        for (int i = 0; i < generator.length(); i++)
            divisor[i] = Integer.parseInt(generator.charAt(i) + "");

        // Calculation of CRC
        for (int i = 0; i < message.length(); i++) {
            if (data[i] == 1)
                for (int j = 0; j < divisor.length; j++)
                    data[i + j] ^= divisor[j];
        }

        // Display CRC
        System.out.print("The checksum code is: ");
        for (int i = 0; i < message.length(); i++)
            System.out.print(data[i]);
        System.out.println();

        System.out.println("At Receiver Side: ");
        // Check for input CRC code
        System.out.print("Enter checksum code: ");
        message = sc.nextLine();
        System.out.print("Enter generator: ");
        generator = sc.nextLine();

        data = new int[message.length() + generator.length() - 1];
        divisor = new int[generator.length()];

        for (int i = 0; i < message.length(); i++)
            data[i] = Integer.parseInt(message.charAt(i) + "");

        for (int i = 0; i < generator.length(); i++)
            divisor[i] = Integer.parseInt(generator.charAt(i) + "");

        // Calculation of remainder
        for (int i = 0; i < message.length(); i++) {
            if (data[i] == 1)
                for (int j = 0; j < divisor.length; j++)
                    data[i + j] ^= divisor[j];
        }

        // Display validity of data
        boolean valid = true;
        for (int i = 0; i < data.length; i++)
            if (data[i] == 1) {
                valid = false;
                break;
            }

        if (valid)
            System.out.println("Data stream is valid");
        else
            System.out.println("Data stream is invalid. CRC error occurred.");
    }
}


/* the generator bit shoulb be same for both sender and receiver side to check the correctness of the code*/
/*
output:-
At Sender Side: 
Enter message bits: 11011101
Enter generator: 01101
The checksum code is: 10101001
At Receiver Side: 
Enter checksum code: 10101110
Enter generator: 01101
Data stream is invalid. CRC error occurred.
*/
