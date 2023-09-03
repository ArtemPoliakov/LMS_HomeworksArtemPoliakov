package org.example.Homework_9;

import java.io.Serializable;
import java.util.*;

public class MyArrayList <E> implements RandomAccess, Cloneable, Serializable{
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_VALUE = {};
    private Object[] data;
    private int dataSize = 0;

   public MyArrayList(){
       data = new Object[DEFAULT_CAPACITY];
    }
    /**
     *  Typing in a negative capacity will result in initialising the MyArrayList object with capacity ZERO
     */
    public MyArrayList(int notNegativeCapacity){
        if(notNegativeCapacity <=0 ) {
            data = EMPTY_VALUE;
        } else{
            data = new Object[notNegativeCapacity];
        }
    }
    public void clear(){
       data = EMPTY_VALUE;
       dataSize = 0;
    }
    public int size(){
       return dataSize;
    }
    public E get(int index){
       if(index>=0&&index<dataSize){
           return (E)data[index];
       } else{
           throw new ArrayIndexOutOfBoundsException("ArrayList index "+index+" is out of range for capacity "+dataSize);
       }
    }
    public void add(E value){
       if(dataSize==data.length){
           data = Arrays.copyOf(data, dataSize+1);
       }
        data[dataSize] = value;
        dataSize++;
    }

       public void add(E value, int index){
           if(index>dataSize) {
               throw new ArrayIndexOutOfBoundsException("ArrayList index " + index + " is out of range for capacity " + dataSize);
           }
           data = Arrays.copyOf(data, dataSize+1);
           dataSize++;
           System.arraycopy(data, index, data, index+1, dataSize-index-1);
           data[index] = value;
       }
       public void remove(int index){
           if(index>=dataSize) {
               throw new ArrayIndexOutOfBoundsException("ArrayList index " + index + " is out of range for capacity " + dataSize);
           }
           dataSize--;
           System.arraycopy(data, index+1, data, index, dataSize-index-1);
       }

       @Override
        public String toString(){
         return Arrays.toString(Arrays.copyOf(data, dataSize));
       }

    }