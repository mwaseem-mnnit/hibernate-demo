class Solution {
    static int allSubArray(int[] arr, int K) {
        int[][] dp = new int[K+1][arr.length+1];
        for(int i=0;i<=arr.length;i++) dp[0][i]=1;
        for(int j=1;j<=K;j++) {
            for(int i=0;i<arr.length;i++) {
                dp[j][i+1] = dp[j][i] + ( j-arr[i]>=0 ? dp[j-arr[i]][i] : 0);
            }
        }
        return dp[K][arr.length];
    }

    public static void main(String[] args) {
        System.out.println(allSubArray(new int[]{17, 18, 6, 11, 2, 4}, 6));
        System.out.println(allSubArray(new int[]{3,1,2,4,2}, 4));
    }
}