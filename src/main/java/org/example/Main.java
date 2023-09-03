package org.example;

import org.example.Homework_8.Circle;
import org.example.Homework_8.ShapeProcessor;
import org.example.Homework_9.MyArrayList;
import org.example.Homework_9.MyLinkedList;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        MyLinkedList<Integer> test = new MyLinkedList<>();
        test.addToEnd(1);
        test.addToEnd(2);
        test.addToEnd(3);
        test.insert(10, 2);

        System.out.println(test);

    }
}