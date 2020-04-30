package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Ball {
    Vektor pos;//Ball is a sphere of fixed radius and coordinates pos
    Vektor speed;

    public Ball(Vektor pos, Vektor speed) {
        this.pos = pos;
        this.speed = speed;
    }

    int radius = 10;


    public Boolean hasTouched(Player xx) {
        if (xx.ssi() - pos.vX == 0 & pos.vY < xx.position + xx.plA & pos.vY > xx.position){
            System.out.println("true dat");
            return true;}
        else return false;
    }

    public Boolean hasGrounded() {
        if (pos.vY - radius == 0 || pos.vY + radius == Main.height) return true;
        else return false;
    }

    public Boolean hasLost(){
        if (pos.vX == 0 || pos.vX == Main.width) return true;
        else return false;
    }

    public void mStep(Player x1, Player x2) {

        pos = pos.summ(speed);

        if(hasTouched(x1)){
            sideTouch();
            x1.count+=1;
        }else{
            if (hasTouched(x2)){
                sideTouch();
                x2.count+=1;
            }else{
                if(hasGrounded()){
                    bottomTouch();
                }else{
                    if(hasLost()){
                        pos = new Vektor(Main.width/2,Main.height/2);
                        System.out.println("you lost");
                        x1.count = 0;
                        x2.count = 0;
                    }
                }
            }
        }




    }

    public void showB(Graphics g) {
        g.fillOval((int) pos.vX, (int) pos.vY, radius, radius);
    }

    private void bottomTouch() {
        speed.vY = -speed.vY;
    }

    private void sideTouch() {
        speed.vX = -speed.vX;
    }

}