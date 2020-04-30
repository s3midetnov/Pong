package com.company;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class Main extends KeyAdapter {

    static int width = 1280;
    static int height = 716;


    //магический код
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong");
        frame.setBackground(Color.black);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.createBufferStrategy(2);
        frame.addKeyListener(new Main());
        //frame.add
        while (true) {
            BufferStrategy bs = frame.getBufferStrategy();
            Graphics g = bs.getDrawGraphics();
            g.clearRect(0, 0, width, height);
            paint(g);
            bs.show();
            g.dispose();
        }

    }

    //Тут добавляем данные которые храним между кадрами
    static long lastFrame = System.currentTimeMillis();
    static Ball mB = new Ball(new Vektor(width / 2, height / 2), new Vektor(1, -0.5));
    static Player p1 = new Player(0, false);
    static Player p2 = new Player(0, true);


    //Тут обновялем данные и рисуем
    public static void paint(Graphics g) {
        //вычисляем длинну кадра
        long currentFrame = System.currentTimeMillis();
        double dt = (currentFrame - lastFrame) / 1000.0;
        lastFrame = currentFrame;
        //рисуем
        g.setColor(Color.CYAN);
        pDotl(g);
        mB.showB(g);
        mB.mStep(p1, p2);
        p1.showWhere(g);
        p1.nextS();
        p2.showWhere(g);
        p2.nextS();
        g.setFont(new Font("BOLD", Font.PLAIN, 20));
        g.drawString(String.valueOf(p1.count), width / 4, height / 3);
        g.drawString(String.valueOf(p2.count), 3 * width / 4, height / 3);


    }


    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case (KeyEvent.VK_DOWN):
                p2.dp = 5;
                break;
            case (KeyEvent.VK_UP):
                p2.dp = -5;
                break;
            case (KeyEvent.VK_W):
                p1.dp = -5;
                break;
            case (KeyEvent.VK_S):
                p1.dp = 5;
                break;
        }

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP) {
            p2.dp = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
            p1.dp = 0;
        }
    }

    static public void pDotl(Graphics g) {
        for (int i = 0; i < height / 10; i++) {
            g.fillRect(width / 2 - 20, height / 40 + i * height / 40, 10, height / 80);
        }
    }


}