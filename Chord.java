/*
Created by Anjana Tiha
Homework 2

 */


package com.company;
import javax.swing.*;
import java.util.*;

import static Misc.Print.print;

public class Chord {
    Node head;
    Chord() {
        this.head = null;
    }

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
            print(succNode.nodeID);
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

    public void addData(int key, int val){
        System.out.println("Adding key, val: "+ key  + ", "+val);
        Node succNode = succ(key);
        succNode.addVal(val);

    }
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
    public Node getHead(){
        return this.head;
    }
    public int getSize(){
        Node iter = this.head;
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
    public void getInter(){
        Scanner sc = new Scanner(System.in);
        String choiceStr = JOptionPane.showInputDialog("Please Enter Choice\n0: Exit\n1: Add Node\n2: Delete Node\n3: Add Value\n");
        int choice = Integer.parseInt(choiceStr);
        while (true){
//            System.out.println("Please Enter Choice\n");
//            System.out.println("0: Exit\n");
//            System.out.println("1: Add Node\n");
//            System.out.println("2: Delete Node\n");
//            System.out.println("3: Add Value\n");
            switch (choice){
                case 0:
                    return;
                case 1:
                    addNode();
                    //printNode();
                    break;
                case 2:
                    choiceStr = JOptionPane.showInputDialog("Please enter NodeID");
                    int nid = Integer.parseInt(choiceStr);
                    deleteNode(nid);
                    printNode();
                    break;
                case 3:
                    choiceStr = JOptionPane.showInputDialog("Please enter data key in integer");
                    int key = Integer.parseInt(choiceStr);
                    choiceStr = JOptionPane.showInputDialog("Please enter data value in integer");
                    int val = Integer.parseInt(choiceStr);
                    addData(key,val);
                    printNode();
                    break;
                default:
                    System.out.println("No choice found");
                    break;
            }
        }

    }

    public void printNode() {
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


    class Node {
        int nodeID, min, max;
        Node prev, next;
        List<Integer> data = new ArrayList<Integer>();

        Node() {
            this.nodeID = genID(100);
            this.data.add(nodeID);
            this.min = data.indexOf(Collections.min(data));
            this.max = data.indexOf(Collections.max(data));
            this.next = null;
            this.prev = null;
        }

        public void addVal(int val) {
            if (!data.contains(val)) {
                this.data.add(val);
                Collections.sort(this.data);
                this.min = data.indexOf(Collections.min(data));
                this.max = data.indexOf(Collections.max(data));
            }

        }
        public List<Integer> getData(){
            return data;
        }
        public String getDataStr(){
            String str = " [";
            for (int val : data) {
                str = str + val + " , ";
            }
            return str.substring(0,str.length()-3) + " ] ";
        }
        public void addValList(List<Integer> val) {
            this.data.addAll(val);
            Collections.sort(data);
        }
        public int removeVal(int val) {
            this.data.remove(val);
            Collections.sort(data);
            this.min = data.indexOf(Collections.min(data));
            this.max = data.indexOf(Collections.max(data));
            this.nodeID = this.max;
            return val;
        }

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



        public int genID(int bound) {
            Random rand = new Random();
            int ID = rand.nextInt(bound) + 1;
            return ID;
        }
    }



    public static void main(String[] args) {
        Chord chord = new Chord();
//        chord.addNode();
//        chord.addNode();
//        chord.addNode();
//        chord.addNode();
//        chord.addData(60,60);
//        chord.addData(32,32);
//        chord.addData(51,51);
//        chord.printNode();
        String test1, test2, test3, avg;



        chord.getInter();



    }

}
