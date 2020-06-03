import java.util.Arrays;

class Solution {
    public void reset(Node[] nodes, int N) {
        for (int i=0;i<N;i++) nodes[i]=null;
    }
    public int[] beautifulArray(int N) {
        Node root=new Node(1);
        Node end=root;
        if(N >= 2) {
            root.addRight(new Node(2));
            end=root.right;
        }
        Node[] visited=new Node[N];
        for(int i=3;i<=N;i++) {
            reset(visited,N);
            Node point = end;
            Node addLeft = null;
            if(i%2==1) {
                while ( point!=null && point.val%2==0) point=point.left;
            }
            while (point != null) {

                if(i%2==0 && point.val%2==1) break;
                if ((i + point.val) % 2 == 0 && visited[(i + point.val) / 2 - 1] != null) {
                    addLeft = visited[(i + point.val) / 2 - 1];
                }
                visited[point.val-1] = point;
                point=point.left;
            }
            if(addLeft == null) {
                end.addRight(new Node(i));
                end=end.right;
            } else {
                addLeft.addLeft(new Node(i));
                if(addLeft == root) root = addLeft.left;
            }
        }
        int[] result=new int[N];
        int i=0;
        while (root!=null) {
            result[i]=root.val;
            root=root.right;
            i+=1;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution obj=new Solution();
        System.out.println(Arrays.toString(obj.beautifulArray(1)));
        System.out.println(Arrays.toString(obj.beautifulArray(2)));
        System.out.println(Arrays.toString(obj.beautifulArray(3)));
        System.out.println(Arrays.toString(obj.beautifulArray(11)));
    }
}
class Node {
    Node left;
    Node right;
    int val;
    Node(int val) {
        this.val=val;left=null;right=null;
    }
    void addLeft(Node left) {
        left.left=this.left;
        if(this.left!=null) this.left.right=left;
        this.left = left;
        left.right = this;
    }
    void addRight(Node right) {
        right.right=this.right;
        if(this.right!=null) this.right.left=right;
        this.right=right;
        right.left=this;
    }
}