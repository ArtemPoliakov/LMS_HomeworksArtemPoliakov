package org.example.Homework_12;

import java.util.concurrent.atomic.AtomicInteger;

public class CoolMethods {

    public static void timeTrack(){
        Object lock = new Object();
        Thread oneSecondTracker = new Thread(()->{
            try {
                Integer count = 0;
                while(true) {
                    synchronized (lock) {
                        lock.wait(1000);
                        count++;
                        System.out.println(count + " seconds since launch");
                        if (count % 5 == 0) {
                            lock.notify();
                        }
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread fiveSecondsTracker = new Thread(()->{
            try {
                synchronized (lock) {
                    while (true) {
                        lock.wait();
                        System.out.println("five seconds have passed");
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        oneSecondTracker.start();
        fiveSecondsTracker.start();
    }







    public void printNumbers(int inclusiveMaximum) throws InterruptedException {
       AtomicInteger current = new AtomicInteger(1);
       StringBuilder builder = new StringBuilder();

       Thread fizz = new Thread(()->{
           synchronized (this){
           while(current.get()<=inclusiveMaximum) {
               if (current.get() % 3 != 0 || current.get() % 5 == 0) {
                   try {
                       wait();
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
                   continue;
               }
               builder.append("fizz, ");
               current.incrementAndGet();
               notifyAll();
             }
           }
       });


        Thread buzz = new Thread(()->{
            synchronized (this){
                while(current.get()<=inclusiveMaximum) {
                    if (current.get() % 5 != 0 || current.get() % 3 == 0) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        continue;
                    }
                    builder.append("buzz, ");
                    current.incrementAndGet();
                    notifyAll();
                }
            }
        });

        Thread fizzBuzz = new Thread(()->{
            synchronized (this){
                while(current.get()<=inclusiveMaximum) {
                    if (current.get() % 3 != 0 || current.get() % 5 != 0) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        continue;
                    }
                    builder.append("fizzBuzz, ");
                    current.incrementAndGet();
                    notifyAll();
                }
            }
        });

        Thread number = new Thread(()->{
            synchronized (this){
                while(current.get()<=inclusiveMaximum) {
                    if (current.get() % 3 == 0 || current.get() % 5 == 0) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        continue;
                    }
                    builder.append(current.get()+", ");
                    current.incrementAndGet();
                    notifyAll();
                }
            }
        });
        startThreads(fizzBuzz, fizz, buzz, number);
        joinThreads(fizzBuzz, fizz, buzz, number);
        System.out.println(builder.subSequence(0, builder.length()-2));
    }

    private void startThreads(Thread... threads){
        for(Thread thread: threads){
            thread.start();
        }
    }
    private void joinThreads(Thread... threads) throws InterruptedException {
        for(Thread thread: threads){
            thread.join();
        }
    }
}









