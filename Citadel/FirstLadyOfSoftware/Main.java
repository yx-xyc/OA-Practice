public class Main {
    public static void main(String[] args) {
        System.out.println(findNumberOfWays(3, 2));  
        System.out.println(findNumberOfWays(3, 3));  
    }
    private static final int MOD = 1000000007;

    public static int findNumberOfWays(int n_intervals, int m_processes) {
        if (m_processes==1 && n_intervals>1) {
            return 0;
        }
        long result = m_processes;
        for (int i=2;i<=n_intervals;i++) {
            result = (result*(m_processes-1)) % MOD;
        }
        return (int) result;
    }
}
