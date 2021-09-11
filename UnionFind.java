package com.company;

import java.util.ArrayList;

public class UnionFind {
    public class Node{
        int data;
        int headData;
        Node next;
        Node head;
        public Node(int data){
            headData=head.data;
        }
        private void setNext(Node node){
            this.next=node;
        }
    }
    public ArrayList<Node> arrayList;
    public UnionFind(){
        arrayList=new ArrayList<>();
    }
    public void MakeSet(int data){
        Node node =new Node(data);
        node.headData=data;
        node.head=node;
        arrayList.add(node);
    }
    public int findSet(Node node){
        return node.headData;
    }
    public void union(Node x,Node y){
        if(x.headData==y.headData)
            return;
        Node temp=x;
        while (temp!=null){
            temp.headData=y.headData;
            temp.head=y.head;
            temp=temp.next;
        }
    }
}
