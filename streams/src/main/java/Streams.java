import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Streams {

    static void op() {
        Optional<Integer> optional = Optional.empty();
        optional = Optional.of(10);
        optional = Optional.ofNullable(null);


        if (optional.isPresent()) {
            System.out.println(optional.get());
        }

        Integer x = 2;
        //optional.map(x * 2);

    }

    public static void main(String[] args) {

        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println(numbers.count());

        Stream<Integer> numbers2 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(numbers2.max(new IntComparator()));

        Stream<Integer> numbers3 = Stream.of();
        System.out.println("! " + numbers3.max(new IntComparator()));

        Stream<Integer> numbers4 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // >= 6
        System.out.println(numbers4.filter(new GreatSixInt()).count());

        Stream<Integer> numbers5 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // >= 6
        System.out.println(numbers5.filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer >= 6;
            }
        }).count());

        Stream<Integer> numbers6 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // >= 6
        System.out.println(numbers6.filter(
                (x) -> x >= 6
        ).count());


 /*       new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread() + " :" + i);
            }
        }).start();
*/

        Stream.of(-1, -2, 0, 12, 45, 3, 46, 7, 2, 4, 78, 43, 54)
                .map(x -> x * 2)
                .filter(x -> x > 4)
                .reduce((x, y) -> x + y).ifPresent(System.out::println);

        System.out.println(Stream.of(-1, -2, 0, 12, 45, 3, 46, 7, 2, 4, 78, 43, 54)
                .map(x -> x * 2)
                .filter(x -> x > 4)
                .reduce(0, (x, y) -> x + y));


        Stream.generate(new IntSequence(200000))
                .limit(100000)
                .reduce((x, y) -> x + y).ifPresent(System.out::println);

        Stream.generate(new InfiniteLongSequence())
                .parallel()
                .filter(x -> x % 3 == 0)
                .map(x -> x - 1)
                .limit(100)
                .forEach(System.out::println);


        Stream.generate(new InfiniteLongSequence())
                .parallel()
                .filter(x -> x % 3 == 0)
                .map(x -> x - 1)
                .limit(100)
                .forEach(System.out::println);

        Stream.generate(new InfiniteLongSequence())
                .filter(x -> x % 3 == 0)
                .map(x -> x - 1)
                .limit(100)
                .map(l -> Long.toString(l))
                .reduce((x, y) -> x + "," + y)
                .isPresent(System.out::println);

      /*
      String s = Stream.generate(new InfiniteLongSequence())
                .filter(x -> x % 3 == 0)
                .map(x -> x - 1)
                .limit(100)
                .reduce("", (x, y) -> x + "," + Long.toString(y)), String::concat));
*/

    }
}

class InfiniteLongSequence implements Supplier<Long> {

    private long i = 0;

    @Override
    public Long get() {
        return i++;
    }
}

class IntSequence implements Supplier<Long> {

    private long i = 0;
    private long n = 0;

    public IntSequence(int n) {
        this.n = n;
    }

    @Override
    public Long get() {
        if (i < n)
            return i++;
        else
            return 0l;
    }
}

class IntComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer x, Integer y) {
        return Integer.compare(x, y);
    }
}

class GreatSixInt implements Predicate<Integer> {

    @Override
    public boolean test(Integer integer) {
        return integer >= 6;
    }
}