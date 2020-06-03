import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    public List<Integer> findAnagrams(String s, String p) {
        int[] pChar = new int[26];
        int[] temp = new int[26];
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<p.length();i++) {
            pChar[p.charAt(i)-'a'] += 1;
            temp[p.charAt(i)-'a'] += 1;
        }
        int l=0,r=0,length=p.length();
        while(r<s.length()) {
            if(temp[s.charAt(r)-'a']>0) {
                temp[s.charAt(r)-'a']-=1;
                length-=1;
                if(length==0) {
                    result.add(l);
                    length+=1;
                    while(l<s.length() && pChar[s.charAt(l)-'a']==0) l+=1;
                    temp[s.charAt(l)-'a']+=1;
                    l+=1;
                }
            }
            r+=1;
        }
        return result;
    }

    public static void main(String[] args) {
        Integer num3 = 100;
        Integer num4 = 100;
        String s = new String("aa");
        String s1 = new String("aa");
        String s2 = "aa";
        String s3 = "aa";
        if(s2==s3){
            System.out.println("num1 == num2");
        }
        else{
            System.out.println("num1 != num2");
        }
        Solution obj=new Solution();
        System.out.println(obj.findAnagrams("cbaebabacd","abc"));
        System.out.println(obj.findAnagrams("abab","ab"));
    }
}