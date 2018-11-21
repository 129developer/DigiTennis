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

    public static void main(String[] args) {
        try {
            ss = new ServerSocket(Integer.parseInt(Constants.LAN_PORT));
            Socket s;//establishes connection
            s = ss.accept();
            s.setKeepAlive(true);
            DataInputStream dis = new DataInputStream(s.getInputStream());

//            DataOutputStream outStr = new DataOutputStream(s.getOutputStream());
            int i = 0;
            while (true) {

//                s.getOutputStream().write(digitennis.DIGITennis.getSendData());
                s.getOutputStream().flush();
//            s.getOutputStream().close();
//                outStr.write(ScreenCapturer.captureScreen());
            }
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println(e);
        } finally {
            try {
                ss.close();
            } catch (IOException ex) {
//                Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public static void startServer() {
        try {
            ss = new ServerSocket(Integer.parseInt(Constants.LAN_PORT));
            s = ss.accept();
            s.setKeepAlive(true);
            out = new ObjectOutputStream(s.getOutputStream());
            int i = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            try {
//                ss.close();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//
//        }
    }

    public static void SendData(String obj) {
        try {
            System.out.println("Sending :" + obj);
            out.writeObject(obj);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
