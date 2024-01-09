/*UDP cleint program*/


import java.io.*;
import java.net.*;

class UDPClient {
    public static void main(String[] args) throws Exception {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        System.out.println("Enter the string in lowercase so that you receive the message in Uppercase from the server");
        String sentence = inFromUser.readLine();
        sendData = sentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String modifiedSentence = new String(receivePacket.getData());
        System.out.println("FROM SERVER: " + modifiedSentence);

        clientSocket.close();
    }
}

     
/*output;-
  first run the server program in one terminal in another terminal run the client program
Server side
javac UDPServer.java
java UDPServer
Server is Ready for the client
RECEIVED: abcdef

Client Side
javac UDPClient.java
java UDPClient
Enter the string in lowercase so that you receive the message in Uppercase from the server
abcdef
FROM SERVER: ABCDEF
*/
