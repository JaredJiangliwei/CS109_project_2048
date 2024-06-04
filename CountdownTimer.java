

public class CountdownTimer {
    private int seconds;

    public CountdownTimer(int totalSeconds) {
        this.seconds = totalSeconds;
    }

    public int getSeconds() {
        return seconds;
    }

    public void decrementSeconds() {
        if (seconds > 0) {
            seconds--;
        }
    }

    public boolean isFinished() {
        return seconds <= 0;
    }
}