package com.company;

import javax.swing.*;
import java.awt.*;

public class Draw extends JPanel {

    private static final int SIZE = 512;
    private int a = SIZE / 2;
    private int b = a;
    private int r = 4 * SIZE / 5;
    private int n;
    private String[] str;
    private int size;


    public Draw(String[] str) {
        super(true);
        repaint();
        this.setPreferredSize(new Dimension(SIZE, SIZE));
        this.str = str;
        this.size = str.length;
    }

    private static void create(String[] str) {
        JFrame f = new JFrame();
        f.repaint();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new Draw(str));
        f.pack();
        f.setVisible(true);

    }

    public static void display(String[] str) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                create(str);

            }


        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.black);

        a = getWidth() / 2;
        b = getHeight() / 2;
        int m = Math.min(a, b);
        r = 4 * m / 5;
        int r2 = Math.abs(m - r) / 2;

        g2d.drawOval(a - r, b - r, 2 * r, 2 * r);

        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }
        for (int i = 0; i < str.length; i++) {
            double t = 2 * Math.PI * i / size;
            int x = (int) Math.round(a + r * Math.cos(t));
            int y = (int) Math.round(b + r * Math.sin(t));
            g2d.setColor(Color.GREEN);
            g2d.drawOval(x - r2, y - r2, 2 * r2, 2 * r2);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.BLUE);
            g2d.drawString(str[i], x - r2 - 10, y - r2 - 10);
            System.out.print(i + " ");
        }

    }
}
