import java.time.LocalTime;
import java.time.Duration;
public class Timer {
    
    LocalTime startTime = LocalTime.now();

    public void timeLeft() {
        
        LocalTime currentTime = LocalTime.now();
        Duration duration = Duration.between(startTime, currentTime);
        long timeElapsed = duration.toSeconds();
        int gameLength = 60;
        
        System.out.println("Time left: " + (gameLength - timeElapsed));
        if (duration.toSeconds() >= gameLength) {
            System.out.println("OUT OF TIME");
            System.exit(0);
        }
        
    }

}
