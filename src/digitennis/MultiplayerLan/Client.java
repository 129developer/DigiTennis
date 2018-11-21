/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitennis.MultiplayerLan;

import java.io.*;
import java.net.*;
import org.apache.commons.io.IOUtils;
import org.codehaus.jettison.json.JSONObject;

public class Client {

    static DataOutputStream dout;
    static Socket s;
    static byte[] bytes;

    public static void main(String[] args) {
        startClient("192.168.10.23");
    }

    public static void startClient(String ip) {
        try {
            s = new Socket(ip, 5555);
            s.setKeepAlive(true);
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            int i = 0;
            String a = "";
            String obj;
            while (true) {
                obj = (String) in.readObject();
                digitennis.DIGITennis.setReceivedData(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
        }
    }
}
