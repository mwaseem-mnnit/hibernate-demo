// Java Program to demonstrate methods of BlockingQueue

import java.util.concurrent.*;
import java.util.*;

public class GFG1 {
    public static void main(String[] args)
            throws InterruptedException {

        // define capacity of ArrayBlockingQueue
        int capacity = 4;

        // create object of ArrayBlockingQueue
        BlockingQueue queue = new BlockingQueue(capacity);
        AddQueue add = new AddQueue(queue);
        DeleteQueue deleteQueue = new DeleteQueue(queue);
        Thread t1 = new Thread(add);
        Thread t2 = new Thread(deleteQueue);
        t1.start();
        t2.start();
    }
}

class BlockingQueue {

    private List queue = new LinkedList();
    private int limit = 10;
    volatile boolean flag = true;

    public BlockingQueue(int limit) {
        this.limit = limit;
    }

    void enqueue(Object item)
            throws InterruptedException {
        synchronized (this) {
            while (queue.size() == this.limit) {
                wait();
            }
            queue.add(item);
            System.out.println(" Add queue contains " + queue);
            if( flag) {
                notify();
            }
        }
    }

    Object dequeue()
            throws InterruptedException {
        synchronized (this) {
            while (this.queue.size() == 0) {
                wait();
            }
            flag = false;
            wait(2000);
            flag = true;
            String ret = (String) this.queue.remove(0);
            System.out.println(" Delete queue contains " + queue);
            notify();
            return ret;
        }
    }

    @Override
    public String toString() {
        return "BlockingQueue{" +
                "queue=" + queue +
                ", limit=" + limit +
                '}';
    }
}

class AddQueue implements Runnable {
    BlockingQueue queue;

    public AddQueue(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public synchronized void run() {
        try {
            while (true) {
                Scanner s =  new Scanner(System.in);
                queue.enqueue(s.nextLine());
                wait(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class DeleteQueue implements Runnable {
    BlockingQueue queue;

    public DeleteQueue(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public synchronized void run() {
        try {
            while (true) {
                queue.dequeue();
                wait(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}