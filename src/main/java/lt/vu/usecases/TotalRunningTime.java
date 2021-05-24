package lt.vu.usecases;

import lt.vu.decorators.TotalRunningTimeResults;
import lt.vu.persistence.SongsDAO;
import lt.vu.services.TotalRunningTimeCalculator;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SessionScoped
@Named
public class TotalRunningTime implements Serializable {
    @Inject
    private SongsDAO songsDAO;

    @Inject
    private TotalRunningTimeCalculator totalRunningTimeCalculator;
    private Future<TotalRunningTimeResults> totalRunningTimeCalculationTask = null;

    public String calculateTotalRunningTime() {

        try {
            totalRunningTimeCalculationTask = totalRunningTimeCalculator
                    .calculateTotalRunningTime(songsDAO.loadAll().size());
        } catch (InterruptedException e) {
            return  "/songs.xhtml?faces-redirect=true";
        }

        return  "/songs.xhtml?faces-redirect=true";
    }

    public boolean isTotalRunningTimeCalculationRunning() {
        return totalRunningTimeCalculationTask != null && !totalRunningTimeCalculationTask.isDone();
    }

    public String getTotalRunningTimeCalculationStatus() throws ExecutionException, InterruptedException {
        if (totalRunningTimeCalculationTask == null) {
            return null;
        } else if (isTotalRunningTimeCalculationRunning()) {
            return "Calculation is in progress";
        }

        return "Your playlist running time is: " +
                totalRunningTimeCalculationTask.get().getValue() + " " +
                totalRunningTimeCalculationTask.get().getUnit();
    }
}
