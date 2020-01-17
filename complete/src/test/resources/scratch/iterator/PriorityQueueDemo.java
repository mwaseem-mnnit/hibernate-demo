import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PriorityQueueDemo {
    private static int ZERO = 0;
    private int[] elements;
    private int size = ZERO;

    public PriorityQueueDemo() {
        elements = new int[ZERO];
    }
    public PriorityQueueDemo(int[] elements) {
        this.elements = elements;
        this.size = elements.length;
        int s = (this.size/2) - 1;
        for( int i=s; i>=0; i--) {
            heapifyUp(i);
        }
    }
    private void swap(int i, int j) {
        int t = elements[i];
        elements[i] = elements[j];
        elements[j] = t;
    }
    private void heapifyUp(int n) {

        int i = n;
        int l = 2 * n + 1;
        int r = 2 * n + 2;
        if( (l < size) && (elements[l] < elements[i])) {
            i = l;
        }
        if( (r < size) && (elements[r] < elements[i])) {
            i = r;
        }
        if( i != n) {
            swap(i,n);
            heapifyUp(i);
        }
    }
    private void heapifyDown(int n) {
        int p = (n-1)/2;
        while (p>=0) {
            if( elements[n] < elements[p]) {
                swap(n,p);
                n = p;
            }
        }
    }

    public int insert(int element) {
        size = size + 1;
        elements = Arrays.copyOf(elements, size);
        elements[size-1] = element;
        heapifyDown(size-1);
        return element;
    }

    public int delete() {
        int r = elements[0];
        elements[0] = elements[size-1];
        size = size - 1;
        heapifyUp(0);
        elements = Arrays.copyOf(elements, size);
        return r;
    }

    public void printHeap() {
        for (int element : elements) {
            System.out.print(element+" ");
        }
    }
    public static void main(String[] args) {
        PriorityQueue<MyPair> q = new PriorityQueue<>();
        MyPair i = new MyPair(1,2);
        MyPair j = new MyPair(1,2);
        q.add(i);
        q.add(j);
        PriorityQueueDemo obj = new PriorityQueueDemo(new int[]{1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17});
        obj.printHeap();
        Scanner sc = new Scanner(System.in);
        while (true) {
            int ch = sc.nextInt();
            if( ch == 1) {
                int ele = sc.nextInt();
                obj.insert(ele);
            } else {
                obj.delete();
            }
            obj.printHeap();
        }
    }


    public static class MyPair {
        int l;
        int r;
        MyPair(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }
}