import java.util.Scanner;

class Solution {
        static int solve(int N, int M, String[] S, String temp) {
            int count=0;
            for (String s : S) {
                boolean flag=true;
                for (int i=0;i<s.length();i++) {
                    if(temp.charAt(i) != '?' && temp.charAt(i) != s.charAt(i)) {
                        flag=false;
                        break;
                    }
                }
                if(flag) count+=1;
            }
            return count;
        }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int q=sc.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++) arr[i] = sc.nextInt();
        for(int i=0;i<q;i++) {
            long ans=0;
            int ch = sc.nextInt();int l=sc.nextInt();int r=sc.nextInt();
            if(ch==1) {
                arr[l-1]=r;
                continue;
            }
            l=l-1;r=r-1;
            for(int j=l;j<=r;j++) {
                long sum=0;
                for(int k=j+1;k<=r;k++) sum+=arr[k];
                ans+= arr[j]*sum;
            }
            System.out.println(ans);
        }
    }
}