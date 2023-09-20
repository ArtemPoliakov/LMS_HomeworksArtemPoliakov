package org.example.Homework_11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Tasks {
    public static String[] sortOddNumberedNamesTaskOne(String[] names){
          return IntStream
                .range(0, names.length)
                .filter((n) -> n%2==1)
                .mapToObj((n)->n)
                .map((n)-> n+"."+names[n])
                .toArray(String[]::new);
    }

    public static String[] taskTwo(String[] words){
        return Arrays.stream(words)
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .toArray(String[]::new);
    }
    public static String taskThree(String[] numbers){
        return Arrays.stream(numbers)
                .mapToInt(Integer::parseInt)
                .sorted()
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    public static Stream<Long> taskFour(long seed, long a, long c, long m){
        return Stream.iterate(seed, n -> (a*n + c)%m);
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second){
        List<T> firstList = first.toList();
        List<T> secondList = second.toList();
        List<T> sumList = new LinkedList<>();
        int i = 0;
        while(i<firstList.size() && i<secondList.size()){
            sumList.add(firstList.get(i));
            sumList.add(secondList.get(i));
            i++;
        }
        return sumList.stream();
    }
}
