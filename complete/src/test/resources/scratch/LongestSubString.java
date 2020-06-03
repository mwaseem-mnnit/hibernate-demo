import java.util.HashMap;
import java.util.Map;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length()==0) return 0;
        Map<Character,Integer> map = new HashMap<>();
        int l=0,r=1,max=1,t=1;
        map.put(s.charAt(l),l);
        while(r<s.length()) {
            if(map.containsKey(s.charAt(r))) {
                int nextL = map.get(s.charAt(r));
                for(int i=l;i<=nextL;i++) map.remove(s.charAt(i));
                l=nextL+1;
                map.put(s.charAt(r),r);
                r+=1;
                t=(r-l);
                max = Math.max(max,t);
            } else {
                t+=1;
                max = Math.max(max,t);
                map.put(s.charAt(r),r);
                r=r+1;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.lengthOfLongestSubstring("pwwkew"));
        System.out.println(obj.lengthOfLongestSubstring("aa"));
        System.out.println(obj.lengthOfLongestSubstring("a"));
        System.out.println(obj.lengthOfLongestSubstring("pabcdapabcdefgh"));
        System.out.println(obj.lengthOfLongestSubstring("pwwkwwpwwkwwppkwp"));
        System.out.println(obj.lengthOfLongestSubstring(" "));
    }
}