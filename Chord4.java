package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Chord4 {
    Node head, current;

    Chord4() {
        this.head = null;
        this.current = null;
    }

    public void addNode() {
        Node newNode = new Node();
        System.out.print(newNode.data + " ");
        if (head == null) {
            this.head = newNode;
            this.head.next = this.head;
            this.head.prev = this.head;
        }
        else
        {
            Node iter = this.head;
            int i = 0;
            while (true) {
                Node curNode = iter;
                Node prevNode = iter.prev;
                Node nextNode = iter.next;
                if (newNode.nodeID == iter.nodeID){
                    break;
                }
                else if (newNode == head && i > 0){
                    break;
                }
                else if (i == 0){
                    if (newNode.nodeID < iter.nodeID){
                        newNode.next = curNode;
                        newNode.prev = curNode;
                        curNode.next = newNode;
                        curNode.prev = newNode;
                        if (iter.min <= newNode.max) {
                            List<Integer> removeData = iter.removeValList(newNode.max);
                            newNode.addValList(removeData);
                        }
                        this.head = newNode;
                    }
                    else if (newNode.nodeID > iter.nodeID && iter.next == this.head){
                        newNode.next = curNode;
                        newNode.prev = curNode;
                        curNode.next = newNode;
                        curNode.prev = newNode;

                    }
                    else {
                        System.out.println("sdasdsdas");
                        break;
                    }
                }
                else if (i > 0){
                    if (iter.next == this.head && newNode.nodeID > iter.nodeID){
                        System.out.println("i>0");
                        newNode.next = curNode.next;
                        newNode.prev = curNode;
                        curNode.next = newNode;
                        curNode.next.prev = newNode;
                        break;

                    }
                    else if (newNode.nodeID < iter.nodeID){
                        newNode.next = curNode;
                        newNode.prev = prevNode;
                        curNode.prev = newNode;
                        prevNode.next = newNode;
                        if (iter.min <= newNode.max) {
                            List<Integer> removeData = iter.removeValList(newNode.max);
                            newNode.addValList(removeData);
                        }

                    }
                    else {

                        System.out.println(iter.data+" c aaa");
                        break;
                    }
                }
                iter = iter.next;
                i++;
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
        System.out.print("\n");
        while (true) {
            if (i > 0 && iter == this.head) {
                break;
            } else {
                System.out.print(iter.nodeID + " ");
            }
            iter = iter.next;
            i++;
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        Chord4 chord = new Chord4();
        chord.addNode();
        chord.printNode();
        chord.addNode();
        chord.printNode();
        chord.addNode();
        chord.printNode();


    }

}
