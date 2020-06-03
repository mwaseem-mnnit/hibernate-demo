import java.util.HashMap;
import java.util.Map;

public class MaxSumSlitBinaryTree {
    Map<TreeNode, Long> subTreeSum;
    int mod = 1000000007;
    Long answer=1L;
    public Long subtreeSumCal(TreeNode node) {
        if(node == null) return 0L;
        Long ans=Long.valueOf(node.val);
        if(node.left !=null)  {  ans = ans + subtreeSumCal(node.left); }
        if(node.right !=null) { ans = ans + subtreeSumCal(node.right);}
        subTreeSum.put(node, ans);
        return ans;
    }
    public void findMax(TreeNode root, Long total) {
        if(root == null) return;
        long temp = (total - subTreeSum.get(root)) * subTreeSum.get(root);
        if(temp > answer) answer = temp;
        findMax(root.left, total);
        findMax(root.right, total);
    }
    public int maxProduct(TreeNode root) {
        subTreeSum = new HashMap<>();
        answer=0L;
        Long total = subtreeSumCal(root);
        findMax(root, total);
        return (int)(answer % mod);
    }

    public static void main(String[] args) {
        MaxSumSlitBinaryTree obj = new MaxSumSlitBinaryTree();
        System.out.println(obj.maxProduct(null));
    }
    public static class TreeNode {
        int val;TreeNode left;TreeNode right;
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;this.left = left;this.right = right;
        }
    }
}