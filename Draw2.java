package com.company;

import javax.swing.*;
import java.awt.*;
import Misc.Print;

public class Draw2 extends JPanel {

    private static final int SIZE = 256;
    private int a = SIZE / 2;
    private int b = a;
    private int r = 4 * SIZE / 5;
    private static int size;
    static Chord.Node head;


    public Draw2(Chord.Node head, int size) {
        super(true);
        this.setPreferredSize(new Dimension(SIZE, SIZE));
        this.head = head;
        this.size = size;
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

        if (this.head==null){
            g2d.drawString("No node found", a, b);
            return;
        }


        g2d.drawOval(a - r, b - r, 2 * r, 2 * r);
        g2d.setColor(Color.lightGray);


        Chord.Node iter = this.head;

        int i = 0;
        while (true) {
            if (i > 0 && iter == head) {
                break;
            } else {
                System.out.println("asdasd"+ iter.data);
                double t = 2 * Math.PI * i / size;
                int x = (int) Math.round(a + r * Math.cos(t));
                int y = (int) Math.round(b + r * Math.sin(t));
                g2d.fillOval(x - r2, y - r2, 2 * r2, 2 * r2);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.drawString(iter.getDataStr(), x - r2-10, y - r2-10);
                iter = iter.next;
                i++;
            }
        }
    }

    private static void create(Chord.Node head, int size) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new Draw2(head, size));
        f.pack();
        f.setVisible(true);
    }
    public static void rund(Chord.Node Head, int size){
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                create(head, size);
            }
        });
    }
    public static void main(String[] args) {

//        EventQueue.invokeLater(new Runnable() {
//
//            @Override
//            public void run() {
//                create();
//            }
//        });
    }
}
