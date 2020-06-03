class Solution {

    boolean compare(int[] a1, int[] a2) {
        boolean flag=true;
        for(int i=0;i<26;i++) {
            if(a1[i]!=a2[i]) {
                flag=false;
                break;
            }
        }
        return flag;
    }
    public boolean checkInclusion(String s1, String s2) {
        if (s1.isEmpty()) return true;
        if (s2.isEmpty() || s1.length() > s2.length()) return false;

        int[] a1 = new int[26];
        int[] a2 = new int[26];
        for (char c : s1.toCharArray()) {
            a1[c - 'a'] += 1;
        }
        int l=0,r=0;
        while (r<s2.length()) {
            if(a1[s2.charAt(r)-'a']==0) {
                l=r+1;
                r=r+1;
                for(int i=0;i<26;i++) a2[i]=0;
            } else {
                if(a1[s2.charAt(r)-'a'] > a2[s2.charAt(r)-'a']) {
                    a2[s2.charAt(r)-'a']+=1;
                    r+=1;
                } else {
                    while (a1[s2.charAt(r)-'a'] == a2[s2.charAt(r)-'a']) {
                        a2[s2.charAt(l)-'a']-=1;
                        l+=1;
                    }
                    a2[s2.charAt(r)-'a']+=1;
                    r+=1;
                }
                if(compare(a1,a2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution obj=new Solution();
        System.out.println(obj.checkInclusion("a", "a"));
        System.out.println(obj.checkInclusion("d", "abc"));
        System.out.println(obj.checkInclusion("abcd", "cbabdc"));
        System.out.println(obj.checkInclusion("ab", "eidboaoo"));
        System.out.println(obj.checkInclusion("ab", "eidbaooo"));
    }
}