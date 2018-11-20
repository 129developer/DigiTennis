/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitennis;

import digitennis.Objects.Screen;
import digitennis.Objects.ball;
import digitennis.Objects.bat;
import digitennis.Objects.powerUp;
import digitennis.Utils.Constants;
import digitennis.Utils.GenUtils;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author bayasys
 */
public class DIGITennis {

    public static ball myball;
    private static Screen sc;
    private static ArrayList<powerUp> powerups = new ArrayList<powerUp>();
    public static bat bat_a;
    public static bat bat_b;
    private static Graphics2D g2d, g2dBuffer;
    private static DIGITennis dt;
    public static boolean RUNNING = true;
    static BufferedImage bufferedImage;
    public static int playerA_Score = 0;
    public static int playerB_Score = 0;
    public static Thread runner;

    public static void main(String[] args) {
        dt = new DIGITennis();
        sc = new Screen();
        g2d = (Graphics2D) sc.getGraphics();
        runner = new Thread(new Runnable() {
            @Override
            public void run() {
                drawDashBoard(g2d);
                while (RUNNING) {
                    try {
                        bufferedImage = new BufferedImage(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, BufferedImage.TYPE_INT_ARGB);
                        g2dBuffer = bufferedImage.createGraphics();
                        g2dBuffer.setColor(Constants.BG_COLOR);
                        g2dBuffer.fillRect(0, Constants.TITLEBARHEIGHT_HEIGHT, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
                        g2dBuffer.setColor(Color.BLACK);
                        g2dBuffer.fillRect(Constants.WINDOW_WIDTH / 2, Constants.TITLEBARHEIGHT_HEIGHT, 5, Constants.WINDOW_HEIGHT);

                        for (Iterator<powerUp> iterator = powerups.iterator(); iterator.hasNext();) {
                            powerUp p = iterator.next();
                            p.move();
                            p.draw(g2dBuffer);
                        }

                        bat_a.move();
                        bat_b.move();
                        myball.move();

                        myball.draw(g2dBuffer);
                        bat_a.draw(g2dBuffer);
                        bat_b.draw(g2dBuffer);

                        g2d.drawImage(bufferedImage, null, 0, 0);
                        Thread.sleep(15);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (RUNNING) {
                    addNewPowerUps();
                }
            }
        }, 5 * 1000, 10 * 1000);
        startGame();
    }

    public static void startGame() {
        powerups = new ArrayList<powerUp>();
        addNewBall(100, Constants.TITLEBARHEIGHT_HEIGHT + 100, true);
        bat_a = new bat(100, (Constants.WINDOW_HEIGHT / 2), "BatA");
        bat_b = new bat(Constants.WINDOW_WIDTH - 100, (Constants.WINDOW_HEIGHT / 2), "BatB");
        if (!runner.isAlive()) {
            runner.start();
        }
    }

    public static void playerWin(String player) throws Exception {
        boolean gameover = false;
        if (player.equalsIgnoreCase("PLAYER_A")) {
            playerA_Score++;
            if (playerA_Score >= Constants.WIN_SCORE) {
                drawGameOver();
                gameover = true;
            }
            drawDashBoard(g2d, "Player A won.");
        } else {
            playerB_Score++;
            if (playerB_Score >= Constants.WIN_SCORE) {
                drawGameOver();
                gameover = true;
            }
            drawDashBoard(g2d, "Player B won.");
        }
//        drawDashBoard(g2d);
        Thread.sleep(5000);
        if (!gameover) {
            startGame();
        }
    }

    public static void drawDashBoard(Graphics2D g2dBuffer) {
        g2dBuffer.setColor(Color.GRAY);
        g2dBuffer.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.TITLEBARHEIGHT_HEIGHT);
        g2d.setColor(Color.ORANGE);
        g2d.setFont(Constants.DEFAULT_FONT);
        g2d.drawString("Player 1 : " + playerA_Score, 100, 100);
        g2d.setColor(Color.BLUE);
        g2d.drawString("Player 2 : " + playerB_Score, Constants.WINDOW_WIDTH - 150, 100);

    }

    public static void drawDashBoard(Graphics2D g2dBuffer, String message) {
        g2dBuffer.setColor(Color.GRAY);
        g2dBuffer.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.TITLEBARHEIGHT_HEIGHT);
        g2d.setColor(Color.ORANGE);
        g2d.setFont(Constants.DEFAULT_FONT);
        g2d.drawString("Player A : " + playerA_Score, 100, 100);
        g2d.setColor(Color.BLUE);
        g2d.drawString("Player B : " + playerB_Score, Constants.WINDOW_WIDTH - 150, 100);
        g2d.setColor(Color.BLUE);
        g2d.drawString(message, Constants.WINDOW_WIDTH / 2 - (message.length() * 10), 100);
    }

    public static void drawGameOver() {
        DIGITennis.setRUNNING(false);
        g2d.setColor(Color.GRAY);
        g2d.setFont(Constants.DEFAULT_FONT);
        g2d.drawString("GAME OVER", Constants.WINDOW_WIDTH / 2 - 80, Constants.WINDOW_HEIGHT / 2);
    }

    public static boolean isRUNNING() {
        return RUNNING;
    }

    public static void setRUNNING(boolean RUNNING) {
        DIGITennis.RUNNING = RUNNING;
    }

    public static void addNewPowerUps() {
        powerUp pu = new powerUp(GenUtils.getRandomNumberInRange(0, 3));
        powerups.add(pu);
    }

    public static void removePowerUp(powerUp p) {
        System.out.println("REMOVE PW");
        powerups.remove(p);
    }

    private static void addNewBall(int x, int y, boolean rand) {
        if (rand) {
            int xVel = GenUtils.getRandomNumberInRange(5, 6), yVel = GenUtils.getRandomNumberInRange(2, 5);
            ball bl = new ball(x, y, xVel, yVel);
            myball = bl;
        } else {
            ball bl = new ball(x, y);
            myball = bl;
        }
    }

}
