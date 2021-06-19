package niss.utils.internal;

import java.util.function.Predicate;
import java.util.stream.LongStream;

public class Checker {

    public static final Predicate<Integer> ODD_CHECKER = x -> x % 2 != 0;

    public static final Predicate<Integer> PRIME_CHECKER =
            x -> LongStream.rangeClosed(2, (long)(Math.sqrt(x))).allMatch(n -> x % n != 0);
}
