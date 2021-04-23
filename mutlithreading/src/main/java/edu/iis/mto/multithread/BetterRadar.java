package edu.iis.mto.multithread;

import java.util.concurrent.ExecutorService;

public class BetterRadar {
    private final PatriotBattery battery;
    private final int rockets;
    ExecutorService executor;

    public BetterRadar(PatriotBattery battery, int rockets, ExecutorService executor) {
        this.battery = battery;
        this.rockets = rockets;
        this.executor = executor;
    }

    public void notice(Scud enemyMissile) {
        launchPatriot(enemyMissile, rockets);
    }

    private void launchPatriot(Scud enemyMissile, int rocketCount) {
        Runnable launchPatriotTask = () -> {
            for (int i = 0; i < rocketCount; i++) {
                battery.launchPatriot(enemyMissile);
            }
        };
        executor.submit(launchPatriotTask);
    }

}
