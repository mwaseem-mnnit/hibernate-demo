import java.util.*;

class SumOfEdgeAll {

    public static Map<Integer, int[]> subTreeTotal;
    public static int[] result;
    public static int[] findSumOfSubtree(List<List<Integer>> adj, int u, boolean[] visited) {
        visited[u] = true;
        int[] result = new int[2];
        result[0]=1;
        for (Integer integer : adj.get(u)) {
            if( visited[integer]) continue;
            int[] child = findSumOfSubtree(adj, integer, visited);
            result[0] += child[0];
            result[1] += child[1] + child[0];
        }
        subTreeTotal.put(u,result);
        return result;
    }

    public static void dfs(List<List<Integer>> adj, int u, boolean[] visited) {
        visited[u]=true;
        for(int v: adj.get(u)) {
            if(visited[v]) continue;
            int ans = subTreeTotal.get(v)[1];
            ans+= result[u] + subTreeTotal.get(u)[0] - 2*subTreeTotal.get(v)[0] - ans;
            result[v] = ans;
            subTreeTotal.get(v)[0] = subTreeTotal.get(u)[0];
            dfs(adj, v, visited);
        }
    }

    public static int[] sumOfDistancesInTree(int N, int[][] edges) {
        subTreeTotal = new HashMap<>();
        List<List<Integer>> adj = new ArrayList<>(N);
        boolean[] visited = new boolean[N];
        for(int i=0;i<N;i++) adj.add(new ArrayList<>());
        for(int[] edge: edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        findSumOfSubtree(adj, 0, visited);
        visited = new boolean[N];
        result = new int[N];
        result[0] = subTreeTotal.get(0)[1];
        dfs(adj, 0, visited);
        return result;
    }

    public static void main(String[] args) {
        int[] result = sumOfDistancesInTree(6,new int[][] {{0,1}, {0,2}, {2,3}, {2,4}, {2,5}});
        Arrays.stream(result).forEach(n-> System.out.print(n+" "));
        System.out.println();
        result = sumOfDistancesInTree(6,new int[][] {{0,1}, {1,2}, {2,3}, {3,4}, {4,5}});
        Arrays.stream(result).forEach(n-> System.out.print(n+" "));
        System.out.println();
        result = sumOfDistancesInTree(2,new int[][] {{0,1}});
        Arrays.stream(result).forEach(n-> System.out.print(n+" "));
        System.out.println();
        result = sumOfDistancesInTree(1,new int[][] {});
        Arrays.stream(result).forEach(n-> System.out.print(n+" "));
        System.out.println();
    }
}