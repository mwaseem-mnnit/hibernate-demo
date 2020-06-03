import sun.reflect.generics.tree.Tree;

import java.util.*;

public class DistributeCoin {
    public static Map<TreeNode, TreeNode> parent;
    public static Map<TreeNode, Boolean> visited;
    public static List<TreeNode> resultNodes;
    public static void findParent(TreeNode node) {
        if( node == null ) return;
        if( node.left != null) parent.put(node.left, node);
        if( node.right != null) parent.put(node.right, node);
        findParent(node.left);
        findParent(node.right);
        visited.put(node, false);
    }

    public static void findResultNodes(TreeNode node) {
        if(node == null) return;
        if(node.val>1) resultNodes.add(node);
        findResultNodes(node.left); findResultNodes(node.right);
    }

    public static int distribute(TreeNode node, int level) {
        Queue<Object[]> queue = new LinkedList<>();
        int ans = 0, remaining=node.val-1;
        queue.add(new Object[]{node, 0});
        while (!queue.isEmpty()) {
            Object[] element = queue.poll();
            TreeNode root = (TreeNode) element[0];
            visited.put(root, true);
            if(root.val == 0) {
                root.val=1;
                ans += (int) element[1];
                node.val-=1;
                remaining-=1;
            }
            if(remaining==0) break;
            if(level == (int) element[1]) continue;
            if( root.left != null && !visited.get(root.left)) queue.add(new Object[]{root.left, (int) element[1]+1});
            if( root.right != null && !visited.get(root.right)) queue.add(new Object[]{root.right, (int) element[1]+1});
            if( parent.get(root) != null && !visited.get(parent.get(root))) queue.add(new Object[]{parent.get(root), (int) element[1]+1});
        }
        return ans;
    }

    public static int distributeCoins(TreeNode root) {
        parent = new HashMap<>(); resultNodes = new ArrayList<>();visited = new HashMap<>();
        findParent(root); findResultNodes( root);
        int ans=0;
        int level=1;
        while(resultNodes.size()>0) {
            resultNodes = new ArrayList<>();
            findResultNodes( root);
            for (TreeNode node : resultNodes) {
                for (Map.Entry<TreeNode, Boolean> treeNodeBooleanEntry : visited.entrySet()) {
                    treeNodeBooleanEntry.setValue(false);
                }
                ans+=distribute(node, level);
                level+=1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5,
                new TreeNode(0, null, null),
                new TreeNode(0,
                        new TreeNode(0,
                                new TreeNode(3, null, null), null),
                        new TreeNode(0, null, new TreeNode(0, null, new TreeNode(0, null, null)))));
        System.out.println(distributeCoins(root));
    }

    public static class TreeNode {
        int val;TreeNode left;TreeNode right;
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;this.left = left;this.right = right;
        }
  }
}