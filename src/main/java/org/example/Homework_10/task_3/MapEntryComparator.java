package org.example.Homework_10.task_3;

import java.util.Comparator;
import java.util.HashMap;

public class MapEntryComparator implements Comparator<HashMap.Entry<String, Integer>> {


    @Override
    public int compare(HashMap.Entry<String, Integer> o1, HashMap.Entry<String, Integer> o2) {
        int result = 0;
        if(o1.getValue()<o2.getValue()){
            result = 1;
        } else if(o1.getValue()>o2.getValue()){
            result = -1;
        }
        return result;
    }
}
