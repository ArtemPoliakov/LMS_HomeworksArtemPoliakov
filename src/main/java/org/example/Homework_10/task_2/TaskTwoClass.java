package org.example.Homework_10.task_2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TaskTwoClass {
    public static void doJsonStuff(String path){
        File file = new File(path);
        try(FileInputStream fis = new FileInputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    "org/example/Homework_10/task_2/peopleJson.json"))))
        {

            Scanner scanner = new Scanner(fis);
            String[] fieldNames = scanner.nextLine().split(" ");
            ArrayList<Person> people = new ArrayList<>();
            while(scanner.hasNextLine()){
                Person person = new Person(null, null);
                String[] data = scanner.nextLine().trim().split(" ");

                    for(int i = 0; i<fieldNames.length; i++){
                        Field field = person.getClass().getDeclaredField(fieldNames[i]);
                        field.set(person, (field.getType())data[i]); // problem is here
                    }
                    people.add(person);
            }
            String jsonOfPeople = parseToJson(people);
            writer.write(jsonOfPeople);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static String parseToJson(Object obj){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
         return gson.toJson(obj);
    }
}
