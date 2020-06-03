public class MultipleSyncStaticMethod {
    private static final Object mutex1=new Object();
    private static final Object mutex2=new Object();
    public static synchronized void fun1()  {
        while(true) {
            System.out.println("inside fun1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized void fun2() {
        while(true) {
            System.out.println("inside fun2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void fun3()  {
        synchronized (mutex1) {
            while(true) {
                System.out.println("inside fun3");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void fun4() {
        synchronized (mutex2) {
            while(true) {
                System.out.println("inside fun4");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            MultipleSyncStaticMethod o1=new MultipleSyncStaticMethod();
            MultipleSyncStaticMethod o2=new MultipleSyncStaticMethod();
            Thread t2 = new Thread(() -> o1.fun2());
            Thread t1 = new Thread(() -> o2.fun1());
            Thread t3 = new Thread(() -> o1.fun3());
            Thread t4 = new Thread(() -> o2.fun4());
            t1.start();
            t2.start();
            t3.start();
            t4.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}