// Java program to illustrate Constructor Chaining to
// other class using super() keyword
class Base {
    String name;
    Base() {
        this("");
        System.out.println("No-argument constructor of base class");
    }

    Base(String name) {
        this.name = name;
        System.out.println("Calling parameterized constructor of base");
    }
}

class Derived extends Base {
    Derived() {
        System.out.println("No-argument constructor of derived");
//        this("");
    }

    Derived(String name) {
        super(name);
//        this();
//        super(name);
        System.out.println("Calling parameterized constructor of derived");

    }

    public static void main(String args[]) {
        Derived obj = new Derived("test");
//        Derived obj1 = new Derived();
    }
}
