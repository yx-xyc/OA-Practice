import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int maxVolume = 0;
    
    public static int phoneCalls(int n, int[] start, int[] duration, int[] volume) {
        backtrack(start, duration, volume, 0, 0, 0);
        return maxVolume;
    }

    private static void backtrack(int[] start, int[] duration, int[] volume, 
    int startIndex, int currentTime, int currentVolume) {
        maxVolume = Math.max(maxVolume, currentVolume);
        for (int i = startIndex; i < start.length; i++) {
            int nextStart = start[i];
            int nextEnd = start[i] + duration[i];
            // Check if the current call can be taken
            if (nextStart > currentTime) {
                // Take the call
                backtrack(start, duration, volume, i + 1, nextEnd, currentVolume + volume[i]);
            }
        }
    }

    public static void main(String[] args) {
        int[] start = { 1, 2, 4 };
        int[] duration = { 2, 2, 1 };
        int[] volume = { 1, 2, 3 };
        // Assuming calls data are added to the lists start, duration, and volume
        System.out.println("Maximum order volume: " + phoneCalls(3, start, duration, volume));

        int[] start2 = { 10, 5, 15, 18, 30 };
        int[] duration2 = { 30, 12, 20, 35, 35 };
        int[] volume2 = { 50, 51, 20, 25, 10 };
        // Assuming calls data are added to the lists start, duration, and volume
        System.out.println("Maximum order volume: " + phoneCalls(5, start2, duration2, volume2));
    }
}