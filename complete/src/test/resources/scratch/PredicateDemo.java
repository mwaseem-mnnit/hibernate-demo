import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateDemo {
    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple("green", 120.0), new Apple("red", 110.0),
                new Apple("brown", 150.0), new Apple("green", 160.0), new Apple("red", 122.0));
        filterApples(apples, isWeightAbove150()).forEach(System.out::println);
        System.out.println();
        filterApples(apples, isWeightAbove150().and(isColorGreen())).forEach(System.out::println);
    }
    public static List<Apple> filterApples(List<Apple> apples, Predicate<Apple> predicate) {
        return apples.stream().filter(predicate).collect(Collectors.toList());
    }
    public static Predicate<Apple> isWeightAbove150() {
        return apple -> apple.getWeight() >= 150;
    }
    public static Predicate<Apple> isColorGreen() {
        return apple -> apple.getColor().equals("green");
    }
}

class Apple {
    private String color;
    private Double weight;

    public Apple(String c, Double w) {
        this.color = c;
        this.weight = w;
    }

    @Override
    public String toString() {
        return "Apple{color:" + this.getColor() + ",weight:" + this.getWeight() + "}";
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}