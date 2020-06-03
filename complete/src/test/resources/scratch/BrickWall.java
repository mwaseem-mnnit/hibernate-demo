import java.util.Scanner;

class Solution {
    int mod = 1000000007;
    int getValueAtIndex(int[] arr, int i) {
        if(i>0) return arr[i];
        return 0;
    }
    int takeMod(long a, long b) {
        return (int)((a+b)%mod);
    }
    void preprocess(int[][] power) {
        for(int i=0;i<=1000;i++) {
            power[0][i] = power[i][0] = 1;
        }
        for(int i=1; i<=1000; i++) {
            power[1][i] = getValueAtIndex(power[1], i-1) + power[1][i-2] + power[1][i-3] + power[1][i-4];
        }
        for(int i=1;i<=1000;i++) {
            for (int j=1;j<=1000;j++) {

            }
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int[][] power = new int[1001][1001];
        int t=s.nextInt();
        while (t>0) {
            int n=s.nextInt(); int m=s.nextInt();

            t--;
        }
    }
}