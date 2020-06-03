import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
    void append(StringBuilder sb, Character ch, int v) {
        for (int i=0;i<v;i++) sb.append(ch);
    }

    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0;i<s.length();i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i),0) + 1 );
        }
        StringBuilder result=new StringBuilder();
        map.entrySet().stream().sorted((e1,e2)-> e2.getValue() - e1.getValue())
                .forEachOrdered(e-> append(result, e.getKey(), e.getValue()));
        return result.toString();
    }

    public static void main(String[] args) {
        Solution obj=new Solution();
        System.out.println(obj.frequencySort("tree"));
        System.out.println(obj.frequencySort(""));
        System.out.println(obj.frequencySort("aa"));
    }
}