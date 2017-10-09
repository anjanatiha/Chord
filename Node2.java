package com.company;

public class Node2 {
    private int nodeId, min = 100000, max = -999999;
    private Item head, tail;
    private Node2 nextList, prevList;

    Node2() {
        this.head = null;
        this.tail = null;
        this.nextList = null;
        this.prevList = null;
    }

    public int getNodeId() {
        return this.nodeId;
    }

    public void setNextList(Node2 nextList) {
        this.nextList = nextList;
    }

    public Node2 getNextList() {
        return this.nextList;
    }

    public void setPrevList(Node2 prevList) {
        this.prevList = prevList;
    }

    public Node2 getPrevList() {
        return this.prevList;
    }

    public int getMinKey() {
        return this.min;
    }

    public int getMaxKey() {
        return this.max;
    }

    public void addItem(int key, int val) {
        if (key > max) {
            this.max = key;
        }
        if (key < min) {
            this.min = key;

        }
        Item item = findNodeInsertPosition(key);
        if (item == null & head == null) {
            head = new Item(key, val);
            head.prev = null;
            head.next = null;
            tail = head;
            nodeId = tail.val;
        } else {
            if (item == head) {
                Item oldHead = head;
                Item newHead = new Item(key, val);
                head = newHead;
                head.next = oldHead;
                head.prev = null;
                oldHead.prev = newHead;
            } else if (item == null & head != null) {
                Item oldTail = tail;
                tail.next = new Item(key, val);
                tail = tail.next;
                tail.prev = oldTail;
                tail.next = null;
                nodeId = tail.val;

            } else {
                Item insertBefore = item;
                Item insertAfter = item.prev;
                Item newItem = new Item(key, val);
                newItem.prev = insertAfter;
                insertAfter.next = newItem;
                newItem.next = insertBefore;
                insertBefore.prev = newItem;


            }
        }

    }

    public void addItem(int key) {
        Item item = findNodeInsertPosition(key);
        if (item == null & head == null) {
            head = new Item(key);
            head.prev = null;
            head.next = null;
            tail = head;
            nodeId = tail.val;
        } else {
            if (item == head) {
                Item oldHead = head;
                Item newHead = new Item(key);
                head = newHead;
                head.next = oldHead;
                head.prev = null;
                oldHead.prev = newHead;
            } else if (item == null & head != null) {
                Item oldTail = tail;
                tail.next = new Item(key);
                tail = tail.next;
                tail.prev = oldTail;
                tail.next = null;
                nodeId = tail.val;

            } else {
                Item insertBefore = item;
                Item insertAfter = item.prev;
                Item newItem = new Item(key);
                newItem.prev = insertAfter;
                insertAfter.next = newItem;
                newItem.next = insertBefore;
                insertBefore.prev = newItem;


            }
        }

    }

    public Item findNodeKey(int key) {
        if (head == null) {
            return null;
        } else {
            Item iterItem = this.head;
            while (iterItem != null) {
                if (iterItem.getKey() == key) {
                    return iterItem;
                }
                iterItem = iterItem.next;
            }

        }
        return null;

    }

    public Item findNodeInsertPosition(int key) {
        if (head == null) {
            return head;
        } else {
            Item iterItem = this.head;
            while (iterItem != null) {
                if (iterItem.getKey() > key) {
                    return iterItem;
                }
                iterItem = iterItem.next;
            }

        }
        return null;

    }

    public void printList() {
        Item iterItem = head;
        while (iterItem != null) {
            System.out.print(iterItem.val + " ");
            iterItem = iterItem.next;
        }
        System.out.println("");
    }

    class Item {
        private int key, val;
        private Item next, prev;

        Item(int key) {
            this.key = key;
        }

        Item(int key, int val) {
            this.key = key;
            this.val = val;
        }

        public void setKey() {
            this.key = key;
        }

        public void setVal() {
            this.val = val;
        }

        public int getKey() {
            return this.key;
        }

        public int getVal() {
            return this.val;
        }

        public void setNext(Item next) {
            this.next = next;
        }

        public void setPrev(Item prev) {
            this.prev = prev;
        }

        public Item getNext() {
            return this.next;
        }

        public Item getPrev() {
            return this.prev;
        }

    }
}
