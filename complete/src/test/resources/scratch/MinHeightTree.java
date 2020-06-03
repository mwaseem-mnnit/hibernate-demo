import java.util.ArrayList;
import java.util.List;

class MinHeightTree {
    public static int max = -1;
    public static List<Integer> result = new ArrayList<>();
    List<Integer> dfs(int u, List<List<Integer>> adj, boolean[] visited) {
        visited[u] = true;
        List<Integer> max1=new ArrayList<>();
        List<Integer> max2=new ArrayList<>();
        for(Integer v: adj.get(u)) {
            if(visited[v]) continue;
            List<Integer> child = dfs(v,adj,visited);
            if (max1.size() < child.size()) { max2=max1;max1=child;}
            else if(max2.size()<child.size()) max2 = child;
        }
        int s = max1.size() + max2.size() + 1;
        if (s > max) {
            int h = s / 2;
            result = new ArrayList<>();
            if (s % 2 == 0) {
                result.add(max1.get(h - 1));
                if (max1.size() == h + 1) result.add(max1.get(h));
                else result.add(u);
            } else {
                if (max1.size() >= h + 1) result.add(max1.get(h));
                else result.add(u);
            }
            max=s;
        }
        max1.add(u);
        return max1;
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        max=-1;
        result=new ArrayList<>();
        List<List<Integer>> adj = new ArrayList<>(n);
        boolean[] visited = new boolean[n];
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());
        for( int i=0; i<edges.length; i++) {
            adj.get(edges[i][0]).add(edges[i][1]);
            adj.get(edges[i][1]).add(edges[i][0]);
        }
        dfs(0, adj, visited);
        return result;
    }


    public static void main(String[] args) {
        MinHeightTree object = new MinHeightTree();
        List<Integer> m = object.findMinHeightTrees(8, new int[][]{{0,1}, {1,2}, {2,3}, {0,4}, {4,5}, {4,6}, {6,7}});
        System.out.println(m);
        m = object.findMinHeightTrees(5, new int[][]{{0,1}, {1,2}, {2,3}, {3,4}});
        System.out.println(m);
    }
}