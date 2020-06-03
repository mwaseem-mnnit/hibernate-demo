import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;
    ListNode() { }
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists==null) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode list : lists) {
            if(list!=null) queue.add(list);
        }
        ListNode root = null,next=null;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            if(root==null) {
                root=node;
                next=root;
            } else {
                next.next=node;
                next=node;
            }
            if(node.next!=null) queue.add(node.next);
        }
        return root;
    }
}