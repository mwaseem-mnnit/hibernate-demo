import java.util.stream.IntStream;

class Solution {
    public String repeat(String s, int n) {
        if(s==null || s.isEmpty()) return s;
        StringBuilder builder = new StringBuilder(s);
        IntStream.range(0,n-1).forEach(i->builder.append(s));
        return builder.toString();
    }
    public int[] getNumber(String s, int idx) {
        int num=0,d=0;
        while(idx < s.length() && Character.isDigit(s.charAt(idx))) {
            d+=1; num = num*10 + s.charAt(idx) - '0';
            idx+=1;
        }
        return new int[]{num,d};
    }
    Object[] createString(String str, int idx) {
        StringBuilder builder = new StringBuilder();
        int[] num = getNumber(str, idx);
        idx+=num[1]+1;
        while(idx<str.length() && str.charAt(idx) != ']') {
            if(Character.isDigit(str.charAt(idx))) {
                Object[] obj = createString(str,idx);
                builder.append(obj[0].toString());
                idx=Integer.parseInt(obj[1].toString())+1;
            } else {
                builder.append(str.charAt(idx));
                idx+=1;
            }
        }
        return new Object[]{repeat(builder.toString(), num[0]), idx};
    }
    public String decodeString(String s) {
        StringBuilder str = new StringBuilder();
        int i=0;
        while( i<s.length()) {
            if(Character.isDigit(s.charAt(i))) {
                Object[] obj = createString(s, i);
                str.append(obj[0].toString());
                i=Integer.parseInt(obj[1].toString())+1;
            } else {
                str.append(s.charAt(i));
                i+=1;
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.decodeString("a"));
        System.out.println(obj.decodeString("aa2[a]"));
        System.out.println(obj.decodeString("3[a]2[bc]"));
        System.out.println(obj.decodeString("3[a2[c]]"));
        System.out.println(obj.decodeString("2[abc]3[cd]ef"));
        System.out.println(obj.decodeString("2[a2[b]3[c]]1[cd]ef"));
    }
}