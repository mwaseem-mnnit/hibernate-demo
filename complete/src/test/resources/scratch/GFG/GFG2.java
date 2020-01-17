import java.util.*;
import java.lang.*;

class GFG2 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t>0) {
            t--;
            int n = sc.nextInt();
            int s = sc.nextInt();
            int[] arr = new int[n];
            for(int i=0; i<n; i++) {
                arr[i] = sc.nextInt();
            }
            int l=0,r=0, sum=0;
            while(r<n && l<n) {
                sum = sum + arr[r];
                if( sum == s) {
                    break;
                }
                if( sum > s) {
                    sum = sum - arr[l];
                    sum = sum - arr[r];
                    l = l+1;
                    continue;
                } else {
                    r = r + 1;
                }
            }
            if( sum == s) {
                l=l+1;
                r=r+1;
                System.out.println(l+" "+r);
            } else {
                System.out.println("-1");
            }
        }
    }
}