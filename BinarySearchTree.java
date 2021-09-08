package com.company;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class BinarySearchTree {
    private class Node {
        int data;
        Node right;
        Node left;
        int size;

        public Node(Integer data) {
            this.data = data;
        }
    }
    Node root;
    public BinarySearchTree(org.w3c.dom.Node root){
        root=root;
    }
    public BinarySearchTree(){
        root=null;
    }
    public void add(int data){
        Node leaf=new Node(data);
        add(leaf,root);
    }
    private void add(Node leaf, Node root){
        if(this.root==null){
            this.root=leaf;
            return;
        }
        if(leaf.data>= root.data){
            if(root.right==null){
                root.right=leaf;
            }else {
                add(leaf, root.right);
            }
        }else {
            if(root.left==null){
                root.left=leaf;
            }else {
                add(leaf, root.left);
            }
        }
    }
    public void remove(int number){
        this.root=remove(number,this.root);
    }
    private Node remove(int number, Node root){
        if(root==null)
            return root;
        if(root.data>number){
            root.left=remove(number,root.left);
        }
        else if(root.data<number){
            root.right=remove(number, root.right);
        }
        //if we made it to here then root.data==number
        //case 1: leaf or only 1 child
        else if(root.left==null)
            return root.right;
        else if(root.right==null)
            return root.left;
        else {
            //case 2: 2 children
            root.data = minValue(root.right);
            root.right = remove(root.data, root.right);

        }
        return root;

    }
    public void preOrder(){
        preOrder(root);
    }
    private void preOrder(Node node){
        if(node==null)
            return;
        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }
    public void postOrder(){
        postOrder(root);
    }
    private void postOrder(Node node){
        if(node==null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.data);
    }
    private int minValue(Node node){
        int minVal=node.data;
        while (node.left!=null){
            minVal=node.left.data;
            node=node.left;
        }
        return minVal;
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
    public void setSize(){
        setSize(root);
    }
    private int setSize(Node root){
        if(root==null) {
            root.size = 0;
            return 0;
        }
        if(root.right==null&&root.left==null){
            root.size=0;
            return 0;
        }
        if(root.right!=null&&root.left==null){
            root.size=setSize(root.right)+1;
            return root.size;
        }
        if(root.right==null&&root.left!=null){
            root.size=setSize(root.left)+1;
            return root.size;
        }
        if(root.right!=null&&root.left!=null){
            root.size=setSize(root.left)+setSize(root.right)+2;
            return root.size;
        }
        return 0;

    }
    public boolean contains(int number){
        return contains(number,root);
    }
    private boolean contains(int number, Node root){
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
        return max(height(root.right),height(root.left))+1;
    }
    private int max(int a, int b) {
        if(a>b)
            return a;
        return b;
    }
    public boolean checkIfBalance(){
        return checkIfBalance(root);
    }
    private boolean checkIfBalance(Node root){
        if(root==null){
            return true;
        }
        if(Math.abs(height(root.left)-height(root.right))<=1){
            return (checkIfBalance(root.left)&&checkIfBalance(root.right));
        }
        else return false;
    }
    public int LCA(int k1,int k2){
        //function that recive 2 different numbers, and if they both in the BST the function returns they least common ancestor
        //works only with trees with no duplicate data
        if(!contains(k1)||!contains(k2))
            throw new NoSuchElementException("input does not exist in the tree");
        if(k1==k2)
            return k1;
        return LCA(root,k1,k2).data;
    }
    private Node LCA(Node root,int k1,int k2){
        if(contains(k1,root)&&contains(k2,root)){
            if(contains(k1,root.left)&&contains(k2,root.left))
                return LCA(root.left,k1,k2);
            if(contains(k1,root.right)&&contains(k2,root.right))
                return LCA(root.right,k1,k2);
            return root;

        }
        return null;
    }
}
