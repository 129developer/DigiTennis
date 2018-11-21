/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitennis.Utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 *
 * @author bayasys
 */
public class LANUtils {

    public static void main(String[] args) {
        try {
            System.out.println(getLANIP());
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static String getLANPort() {
        return Constants.LAN_PORT;
    }

    public static void setLANPort(String port) {
        Constants.LAN_PORT = port;
    }

    public static String getLANIP() throws SocketException {
        String ip = "";
        for (final Enumeration< NetworkInterface> interfaces
                = NetworkInterface.getNetworkInterfaces();
                interfaces.hasMoreElements();) {
            final NetworkInterface cur = interfaces.nextElement();

            if (cur.isLoopback()) {
                continue;
            }

            System.out.println("interface " + cur.getName());

            for (final InterfaceAddress addr : cur.getInterfaceAddresses()) {
                final InetAddress inet_addr = addr.getAddress();

                if (!(inet_addr instanceof Inet4Address)) {
                    continue;
                }
                ip = inet_addr.getHostAddress();
            }
        }
        return ip;
    }

}
