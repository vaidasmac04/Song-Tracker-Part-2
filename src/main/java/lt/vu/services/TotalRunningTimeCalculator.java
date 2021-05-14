package lt.vu.services;

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
public class TotalRunningTimeCalculator implements Serializable {

    @Futureable
    public Future<Integer> calculateTotalRunningTime(int count) {

        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {

        }

        final Integer totalRunningTime = 3 * count;
        return new AsyncResult<>(totalRunningTime);
    }
}