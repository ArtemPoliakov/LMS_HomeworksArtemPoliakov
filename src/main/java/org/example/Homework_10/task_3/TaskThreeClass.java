package org.example.Homework_10.task_3;

import javax.imageio.IIOException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class TaskThreeClass {
    public static void countWordsInFile(String path){
        try(FileInputStream fis = new FileInputStream(path)){
            byte[] byteArray = new byte[fis.available()];
            fis.read(byteArray);
            String input = new String(byteArray);
            input = input.replaceAll("(\\\\n)", "");
            String[] words = input.trim().split(" ");
            Map<String, Integer> wordMap = new HashMap<>();
            for(String word: words){
                if(!word.isBlank()){
                    if(wordMap.containsKey(word)){
                        wordMap.put(word, wordMap.get(word)+1);
                    } else{
                        wordMap.put(word, 1);
                    }
                }
            }
            ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>(wordMap.entrySet());
            entries.sort(new MapEntryComparator());
            for(Map.Entry<String, Integer> entry: entries){
                System.out.println(entry);
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
