public class SumOfXor {
    public static void main(String[] args) {

        int[] arr = new int[]{1,2,1,2,1,2};
        int[][] dp = new int[4][6];
        int[] xor = new int[6];
        xor[0] = arr[0];
        for( int i=1; i<arr.length; i++) {
            xor[i] = xor[i-1] ^ arr[i];
        }
        for( int i=0; i<arr.length; i++) {
            dp[0][i] = xor[i];
        }
        for( int i=1; i<4; i++) {
            for( int j=i; j<arr.length; j++) {
                for( int k=j; k>=i; k--) {
                    int n1 = (xor[k-1]^xor[j]) + dp[i-1][k-1];
                    if( dp[i][j] < n1) {
                        dp[i][j] = n1;
                    }
                }
            }
        }
        for( int i=0; i<4; i++) {
            for( int j=0; j<6; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
    }
}