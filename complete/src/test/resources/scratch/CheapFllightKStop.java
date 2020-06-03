import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        List<List<int[]>> adj = new ArrayList<>();
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());
        for (int[] flight : flights) {
            adj.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int[] visited = new int[n];
        queue.add(new int[]{src,0,0});
        visited[src]=1;
        int answer=-1;
        while(!queue.isEmpty()) {
            int[] u = queue.poll();
            if(u[0] == dst) {
                if(u[2] - 1 <= K && (answer==-1 || answer > u[1]) ) {
                    answer = u[1];
                }
            } else if(u[2] <= K ) {
                for (int[] ints : adj.get(u[0])) {
                    if(visited[ints[0]]==0) {
                        visited[ints[0]]=1;
                        queue.add(new int[]{ints[0], ints[1] + u[1], u[2] + 1});
                    }
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.findCheapestPrice(3, new int[][]{{0,1,100}, {1,2,100}, {0,2,500}}, 0,2, 1));
        System.out.println(obj.findCheapestPrice(3, new int[][]{{0,1,100}, {1,2,100}, {0,2,500}}, 0,2, 0));
        System.out.println(obj.findCheapestPrice(8,
                new int[][]{{0,1,1000}, {1,7,1000}, {0,2,100}, {0,3,10}, {2,4,100}, {3,5,10}, {4,7,100}, {5,6,10}, {6,7,10}}, 0,7, 3));
        System.out.println(obj.findCheapestPrice(6, new int[][]{{0,1,10}, {0,2,1}, {2,3,1}, {3,4,1}, {4,5,1},{1,4,10}}, 0,5, 2));
    }
}