import java.util.Stack;
import java.util.stream.IntStream;

class Solution {
    public String repeat(String s, int n) {
        if(s==null || s.isEmpty()) return s;
        StringBuilder builder = new StringBuilder(s);
        IntStream.range(0,n).forEach(i->builder.append(s));
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
    public String decodeString(String s) {
        Stack<Object> stack = new Stack<>();
        StringBuilder str = new StringBuilder();
        int i=0;
        while (i<s.length()) {
            if(Character.isDigit(s.charAt(i))) {
                int num[] = getNumber(s,i);
                i=i+num[1];
                stack.push(num[0]);

            } else if(s.charAt(i) == ']') {

            } else {
                str.append(s.charAt(i));
            }
            i++;
        }
    }
}