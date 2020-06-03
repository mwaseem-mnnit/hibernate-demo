import java.util.ArrayList;
import java.util.List;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.canFinish(2, new int[][]{{1,0}}));
        System.out.println(solution.canFinish(2, new int[][]{{0,0}}));
        System.out.println(solution.canFinish(2, new int[][]{{1,0}, {0,1}}));
        System.out.println(solution.canFinish(5, new int[][]{ {0,1}, {0,2}, {1,3}, {2,3}, {2,4}, {3,4}}));
        System.out.println(solution.canFinish(5, new int[][]{ {0,1}, {0,2}, {1,2}, {3,1}, {2,3}, {2,4}, {3,4}}));
    }

    boolean dfs(int u, List<List<Integer>> adj, int[] visited) {
        if(visited[u] == 1) return false;
        if(visited[u] == 2) return true;
        visited[u] = 1;
        for(int v: adj.get(u) ) {
            boolean passed = dfs(v, adj, visited);
            if( !passed) return passed;
        }
        visited[u]=2;
        return true;
    }

    boolean topoSort(List<List<Integer>> adj, int numCourses) {
        int[] visited = new int[numCourses];
        for(int i=0; i<visited.length;i++) {
            if(visited[i] == 0) {
                boolean passed = dfs(i, adj, visited);
                if(!passed) return passed;
            }
        }
        return true;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>(numCourses);
        for(int i=0;i<numCourses;i++) adj.add(new ArrayList<>());
        for( int i=0; i<prerequisites.length; i++) {
            adj.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }
        return topoSort(adj, numCourses);
    }
}