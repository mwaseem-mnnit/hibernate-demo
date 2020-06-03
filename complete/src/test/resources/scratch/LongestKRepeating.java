class Solution {
    public int recursion(String s, int k, int l, int r) {
        if(l>r) return -1;
        int[] f = new int[26];
        for(int i=l;i<=r;i++) f[s.charAt(i)-'a']+=1;
        int max=r-l+1;
        for(int i=0;i<26;i++) {
            if(f[i]>0 && f[i]<k) {
                max=-1;break;
            }
        }
        if(max!=-1) return max;
        int st=0,e=0;
        while (e<=r) {
            if(f[s.charAt(e)-'a']>0 && f[s.charAt(e)-'a']<k) {
                int t = recursion(s, k, st, e-1);
                if(t>max) max=t;
                st=e+1;e=e+1;
            } else e+=1;
        }
        int t = recursion(s, k, st, e-1);
        if(t>max) max=t;
        return max;
    }
    public int longestSubstring(String s, int k) {
        int result = recursion(s,k,0,s.length()-1);
        if(result==-1) return 0;
        return result;
    }

    public static void main(String[] args) {
        Solution obj=new Solution();
        System.out.println(obj.longestSubstring("aabcbbcc", 3));
        System.out.println(obj.longestSubstring("aabcabbcc", 3));
        System.out.println(obj.longestSubstring("aabcabbbccc", 4));
        System.out.println(obj.longestSubstring("aaabb", 3));
        System.out.println(obj.longestSubstring("ababbc", 2));
        System.out.println(obj.longestSubstring("aabaacaadaaa", 2));
    }
}