public class FizzBuzz {
    private int n;
    private int i;
    private Object mutex;

    public FizzBuzz(int n) {
        this.n = n;
        this.i = 1;
        this.mutex = new Object();
    }

    // printFizz.run() outputs "fizz".
    public void fizz()  {
        while(i<=n) {

            synchronized(mutex) {
                try {
                    while( i<=n && i%3 != 0 )
                        mutex.wait(100);
                    if( i > n) return;
                    if( i%5 != 0) {
                        System.out.print("Fizz ");
                        i+=1;
                        mutex.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz()  {
        while(i<=n) {

            synchronized(mutex) {
                try {
                    while( i<=n && i%5 != 0 )
                        mutex.wait(100);
                    if( i > n) return;
                    if( i%3 != 0) {
                        System.out.print("Buzz ");
                        i+=1;
                        mutex.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz()  {
        while(i<=n) {

            synchronized(mutex) {
                try {
                    while( i<=n && (i%3 != 0 || i%5 != 0 ))
                        mutex.wait(100);
                    if( i > n) return;
                    System.out.print("FizzBuzz ");
                    i+=1;
                    mutex.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number()  {
        while(i<=n) {

            synchronized(mutex) {
                try {
                    while( i<=n && (i%3 == 0 || i%5 == 0 ))
                        mutex.wait(100);
                    if( i > n) return;
                    System.out.print(i+" ");
                    i+=1;
                    mutex.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        try {
            new Thread(fizzBuzz::number).start();
            new Thread(fizzBuzz::fizz).start();
            new Thread(fizzBuzz::buzz).start();
            new Thread(fizzBuzz::fizzbuzz).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}