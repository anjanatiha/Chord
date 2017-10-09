package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Chord3 {
    Node head, current;

    Chord3() {
        this.head = null;
        this.current = null;
    }

    public void addNode() {
        Node newNode = new Node();
        System.out.println(newNode.data);
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
                if(iter == null){
                    break;
                }
                if (i == 0 && iter == this.head && newNode.nodeID < iter.nodeID){
                    Node currHead = iter;
                    newNode.next = currHead;
                    newNode.prev = currHead;
                    currHead.prev = newNode;
                    currHead.next = newNode;
                    this.head = newNode;
                    if (iter.min <= newNode.max) {
                        List<Integer> removeData = iter.removeValList(newNode.max);
                        this.head.addValList(removeData);
                    }
                    break;
                }
                else if (i > 0 && iter == this.head && newNode.nodeID > iter.prev.nodeID){
                    Node currHead = iter;
                    Node prevNode = iter.prev;
                    newNode.next = currHead;
                    newNode.prev = prevNode;
                    currHead.prev = newNode;
                    prevNode.next = newNode;
                    break;
                }
                else if (i == 0 && iter.next == null && newNode.nodeID > iter.nodeID){
                    Node currHead = iter;
                    currHead.next = newNode;
                    newNode.prev = currHead;
                    currHead.prev = newNode;
                    newNode.next = currHead;
                    break;
                }
                else if (i > 0 && iter != head && iter.next != null &&  newNode.nodeID < iter.nodeID) {
                    Node prevNode = iter.prev;
                    newNode.next = iter;
                    newNode.prev = prevNode;
                    prevNode.next = newNode;
                    iter.prev = newNode;
                    if (iter.min <= newNode.max) {
                        List<Integer> removeData = iter.removeValList(iter.max);
                        newNode.addValList(removeData);
                    }
                    break;
                }
                else if(newNode.nodeID == iter.nodeID) {
                    break;
                }

                i++;
                iter = iter.next;
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
        Node iter = head;
        int i = 0;
        while (true) {
            if (i > 0 && iter == head) {
                break;
            } else {
                System.out.println(iter.nodeID);
            }
            iter = iter.next;
            i++;
        }
    }

    public static void main(String[] args) {
        Chord3 chord = new Chord3();
        System.out.println("\n\n\nkkkk\n\n");
        chord.addNode();
        //chord.printNode();
        //System.out.println("\n\n\nkkkk\n\n");
        chord.addNode();
        //chord.printNode();
        //System.out.println("\n\n\nkkkk\n\n");
        chord.addNode();
        //chord.printNode();
        //System.out.println("\n\n\nkkkk\n\n");
        chord.addNode();
        //chord.printNode();
        //System.out.println("\n\n\nkkkk\n\n");
        chord.addNode();
        //chord.printNode();
        //System.out.println("\n\n\nkkkk\n\n");
        chord.addNode();
        //chord.printNode();
        System.out.println("\n\n\nkkkk\n\n");
        chord.printNode();

    }

}
