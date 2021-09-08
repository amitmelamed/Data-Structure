package com.company;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class AVLTree {
    private class Node{
        int data;
        Node right;
        Node left;
        int height;
        //size is the number of childs on the specipic root
        int size;


        public Node(int data){
            this.data=data;
        }
    }
    private Node root;
    PriorityQueue<Integer> queue; //queue is implemented for bigger function
    public AVLTree(){
        root=null;
        queue=new PriorityQueue<>();

    }
    public void updateSize(){
        updateSize(root);
    }
    private int updateSize(Node root){
        if(root==null) {
            return 0;
        }
        if(root.left==null&&root.right==null){
            root.size=0;
            return 1;
        }
        if(root.left!=null&&root.right==null){
            root.size=updateSize(root.left)+1;
            return root.size;
        }
        if(root.left==null&&root.right!=null){
            root.size=updateSize(root.right)+1;
            return root.size;
        }
        if(root.left!=null&&root.right!=null){
            root.size=updateSize(root.left)+updateSize(root.right)+1;
            return root.size;
        }
        return 0;
    }
    private int max(int a,int b){
        if(a>b)
            return a;
        return b;
    }
    private int min(int a,int b){
        if(a>b)
            return b;
        return a;
    }
    public boolean contains(int number){
        return contains(number,root);
    }
    private boolean contains(int number,Node root){
        if(root==null)
            return false;
        if(root.data==number)
            return true;
        if(root.data>number&&root.left!=null)
            return contains(number,root.left);
        if(root.data<number&&root.right!=null)
            return contains(number,root.right);
        return false;
    }
    public int height(){
        return height(root);
    }
    private int height(Node root){
        if(root==null)
            return 0;
        if(root.left==null&&root.right==null)
            return 1;
        if(root.left!=null&&root.right==null)
            return height(root.left)+1;
        if(root.left==null&&root.right!=null)
            return height(root.right)+1;
        return max(height(root.right),height(root.left))+2;
    }
    public void add(int number){
        root=insert(number,root);
    }
    public Node rightRotate(Node y){
        Node x;
        x=y.left;
        Node t2=x.right;
        x.right=y;
        y.left=t2;
        y.height=height(y);
        x.height=height(x);
        return x;
    }
    public Node leftRotate(Node y){
        Node x;
        x=y.right;
        Node t2=x.left;
        x.left=y;
        y.right=t2;
        y.height=height(y);
        x.height=height(x);
        return x;
    }
    private Node insert(int key,Node root){
        //step 1:insert the new key into node
        if(root==null)
            return new Node(key);

        if(key>root.data)
            root.right=insert(key,root.right);
        else if(key<root.data)
            root.left=insert(key,root.left);
        else {
            //no duplicate key;
            return root;
        }
        // if we made here we have new node
        //step 2: update height
        root.height=height(root);
        //step 3: get balance
        int balance=getBalance(root);
        //step 4:check for unbalance in 4 ways:
        //left left case:right rotate
        if(balance>1&&key<root.left.data){
            return rightRotate(root);
        }
        //left right case: left rotate on root
        if(balance>1&&key>root.left.data){
            root.left=leftRotate(root.left);
            return rightRotate(root);

        }
        //right right case
        if(balance<-1&&key>root.right.data){
            return leftRotate(root);
        }
        //right left case
        if(balance<-1&&key<root.right.data){
            root.right=rightRotate(root.right);
            return leftRotate(root);
        }
        return root;


    }
    public void inOrder(){
        inOrder(root);
    }
    private void inOrder(Node node){
        if(node==null)
            return;
        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }
    private int getBalance(Node node){
        if(node==null){
            return 0;
        }
        return height(node.left)-height(node.right);
    }
    public int getRank(int data){
        updateSize();
        if(!contains(data))
            throw new NoSuchElementException("element not exist in tree");
        return getRank(data,root);
    }
    private int getRank(int data,Node root){
        if(root==null)
            return 0;
        if(data>root.data){
            if(root.left!=null){
                return getRank(data,root.right)+1+root.left.size;
            }
            return getRank(data,root.right)+1;
        }
        else if(data<root.data){
            return getRank(data,root.left);
        }
        else if(root.left!=null) {
            return root.left.size+1;
        }
        return 1;
    }
    public int bigger(int x){

        //write function that if x exist re turn x, if he not exist re turn the bigger number that is smaller than x
        if(contains(x))// o(log n)
            return x;
        return bigger(x,this.root);

    }
    private int bigger(int x, Node root){
        bigger2(x,root); //O(log n)
        ArrayList<Integer> arrayList=new ArrayList<>();
        while (!queue.isEmpty()) {// queue lenght will be O(log n)
            arrayList.add(queue.poll());
        }
        int temp=arrayList.get(0);
        for(int k=0;k<arrayList.size();k++){// array will be O(log n)
            if(arrayList.get(k)>temp&&arrayList.get(k)<x)
                temp=arrayList.get(k);
        }
        return temp;
    }
    private void bigger2(int x, Node root){
        if(root==null)
            return;
        queue.add(root.data);
        if(root.data>=x){
            bigger2(x,root.left);
        }
        if(root.data<=x){
            bigger2(x,root.right);
        }
    }
}
