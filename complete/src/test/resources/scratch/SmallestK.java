import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        Map<TreeNode, Integer> map=new HashMap<>();
        map.put(null,0);map.put(root,1);
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode ch=stack.peek();
            if(!map.containsKey(ch.left)) {
                map.put(ch.left,1);
                stack.push(ch.left);
            } else if(!map.containsKey(ch.right)) {
                map.put(ch.right,1);
                stack.push(ch.right);
            } else {
                map.put(ch, map.get(ch) + map.get(ch.left) + map.get(ch.right));
                stack.pop();
            }
        }
        while (root!=null) {
            if(map.get(root) - map.get(root.right) == k) { return root.val; }
            else if( root.left != null && map.get(root.left) >=k ) {
                root=root.left;
            } else {

                k = k-map.get(root.left)-1;
                root=root.right;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        Solution obj=new Solution();
        System.out.println(obj.kthSmallest(
                new TreeNode(5,
                new TreeNode(3, new TreeNode(2, new TreeNode(1, null,null),
                        null),
                        new TreeNode(4, null, null)),
                new TreeNode(6, null, null)),
                3));
        System.out.println(obj.kthSmallest(new TreeNode(2, new TreeNode(1, null, null) , new TreeNode(3,null, null)),3));
    }
    public static class TreeNode {
        int val;TreeNode left;TreeNode right;
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;this.left = left;this.right = right;
        }
    }
}