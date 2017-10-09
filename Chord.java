package com.company;

import java.util.LinkedList;
import java.util.Random;

public class Chord {
    int limitNumber;
    LinkedList<com.company.Node> nodeList;

    Chord(int limitNumber) {
        this.limitNumber = limitNumber;
        nodeList = new LinkedList<>();
    }

    public void randomChordRing() {
        int randNum = random(limitNumber);
        for (int i = 0; i < randNum; i++) {
            //nodeList.add()
        }


    }

    public void addNode() {
        int newNodeID = genID(this.limitNumber);
        com.company.Node succNode = succ(newNodeID);
        com.company.Node newNode = new com.company.Node();
        newNode.addItem(newNodeID);
        succNode.getPrevList().setNextList(newNode);
        succNode.setNextList(newNode);
    }

    public void addData(int key, int val) {
        if (nodeList == null) {
            return;
        }
        com.company.Node nodeIter = nodeList.getFirst();
        while (nodeIter != null) {
            if (nodeIter.getSmallestKey() > key) {
                nodeIter.addItem(key, val);
            }
            nodeIter = nodeIter.getNextList();
        }
    }

    public int findData(int key) {
        com.company.Node dataNode = succ(key);
        com.company.Node.Item dataItem = dataNode.findNodeKey(key);
        return dataItem.getVal();
    }

    public com.company.Node succ(int id) {
        com.company.Node nodeIter = nodeList.getFirst();
        while (nodeIter != null) {
            if (nodeIter.getNodeId() > id) {
                break;
            }
            nodeIter = nodeIter.getNextList();
        }
        return nodeIter;
    }

    public int genID(int bound) {
        int ID = random(bound);
        return ID;
    }

    public int random(int bound) {
        Random rand = new Random();
        int randNum = rand.nextInt(bound) + 1;
        return randNum;
    }
}
