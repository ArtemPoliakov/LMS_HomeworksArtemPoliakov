package org.example.Homework_9;

import java.util.Arrays;

public class MyHashMap <K,V>{
    private static final int DEFAULT_CAPACITY = 8;
    private static final Entry[] EMPTY = new Entry[0];
    private static final double ENLARGEMENT_PERCENTAGE_BOUND = 75;
    private Entry[] entries = new Entry[DEFAULT_CAPACITY];
    private int size = 0;

    private int doHashThing(K key){
        return Math.abs(key.hashCode()%entries.length);
    }

    public int size(){
        return size;
    }
    public void clear(){
        entries = EMPTY;
        size = 0;
    }
    public void put(K key, V value){
        if(((float)size/(float)entries.length)*100>=ENLARGEMENT_PERCENTAGE_BOUND){
           resizeAndRehash();
        }
        Entry<K,V> newEntry = new Entry<>(key, value);
        int index = doHashThing(key);
        if(entries[index]==null){
            entries[index] = newEntry;
        } else{
            Entry<K,V> tempEntry = entries[index];
            while(tempEntry!=null){
                if(tempEntry.key.equals(newEntry.key)){
                    tempEntry.value = newEntry.value;
                    return;
                }
                if(tempEntry.next==null){
                    break;
                }
                tempEntry = tempEntry.next;
            }
            tempEntry.next = newEntry;
        }
        size++;
    }

    public V get(K key){
        int index = doHashThing(key);
        Entry<K,V> temp = entries[index];
        while(temp!=null){
            if(temp.key.equals(key)){
                return temp.value;
            }
            temp = temp.next;
        }
        throw new IllegalArgumentException("No such key exists");
    }
    public void remove(K key){
        int index = doHashThing(key);
        Entry<K,V> temp = entries[index];
        if (temp!=null && temp.key.equals(key)) {
            entries[index] = temp.next;
        } else {
            while (temp.next != null) {
                if(temp.next.key.equals(key)){
                    temp.next = temp.next.next;
                }
            }
        }
    }


    private void resizeAndRehash() {
        size = 0;
        Entry<K, V>[] copy = entries;
        entries = new Entry[entries.length * 2];

        for (Entry<K, V> entry : copy) {
            while (entry != null) {
                put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }

    @Override
    public String toString(){
        return Arrays.toString(entries).replaceAll("(, null(?! = ))|(?<! = )null,( ?)", "");
    }

    private static class Entry<K,V>{
        private K key;
        private V value;
        private Entry<K,V> next;
        public Entry(K key, V value){
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString(){
            if(this!=null) {
                StringBuilder result = new StringBuilder();
                Entry<K, V> temp = this;
                while(temp!=null){
                    result.append(temp.key + " = " + temp.value);
                    if(temp.next!=null){
                        result.append(", ");
                    }
                    temp = temp.next;
                }
                return result.toString();
            } else{
                return "";
            }
        }
    }
}
