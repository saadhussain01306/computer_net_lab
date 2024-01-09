/*Client program*/

import java.net.*;
import java.io.*;

public class TCPC {
    public static void main(String[] args) throws Exception {
        Socket sock = new Socket("127.0.0.1", 4000);
        System.out.println("Enter the filename");
        BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
        String fname = keyRead.readLine();

        OutputStream ostream = sock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);
        pwrite.println(fname);

        InputStream istream = sock.getInputStream();
        BufferedReader socketRead = new BufferedReader(new InputStreamReader(istream));

        String str;
        while ((str = socketRead.readLine()) != null) {
            System.out.println(str);
        }

        pwrite.close();
        socketRead.close();
        keyRead.close();
    }
}

       
/*First run the server program then in new terminal window run the client program and input the 
file name the server will return the file content if it exists

output:-
SERVER PROGRAM

javac TCPS.java
java TCPS
Server ready for connection
Connection Is successful and waiting for the client request

CLIENT program
in new terminal window
javac TCPC.java
 java TCPC
Enter the filename
text.txt
This the file client requested from the server....
*/
  
