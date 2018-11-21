/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitennis.MultiplayerLan;

/**
 *
 * @author bayasys
 */
import static digitennis.MultiplayerLan.Client.s;
import digitennis.Utils.Constants;
import java.io.*;
import java.net.*;
import org.codehaus.jettison.json.JSONObject;

public class Server {

    static ServerSocket ss;
    static byte[] ary;
    static Socket s;
    static ObjectOutputStream out;
    static ObjectInputStream in;

    public static void startServer() {
        try {
            ss = new ServerSocket(Integer.parseInt(Constants.LAN_PORT));
            s = ss.accept();
            s.setKeepAlive(true);
            out = new ObjectOutputStream(s.getOutputStream());
            in = new ObjectInputStream(s.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void SendData(String obj) {
        try {
            out.writeObject(obj);
            out.flush();
            digitennis.DIGITennis.setReceivedData((String) in.readObject());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
