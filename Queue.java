package com.company;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Queue<T> {
    private ArrayList arrayList;
    private int size;
    public Queue(){
        arrayList = new ArrayList();
        size=0;
    }
    public void enqueue(T data){
        arrayList.add(size,data);
        size++;
    }
    public T dequeue(){
        if(size==0)
            throw new NoSuchElementException("empty");
        T temp= (T) arrayList.get(0);
        for(int i=0;i<size-1;i++){
            arrayList.set(i,arrayList.get(i+1));
        }
        size--;
        return temp;
    }
    public T peek(){
        if(size==0)
            throw new NoSuchElementException("empty");
        return (T) arrayList.get(0);
    }
    public void printQueue(){
        for(int i=0;i<size;i++)
            System.out.println(arrayList.get(i));
    }


}
