/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitennis.Objects;

import digitennis.Utils.Constants;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author bayasys
 */
public class bat {

    private int x, y, xVel = 0, yVel = 0;
    private int width, height, POWER_SPEED = 0;
    private Color color;
    private String name;

    public bat(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.height = Constants.BAT_HEIGHT;
        this.width = Constants.BAT_WIDTH;
        this.name = name;
        if (name.equalsIgnoreCase("BatA")) {
            setColor(Constants.BAT_A_COLOR);
        } else {
            setColor(Constants.BAT_B_COLOR);
        }
    }

    public void move() {
        this.y += yVel;
        if (name.equalsIgnoreCase("BatA") && this.x + width + xVel + 5 < Constants.WINDOW_WIDTH / 2) {
            this.x += xVel;
        } else if (this.x + xVel - 5 > Constants.WINDOW_WIDTH / 2) {
            this.x += xVel;
        }
    }

    private void setColor(Color color) {
        this.color = color;
    }

    public void draw(Graphics2D g2d) throws Exception {
        g2d.setColor(color);
        g2d.fillRect(x, y, width, height);
    }

    public void action(int action) {
        final int MOVE_DOWN = 1,
                MOVE_UP = 2,
                STOP_ACTION = 3,
                MOVE_LEFT = 4,
                MOVE_RIGHT = 5;
        switch (action) {
            case MOVE_DOWN:
                yVel = Constants.BAT_MOVESPEED + POWER_SPEED;
                break;
            case MOVE_UP:
                yVel = -Constants.BAT_MOVESPEED + POWER_SPEED;
                break;
            case STOP_ACTION:
                yVel = 0;
                xVel = 0;
                break;
            case MOVE_LEFT:
                xVel = -Constants.BAT_MOVESPEED + POWER_SPEED;
                break;
            case MOVE_RIGHT:
                xVel = Constants.BAT_MOVESPEED + POWER_SPEED;
                break;
            default:

        }
    }

    public int getPOWER_SPEED() {
        return POWER_SPEED;
    }

    public void setPOWER_SPEED(int POWER_SPEED) {
        this.POWER_SPEED = POWER_SPEED;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
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
}
