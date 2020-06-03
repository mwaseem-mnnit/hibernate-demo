import java.util.*;

class Solution {
    public int idx;
    public String getElement(String s) {
        StringBuilder element = new StringBuilder();
        element.append(s.charAt(idx)); idx+=1;
        while(idx<s.length() && Character.isLowerCase(s.charAt(idx))) {
            element.append(s.charAt(idx)); idx+=1;
        }
        return element.toString();
    }

    public int getNumber(String s) {
        int num=0;
        while(idx < s.length() && Character.isDigit(s.charAt(idx))) {
            num = num*10 + s.charAt(idx) - '0'; idx+=1;
        }
        return num;
    }
    Map<String,Integer> mergeMap(Map<String,Integer> m1, Map<String,Integer> m2) {
        for (Map.Entry<String, Integer> e : m2.entrySet()) {
            m1.put( e.getKey(), e.getValue() + m1.getOrDefault(e.getKey(),0));
        }
        return m1;
    }
    void multiplyMap(Map<String,Integer> m, int num) {
        for (Map.Entry<String, Integer> e : m.entrySet()) {
            e.setValue(e.getValue()*num);
        }
    }
    public Map<String, Integer> computeFormula(String formula) {
        Map<String, Integer> m=new HashMap<>();
        while(idx < formula.length()) {
            if(formula.charAt(idx) == '(') {
                idx+=1;
                Map<String, Integer> m1 = computeFormula(formula);
                m=mergeMap(m,m1);
            } else if(formula.charAt(idx) == ')') {
                idx+=1;
                if(idx < formula.length()) {
                    int num = getNumber(formula);
                    multiplyMap(m, num);
                }
                return m;
            } else if(Character.isUpperCase(formula.charAt(idx))) {
                String atom = getElement(formula);
                if( idx<formula.length() && Character.isDigit(formula.charAt(idx))) {
                    m.put( atom, m.getOrDefault(atom, 0) + getNumber(formula));
                } else {
                    m.put( atom, m.getOrDefault(atom, 0) + 1);
                }
            }
        }
        return m;
    }

    public String countOfAtoms(String formula) {
        idx=0;
        Map<String, Integer> m = computeFormula(formula);
        List<String> atoms = new ArrayList<>(m.keySet());
        Collections.sort(atoms);
        StringBuilder result = new StringBuilder();
        for (String atom : atoms) {
            result.append(atom).append(m.get(atom)==1? "" : m.get(atom));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.countOfAtoms("K4(ON(SO3)2)2"));
        System.out.println(obj.countOfAtoms("Mg(OH)2"));
        System.out.println(obj.countOfAtoms("(H2O2K)1K"));
    }
}