package lt.vu.services;

import lt.vu.decorators.ITotalRunningTimeCalculator;
import lt.vu.decorators.TotalRunningTimeResults;
import lt.vu.entities.Song;
import lt.vu.persistence.SongsDAO;
import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.Future;

@ApplicationScoped
public class TotalRunningTimeCalculator implements ITotalRunningTimeCalculator, Serializable {

    @Futureable
    public Future<TotalRunningTimeResults> calculateTotalRunningTime(int count) throws InterruptedException {

        Thread.sleep(3000); // Simulate intensive work

        final TotalRunningTimeResults totalRunningTimeResults = new TotalRunningTimeResults();
        totalRunningTimeResults.setValue(3 * count);
        totalRunningTimeResults.setUnit("min");
        return new AsyncResult<>(totalRunningTimeResults);
    }
}