import java.util.Scanner;
import java.util.concurrent.ExecutorService;

public class H2O {

    public static int hydrogen = 0;
    public static int oxygen = 0;

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
//        releaseHydrogen.run();
        synchronized (this) {
            while( hydrogen > 1 )
                wait();
            hydrogen += 1;
            System.out.print("H");
            if( hydrogen  == 2 && oxygen == 1) {
                hydrogen = 0;
                oxygen = 0;

                notifyAll();
            }
        }

    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        // releaseOxygen.run() outputs "O". Do not change or remove this line.
//        releaseOxygen.run();
        synchronized (this) {
            while( oxygen > 0 )
                wait();
            oxygen += 1;
            System.out.print("O");
            if( hydrogen  == 2 && oxygen == 1) {
                hydrogen = 0;
                oxygen = 0;

                notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        H2O h2o = new H2O();
        Scanner sc = new Scanner(System.in);
//        while(true) {
            String s = sc.nextLine();
        ExecutorService executorService =
            for( int i=0; i < s.length(); i++) {
                if( s.charAt(i) == 'H') {
                    new Thread(new Hydrogen(h2o)).start();
                } else if( s.charAt(i) == 'O') {
                    new Thread(new Oxygen(h2o)).start();
                }
            }
//        }
    }
}
class Hydrogen implements  Runnable {
    private H2O h2o;
    private int molecule;
    Hydrogen(H2O h2o) {
        this.h2o = h2o;
    }
    @Override
    public void run() {
        try {
            h2o.hydrogen(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Oxygen implements  Runnable {
    private H2O h2o;
    Oxygen(H2O h2o) {
        this.h2o = h2o;
    }
    @Override
    public void run() {
        try {
            h2o.oxygen(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}