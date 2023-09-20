package org.example.Homework_11;

import java.util.Arrays;
import java.util.stream.Stream;

public class Demo {
    public static void main(String[] args){
        String[] array = {"Artem", "Ivan", "Nazar", "Sasha", "Bogdan", "Danil", "Olena"};
        String[] numbers = {"1", "2", "10","6","2","9","34","67"};
        //TEST#1
        System.out.println(Arrays.toString(Tasks.sortOddNumberedNamesTaskOne(array)));
        //TEST#2
        System.out.println(Arrays.toString(Tasks.taskTwo(array)));
        //TEST#3
        System.out.println(Tasks.taskThree(numbers));
        //TEST#4
        System.out.println(Arrays.toString(
                Tasks.taskFour(0l, 25214903917l, 11l, 2^48l).limit(20).toArray(Long[]::new)));
        //TEST#5
        System.out.println(Tasks.zip(Stream.of
                ("Something", "Artem"), Stream.of("Dania", "Petia", "Nazar")).toList());
    }
}
