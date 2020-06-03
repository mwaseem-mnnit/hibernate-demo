import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class PrimitiveStream {
    public static void main(String[] args) {
        String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};
        Stream<String[]> stream = Stream.of(data);
        Stream<String> stringStream = stream.flatMap(Stream::of).filter(c -> !"a".equals(c));
//        stringStream.forEachOrdered(System.out::println);

        int[] intArray = {1, 2, 3, 4, 5, 6};
        int[][] td = {{1, 2}, {3, 4}};
        Stream<int[]> streamArray = Stream.of(intArray);
        Stream<int[]> tds = Stream.of(td);
        Stream<int[]> atds = Arrays.stream(td);
        atds.flatMapToInt(IntStream::of).forEach(System.out::println);
        tds.flatMapToInt(IntStream::of).forEach(System.out::println);
        atds.flatMapToInt(IntStream::of).forEach(System.out::println); // throws error

        String[] arr = {"", "", ""};
        Stream<String> tt = Stream.of(arr);

        Long[] L = {1l, 2L};
        Stream<Long> Ls = Stream.of(L);

        long[] l = {1l, 2L};
        Stream<long[]> ls = Stream.of(l);
    }
}