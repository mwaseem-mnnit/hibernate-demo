import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < nums.length; i++) {
            if (queue.size() == k && queue.peek() > nums[i]) {
                queue.poll();
                queue.add(nums[i]);
            } else {
                queue.add(nums[i]);
            }
        }
        return queue.peek();
    }

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int l=0,r=0;
        List<int[]> result = new ArrayList<>();
        while(l<A.length && r<B.length) {
            int s=Math.max(A[l][0], B[r][0]);
            int e=Math.min(A[l][1], B[r][1]);
            if(s<=e) result.add(new int[]{s,e});
            if(A[l][1]<B[r][1]) l+=1;
            else r+=1;
        }
        int[][] ret=new int[result.size()][2];
        int i=0;
        for (int[] ints : result) {
            ret[i] = ints;
            i+=1;
        }
        return ret;
    }

    public static void main(String[] args) {
        List<List<Integer>> l=new ArrayList<>();

    }

}