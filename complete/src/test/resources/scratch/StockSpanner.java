import java.util.Stack;

class StockSpanner {
    private Stack<MyPair<Integer, Integer>> stack;
    public StockSpanner() {
        stack=new Stack<>();
    }

    public int next(int price) {
        if(stack.isEmpty()) {
            stack.push(new MyPair<>(price, 1));
            return 1;
        }
        MyPair<Integer, Integer> pair = new MyPair<>(price, 1);
        while (!stack.isEmpty()) {
            MyPair<Integer, Integer> next=stack.peek();
            if(next.key<=pair.key) {
                pair.value+=next.value;
                stack.pop();
            } else break;
        }
        stack.push(pair);
        return pair.value;
    }

    public static class MyPair<K,V> {
        public K key;
        public V value;
        public MyPair(K key, V value) {
            this.key = key;this.value = value;
        }
    }

    public static void main(String[] args) {
        StockSpanner obj = new StockSpanner();
        System.out.println(obj.next(100));
        System.out.println(obj.next(80));
        System.out.println(obj.next(60));
        System.out.println(obj.next(70));
        System.out.println(obj.next(60));
        System.out.println(obj.next(75));
        System.out.println(obj.next(85));
    }
}