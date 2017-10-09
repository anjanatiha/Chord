package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Chord2 {
    Node head, current;

    Chord2() {
        this.head = null;
        this.current = null;
    }

    public void addNode() {
        Node newNode = new Node();
        System.out.println(newNode.data);
        if (head == null) {
            this.head = newNode;
            this.current = this.head;
            this.head.next = this.head;
        }
        else
        {
            Node iter = head;
            int i = 0;
            while (true) {
                if (i >= 0 & iter.next == head & iter.nodeID < newNode.nodeID) {
                    Node prevNode = iter;
                    prevNode.next = newNode;
                    newNode.next = head;
                    head.prev = newNode;
                    break;
                }

                else if (newNode.nodeID > iter.max) {
                    if (iter != head) {
                        Node prevNode = iter.prev;
                        prevNode.next = newNode;
                        newNode.next = iter;
                        newNode.prev = prevNode;
                        iter.prev = newNode;
                        if (iter.min < newNode.max) {
                            List<Integer> removeData = iter.removeValList(newNode.max);
                            newNode.addValList(removeData);
                        }
                        break;
                    }
                    else {
                        newNode.next = iter;
                        iter.prev = newNode;
                        this.head = newNode;
                        if (iter.min < newNode.max) {
                            List<Integer> removeData = iter.removeValList(newNode.max);
                            newNode.addValList(removeData);
                        }
                        break;
                    }

                }
                else if (newNode.nodeID == iter.nodeID){
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
            this.max = this.nodeID;
            this.min = 99999;
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
            if (i != 0 & iter == head) {
                break;
            } else {
                System.out.println(iter.nodeID);
            }
            iter = iter.next;
            i++;
        }
    }

    public static void main(String[] args) {
        Chord2 chord = new Chord2();
        chord.addNode();
        chord.addNode();
        chord.addNode();
        chord.addNode();
        chord.printNode();

    }

}
