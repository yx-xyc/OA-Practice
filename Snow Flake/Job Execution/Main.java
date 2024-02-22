public class Main {
    
    private static boolean canFinishJobs(int[] executionTime, int x, int y, int mid) {
        int[] times = executionTime.clone(); // Clone the array to avoid modifying the original
        for (int i = 0; i < mid; i++) {
            int indexMax = 0; // Find the index of the job with the maximum remaining time
            for (int j = 1; j < times.length; j++) {
                if (times[j] > times[indexMax]) {
                    indexMax = j;
                }
            }
            for (int j = 0; j < times.length; j++) {
                if (j == indexMax) {
                    times[j] -= x; // Reduce the major job by x seconds
                } else if (times[j] > 0) {
                    times[j] -= y; // Reduce the other jobs by y seconds
                }
            }
        }
        // Check if all jobs are completed
        for (int time : times) {
            if (time > 0) {
                return false; // If any job is not completed, return false
            }
        }
        return true; // All jobs are completed
    }

    public static int getMinimumOperations(int[] executionTime, int x, int y) {
        int maxExecutionTime = 0;
        for (int time : executionTime) {
            maxExecutionTime = Math.max(maxExecutionTime, time);
        }
        
        int low = 0;
        int high = (int)((long)maxExecutionTime / y); // The initial high value
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            
            if (canFinishJobs(executionTime, x, y, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        
        return low;
    }

    public static void main(String[] args) {
        int[] executionTime = {3, 4, 1, 7, 6};
        int x = 4;
        int y = 2;
        
        int result = getMinimumOperations(executionTime, x, y);
        System.out.println("Minimum operations needed: " + result);
    }
}
