package assignments;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class AccumulatorImpl implements Accumulator {
    private int totalValueOfAccumulator = 0;

    @Override
    public int accumulate(int... values) throws ExecutionException, InterruptedException {
        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        List<Future<Integer>> futures = new ArrayList<>();
        if (values != null && values.length > 0) {
            for (int i = 0; i < threads; i++) {
                int start = i * values.length / threads;
                int end = (i + 1) * values.length / threads;
                futures.add(executorService.submit(() -> {
                    int sum = 0;
                    for (int j = start; j < end; j++) {
                        sum = Math.addExact(sum, values[j]);
                        totalValueOfAccumulator = Math.addExact(totalValueOfAccumulator, values[j]);
                    }
                    return sum;
                }));
            }

            int result = 0;
            try {
                for (Future<Integer> future : futures) {
                    result = Math.addExact(result, future.get());
                }
            } catch (InterruptedException | ExecutionException e) {
                //e.printStackTrace();
                throw e;
            } finally {
                executorService.shutdown();
            }
            return result;
        }
        return 0;
    }

    @Override
    public int getTotal() {
        return totalValueOfAccumulator;
    }

    @Override
    public void reset() {
        totalValueOfAccumulator = 0;
    }
}
