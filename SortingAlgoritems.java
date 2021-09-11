package com.company;

import java.util.NoSuchElementException;

public class SortingAlgoritems {
    //usefull functions for sorting and printing
    private static void swap(int [] arr,int indexA,int indexB){
        if(arr.length<indexA||arr.length<indexB)
            throw new NoSuchElementException("out of bounds");
        int temp=arr[indexA];
        arr[indexA]=arr[indexB];
        arr[indexB]=temp;
    }
    public static void printArr(int [] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }
    //quick sort functions
    /*
    quick sort runnings times:
    worst case: O(n^2)
    best case: O(n*log n)
    */
    public static void quickSort(int [] arr){
        quickSort(arr,0,arr.length-1);
    }
    private static void quickSort(int[] arr,int start,int end){
        if(start<end) {

            int partitionIndex = partition(arr, start, end);
            quickSort(arr, start, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }
    private static int partition(int [] arr,int start,int end){
        int pivot=arr[end];
        int i=start-1;  // Index of smaller element and indicates the right position of pivot found so far
        for(int k=start;k<=end-1;k++){
            if(arr[k]<pivot){
                i++;
                swap(arr,i,k);
            }
        }
        swap(arr,i+1,end);
        return i+1;
    }

    //merge sort algoritem
    //complexity θ(n*Log n)
    
    public static void mergeSort(int [] arr){
        mergeSort(arr,0,arr.length-1);
    }
    private static void mergeSort(int [] arr,int l,int r){
        if(l<r){
            int m=l+(r-l)/2;
            mergeSort(arr,l,m);
            mergeSort(arr,m+1,r);
            merge(arr,l,m,r);
        }
    }
    private static void merge(int [] arr,int l,int m,int r){
        int lengthA=m - l + 1;
        int lengthB=r - m;
        int [] arr1=new int[lengthA];
        int [] arr2=new int[lengthB];
        for(int k=l;k<=m;k++)
            arr1[k-l]=arr[k];
        for(int k=m+1;k<=r;k++)
            arr2[k-m-1]=arr[k];
        int i=0;
        int j= 0;
        int k = l;
        while (i < lengthA && j < lengthB) {
            if (arr1[i] <= arr2[j]) {
                arr[k] = arr1[i];
                i++;
            }
            else {
                arr[k] = arr2[j];
                j++;
            }
            k++;
        }
        while (i < lengthA) {
            arr[k] = arr1[i];
            i++;
            k++;
        }
        while (j < lengthB) {
            arr[k] = arr2[j];
            j++;
            k++;
        }
    }
    //dual pivot quicksort is a quicksort algorithm that is using 2 pivots to divide the array instead of one
    //complexity is a little better than quicksort because log base is 3 and not 2
    // worst case: O(n^2)
    //    best case: O(n*log_3(n))
    public static void dualPivotQuickSort(int [] arr){
        dualPivotQuickSort(arr,0,arr.length-1);
    }
    private static void dualPivotQuickSort(int [] arr,int start,int end){
        if(start<end){
            int partitionIndex1=partition(arr,start,end);
            int partitionIndex2=partition(arr,partitionIndex1+1,end);
            dualPivotQuickSort(arr,start,partitionIndex1-1);
            dualPivotQuickSort(arr,partitionIndex1+1,partitionIndex2-1);
            dualPivotQuickSort(arr,partitionIndex2+1,end);
        }
    }
    public static void countingSort(int [] arr){
        //complexity O(n+k) n=arr.length k=max-min
        //if k>n^2 dont use this sort.
        if(arr.length==0)
            throw new NoSuchElementException("empty arr");
        int min=arr[0];
        int max=arr[0];
        for(int i=0;i<arr.length;i++){ //O(n)
            if(arr[i]>max)
                max=arr[i];
            if(arr[i]<min)
                min=arr[i];
        }
        int [] countingArr=new int[max-min+1];
        for(int i=0;i<countingArr.length;i++){ // O(n+k)
            countingArr[i]=0;
        }
        for(int i=0;i<arr.length;i++){ //O(n)
            countingArr[arr[i]-min]++;
        }
        int index=0;
        for(int i=0;i<countingArr.length;i++){// two loops but complexity is still O(n+k) because we have exactly n points on countingArray
            while (countingArr[i]>0){
                arr[index]=i+min;
                countingArr[i]--;
                index++;
            }
        }
    }

    private static void minHeapify(int [] arr,int index){
        if(index>arr.length/2-1)
            return;
        int left=arr[2*index];
        int right=arr[2*index+1];
        if(arr[index]>left||arr[index]>right){
            if(left>right){
                swap(arr,index,index*2+1);
                minHeapify(arr,index*2+1);
            }
            else {
                swap(arr,index,index*2);
                minHeapify(arr,index*2);
            }
        }
    }
    public static int [] buildMinHeap(int [] arr){
        int [] newArr=new int[arr.length+1];
        newArr[0]=-1;
        for(int i=1;i<arr.length+1;i++){
            newArr[i]=arr[i-1];
        }
        for(int i=newArr.length/2-1;i>=1;i--){
            minHeapify(newArr,i);
        }
        return newArr;
    }
    public static boolean isMinHeap(int [] arr){
        //check is an array is minHeap in O(n) complexity
        for(int i=1;i<(arr.length-1)/2;i++){
            if(arr[i]>arr[2*i+1]||arr[i]>arr[2*i])
                return false;
        }
        return true;
    }
}
