package org.example.Homework_9;

public class MyStack <E> {
    private class Node{
        private Node next;
        private Node previous;
        private E value;
        public Node(E value){
            this.value = value;
        }
    }
    private Node front;
    private Node rear;
    private int length;

    public MyStack(){
        front = null;
        rear = null;
        length = 0;
    }
    public boolean isEmpty(){
        return front == null;
    }
    public int size(){
        return length;
    }
    public void clear(){
        front = null;
        rear = null;
        length = 0;
    }
    public E peek(){
        if(isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Stack is empty");
        }
        return (E)rear.value;
    }
    public E pop(){
        E toReturn = peek();
        rear = rear.previous;
        rear.next = null;
        length--;
        return toReturn;
    }
    public void push(E value){
        Node newNode = new Node(value);
        if(isEmpty()){
            front = newNode;
        } else{
            newNode.previous = rear;
            rear.next = newNode;
        }
        rear = newNode;
        length++;
    }
    public void remove(int index){
        validateIndex(index);
        Node temp = findNode(index);
        if(index == 0){
            front = temp.next;
            front.previous = null;
            return;
        }
        if(index==length-1) {
            rear = temp.previous;
            rear.next = null;
            return;
        }
        temp.previous.next = temp.next;
        temp.next.previous = temp.previous;
        length--;
    }
    private void validateIndex(int index){
        if(index>=length||index<0)
            throw new ArrayIndexOutOfBoundsException("index "+index+" is out of bounds for size "+length);
    }
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        Node temp = front;
        builder.append("[");
        while(temp!=null){
           builder.append(temp.value + "<--");
           temp=temp.next;
        }
        builder.delete(builder.length()-3, builder.length());
        builder.append("]");
        return String.valueOf(builder);
    }
    private Node findNode(int index){
        Node temp;
        if(index<index/2) {
            temp = front;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        } else{
            temp = rear;
            for (int i = length; i > index+1; i--) {
                temp = temp.previous;
            }
        }
        return temp;
    }
}


