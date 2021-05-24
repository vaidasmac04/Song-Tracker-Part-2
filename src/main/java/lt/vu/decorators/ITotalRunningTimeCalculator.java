package lt.vu.decorators;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public interface ITotalRunningTimeCalculator {
    Future<TotalRunningTimeResults> calculateTotalRunningTime(int count) throws ExecutionException, InterruptedException;
}
