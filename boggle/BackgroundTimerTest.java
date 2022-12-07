package boggle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackgroundTimerTest {
    @Test
    public void TimerTest() {
        BackgroundTimer timer = new BackgroundTimer(10);
        timer.run();
        assertEquals(0, timer.getTime());
    }
}
