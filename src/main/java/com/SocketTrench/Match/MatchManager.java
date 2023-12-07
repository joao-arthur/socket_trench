import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class MatchManager {
    private static final int FPS = 30;
    private MatchModel matchModel;

    public MatchManager(MatchModel matchModel) {
        this.matchModel = matchModel;
    }

    public void init() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            final var hasChanged = this.matchModel.player1.update();
            System.out.println("Task executed at: " + System.currentTimeMillis());
        };

        scheduler.scheduleAtFixedRate(task, 0, 1000 / FPS, TimeUnit.MILLISECONDS);
    }
}
