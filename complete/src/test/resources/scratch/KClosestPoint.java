import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    int distance(int x, int y) {
        return x*x + y*y;
    }
    public int[][] kClosest(int[][] points, int K) {
        if(points == null || points.length==0 || K==0) return null;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->distance(b[0],b[1])-distance(a[0],a[1]));
        for (int[] point : points) {
            if(queue.size()<K) {
                queue.add(point);
            } else {
                queue.add(point);
                queue.poll();
            }
         }
        int[][] result=new int[queue.size()][2];
        int i=0;
        while (!queue.isEmpty()) {
            result[i] = queue.poll();
            i++;
        }
        return result;
    }
}