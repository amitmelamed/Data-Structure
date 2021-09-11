package com.company;
//the priority queue is not a regular java priority queue.
//the queue is acting like a regular queue but when you priority enqueue element, this element will dequeue before elements that came before him.
//and will be dequeue before elements that priority queued after him.

public class PriorityQueue<T> {
    Stack<T> stack1;
    Stack<T> stack2;
    Stack<T> priorityStack;
    int size;

    public PriorityQueue(){
        stack1=new Stack<>();
        stack2=new Stack<>();
        priorityStack=new Stack<>();
        size=0;
    }

    public void enqueue(T element){
        stack1.push(element);
    }
    public void priorityEnqueue(T element){
        priorityStack.push(element);
    }
    public T dequeue(){
        if(priorityStack.getSize()!=0){
            while(priorityStack.getSize()>1){
                stack2.push(priorityStack.pop());
            }
            T temp=priorityStack.pop();
            while (stack2.getSize()!=0){
                priorityStack.push(stack2.pop());
            }
            return temp;
        }
        while (stack1.getSize()>1){
            stack2.push(stack1.pop());
        }
        T temp=stack1.pop();
        while (stack2.getSize()!=0){
            stack1.push(stack2.pop());
        }
        return temp;
    }
}
