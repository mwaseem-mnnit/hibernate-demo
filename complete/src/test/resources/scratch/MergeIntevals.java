import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    int[] intersection(int[] l, int[] r) {
        return new int[]{Math.max(l[0], r[0]), Math.min(l[1], r[1])};
    }
    int[] union(int[] l, int[] r) {
        return new int[]{Math.min(l[0], r[0]), Math.max(l[1], r[1])};
    }
    public int[][] merge(int[][] intervals) {
        if(intervals.length<=1) return intervals;
        Arrays.sort(intervals, (a1,a2)-> {
            if(a1[0]!=a2[0]) return a1[0]-a2[0];
            else return a1[1]-a2[1];
        });
        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);
        for(int i=1;i<intervals.length;i++) {
            int[] l=result.get(result.size()-1);
            int[] x=intersection(l,intervals[i]);
            if(x[0] <= x[1]) {
                result.set(result.size()-1, union(l, intervals[i]));
            } else {
                result.add(intervals[i]);
            }
        }
        int[][] ret=new int[result.size()][2];
        int i=0;
        for (int[] ints : result) {
            ret[i] = ints;
            i+=1;
        }
        return ret;
    }
}