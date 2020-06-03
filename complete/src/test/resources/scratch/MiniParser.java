import java.util.ArrayList;
import java.util.List;

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation

class Solution {
    public Integer idx;
    public int getNumber(String s) {
        int num=0;
        while(idx < s.length() && Character.isDigit(s.charAt(idx))) {
            num = num*10 + s.charAt(idx) - '0'; idx+=1;
        }
        return num;
    }
    //"[123,[456,[789]]]"
    //IDX= 0 1 4 5
    public NestedInteger recur(String s) {
        if(s==null || s.length()==0) return new NestedInteger();
        NestedInteger result=null;
        while (idx<s.length()) {
            if(Character.isDigit(s.charAt(idx)) || s.charAt(idx) == '-') {
                int ff=1;
                if(s.charAt(idx) == '-') {
                    ff=-1;idx+=1;
                }
                result = new NestedInteger();
                result.setInteger(ff*getNumber(s));
                return result;
            } else if(s.charAt(idx) == '[') {
                if(result == null) result = new NestedInteger();
                idx+=1;
                NestedInteger t=recur(s);
                if(t!=null)
                    result.add(t);
            } else if(s.charAt(idx) == ']') {
                if(result!=null&&result.getInteger() == null && result.getList()== null) return null;
                return result;
            } else {
                idx+=1;
                NestedInteger t=recur(s);
                if(result==null) result=new NestedInteger();
                if(t!=null)
                    result.add(t);
            }
        }
        if(result!=null && result.getInteger() == null && result.getList()== null) return null;
        return result;
    }
    public NestedInteger deserialize(String s) {
        idx=0;
        return recur(s);
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        NestedInteger n = obj.deserialize("[123,[456,[789]]]");
        System.out.println(n);
        n = obj.deserialize("[123]");
        System.out.println(n);
    }
}
interface NestedInteger {
    public boolean isInteger();
    public Integer getInteger();
    public void setInteger(int value);
    public void add(NestedInteger ni);
    public List<NestedInteger> getList();
}
class Child implements NestedInteger {
    private Integer val;
    private List<NestedInteger> children;
    @Override
    public boolean isInteger() {
        return val!=null;
    }

    @Override
    public Integer getInteger() {
        return val;
    }

    @Override
    public void setInteger(int value) {
        this.val = value;
    }

    @Override
    public void add(NestedInteger ni) {
        if(children==null) children = new ArrayList<>();
        children.add(ni);
    }

    @Override
    public List<NestedInteger> getList() {
        return children;
    }
}
