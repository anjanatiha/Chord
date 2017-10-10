package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Chord10 {
    Node head;

    Chord10() {
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
    public void addVal(int key, int val){
        System.out.println("Adding key, val: "+ key  + ", "+val);
        Node succNode = succ(key);
        succNode.addVal(val);

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
    public void print(){
        System.out.println("Debug");
    }
    public void print(String str){
        System.out.println(str);
    }
    public void print(int str){
        System.out.println(str);
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

    public static void main(String[] args) {
        Chord10 chord = new Chord10();
        chord.addNode();
        chord.addNode();
        chord.addNode();
        chord.addNode();
        chord.addVal(60,60);
        chord.addVal(32,32);
        chord.addVal(51,51);
        chord.printNode();


    }

}
