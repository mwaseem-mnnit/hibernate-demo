class Solution {
    int findInArray(int[] arr, int l, int r, int val) {
        for(int i=l;i<=r;i++) {
            if(arr[i] == val) return i;
        }
        return -1;
    }

    public TreeNode iterate(int[] inorder, int inl, int inr, int[] postorder, int pol, int por) {
        if(inl>inr || pol>por ) return null;
        TreeNode root = new TreeNode();
        root.val = postorder[por];
        int inidx = findInArray(inorder, inl, inr, root.val);
        root.left = iterate(inorder, inl, inidx-1, postorder, pol, pol + ( inidx-1-inl));
        root.right = iterate(inorder, inidx+1, inr, postorder, pol + ( inidx-1-inl) + 1, por-1);
        return root;
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return iterate(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
    }

    public static class TreeNode {
        int val;TreeNode left;TreeNode right;
        TreeNode() {}
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;this.left = left;this.right = right;
        }
    }
}