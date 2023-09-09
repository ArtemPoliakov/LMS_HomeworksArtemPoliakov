package org.example.Homework_9;

import java.io.Serializable;

public class MyLinkedList <E> implements Cloneable, Serializable {
    private Node head;
    private Node tail;
    private int length;

    public MyLinkedList(){
        head = null;
        tail = null;
        length = 0;
    }
    public boolean isEmpty(){
        return head == null;
    }

    public void addToEnd(E value){
        Node newNode = new Node(value);
        if(isEmpty()){
            head = newNode;
        } else{
            tail.next = newNode;
            newNode.previous = tail;
        }
        tail = newNode;
        length++;
    }
    public void addToBeginning(E value){
        Node newNode = new Node(value);
        if(isEmpty()){
            head = newNode;
        }else{
            head.previous = newNode;
            newNode.next = head;
        }
        head = newNode;
        length++;
    }
    public void clear(){
        head = null;
        tail = null;
        length = 0;
    }
    public int size(){
        return length;
    }
    public E get(int index){
        validateIndex(index);
        Node temp = findNode(index);
        return (E)temp.value;
    }
    public void remove(int index){
        validateIndex(index);
        Node temp = findNode(index);
        if(index == 0){
            head = temp.next;
            head.previous = null;
            return;
        }
        if(index==length-1) {
            tail = temp.previous;
            tail.next = null;
            return;
        }
        temp.previous.next = temp.next;
        temp.next.previous = temp.previous;
        length--;
    }

    public void insert(E value, int index){
        validateIndex(index);
        if(index == 0){
            addToBeginning(value);
        } else{
            Node newNode = new Node(value);
            Node temp = findNode(index);
            newNode.previous = temp.previous;
            newNode.next = temp;
            temp.previous.next = newNode;
            temp.previous = newNode;
        }
    }
    public void set(E value, int index){
        Node temp = findNode(index);
        temp.value = value;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        Node temp = head;
        builder.append("[");
        while(temp!=null){
            builder.append(temp.value + ", ");
            temp = temp.next;
        }
        builder.delete(builder.length()-2, builder.length());
        builder.append("]");
        return builder.toString();
    }
    private void validateIndex(int index){
        if(index>=length||index<0)
            throw new ArrayIndexOutOfBoundsException("index "+index+" is out of bounds for size "+length);
    }
    private Node findNode(int index){
        Node temp;
        if(index<index/2) {
            temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        } else{
            temp = tail;
            for (int i = length; i > index+1; i--) {
                temp = temp.previous;
            }
        }
        return temp;
    }
    private class Node {
        private E value;
        private Node previous;
        private Node next;

        public Node(E value){
            this.value = value;
        }
    }
}
