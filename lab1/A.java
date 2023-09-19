/*
A. Use appropriate tool to find Host name, IP address, physical
address, subnet mask, default gateway etc [hint: Ipconfig]. Explore
different options available with the tool.
*/

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class lab1{
    public static void main(String[] args) {
        try {
             
            InetAddress localHost = InetAddress.getLocalHost();
            //use getHostName()
            String hostName = localHost.getHostName();
            System.out.println("Hostname: " + hostName);
            
            //use getHostAddress()
            String ipAdd = localHost.getHostAddress();
            System.out.println("IP Address: " + ipAdd);
            
            //to find the physical address
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                byte[] macAddress = networkInterface.getHardwareAddress();
                if (macAddress != null) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < macAddress.length; i++) {
                        sb.append(String.format("%02X%s", macAddress[i], (i < macAddress.length - 1) ? ":" : ""));
                    }
                    System.out.println("Physical Address (MAC): " + sb.toString());
                }
            }
            
            //to find the subnet mask
           
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        NetworkInterface subInterface = NetworkInterface.getByInetAddress(inetAddress);
                        short subnetBits = subInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength();
                        int subnetMask = 0xFFFFFFFF << (32 - subnetBits);

                        String subnetMaskStr = 
                            ((subnetMask >> 24) & 0xFF) + "." +
                            ((subnetMask >> 16) & 0xFF) + "." +
                            ((subnetMask >> 8) & 0xFF) + "." +
                            (subnetMask & 0xFF);
                         System.out.println("Subnet Mask: " + subnetMaskStr);
                        
                    }
                }
            }
            
            //To find the default gateway
            String os = System.getProperty("os.name").toLowerCase();

            Process process;
            if (os.contains("win")) {
                // For Windows
                process = new ProcessBuilder("cmd.exe", "/c", "ipconfig", "|", "findstr", "Default Gateway").start();
            } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                // For Linux, Unix, and macOS
                process = new ProcessBuilder("/bin/sh", "-c", "ip route | awk '/default/ { print $3 }'").start();
            } else {
                throw new UnsupportedOperationException("Unsupported operating system: " + os);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Default Gateway: " + line);
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Failed to retrieve the default gateway.");
            }
              
        } catch (Exception e) {
            System.err.println(e);
           
        }
    }
}
