import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    boolean count(String a, String b) {
        int c=0;
        for(int i=0;i<a.length();i++) {
            if(a.charAt(i) != b.charAt(i)) {
                c+=1;
            }
            if(c>1) return false;
        }
        return true;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int answer = 0;
        boolean[] visited=new boolean[wordList.size()];
        for(int i=0;i<wordList.size();i++) visited[i]=false;
        PriorityQueue<Mp> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.b));
        for(int i=0;i<wordList.size();i++) {
            if(wordList.get(i).equals(endWord)) {
                queue.add(new Mp(endWord, 1));
                visited[i]=true;
            }
        }
        while (!queue.isEmpty()) {
            Mp mp=queue.poll();
            if(count(mp.a, beginWord) && (answer == 0 || answer>mp.b )) {
                answer=mp.b+1;
            }
            for(int i=0;i<wordList.size();i++) {
                if(!visited[i] && count(wordList.get(i), mp.a)) {
                    visited[i]=true;
                    queue.add(new Mp(wordList.get(i), mp.b+1));
                }
            }
        }
        return answer;
    }

    public static class Mp {
        String a;
        int b;
        public Mp(String a, int b) {
            this.a = a;this.b = b;
        }
    }
}