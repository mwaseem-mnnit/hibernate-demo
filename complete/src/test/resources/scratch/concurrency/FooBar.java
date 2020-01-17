class FooBar {
    private int n;
    volatile int ctr;

    Object mutex;

    public FooBar(int n) {
        this.n = n;
        ctr=1;

        mutex = new Object();
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            synchronized(mutex)
            {
                while(ctr%2==0)
                    mutex.wait();

                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();

                ctr++;
                mutex.notifyAll();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            synchronized(mutex)
            {
                while(ctr%2==1)
                    mutex.wait();

                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();

                ctr++;
                mutex.notifyAll();
            }
        }
    }
}