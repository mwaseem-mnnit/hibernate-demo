import java.util.Arrays;
import java.util.List;

public class SplitArrayIntoBoxes {
    static int[] xor = new int[]{0,0,0,0};
    static int max = 0;
    static void recursion(List<Integer> list, int i, int boxes) {
        if( i == list.size()) {
            int sum=0;
            for( int j=0; j<boxes; j++) {
                sum += xor[j];
            }
            if( max < sum) {
                max  = sum;
            }
            return;
        }
        for( int j=0; j<boxes; j++) {
            xor[j] = xor[j] ^ list.get(i);
            recursion(list, i + 1, boxes);
            xor[j] = xor[j] ^ list.get(i);
        }
    }

    static void breakAndCompute(List<Integer> integers, int boxes) {
        int[] bitsFrequency = new int[100];
        for (Integer integer : integers) {
            int num = integer;
            int count = 0;
            while( num > 0 ) {
                if( num % 2 == 1) {
                    bitsFrequency[count] = bitsFrequency[count] + 1;
                }
                num = num/2;
                count = count + 1;
            }
        }
        int max = 0;
        for(int i=0; i<100; i++) {
            int d = bitsFrequency[i] / boxes;
            int m = bitsFrequency[i] % boxes;
            int mul;
            if( d%2 == 0) {
                mul = m;
            } else {
                mul = boxes - m;
            }
            max += Math.pow(2, i) * mul;
        }
        System.out.println(max);
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(29, 14, 18);
        recursion(numbers, 0, 2);
        System.out.println(max);
        breakAndCompute(numbers,2);
    }
}