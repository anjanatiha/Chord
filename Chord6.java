package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Chord6 {
    Node head, current;

    Chord6() {
        this.head = null;
        this.current = null;
    }

    public void addNode() {
        Node newNode = new Node();
        System.out.println("Adding new Node: " + newNode.data);
        if (head == null) {
            System.out.println("head null");
            this.head = newNode;
            this.head.next = this.head;
            this.head.prev = this.head;
            return;
        }
        else
        {
            Node iter = this.head;
            int i = 0;
            while (true) {
                System.out.println("i: "+ i + " , Head: " + this. head.data+ ", iter:" + iter.data);
                Node curNode = iter;
                Node prevNode = iter.prev;
                Node nextNode = iter.next;
                if (newNode.nodeID == iter.nodeID){
                    System.out.println("same : newNode.nodeID == iter.nodeID");
                    System.out.println("\n\nOp : same--endAdding--\n-\n");
                    break;
                }
                else if (newNode == this.head.next && i > 1){
                    System.out.println("repeat: newNode == this.head.next && i > 1");
                    System.out.println("\n\nOp : repeat--endAdding--\n-\n");
                    break;
                }
                else if (i == 0){
                    if(newNode.nodeID < iter.nodeID){
                        System.out.println("Op1 : i==0 && newNode.nodeID < iter.nodeID ");
                        if (iter.next==this.head) {
                            newNode.next = curNode;
                            newNode.prev = curNode;
                            curNode.next = newNode;
                            curNode.prev = newNode;
                        }
                        else {
                            Node prevPrevNode = prevNode;
                            newNode.next = curNode;
                            newNode.prev = prevPrevNode;
                            prevPrevNode.next = newNode;
                            curNode.prev = newNode;
                        }
                        if (iter.min <= newNode.max) {
                            List<Integer> removeData = iter.removeValList(newNode.max);
                            newNode.addValList(removeData);
                        }
                        this.head = newNode;
                        System.out.println("\n\nOp : 0--endAdding--\n-\n");
                        break;
                    }
                    else if(newNode.nodeID > iter.nodeID && iter.next == this.head){
                        System.out.println("Op2: i==0 && newNode.nodeID > iter.nodeID && iter.next == this.head");
                        newNode.next = curNode;
                        newNode.prev = curNode;
                        curNode.next = newNode;
                        curNode.prev = newNode;
                        System.out.println("\n\nOp : 2--endAdding--\n-\n");
                        break;

                    }
                    else {
                        System.out.println("Op3 : i==0 && else");

                    }
                }
                else if (i > 0){
                    if (iter.next == this.head && newNode.nodeID > iter.nodeID){
                        System.out.println("Op : 4 : i> 0 && iter.next == this.head && newNode.nodeID > iter.nodeID");
                        newNode.next = curNode.next;
                        newNode.prev = curNode;
                        curNode.next = newNode;
                        curNode.next.prev = newNode;
                        System.out.println("\n\nOp : 4--endAdding--\n-\n");
                        break;

                    }
                    else if (newNode.nodeID < iter.nodeID){
                        System.out.println("Op : 5 : i > 0newNode.nodeID < iter.nodeID ");
                        newNode.next = curNode;
                        newNode.prev = prevNode;
                        curNode.prev = newNode;
                        prevNode.next = newNode;
                        if (iter.min <= newNode.max) {
                            List<Integer> removeData = iter.removeValList(newNode.max);
                            newNode.addValList(removeData);
                        }
                        System.out.println("\n\nOp : 5--endAdding--\n-\n");
                        break;
                    }

                    else {
                        System.out.println("Op : 6");
                    }
                }
                iter = iter.next;
                i++;
                System.out.println("\n\n--endAdding--\n-\n");
            }
        }
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
            this.data.add(val);
            Collections.sort(this.data);
            this.min = data.indexOf(Collections.min(data));
            this.max = data.indexOf(Collections.max(data));

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

        public void addValList(List<Integer> val) {
            this.data.addAll(val);
            Collections.sort(data);
        }

        public int genID(int bound) {
            Random rand = new Random();
            int ID = rand.nextInt(bound) + 1;
            return ID;
        }
    }

    public void printNode() {
        Node iter = this.head;
        int i = 0;
        System.out.print("node Data: ");
        while (true) {
            if (i > 0 && iter == this.head) {
                break;
            } else {
                System.out.print(iter.nodeID + ", ");
            }
            iter = iter.next;
            i++;
        }
        System.out.print("\n\n");
    }

    public static void main(String[] args) {
        Chord6 chord = new Chord6();
        chord.addNode();
        //chord.printNode();
        chord.addNode();
        //chord.printNode();
        chord.addNode();
        //chord.printNode();
        chord.addNode();
        chord.printNode();


    }

}
