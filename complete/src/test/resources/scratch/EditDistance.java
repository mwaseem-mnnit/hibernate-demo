class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[2][word2.length()+1];
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }
        int flag=0;
        for(int i=0;i<word1.length();i++) {
            flag=(flag+1)%2;
            for(int j=0;j<=word2.length();j++) {
                if(j==0) {
                    dp[flag][j] = dp[(flag+1)%2][j] + 1;
                    continue;
                }
                if(word1.charAt(i)==word2.charAt(j-1)) {
                    dp[flag][j] = dp[(flag+1)%2][j-1];
                } else {
                    dp[flag][j] = Math.min(dp[(flag+1)%2][j-1], Math.min(dp[(flag+1)%2][j], dp[flag][j-1]))+1;
                }
            }
        }
        return dp[flag][word2.length()];
    }

    public static void main(String[] args) {
        Solution obj=new Solution();
        System.out.println(obj.minDistance("horse", "ros"));
        System.out.println(obj.minDistance("", ""));
        System.out.println(obj.minDistance("a", "a"));
        System.out.println(obj.minDistance("", "a"));
    }
}