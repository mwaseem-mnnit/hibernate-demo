import java.util.HashMap;
import java.util.Map;

public class BinaryTreeCameras {

    public static Map<TreeNode,Boolean> visited;
    public static int answer;

    public static void preProcess(TreeNode root) {
        if( root == null) return;
        preProcess(root.left);
        preProcess(root.right);
        visited.put(root,false);
    }

    public static int dfs(TreeNode root) {
        if(root==null || visited.get(root)) return 0;
        int l,r;
        l=dfs(root.left);
        r=dfs(root.right);
        if(l==0 && r==0) {
            visited.put(root, true);
            return 1;
        }

        if(l==1 || r==1) {
            answer+=1;
            visited.put(root.left, true);
            visited.put(root.right, true);
            visited.put(root, true);
            return 2;
        }
        if(l==2 || r==2) {
            visited.put(root, true);
            return 3;
        }
        return Math.max(l,r)+1;
    }

    public static boolean checkNull(TreeNode root) {
        return root == null || visited.get(root);
    }
    public static int minCameraCover(TreeNode root) {
        visited = new HashMap<>();
        answer=0;
        preProcess(root);
        while(root != null) {
            if(checkNull(root.left) && checkNull(root.right)) {
                answer+=1;break;
            }
            dfs(root);
            root=null;
            for (Map.Entry<TreeNode, Boolean> entry : visited.entrySet()) {
                if(!entry.getValue()) {
                    root=entry.getKey();
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0,
                null,
                 new TreeNode(0,
                         new TreeNode(0,
                                 new TreeNode(0, null,
                                         new TreeNode(0, new TreeNode(0, null, null),
                                                 new TreeNode(0, null, null))), null), null));
        System.out.println(minCameraCover(root));
        root = new TreeNode(0,
                null,
                new TreeNode(0,
                        new TreeNode(0,
                                new TreeNode(0, null,
                                        new TreeNode(0, new TreeNode(0, null, null),
                                                new TreeNode(0, null, null))), null), null));
        System.out.println(minCameraCover(root));
        root = new TreeNode(0,
                null,
                new TreeNode(0,
                        new TreeNode(0,
                                new TreeNode(0, null,
                                        new TreeNode(0, new TreeNode(0, null, null),
                                                new TreeNode(0, null, null))), null), null));
        System.out.println(minCameraCover(root));
        root = new TreeNode(0,
                null,
                 new TreeNode(0,
                        new TreeNode(0,
                                new TreeNode(0,
                                        null,
                                         new TreeNode(0,
                                                 new TreeNode(0, null, null),
                                                 new TreeNode(0, null, null)
                                         )
                                ),
                                null),
                         null)
        );
        System.out.println(minCameraCover(root));
        root = new TreeNode(0,
                new TreeNode(0,
                        new TreeNode(0,null,null),
                        new TreeNode(0,null,null)),
                null);
        System.out.println(minCameraCover(root));
        root = new TreeNode(5,
                new TreeNode(0, null, null),
                new TreeNode(4,
                        new TreeNode(3,
                                new TreeNode(0, null, null),
                                null),
                        new TreeNode(2,
                                null,
                                new TreeNode(1,
                                        null,
                                        new TreeNode(0, null, null)
                                )
                        )
                )
        );
        System.out.println(minCameraCover(root));
        root = new TreeNode(5,
                new TreeNode(4,
                        new TreeNode(3,
                                new TreeNode(0, null, new TreeNode(0, null, null)),
                                null),
                        null),
                null);
        System.out.println(minCameraCover(root));
    }

    public static class TreeNode {
        int val;TreeNode left;TreeNode right;
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;this.left = left;this.right = right;
        }
    }
}