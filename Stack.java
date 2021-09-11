package com.company;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Stack <T> {
    private ArrayList<T> arrayList;
    private int size;
    public Stack(){
        arrayList=new ArrayList<>();
        size=0;
    }
    public void push(T data){
        arrayList.add(size,data);
        size++;
    }
    public T pop(){
        if(size==0)
            throw new NoSuchElementException("empty");
        size--;
        T temp=arrayList.get(size);
        arrayList.remove(size);
        return temp;
    }

    public int getSize() {
        return size;
    }
    public T peek(){
        return arrayList.get(size);
    }
    public void printStack(){
        for(int i=0;i<arrayList.size();i++){
            System.out.println(arrayList.get(i));
        }
    }
}
