package org.example.Homework_10.task_1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskOneClass {
    private static final String NUMBER_PATTERN = "([(]\\d{3}[)] |\\d{3}-)\\d{3}-\\d{4}";
    public static void findValidNumbers(String path) {
        File file = new File(path);
        try( FileInputStream fis = new FileInputStream(file) ) {
           Scanner scanner = new Scanner(fis);
           while(scanner.hasNextLine()){
               Pattern pattern = Pattern.compile(NUMBER_PATTERN);
               Matcher matcher = pattern.matcher(scanner.nextLine().trim());
               while(matcher.find()){
                   System.out.println(matcher.group());
               }
           }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


//"src/main/java/org/example/Homework_10/task_1/file_1.txt"