import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map=new HashMap<>();
        Arrays.stream(nums).forEach(i-> map.put( i, map.getOrDefault(i,0) + 1 ));
        PriorityQueue<int[]> queue= new PriorityQueue<>( Comparator.comparingInt(a -> a[1]));
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if( queue.size()==k && queue.peek()[1]< e.getValue()) {
                queue.poll();
                queue.add(new int[]{e.getKey(), e.getValue()});
            } else if(queue.size()<k) {
                queue.add(new int[]{e.getKey(), e.getValue()});
            }
        }
        int[] result = new int[k];
        int i=0;
        while (!queue.isEmpty()) {
            int[] p=queue.poll();
            result[i] = p[0];
            i+=1;
        }
        return result;
    }
}