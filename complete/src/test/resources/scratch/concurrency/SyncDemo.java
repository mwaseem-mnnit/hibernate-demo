public class SyncDemo {
    final Object mutex = new Object();

    public void foo() {
        synchronized (mutex) {
            System.out.println("foo!!");
            bar();
        }
    }

    public void bar() {
        synchronized (mutex) {
            System.out.println("bar!!");
        }
    }

    public static void main(String[] args) {
        SyncDemo obj = new SyncDemo();
        obj.foo();
    }
}