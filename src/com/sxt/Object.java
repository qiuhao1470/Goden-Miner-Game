package com.sxt;

import java.awt.*;

public class Object {
    //location
    int x;
    int y;
    //width and height
    int width;
    int height;
    //image
    Image img;
    //whether move?
    boolean flag;
    //mass of rock and gold
    int m;
    //score
    int count;
    //rock (2) or gold (1)
    int type;


    void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    public int getWidth() {
        return width;
    }
    //get rectangle
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }

}
