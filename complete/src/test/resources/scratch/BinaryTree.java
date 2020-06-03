import java.util.Iterator;
import java.util.Stack;

public class BinaryTree<T> implements Iterable<T> {
    TreeNode<T> node;
    public BinaryTree(TreeNode<T> node) {
        this.node = node;
    }

    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator<T>(node);
    }

    public static class BinaryTreeIterator<T> implements Iterator<T> {
        private Stack<TreeNode<T>> stack;
        public BinaryTreeIterator(TreeNode<T> node) {
            stack=new Stack<>();
            while (node!=null) {
                stack.push(node);
                node=node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            if(!hasNext()) {
                return null;
            }
            TreeNode<T> node = stack.pop();
            TreeNode<T> right = node.right;
            while(right!=null) {
                stack.push(right);
                right=right.left;
            }
            return node.val;
        }
    }

    public static void main(String[] args) {
        BinaryTree<Integer> bt = new BinaryTree<>(new TreeNode<>(5,
                new TreeNode<>(3, new TreeNode<>(2, null, null), new TreeNode<>(4, null, null)),
                new TreeNode<>(7, null, new TreeNode<>(9, null, null))));
        bt.forEach(System.out::println);
    }
}

class TreeNode<T> {
    T val;
    TreeNode<T> left;
    TreeNode<T> right;

    public TreeNode(T val, TreeNode<T> left, TreeNode<T> right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}