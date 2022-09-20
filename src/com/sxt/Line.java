package com.sxt;

import java.awt.*;

public class Line {
    //start position of line
    int x = 380;
    int y = 180;
    //end position if line
    int endx = 500; //endx=x+length*cos(a)
    int endy = 500; //endy=y+length*sin(a)
    //line degree
    double length = 100;
    double MIN_Length = 100;
    double MAX_Length = 750;
    double n = 0;
    // direction
    int dir = 1;
    //state 0-wing 1-catch 2-back 3-catch & back
    int state;
    //image of hook
    Image hook = Toolkit.getDefaultToolkit().getImage("imgs/hook.png");

    GameWin frame;
    Line(GameWin frame) {
        this.frame = frame;
    }

    //check if the object is caught
    void logic() {
        for (Object obj : this.frame.objectList) {
            if (endx > obj.x && endx < (obj.x + obj.width)
                    && endy > obj.y && endy < (obj.y + obj.height)) {
                state = 3;
                obj.flag = true;
            }
        }
    }

    //graph method
    void lines(Graphics g) {
        endx = (int) (x + length * Math.cos(n * Math.PI));
        endy = (int) (y + length * Math.sin(n * Math.PI));
        g.setColor(Color.red);
        g.drawLine(x-1, y, endx-1, endy);
        g.drawLine(x, y, endx, endy);
        g.drawLine(x+1, y, endx+1, endy);
        g.drawImage(hook, endx - 36, endy - 2, null);
    }

    void paintSelf(Graphics g) {
        logic();
        switch (state) {
            case 0:
                if (n < 0.1) {
                    dir = 1;
                } else if (n > 0.9) {
                    dir = -1;
                }
                n = n + 0.005 * dir;
                lines(g);
                break;
            case 1:
                if (length < MAX_Length) {
                    length = length + 10;
                    lines(g);
                } else {
                    state = 2;
                }
                break;
            case 2:
                if (length > MIN_Length) {
                    length = length - 10;
                    lines(g);
                } else {
                    state = 0;
                }
                break;
            case 3: //catch and back
                int m = 1;
                if (length > MIN_Length) {
                    length = length - 10;
                    lines(g);
                    for (Object obj : this.frame.objectList) {
                        if (obj.flag) {
                            m = obj.m;
                            obj.x = endx - obj.getWidth() / 2;
                            obj.y = endy;
                            if (length <= MIN_Length) {
                                obj.x = -150;
                                obj.y = -150;
                                obj.flag = false;
                                Bg.waterFlag = false;
                                Bg.count += obj.count; //add score
                                state = 0;
                            }
                            if (Bg.waterFlag) {
                                if (obj.type==1) {m=1;}
                                if (obj.type==2) {
                                    obj.x = -150;
                                    obj.y = -150;
                                    obj.flag = false;
                                    Bg.waterFlag = false;
                                    state = 2;
                                }
                            }
                        }
                    }
                }
                try {
                    Thread.sleep(m);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
            //default:
        }


    }
    //reset line
    void reGame() {
        n = 0;
        length = 100;
    }
}
