/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitennis.Objects;

import digitennis.DIGITennis;
import digitennis.Utils.Constants;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author bayasys
 */
public class Screen extends JFrame {

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_NUMPAD8 || key == KeyEvent.VK_NUMPAD2 || key == KeyEvent.VK_NUMPAD4 || key == KeyEvent.VK_NUMPAD6) {
                DIGITennis.bat_b.action(3);
            }

            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
                DIGITennis.bat_a.action(3);
            }

        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_UP) {
                DIGITennis.bat_a.action(2);
            }

            if (key == KeyEvent.VK_DOWN) {
                DIGITennis.bat_a.action(1);
            }
            if (key == KeyEvent.VK_LEFT) {
                DIGITennis.bat_a.action(4);
            }

            if (key == KeyEvent.VK_RIGHT) {
                DIGITennis.bat_a.action(5);
            }

            if (key == KeyEvent.VK_NUMPAD8) {
                DIGITennis.bat_b.action(2);
            }

            if (key == KeyEvent.VK_NUMPAD2) {
                DIGITennis.bat_b.action(1);
            }
            if (key == KeyEvent.VK_NUMPAD6) {
                DIGITennis.bat_b.action(5);
            }

            if (key == KeyEvent.VK_NUMPAD4) {
                DIGITennis.bat_b.action(4);
            }
        }
    }

    public Screen() throws HeadlessException {
        this(Constants.Title);
        addKeyListener(new TAdapter());
    }

    public Screen(String title) throws HeadlessException {
        super(title);
        this.setBounds(new Rectangle(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        setVisible(true);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                DIGITennis.setRUNNING(false);
                System.exit(0);
            }
        });
    }

}
