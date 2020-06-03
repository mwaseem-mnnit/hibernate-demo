import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() { }
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    double findDiff(int val, double target) {
        double diff=target-(double)val;
        return diff>0?diff:-diff;
    }
    void findTarget(TreeNode root, double target, Stack<TreeNode> s, Stack<TreeNode> l) {
        TreeNode found=root;
        s.push(root); l.push(root);
        while (true) {
            if(findDiff(root.val, target)<findDiff(found.val, target)) {
                found=root;
            }
            if(root.left!=null && target<root.val) {
                root=root.left;
            } else if(root.right!=null && target>root.val) {
                root=root.right;
            } else break;
            s.push(root); l.push(root);
        }
        while (s.peek()!=found) {
            s.pop();l.pop();
        }
    }

    void findSmaller(Stack<TreeNode> stack) {
        TreeNode node=stack.pop();
        int val=node.val;
        if(node.left!=null) {
            stack.push(node.left);
            node=node.left;
            while (node.right!=null) {
                stack.push(node.right);
                node=node.right;
            }
        } else {
            while (!stack.isEmpty() && stack.peek().val>val) {
                stack.pop();
            }
        }

    }
    void findHigher(Stack<TreeNode> stack) {
        TreeNode node=stack.pop();
        int val=node.val;
        if(node.right!=null) {
            node=node.right;
            stack.push(node);
            while (node.left!=null) {
                node=node.left;stack.push(node);
            }
        } else {
            while (!stack.isEmpty() && stack.peek().val<val) {
                stack.pop();
            }
        }
    }
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        if(root==null) return new ArrayList<>();
        Stack<TreeNode> smaller=new Stack<>();
        Stack<TreeNode> larger=new Stack<>();
        findTarget(root, target, smaller, larger);
        List<Integer> result = new ArrayList<>();
        root=smaller.peek();
        result.add(root.val);
        findSmaller(smaller);
        findHigher(larger);
        while (result.size()<k) {
            if(smaller.isEmpty() || (!larger.isEmpty() && findDiff(smaller.peek().val, target) >= findDiff(larger.peek().val, target))) {
                result.add(larger.peek().val);
                findHigher(larger);
            } else if(larger.isEmpty() || (!smaller.isEmpty() && findDiff(smaller.peek().val, target) <= findDiff(larger.peek().val, target))) {
                result.add(smaller.peek().val);
                findSmaller(smaller);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] dp=new int[10][1024];
        for(int i=0;i<11;i++) {
            for(int j=0;j<1025;j++){
                dp[i][j]=-1;
            }
        }
        Solution obj=new Solution();
        obj.closestKValues(new TreeNode(3,
                new TreeNode(2, new TreeNode(1,null,null), null),
                new TreeNode(4,null,null)) ,2.0000,3 ).forEach(System.out::println);
    }
}