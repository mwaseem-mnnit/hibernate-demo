class ZeroEvenOdd {
    private int n;
    final Object mutex;
    int flag;
    int i;

    public ZeroEvenOdd(int n) {
        this.n = n;
        mutex = new Object();
        flag = 0;
        i = 1;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for( ; i<=n; ) {
            synchronized (mutex) {
                while( flag != 0)
                    mutex.wait();
                if( i<=n)
                    printNumber.accept(0);
                if( i%2 == 0) {
                    flag = 2;
                } else {
                    flag = 1;
                }
                mutex.notifyAll();
            }
        }
        return;
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for( ; i<=n; ) {
            synchronized (mutex) {
                while( flag != 2)
                    mutex.wait();
                if( i>n) return;
                printNumber.accept(i);
                flag = 0;
                i++;
                mutex.notifyAll();
            }
        }
        return;
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for( ; i<=n; ) {
            synchronized (mutex) {
                while( flag != 1)
                    mutex.wait();
                if( i>n) return;
                printNumber.accept(i);
                flag = 0;
                i++;
                mutex.notifyAll();
            }
        }
        return;
    }

    public static void main(String[] args) {
        ZeroEvenOdd obj = new ZeroEvenOdd(20);
        IntConsumer c = new IntConsumer();
        Thread t0 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    obj.zero(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    obj.odd(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    obj.even(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
        t1.start();
        t0.start();
    }

    public static class IntConsumer {
        public void accept(Integer num) {
            System.out.print(num);
        }
    }
}

