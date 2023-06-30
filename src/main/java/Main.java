import assignments.Accumulator;
import assignments.AccumulatorImpl;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Accumulator accumulator = new AccumulatorImpl();
         int firstSum = accumulator.accumulate(2,3, 4);
        int secondSum = accumulator.accumulate(4);
        int total = accumulator.getTotal();

        accumulator.getTotal();
        System.out.println(firstSum);
        System.out.println(secondSum);
        System.out.println(total);
        accumulator.reset();
        total = accumulator.getTotal();
        System.out.println(total);
    }
}