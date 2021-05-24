package lt.vu.decorators;

import lt.vu.usecases.TotalRunningTime;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.ejb.AsyncResult;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Decorator
public abstract class TotalRunningTimeCalculatorDecorator implements ITotalRunningTimeCalculator {
    @Inject
    @Delegate
    @Any
    ITotalRunningTimeCalculator totalRunningTimeCalculator;

    @Override
    public Future<TotalRunningTimeResults> calculateTotalRunningTime(int count) throws ExecutionException, InterruptedException {
        final Future<TotalRunningTimeResults> totalRunningTimeTask = totalRunningTimeCalculator.calculateTotalRunningTime(count);

        TotalRunningTimeResults totalRunningTimeResults = totalRunningTimeTask.get();
        totalRunningTimeResults.setUnit("s");
        totalRunningTimeResults.setValue(totalRunningTimeResults.getValue() * 60);

        return new AsyncResult<>(totalRunningTimeResults);
    }
}