
public class InterruptDemo {
    public static final Object mutex = new Object();
    public static Thread t1;
    public static Thread t2;
    public static void main(String[] args) {
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (mutex) {
                    try {
                        while (true) {
                            System.out.println("Thread 1 active");
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        System.out.println("Thread 1 exception " + e.getMessage());
                    }
                }
            }
        });
        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("Thread 2 active");
                    Thread.sleep(1000);
                    System.out.println("Thread 2 calling interrupt");
                    t1.interrupt();
                    Thread.sleep(1000);
                    System.out.println("Thread 2 exiting");
                } catch (InterruptedException e) {
                    System.out.println("Thread 2 exception " + e.getMessage());
                }
            }
        });
        t1.start();
        t2.start();
    }
}