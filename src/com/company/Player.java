package com.company;

import java.awt.*;

public class Player {
    int position;//position of  player's racket +-height/3
    int dp;
    Boolean side;//which of players this is
    int plA = Main.height / 4;
    int plB = Main.width / 50;

    int count = 0;

    public Player(int position, Boolean side) {
        this.position = position;
        this.side = side;
    }

    public int ssi() {
        if (side == false) return 0;
        else return Main.width-plB;
    }

    public void showWhere(Graphics g) {
        g.fillRect(ssi(), position, plB, plA);
    }

    public void nextS() {

        if (position + dp > 0 & position + plA + dp < Main.height) {
            position += dp;
        }


    }


}

