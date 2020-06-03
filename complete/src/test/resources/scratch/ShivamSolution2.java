import java.util.Scanner;

class Solution {
    static long mod=1000000007;
    static long power(long x, long y) {
        if (y == 0)
            return 1;
        else if (y % 2 == 0)
            return (power(x, y / 2)%mod * power(x, y / 2)%mod )%mod;
        else
            return (((x%mod * power(x, y / 2)%mod)%mod) * power(x, y / 2)%mod)%mod;
    }
    static int getBitCount(long num) {
        int c=0;
        while (num>0){
            c+=1;
            num=num/2;
        }
        return c;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long ans=n;
        for(int i=n-1;i>=1;i--) {
            int lShift = getBitCount(ans);
            long num = (i*power(2,lShift))%mod;
            ans = (ans+num)%mod;
        }
        System.out.println(ans);
    }
}