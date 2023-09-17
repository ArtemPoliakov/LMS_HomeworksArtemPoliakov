package org.example.Homework_10.task_2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TaskTwoClass {
    public static void doJsonStuff(String path){
        File file = new File(path);
        try(FileInputStream fis = new FileInputStream(file);
            FileOutputStream writer = new FileOutputStream("src/main/java/org/example/Homework_10/task_2/peopleJson.json"))
        {

            Scanner scanner = new Scanner(fis);
            String[] fieldNames = scanner.nextLine().split(" ");
            ArrayList<Person> people = new ArrayList<>();
            while(scanner.hasNextLine()){
                Person person = new Person(null, null);
                String[] data = scanner.nextLine().trim().split(" ");

                    for(int i = 0; i<fieldNames.length; i++){
                        Field field = person.getClass().getDeclaredField(fieldNames[i]);
                        field.setAccessible(true);
                        field.set(person, cast(field, data[i]));
                        field.setAccessible(false);
                    }
                    people.add(person);   // problem here maybe
            }
            String jsonOfPeople = parseToJson(people);
            writer.write(jsonOfPeople.getBytes());

        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    private static String parseToJson(Object obj){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
         return gson.toJson(obj);
    }
    private static Object cast(Field field, String value){
        Class fieldCLass = field.getType();
        if(fieldCLass==Integer.class){
           return Integer.parseInt(value);
        } else if(fieldCLass==String.class){
            return value;
        } else if(fieldCLass== Double.class){
            return Double.parseDouble(value);
        } else{
            throw new UnsupportedOperationException();
        }
    }
}
