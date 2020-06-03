import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {
    String generateString(Stack<Character> stack) {
        StringBuilder sb=new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    boolean dfs(List[] adj, int[] visited, Stack<Character> stack, int u) {
        if(visited[u]==1) return false;
        if(visited[u]==2) return true;
        visited[u]=1;
        for (int i = 0; adj[u] !=null && i < adj[u].size(); i++) {
            if(!dfs(adj, visited, stack, (int)adj[u].get(i))) return false;
        }
        visited[u]=2;
        stack.push((char) (u+'a'));
        return true;
    }

    String topoSort(List[] adj) {
        int[] visited = new int[26];
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < visited.length; i++) {
            if( adj[i]!=null && visited[i]==0) {
                if(!dfs(adj, visited, stack, i)){
                    return "";
                }
            }
        }
        return generateString(stack);
    }
    boolean computeEdge(String word1, String word2, List[] adj) {
        int i=0,j=0;
        while(i<word1.length() && j<word2.length() && word1.charAt(i)==word2.charAt(j)) {
            i++;j++;
        }
        if(j==word2.length() && i<word1.length()) return false;
        if(i==word1.length()) return true;
        adj[word1.charAt(i)-'a'].add(word2.charAt(j)-'a');
        return true;
    }
    void initAdj(List[] adj, String word) {
        for(int i=0;i<word.length();i++) {
            if(adj[word.charAt(i)-'a']==null)
                adj[word.charAt(i)-'a']=new ArrayList<>();
        }
    }
    public String alienOrder(String[] words) {
        List<Integer>[] adj=new List[26];
        initAdj(adj, words[0]);
        for (int i = 1; i < words.length; i++) {
            initAdj(adj, words[i]);
            if(!computeEdge(words[i-1], words[i], adj)) return "";
        }
        return topoSort(adj);
    }

    public static void main(String[] args) {
        Solution o=new Solution();
        System.out.println(o.alienOrder(new String[]{"ad","bd","cd","ad"}));
        System.out.println(o.alienOrder(new String[]{"ad","bd","cd"}));
        System.out.println(o.alienOrder(new String[]{ "wrt", "wrf", "er", "ett", "rftt"}));
        System.out.println(o.alienOrder(new String[]{ "z", "x"}));
        System.out.println(o.alienOrder(new String[]{ "z", "x", "z"}));
    }
}