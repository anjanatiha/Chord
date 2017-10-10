/*
Created by Anjana Tiha
Homework 2
Chord graphical implementation
Function includes, Add Node, Delete Node, Add data(with key), Exit
Data: 10/11/2017

 */


package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static Misc.Print.print;

public class Chord2 extends JPanel{
    Node head;

    Chord2() {
        this.head = null;
        getInter();
    }



    //Add new Node by generating random number
    public void addNode() {
        Node newNode = new Node();
        System.out.println("Adding new Node: " + newNode.data);
        if (this.head == null) {
            this.head = newNode;
            this.head.next = this.head;
            this.head.prev = this.head;
        }
        else
        {
            Node succNode = succ(newNode.nodeID);
            System.out.println(succNode.nodeID);
            if (newNode.nodeID != succNode.nodeID) {
                Node prevNode = succNode.prev;
                if (succNode == this.head){
                    if (newNode.nodeID < this.head.nodeID){
                        newNode.next = succNode;
                        newNode.prev = prevNode;
                        prevNode.next = newNode;;
                        succNode.prev = newNode;
                        this.head = newNode;
                        if (succNode.min <= newNode.max) {
                            List<Integer> removeData = succNode.removeValList(newNode.max);
                            newNode.addValList(removeData);
                        }
                    }
                    else {
                        newNode.prev = prevNode;
                        newNode.next = succNode;
                        succNode.prev = newNode;
                        prevNode.next = newNode;
                    }
                }
                else {
                    newNode.next = succNode;
                    newNode.prev = prevNode;
                    succNode.prev = newNode;
                    prevNode.next = newNode;
                    if (succNode.min <= newNode.max) {
                        List<Integer> removeData = succNode.removeValList(newNode.max);
                        newNode.addValList(removeData);
                    }
                }
            }
        }
    }



    // Add new data to appropriate node
    public void addData(int key, int val){
        System.out.println("Adding key, val: "+ key  + ", "+val);
        Node succNode = succ(key);
        succNode.addVal(key);

    }

    // Delete Node
    public void deleteNode(int key){
        Node delNodeTemp = succ(key);
        Node prevNode = delNodeTemp.prev;
        Node nextNode = delNodeTemp.next;
        if (delNodeTemp.nodeID == key){
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }
        else if (prevNode.nodeID == key){
            prevNode = prevNode.prev;
            nextNode = delNodeTemp;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }

        else if (nextNode.nodeID == key){
            prevNode = delNodeTemp;
            nextNode = nextNode.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }

    }

    // get Head of the chord p2p network
    public Node getHead(){
        return this.head;
    }

    //get size or number of machines of the chord network
    public int getNetworkSize(){
        Node iter = this.head;
        if (iter == null)
            return 1;
        int i = 0;
        while (true) {
            if (i > 0 && iter == this.head) {
                break;
            }
            iter = iter.next;
            i++;
        }
        return i;
    }


    //return successor node
    public Node succ(int key){
        Node iter = this.head;
        int i = 0;
        while (true){
            if (i > 0 && iter == head){
                break;
            }
            else {
                if (key <= iter.nodeID){
                    break;
                }
                else {
                    iter = iter.next;
                }
            }
            i++;

        }
        System.out.print("key: ");
        System.out.println(iter.data);
        return iter;
    }

    // Inter input for adding, deleting node and adding data
    public void getInter(){
        while (true){
            String choiceStr = JOptionPane.showInputDialog("Please Enter Choice\n0: Exit\n1: Add Node\n2: Delete Node\n3: Add Value\n");
            int choice = Integer.parseInt(choiceStr);
            switch (choice){
                case 0:
                    return;
                case 1:
                    addNode();
                    display();
                    printNetwork();
                    break;
                case 2:
                    choiceStr = JOptionPane.showInputDialog("Please enter NodeID");
                    int nid = Integer.parseInt(choiceStr);
                    deleteNode(nid);
                    printNetwork();
                    break;
                case 3:
                    choiceStr = JOptionPane.showInputDialog("Please enter data key in integer");
                    int key = Integer.parseInt(choiceStr);
                    choiceStr = JOptionPane.showInputDialog("Please enter data value in integer");
                    int val = Integer.parseInt(choiceStr);
                    addData(key,val);
                    printNetwork();
                    break;
                default:
                    System.out.println("No choice found");
                    break;
            }
        }

    }


    // print all component data
    public void printNetwork() {
        Node iter = this.head;
        int i = 0;
        System.out.print("All node Data: ");
        while (true) {
            if (i > 0 && iter == this.head) {
                break;
            } else {
                System.out.print(iter.data + " ");
            }
            iter = iter.next;
            i++;
        }
        System.out.print("\n\n");
    }

    //node class for each node
    class Node {
        int nodeID, min, max;
        Node prev, next;
        List<Integer> data = new ArrayList<Integer>();


        //constructor class
        Node() {
            this.nodeID = genID(100);
            this.data.add(nodeID);
            this.min = data.indexOf(Collections.min(data));
            this.max = data.indexOf(Collections.max(data));
            this.next = null;
            this.prev = null;
        }


        //add data value to node
        public void addVal(int val) {
            if (!data.contains(val)) {
                this.data.add(val);
                Collections.sort(this.data);
                this.min = data.indexOf(Collections.min(data));
                this.max = data.indexOf(Collections.max(data));
            }

        }

        // get data from node
        public List<Integer> getData(){
            return data;
        }


        // get data in node in string format
        public String getDataStr(){
            String str = " [";
            for (int val : data) {
                str = str + val + " , ";
            }
            return str.substring(0,str.length()-3) + " ] ";
        }

        // add list of data values to new node after change in node organization
        public void addValList(List<Integer> val) {
            this.data.addAll(val);
            Collections.sort(data);
        }


        //remove single data from node
        public int removeVal(int val) {
            this.data.remove(val);
            Collections.sort(data);
            this.min = data.indexOf(Collections.min(data));
            this.max = data.indexOf(Collections.max(data));
            this.nodeID = this.max;
            return val;
        }

        //remove data less than or equal to value val in a node for reorganizing network
        public List<Integer> removeValList(int val) {
            List<Integer> removedData = new ArrayList<Integer>();
            for (int ival : this.data) {
                if (ival <= val) {
                    removedData.add(ival);
                    this.data.remove(ival);
                }
                else
                    break;
            }
            Collections.sort(data);
            return removedData;
        }


        //Generate random number for new Node
        public int genID(int bound) {
            Random rand = new Random();
            int ID = rand.nextInt(bound) + 1;
            return ID;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.black);

       int SIZE = 256;
//        private int a = SIZE / 2;
//        private int b = a;
//        private int r = 4 * SIZE / 5;
        int size = getNetworkSize();
        this.setPreferredSize(new Dimension(SIZE, SIZE));


        int a = getWidth() / 2;
        int b = getHeight() / 2;
        int m = Math.min(a, b);
        int r = 4 * m / 5;
        int r2 = Math.abs(m - r) / 2;

        if (this.head==null){
            g2d.drawString("No node found", a, b);
            return;
        }


        g2d.drawOval(a - r, b - r, 2 * r, 2 * r);
        g2d.setColor(Color.lightGray);


        Node iter = this.head;

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
                System.out.print("asda");
                i++;
            }
        }
    }

    public static void create() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.add(new Chord());
        f.pack();
        f.setVisible(true);
    }
    public static void display(){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                create();
            }
        });
    }


    public static void main(String[] args) {
        Chord2 chord = new Chord2();
        chord.getInter();



    }

}
