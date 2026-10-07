import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Lab4 {

    public static OptionalDouble average(List<Integer> ints) {
        return ints.stream()
                .mapToInt(Integer::intValue)
                .average();
    }

    public static List<String> upperWithPrefix(List<String> src) {
        return src.stream()
                .map(s -> "_new_" + s.toUpperCase(Locale.ROOT))
                .toList();
    }

    public static List<Integer> squaresOfUniques(List<Integer> ints) {
        return ints.stream()
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(e -> e.getKey() * e.getKey())
                .toList();
    }

    public static <T> T lastOrThrow(Collection<T> coll) {
        return coll.stream()
                .reduce((prev, next) -> next)
                .orElseThrow(() -> new NoSuchElementException("Collection is empty"));
    }

    public static int sumEven(int[] arr) {
        return Arrays.stream(arr)
                .filter(n -> n % 2 == 0)
                .sum();
    }

    public static Map<Character, String> toMapByFirstChar(List<String> src) {
        return src.stream()
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.toMap(
                        s -> s.charAt(0),
                        s -> s.substring(1),
                        (old, last) -> last,
                        LinkedHashMap::new));
    }

    public static void main(String[] args) {
        System.out.println(average(List.of(1, 2, 5)).orElse(Double.NaN));
        System.out.println(upperWithPrefix(List.of("ab", "cde")));
        System.out.println(squaresOfUniques(List.of(1, 2, 2, 3, 4, 4)));
        System.out.println(lastOrThrow(List.of(20, 10, 50)));
        System.out.println(sumEven(new int[]{1, 2, 3, 4, 5}));
        System.out.println(toMapByFirstChar(List.of("aaw", "x", "aBB")));
    }
}