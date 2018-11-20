/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitennis.Objects;

import digitennis.DIGITennis;
import digitennis.Utils.Constants;
import static digitennis.Utils.Constants.GRAVITY;
import digitennis.Utils.GenUtils;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author bayasys
 */
public class powerUp {

    private final int ADD_BALL_SPEED = 0,
            ADD_BAT_SPEED = 1,
            ADD_BAT_LENGTH = 2,
            REDUCE_BALL_SPEED = 3;
    private final String[] actionName = {"BALL SPEED +", "BAT SPEED +", "BAT LENGTH +", "BALL SPEED -"};
    private int action;
    private String name;
    private int x, y, xVel, yVel, width = 50, height = 20;
    private Color color = Color.CYAN;
    private bat myBat;
    private boolean visible;

    public powerUp(int action) {
        this.action = action;
        this.y = Constants.TITLEBARHEIGHT_HEIGHT + 10;
        this.visible = true;
        this.xVel = GRAVITY;
        this.name = actionName[action];
        width = name.length() * 10;
        this.x = GenUtils.getRandomNumberInRange(width, Constants.WINDOW_WIDTH);

    }

    private void doAction() {
        switch (action) {
            case ADD_BALL_SPEED:
                DIGITennis.myball.setxVel(DIGITennis.myball.getxVel() * 2);
                DIGITennis.myball.setyVel(DIGITennis.myball.getyVel() * 2);
                break;
            case ADD_BAT_SPEED:
                myBat.setPOWER_SPEED(myBat.getPOWER_SPEED() + Constants.BAT_POWER_SPEED_VAL);
                break;
            case ADD_BAT_LENGTH:
                myBat.setHeight(myBat.getHeight() + 20);
                break;
            case REDUCE_BALL_SPEED:
                DIGITennis.myball.setxVel(DIGITennis.myball.getxVel() / 2);
                DIGITennis.myball.setyVel(DIGITennis.myball.getyVel() / 2);
                break;

            default:
        }
        setVisible(false);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
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

    public void draw(Graphics2D g2d) {
        if (visible) {
            g2d.setColor(color);
            g2d.fill3DRect(x, y, width, height, true);
            g2d.setColor(Color.BLACK);
            g2d.drawString(name, x + (width / name.length()), y + 12);
        }
    }

    public boolean overlaps(bat r) {
        return x < r.getX() + r.getWidth() && x + width > r.getX() && y < r.getY() + r.getHeight() && y + height > r.getY();
    }

    public void move() throws Exception {
        if (visible) {
            this.y += 1;
            if (overlaps(DIGITennis.bat_a)) {
                myBat = DIGITennis.bat_a;
                doAction();
            }
            if (overlaps(DIGITennis.bat_b)) {
                myBat = DIGITennis.bat_b;
                doAction();
            }
            if (y > Constants.WINDOW_HEIGHT) {
                setVisible(false);
            }
        }
    }

}
