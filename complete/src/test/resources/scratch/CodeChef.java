/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
    public static void main (String[] args) throws java.lang.Exception
    {
//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
//        int T = sc.nextInt();
        System.out.println("T is: "+T);
        while (T>0)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N =  Integer.parseInt(st.nextToken());
            System.out.println(N);
            int C =  Integer.parseInt(st.nextToken());
            System.out.println(C);
            int L[] = new int [N];
            int B[] = new int [N];
            int c[] = new int [N];
            int maxL =0, maxB=0;
            for(int i = 0;i<N;i++)
            {
                st = new StringTokenizer(br.readLine());
                L[i] =  Integer.parseInt(st.nextToken());
                B[i] =  Integer.parseInt(st.nextToken());
                c[i] =  Integer.parseInt(st.nextToken());
                if(maxL<L[i])
                    maxL = L[i];
                if(maxB<B[i])
                    maxB = B[i];
            }
            int Graph[][] = new int[maxL/2][maxB/2];
            for(int i=0;i<N;i++)
            {
                for(int j=0;j<L[i]/2;j++)
                {
                    for(int k=0;k<B[i]/2;k++)
                    {
                        Graph[j][k]= c[i];
                    }
                }
            }
            int result[] =new int [C];
            for(int i=0;i<maxL/2;i++)
            {
                for(int j=0;j<maxB/2;j++)
                {
                    if(Graph[i][j]>0)
                    {
                        result[Graph[i][j]-1]++;
                    }
                }
            }
            for(int i=0; i<C;i++)
            {
                System.out.print(result[i]*4+" ");
            }
            T--;
        }
    }
}