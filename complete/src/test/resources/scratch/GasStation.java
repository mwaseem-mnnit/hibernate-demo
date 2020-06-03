import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class GasStation {
    public static void main(String[] args) {
        int station = canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2});
        System.out.println(station);
        station = canCompleteCircuit(new int[]{2,3,4}, new int[]{3,4,3});
        System.out.println(station);
        station = canCompleteCircuit(new int[]{5,1,2,3,4}, new int[]{4,4,1,5,1});
        System.out.println(station);
    }
    public static int findNextPositive(int[] diff, int l) {
        for( int i=l;i<diff.length;i++) {
            if(diff[i]>=0) {
                return i;
            }
        }
        return -1;
    }
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int[] diff = new int[gas.length];

        if( gas.length < 2) return gas.length-1;

        for(int i=0;i<gas.length;i++) {
            diff[i] = gas[i] - cost[i];
        }
        int l=findNextPositive(diff, 0);
        int sum=diff[l], r=(l+1) % gas.length;
        while( r!=l ) {
            sum += diff[r];
            if( sum < 0) {
                l=findNextPositive(diff, l+1);
                if( l<0) return -1;
                r=(l+1) % gas.length;
                sum=diff[l];
            } else {
                r=(r+1) % gas.length;
            }
        }
        return l;
    }
}