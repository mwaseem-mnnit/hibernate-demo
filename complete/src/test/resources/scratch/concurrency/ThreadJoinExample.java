public class ThreadJoinExample {

    public static void main(String[] args) {
        System.out.println("starting thread...");

        Thread t1 = new Thread(new MyRunnable(Thread.currentThread(), "First"));
        Thread t2 = new Thread(new MyRunnable(Thread.currentThread(), "Second"));
        t1.start();
        t2.start();
//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        int i=0;
        while (i < 10 ) {
            try {
                Thread.sleep(500L);
                System.out.println("main");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
        System.out.println("finishing main thread...");
    }
    public static class MyRunnable implements Runnable {
        Thread t;
        String name;
        public MyRunnable(Thread t, String name) {
            this.t = t;
            this.name = name;
        }
        @Override
        public void run() {
            System.out.println("Starting thread: "+name);
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Ending thread: "+name);
        }
    }
}