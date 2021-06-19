package niss.utils.common;

import niss.log4j.logger.ILogger;
import niss.log4j.logger.impl.SpecificLogger;
import niss.utils.internal.Checker;

import java.text.MessageFormat;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Solver {
    private static final ILogger LOG = new SpecificLogger();
    private static final Function<List<IntUnaryOperator>, UnaryOperator<List<Integer>>> multifunctionalMapper =
            list -> integers -> integers.stream()
                    .map(i -> list.stream()
                            .reduce(IntUnaryOperator::andThen)
                            .map(x -> x.applyAsInt(i))
                            .get())
                    .collect(Collectors.toList());

    private final UnaryOperator<List<Integer>> operator;

    private Solver(UnaryOperator<List<Integer>> operator) {
        this.operator = operator;
    }

    public static Solver of(List<IntUnaryOperator> unaryOperators) {
        if (unaryOperators.isEmpty()) {
            LOG.error("Use Identity instead empty list");
            return null;
        }
        return new Solver(multifunctionalMapper.apply(unaryOperators));
    }

    public List<Integer> apply(List<Integer> integers) {
        if (operator == null) {
            LOG.error("Should use of() to initialize Operator");
            return null;
        }
        LOG.info("Start solving...");
        List<Integer> mappedIntegers = operator.apply(integers);
        LOG.debug(MessageFormat.format("Input {0} \nOutput {1}", integers, mappedIntegers));
        LOG.info("Solved.");
        return mappedIntegers;
    }

    private static List<Integer> check(List<Integer> integers, Predicate<Integer> checker) {
        return integers.stream()
                .filter(checker)
                .collect(Collectors.toList());
    }

    public static List<Integer> filterPrime(List<Integer> integers) {
        LOG.info("Filter prime numbers");
        return check(integers, Checker.PRIME_CHECKER);
    }

    public static List<Integer> filterOdd(List<Integer> integers) {
        LOG.info("Filter odd numbers");
        return check(integers, Checker.ODD_CHECKER);
    }
}
