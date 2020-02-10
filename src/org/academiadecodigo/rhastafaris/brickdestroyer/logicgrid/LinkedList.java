package org.academiadecodigo.rhastafaris.brickdestroyer.logicgrid;

import org.academiadecodigo.rhastafaris.brickdestroyer.gameobjects.Brick;

public class LinkedList {

    private Node head;
    private int length = 0;

    public LinkedList() {
        this.head = new Node(null);
    }

    public int size() {
        return length;
    }

    /**
     * Adds an element to the end of the list
     *
     * @param data the element to add
     */
    /*public void add(Brick[] data) {

        Node node = new Node(data);
        Node iterator = head;

        while (iterator.getNext() != null) {

            iterator = iterator.getNext();
        }

        iterator.setNext(node);
        length++;

    }*/

    /**
     * Adds an element to the end of the list
     *
     * @param data the element to add
     */
    public void addArray(Brick[] data) {

        Node node = new Node(data);
        Node iterator = head;

        while (iterator.getNext() != null) {

            iterator = iterator.getNext();
        }

        iterator.setNext(node);
        length++;

    }

    /**
     * Obtains an element by index
     *
     * @param index the index of the element
     * @return the element
     */
    public Object get(int index) {

        Node iterator = head;

        for (int i = -1; i < index; i++) {

            if (iterator.getNext() == null) {
                return null;
            }

            iterator = iterator.getNext();
        }

        return iterator.getData();

    }

    public LinkedList getLinked(int index) {

        Node iterator = head;

        for (int i = -1; i < index; i++) {

            if (iterator.getNext() == null) {
                return null;
            }

            iterator = iterator.getNext();
        }

        return iterator.getLinkedlist();

    }

    public Brick getBrick(int index) {

        Node iterator = head;

        for (int i = -1; i < index; i++) {

            if (iterator.getNext() == null) {
                return null;
            }

            iterator = iterator.getNext();
        }

        return iterator.getBrick();

    }

    public Brick[] getArray(int index) {

        Node iterator = head;

        for (int i = -1; i < index; i++) {

            if (iterator.getNext() == null) {
                return null;
            }

            iterator = iterator.getNext();
        }

        return iterator.getDataArray();

    }

    /**
     * Returns the index of the element in the list
     *
     * @param data element to search for
     * @return the index of the element, or -1 if the list does not contain element
     */
    public int indexOf(Object data) {

        Node iterator = head;

        int index = 0;

        while (iterator.getNext() != null) {

            iterator = iterator.getNext();

            if (iterator.getData() == data) {
                return index;
            }

            index++;

        }

        return -1;
    }

    /**
     * Removes the first occurrence of an element from the list
     *
     * @param data the element to remove
     * @return true if element was removed
     */
    public boolean remove(Brick[] data) {

        Node iterator = head;
        Node lastIterator;

        while (iterator.getNext() != null) {

            lastIterator = iterator;

            iterator = iterator.getNext();

            if (iterator.getDataArray() == data) {

                lastIterator.setNext(iterator.getNext());
                length--;
                return true;

            }


        }

        return false;



    }

    private class Node {

        private Object data;
        private Brick[] dataArray;
        private Node next;
        private Brick brick;
        private LinkedList linkedList;

        public Node(Brick[] dataArray) {
            this.dataArray = dataArray;
            next = null;
        }

        public Object getData() {
            return data;
        }

        public Brick getBrick() {
            return brick;
        }

        public LinkedList getLinkedlist() {
            return linkedList;
        }

        public Brick[] getDataArray() {
            return dataArray;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

}
