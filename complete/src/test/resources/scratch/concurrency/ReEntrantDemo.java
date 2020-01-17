import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReEntrantDemo {
    public static final Lock lock = new ReentrantLock();
    public static final Condition wait = lock.newCondition();
    public static boolean flag = true;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (flag) {
                    lock.lock();
                    try {
                        wait.await(1000, TimeUnit.MILLISECONDS);
                        System.out.println("Waiting thread".equalsIgnoreCase());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        wait.signal();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
//                    Thread.sleep(1000);
                    System.out.println("Notify Thread");
                }
            }
        }).start();
    }
}