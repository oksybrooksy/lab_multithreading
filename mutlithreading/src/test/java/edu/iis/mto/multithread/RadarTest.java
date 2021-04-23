package edu.iis.mto.multithread;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import java.util.concurrent.ExecutorService;

@ExtendWith(MockitoExtension.class)
class RadarTest {

    @Mock
    private PatriotBattery batteryMock;
    @Mock
    ExecutorService executorService;

    @Test
    @BeforeEach
    void startUp() {
        when(executorService.submit(any(Runnable.class))).thenAnswer(invocation -> {
            ((Runnable) invocation.getArgument(0)).run();
            return null;
        });
    }

    @RepeatedTest(10)
    void launchPatriotOnceWhenNoticesAScudMissle() {
        int amountOfRockets = 1;
        BetterRadar radar = new BetterRadar(batteryMock, amountOfRockets, executorService);
        Scud scud = new Scud();
        radar.notice(scud);
        verify(batteryMock, times(amountOfRockets)).launchPatriot(scud);
    }

    @RepeatedTest(10)
    void launchPatriotTenTimesWhenNoticesAScudMissle() {
        int amountOfRockets = 10;
        BetterRadar radar = new BetterRadar(batteryMock, amountOfRockets, executorService);
        Scud scud = new Scud();
        radar.notice(scud);
        verify(batteryMock, times(amountOfRockets)).launchPatriot(scud);
    }

    @RepeatedTest(10)
    void launchPatriotZeroTimes() {
        int amountOfRockets = 0;
        BetterRadar radar = new BetterRadar(batteryMock, amountOfRockets, executorService);
        Scud scud = new Scud();
        radar.notice(scud);
        verify(batteryMock, times(amountOfRockets)).launchPatriot(scud);
    }

}
