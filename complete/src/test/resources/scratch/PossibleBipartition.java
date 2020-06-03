import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    boolean assignGroup(List<List<Integer>> list, int[] visited, int u, int val) {
        visited[u] = val;
        for (Integer v : list.get(u)) {
            if(visited[v] == 0) {
                if(!assignGroup(list, visited, v, -1*val)) return false;
            } else {
                if(visited[v] != -1*val) return false;
            }
        }
        return true;
    }

    public boolean possibleBipartition(int N, int[][] dislikes) {
        List<List<Integer>> list = new ArrayList<>(N);
        for(int i=0;i<N;i++) list.add(new ArrayList<>(100));
        for (int[] dislike : dislikes) {
            list.get(dislike[0]-1).add(dislike[1]-1);
            list.get(dislike[1]-1).add(dislike[0]-1);
        }
        int[] visited = new int[N];
        for (int i=0;i<N;i++) {
            if(visited[i]==0) {
                if(!assignGroup(list, visited, i, -1)) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.possibleBipartition(10, new int[][]{{4,7},{4,8},{2,8},{8,9},{1,6},{5,8},{1,2},{6,7},{3,10},{8,10},{1,5},{7,10},{1,10},{3,5},{3,6},{1,4},{3,9},{2,3},{1,9},{7,9},{2,7},{6,8},{5,7},{3,4}}));
    }
}