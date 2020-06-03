import java.util.*;

public class Solution1 {

    public static void main(String[] args) {
        int stop = minRefuelStops(100, 10, new int[][]{{10,60}, {20,30}, {30,30}, {60,40}});
        System.out.println(stop);
    }
    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        int tank = startFuel;
        int ans = 0;
        int prevStop=0, diff;
        Queue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int[] station : stations) {
            boolean flag = true;
            diff = station[0] - prevStop;
            if(diff == tank) {
                pq.add(station[1]);
                flag = false;
            }
            while( tank <= diff && !pq.isEmpty()) {
                int fuel = pq.poll();
                tank += fuel;
                ans+=1;
            }
            tank-=diff;
            if( tank < 0) return -1;
            prevStop = station[0];
            if( flag) pq.add(station[1]);
        }
        diff = target-prevStop;
        while( tank < diff && !pq.isEmpty()) {
            int fuel = pq.poll();
            tank += fuel;
            ans+=1;
        }
        tank-=diff;
        if( tank < 0) {
            return -1;
        }
        return ans;
    }

    Function<>
    public static void main1(String[] args) {

        Node node = new Node(1, Arrays.asList(new Node(2),
                new Node(3,  Arrays.asList(new Node(4), new Node(5)) )));

        Node node1 = new Node(1);
        Node copy = cloneGraph(node);
        System.out.println(copy);
        copy = cloneGraph(node1);
        System.out.println(copy);
        copy = cloneGraph(null);
        System.out.println(copy);
    }
    public static Node cloneGraph(Node node) {
        Map<Integer,Node> memory = new HashMap<>();
        return doSomething(node, memory);
    }

    public static Node doSomething(Node node, Map<Integer, Node> memory) {
        if( node == null ) {
            return null;
        }
        if( memory.containsKey(node.val)) {
            return memory.get(node.val);
        }
        Node copy = new Node();
        copy.val = node.val;
        memory.put(copy.val, copy);
        if(node.neighbors != null && node.neighbors.size() > 0) {
            for(Node neighbor: node.neighbors) {
                copy.neighbors.add(doSomething(neighbor, memory));
            }
        }
        return copy;
    }

    public static class Node {
        public int val;
        public List<Node> neighbors;

        public void test() {
            cloneGraph(null);
        }

        public static void testStatic() {
            cloneGraph(null);
        }

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
