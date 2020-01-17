
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class ClimbingLeaderBoard {

    static int binarySearch(int[] scores, int key) {
        int h=0, l=scores.length-1;
        if( key > scores[h]) return -1;
        if( key < scores[l]) return l+1;
        while( l>=h ) {
            int mid = (l+h)/2;
            if( scores[mid] == key) return mid;
            else if( scores[mid] < key ) l = mid-1;
            else h = mid+1;
        }
        return l;
    }

    // Complete the climbingLeaderboard function below.
    static int[] climbingLeaderboard(int[] scores, int[] alice) {
        int[] rank = new int[scores.length];
        int r = 1;
        rank[0] = r;
        for( int i=1; i< scores.length; i++) {
            if( scores[i-1] == scores[i]) { rank[i] = r; }
            else { r++; rank[i] = r; }
        }
        int[] aliceRank = new int[alice.length];
        for( int i=0; i< alice.length; i++) {
            int ind = binarySearch(scores, alice[i]);
            if( ind == -1) aliceRank[i] = 1;
            else if( ind == scores.length) aliceRank[i] = rank[ind-1]+1;
            else if( scores[ind] == alice[i] ) {
                aliceRank[i] = rank[ind];
            } else if( scores[ind] < alice[i] ) {
                aliceRank[i] = rank[ind] - 1;
            } else if( scores[ind] > alice[i] ) {
                aliceRank[i] = rank[ind] + 1;
            }
        }
        return aliceRank;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int scoresCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] scores = new int[scoresCount];

        String[] scoresItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < scoresCount; i++) {
            int scoresItem = Integer.parseInt(scoresItems[i]);
            scores[i] = scoresItem;
        }

        int aliceCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] alice = new int[aliceCount];

        String[] aliceItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < aliceCount; i++) {
            int aliceItem = Integer.parseInt(aliceItems[i]);
            alice[i] = aliceItem;
        }

        int[] result = climbingLeaderboard(scores, alice);

//        for (int i = 0; i < result.length; i++) {
//            bufferedWriter.write(String.valueOf(result[i]));
//
//            if (i != result.length - 1) {
//                bufferedWriter.write("\n");
//            }
//        }
//
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}
