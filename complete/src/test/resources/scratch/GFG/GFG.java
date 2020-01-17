// Java Program to demonstrate methods of BlockingQueue

import java.util.concurrent.*;
import java.util.*;

public class GFG {
    public static void main(String[] args)
            throws InterruptedException
    {

        // define capacity of ArrayBlockingQueue
        int capacity = 4;

        // create object of ArrayBlockingQueue
        BlockingQueue
                queue = new BlockingQueue(capacity);

        // Add elements to ArrayBlockingQueue using enqueue(); method
        queue.enqueue("StarWars");
        queue.enqueue("SuperMan");
        queue.enqueue("Flash");
        queue.enqueue("BatMan");
        queue.enqueue("Avengers");

        // print Queue
        System.out.println("queue contains "
                + queue);

        // remove some elements
        queue.dequeue();
        queue.dequeue();

        // Add elements to ArrayBlockingQueue
        // using enqueue(); method
        queue.enqueue("CaptainAmerica");
        queue.enqueue("Thor");

        System.out.println("queue contains "
                + queue);
    }
}

class BlockingQueue {

    private List queue = new LinkedList();
    private int limit = 4;

    public BlockingQueue(int limit)
    {
        this.limit = limit;
    }

    public synchronized void enqueue(Object item)
            throws InterruptedException
    {
        while (this.queue.size() == this.limit) {
            wait();
        }
        if (this.queue.size() == 0) {
            notifyAll();
        }
        this.queue.add((String)item);
    }

    public synchronized Object dequeue()
            throws InterruptedException
    {
        while (this.queue.size() == 0) {
            wait();
        }
        if (this.queue.size() == this.limit) {
            notifyAll();
        }

        return this.queue.remove(0);
    }
}

class AddQueue implements Runnable {
    BlockingQueue queue;

    public AddQueue(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            queue.enqueue("StarWars");
            queue.enqueue("SuperMan");
            queue.enqueue("Flash");
            queue.enqueue("BatMan");
            queue.enqueue("Avengers");
            System.out.println("t1");
            queue.enqueue("Avengers");
            System.out.println("t1");
            // print Queue
            System.out.println(" add queue contains "
                    + queue);
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
    public void run() {
        try {
            // remove some elements
            queue.dequeue();
            System.out.println("t2");
            wait(1000);
            queue.dequeue();
            System.out.println("t2");
            // print Queue
            System.out.println("delete queue contains "
                    + queue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}