package com.sxt;

import java.awt.*;

public class Bg {

    static int level = 1;
    int goal = level * 15;
    static int count = 0;
    static int waterNum = 3;
    static boolean waterFlag = false; //use or not use water
    long startTime;
    long endTime;
    int price = (int) (Math.random()*10);
    boolean shop = false;
    Image bg = Toolkit.getDefaultToolkit().getImage("imgs/bg.jpg");
    Image bg1 = Toolkit.getDefaultToolkit().getImage("imgs/bg1.jpg");
    Image peo = Toolkit.getDefaultToolkit().getImage("imgs/cat.png");
    Image water = Toolkit.getDefaultToolkit().getImage("imgs/water.png");



    void paintSelf(Graphics g) {
        g.drawImage(bg1, 0, 0, null);
        g.drawImage(bg, 0, 200, null);
        switch (GameWin.state) {
            case 0:
                drawWord(g, 80, Color.green, "Start Game", 170, 400);
                break;
            case 1:
                g.drawImage(peo, 310, 50, null);
                drawWord(g, 30, Color.BLACK, "Score:" + count, 30, 150);
                //water package
                g.drawImage(water, 450, 40, null);
                drawWord(g, 30, Color.BLACK, "*" + waterNum, 510, 70);
                //for next level
                drawWord(g, 20, Color.BLACK, "" + level + " Level", 30, 60);
                //goal score
                drawWord(g, 30, Color.BLACK, "Goal:" + goal, 30, 110);
                //time setting
                endTime = System.currentTimeMillis();
                long tim = 20-(endTime-startTime)/1000;
                drawWord(g, 30, Color.BLACK, "Time" + (tim>0?tim:0), 520, 150);
                break;
            case 2:
                g.drawImage(water, 300, 400, null);
                drawWord(g, 30, Color.BLACK, "Price:" +price, 300, 500);
                drawWord(g, 30, Color.BLACK, "BUY?", 300, 550);
                if (shop) {
                    count = count - price;
                    waterNum++;
                    shop =false;
                    GameWin.state = 1;
                    startTime = System.currentTimeMillis();
                }
                break;
            case 3:
                drawWord(g, 80, Color.cyan,  "GameOver", 250, 350);
                drawWord(g, 80, Color.cyan,  "Score:" + count, 200, 450);
                break;
            case 4:
                drawWord(g, 80, Color.red,  "You Win", 250, 350);
                drawWord(g, 80, Color.red,  "Score:" + count, 200, 450);
                break;
            default:
        }

    }

    //t finish time f continuing time
    boolean gameTime() {
        long tim = (endTime - startTime)/1000;
        if (tim > 20) {
            return  true;
        }
        return false;
    }


    //regame all
    void reGame() {
        level = 1;
        goal = level * 15;
        count = 0;
        waterNum = 3;
        waterFlag = false;

    }

    public static void drawWord(Graphics g, int size, Color color, String str, int x, int y) {
        g.setColor(color);
        g.setFont(new Font("仿宋", Font.BOLD, size));
        g.drawString(str, x, y);

    }

}
