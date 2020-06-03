import java.util.*;

class Solution {
    public int longestStrChain(String[] words) {
        if(words==null || words.length==0) return 0;
        Arrays.sort(words, Comparator.comparingInt(String::length));
        Map<String,Integer> map=new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i],i);
        }
        int[] result = new int[words.length];
        result[words.length-1]=1;
        for(int i=0;i<words.length;i++) {
            result[i]=1;
        }
        int max=1;
        for(int i=words.length-1;i>=0 && words[i].length()>1;i--) {
            StringBuilder sb = new StringBuilder(words[i]);
            for(int j=0;j<words[i].length();j++) {
                sb=sb.deleteCharAt(j);
                String s=sb.toString();
                if(map.containsKey(s)) {
                    int idx=map.get(s);
                    result[idx] = Math.max(result[i]+1, result[idx]);
                    max = Math.max(max, result[idx]);
                }
                sb=sb.insert(j, words[i].charAt(j));
            }
        }
        return max;
    }
}
