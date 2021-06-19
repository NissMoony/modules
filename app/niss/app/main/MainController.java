package niss.app.main;

import niss.log4j.logger.ILogger;
import niss.log4j.logger.impl.SpecificLogger;
import niss.utils.common.Solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainController {
    private static final ILogger LOG = new SpecificLogger();

    public static void main(String[] args) {
        List<Integer> fValues = getF();
        LOG.debug(Solver.filterPrime(fValues).toString());
        LOG.debug(Solver.filterOdd(fValues).toString());

        calcF2();
        calcFWithEmpty();
        warnExample();
    }

    // Найти целые значения функции 5 * x ^ 3 / 7 + 11 для x [0, 10]
    public static List<Integer> getF() {
        LOG.info("Calc F values.");

        return Solver
                .of(Arrays.asList(x -> (int) Math.pow(x, 3), x -> x * 5, x -> (int) (x / 7), x -> x + 11))
                .apply(getRange(0, 10));
    }

    public static void calcF2() {
        LOG.info("Calc F2 values.");
        List<Integer> fValues = Solver
                .of(Arrays.asList(
                        x -> (int) Math.pow(x, 2),
                        x -> (int) (Math.sin(x) * 100)))
                .apply(getRange(0, 10));
    }

    public static void calcFWithEmpty() {
        LOG.info("Calc F values with empty mapper.");
        Solver.of(new ArrayList<>());
    }

    public static void warnExample() {
        LOG.info("Some warning may be here.");
        new Random().ints(10, 0, 10)
                .filter(x -> x > 7)
                .findAny().ifPresent(x -> LOG.warn("RND number > 7: " + x));
    }

    private static List<Integer> getRange(int low, int upper) {
        return IntStream.rangeClosed(low, upper).boxed().collect(Collectors.toList());
    }
}
