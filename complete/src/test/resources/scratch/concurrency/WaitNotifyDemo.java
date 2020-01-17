public class WaitNotifyDemo {
    public static final Object mutex = new Object();
    public static boolean flag = true;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (mutex) {
                    while (flag) {
                        try {
                            mutex.wait(1000);
                            System.out.println("Waiting thread");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        synchronized (mutex) {
                            mutex.notify();
                        }
                        Thread.sleep(1000);
                        System.out.println("Notify Thread");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}