package com.company;

import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class StackWithPriorityQueue<T> {
    private class Node<T> implements Comparable<Node>{
        T element;
        int index;
        public Node(T element,int index){
            this.element=element;
            this.index=index;
        }

        @Override
        public int compareTo(Node node) {
            if(node.index>index)
                return 1;
            if(node.index<index)
                return -1;
            return 0;
        }
    }
    private PriorityQueue <Node<T>> priorityQueue;
    private int size;
    public StackWithPriorityQueue() {
        priorityQueue=new PriorityQueue<>();
        size=0;
    }
    public int getSize(){
        return size;
    }
    public void push(T element){
        priorityQueue.add(new Node(element,size++));
    }
    public T pop(){
        if(priorityQueue.isEmpty())
            throw new NoSuchElementException("Empty Stock");
        size--;
        return priorityQueue.poll().element;
    }
    public T peek(){
        if(priorityQueue.isEmpty())
            throw new NoSuchElementException("Empty Stock");
        return priorityQueue.peek().element;
    }
}
