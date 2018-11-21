/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitennis.Objects;

import digitennis.DIGITennis;
import digitennis.Utils.Constants;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Mukil
 */
public class ball {

    private static final int radius = 10;
    private int x, y;
    private int xVel = 2, yVel = 1;
    private final Color color = Color.RED;
    private boolean MOVERIGHT = true;
    private boolean MOVEDOWN = true;

    public ball(int ix, int iy) {
        this.x = ix;
        this.y = iy;
//        MOVEDOWN = yVel >= 0;
//        MOVERIGHT = xVel >= 0;
    }

    public ball(int ix, int iy, int xVel, int yVel) {
        this.x = ix;
        this.y = iy;
        this.xVel = xVel;
        this.yVel = yVel;

        MOVEDOWN = yVel > 0;
        MOVERIGHT = xVel > 0;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval(x, y, radius, radius);
    }

    public void move() throws Exception {
        if (x + radius >= Constants.WINDOW_WIDTH) {
            DIGITennis.playerWin("PLAYER_A");
        }
        if (x <= 0) {
            DIGITennis.playerWin("PLAYER_B");
        }

        if (MOVERIGHT) {
            if ((x + radius >= DIGITennis.bat_b.getX() && y >= DIGITennis.bat_b.getY() && y <= (DIGITennis.bat_b.getY() + DIGITennis.bat_b.getHeight()))) {
                MOVERIGHT = false;
                xVel *= -1;
            }
        } else {
            if ((x <= DIGITennis.bat_a.getX() + DIGITennis.bat_a.getWidth() && y >= DIGITennis.bat_a.getY() && y <= (DIGITennis.bat_a.getY() + DIGITennis.bat_a.getHeight()))) {
                MOVERIGHT = true;
                xVel *= -1;
            }
        }
        if (MOVEDOWN) {
            if (y + radius >= Constants.WINDOW_HEIGHT) {
                MOVEDOWN = false;
                yVel *= -1;
            }
        } else {
            if (y <= Constants.TITLEBARHEIGHT_HEIGHT) {
                MOVEDOWN = true;
                yVel *= -1;
            }
        }
        x = x + xVel;
        y = y + yVel;
    }

    public int getxVel() {
        return xVel;
    }

    public void setxVel(int xVel) {
        this.xVel = xVel;
    }

    public int getyVel() {
        return yVel;
    }

    public void setyVel(int yVel) {
        this.yVel = yVel;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isMOVERIGHT() {
        return MOVERIGHT;
    }

    public void setMOVERIGHT(boolean MOVERIGHT) {
        this.MOVERIGHT = MOVERIGHT;
    }

    public boolean isMOVEDOWN() {
        return MOVEDOWN;
    }

    public void setMOVEDOWN(boolean MOVEDOWN) {
        this.MOVEDOWN = MOVEDOWN;
    }

}
