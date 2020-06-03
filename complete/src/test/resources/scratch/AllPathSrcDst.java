import java.util.*;

class Solution {
    public boolean detectCycle(List<Integer>[] adj, int u, int[] visited) {
        if(visited[u]==1) return false;
        visited[u]=1;
        for(int i=0; adj[u]!=null && i<adj[u].size();i++) {
            if(visited[adj[u].get(i)]!=2){
                if(!detectCycle(adj, adj[u].get(i), visited)) {
                    return false;
                }
            }
        }
        visited[u]=2;
        return true;
    }

    boolean bfs(List<Integer>[] adj, int[] visited, int destination) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(destination);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            visited[u]=1;
            for(int i=0; adj[u]!=null && i<adj[u].size();i++) {
                if(visited[adj[u].get(i)] == 0) {
                    visited[adj[u].get(i)]=1;
                    queue.add(adj[u].get(i));
                }
            }
        }
        for (int i = 0; i < visited.length; i++) {
            if(visited[i]==0) return false;
        }
        return true;
    }
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < edges.length; i++) {
            if(adj[edges[i][1]] == null) adj[edges[i][1]] = new ArrayList<>();
            adj[edges[i][1]].add(edges[i][0]);
        }
        int[] visited = new int[n];
        for (int i = 0; i < visited.length; i++) {
            if(visited[i]==0) {
                if(!detectCycle(adj, i, visited)) {
                    return false;
                }
            }
        }
        Arrays.fill(visited, 0);
        return bfs(adj, visited, destination);
    }

    public static void main(String[] args) {
        Solution o=new Solution();
        System.out.println(o.leadsToDestination(4, new int[][]{{0,1},{0,2},{1,3},{2,3},{1,2}}, 0, 3));
        System.out.println(o.leadsToDestination(4, new int[][]{{0,1},{1,2},{2,3},{3,0}}, 0, 3));
    }
}
