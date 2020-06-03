import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Solution {
    static final int mod = 1000000007;

    static int tileStackingProblem(int n, int m, int k) {
        int[][] dp = new int[n + 1][m + 1];
        int[][] sum = new int[n + 1][m + 1];
        for (int i = 0; i <= m; i++) {
            dp[1][i] = i; dp[0][i] = 1;
            sum[1][i] = sum[0][i] + i;
        }
        for (int i = 1; i <= n; i++){ sum[i][0] = sum[i-1][0];}
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
//                sum[i][j-1] = (sum[i][j-1] + sum[i-1][j-1])%mod;
                dp[i][j] = ( dp[i][j] + sum[i][j - 1] - (k<i ? sum[i-k][j-1]:0)) % mod;
                sum[i][j] = (sum[i-1][j] + dp[i][j])%mod;
//                for (int l = 0; (l <= k && l <= i); l++) {
//                    if (l == i) {
//                        dp[i][j] = (dp[i][j] + 1) % mod;
//                    } else {
//                        dp[i][j] = (dp[i][j] + dp[i - l][j - 1]) % mod;
//                    }
//                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();
            int result = tileStackingProblem(n, m, k);
            System.out.println(result);
            in.close();

    }
}