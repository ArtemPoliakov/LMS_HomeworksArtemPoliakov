package org.example.Homework_9;

public class MyQueue <E> {
    private Node front;
    private Node rear;
    private int length;

    public MyQueue() {
        front = null;
        rear = null;
        length = 0;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return length;
    }

    public void clear() {
        front = null;
        rear = null;
        length = 0;
    }

    public E peek() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Queue is empty");
        }
        return (E) front.value;
    }

    public E poll() {
        E toReturn = peek();
        front = front.next;
        length--;
        return toReturn;
    }


    public void add(E value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
        length++;
    }

    public MyQueue<E> copy() {
        MyQueue<E> clone = new MyQueue<>();
        Node temp = front;
        while (temp != null) {
            clone.add((E) temp.value);
            temp = temp.next;
        }
        return clone;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node temp = front;
        builder.append("[");
        while (temp != null) {
            builder.append(temp.value + "-->");
            temp = temp.next;
        }
        builder.delete(builder.length() - 3, builder.length());
        builder.append("]");
        return String.valueOf(builder);
    }
    private class Node {
        private E value;
        private Node next;

        public Node(E value) {
            this.value = value;
        }
    }
}