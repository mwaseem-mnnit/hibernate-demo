import java.util.Stack;

public class BinaryTree {
    private Node root;
    public BinaryTree(Node node) {
        this.root = node;
    }

    public BinaryTreeInOrderIterator inOrderIterator() {
        return new BinaryTreeInOrderIterator(root);
    }

    public BinaryTreePreOrderIterator preOrderIterator() {
        return new BinaryTreePreOrderIterator(root);
    }

    private class BinaryTreeInOrderIterator {
        private Stack<Node> stack;

        public BinaryTreeInOrderIterator(Node root) {
            this.stack = new Stack<>();
            while ( root != null ) {
                stack.push(root);
                root = root.getLeft();
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public Node next() {
            Node node = stack.pop();
            Node child = node.getRight();
            while( child != null ) {
                stack.push(child);
                child = child.getLeft();
            }
            return node;
        }
    }

    private class BinaryTreePreOrderIterator {
        private Stack<Node> stack;

        public BinaryTreePreOrderIterator(Node root) {
            this.stack = new Stack<>();
            stack.push(root);
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public Node next() {
            Node node = stack.pop();
            if( node.getRight() != null ) {
                stack.push(node.getRight());
            }
            if( node.getLeft() != null ) {
                stack.push(node.getLeft());
            }
            return node;
//            Node node1 = stack.peek();
//            if( node1.getLeft() != null ) {
//                stack.push(node1.getLeft());
//                return node1;
//            }
//            while(!stack.isEmpty()) {
//                Node node = stack.pop();
//                if( node.getRight() != null ) {
//                    stack.push(node.getRight());
//                    break;
//                }
//            }
//            return node1;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.setLeft(new Node(2));
        root.setRight(new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getRight().setLeft(new Node(6));
        root.getRight().setRight(new Node(7));
        BinaryTree tree = new BinaryTree(root);
        BinaryTreeInOrderIterator inIterator = tree.inOrderIterator();
        while (inIterator.hasNext()) {
            System.out.print(inIterator.next().getVal()+" ");
        }
        System.out.println();
        BinaryTreePreOrderIterator preIterator = tree.preOrderIterator();
        while (preIterator.hasNext()) {
            System.out.print(preIterator.next().getVal()+" ");
        }
        System.out.println();
    }

}

class Node {
    private int val;
    private Node left;
    private Node right;
    public Node(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}