package com.company;

import java.util.NoSuchElementException;

public class StackWith2Queues<T> {
    Queue <T> queue1;
    Queue<T> queue2;
    int size;
    public StackWith2Queues(){
        queue1=new Queue<>();
        queue2=new Queue<>();
        size=0;
    }
    public void push(T element){
        queue1.enqueue(element);
        size++;
    }
    public T pop(){
        if(size==0)
            throw new NoSuchElementException("Empty Stock");
        for(int i=0;i<size-1;i++){
            queue2.enqueue(queue1.dequeue());
        }
        T parameter=queue1.dequeue();
        queue1=queue2;
        queue2=new Queue<>();
        size--;
        return parameter;
    }
    public void printStack(){
        queue1.printQueue();
    }

}
