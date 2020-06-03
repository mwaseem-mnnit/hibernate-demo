/*1066. Campus Bikes II*/
class Solution {
    int mod(int a, int b) {
        return (a-b)<0?(b-a):(a-b);
    }
    int dist(int[] w, int[] b) {
        return mod(w[0],b[0]) + mod(w[1],b[1]);
    }
    int visitedBit(int[] visited) {
        int r=0,m=1;
        for(int i=0;i<visited.length;i++) {
            r += visited[i]!=-1?m:0;
            m=m*2;
        }
        return r;
    }

    int compute(int[][] workers, int[][] bikes, int mask, int w, int[][] dp) {
        if(w==workers.length) return 0;
        if(dp[w][mask]!=-1) return dp[w][mask];
        int min=Integer.MAX_VALUE;
        for(int i=0;i<bikes.length;i++) {
            if((mask & (1<<i)) == 0) {
                int sum = compute(workers, bikes, mask|(1<<i), w+1, dp) + dist(workers[w], bikes[i]);
                if(min>sum) {
                    min=sum;
                }
            }
        }
        dp[w][mask]=min;
        return min;
    }
    public int assignBikes(int[][] workers, int[][] bikes) {
        int mask=(int)Math.pow(2, bikes.length);
        int[][] dp=new int[workers.length][mask];
        for(int i=0;i<workers.length;i++) {
            for(int j=0;j<mask;j++){
                dp[i][j]=-1;
            }
        }
        return compute(workers, bikes, 0, 0, dp);
    }
}