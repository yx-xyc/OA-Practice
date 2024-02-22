public class Main {
    public static int[] getMaxUpgradedServers(int[] num_servers, int[] money, int[] sell, int[] upgrade) {
        int[] maxUpgrades = new int[num_servers.length];

        for (int i = 0; i < num_servers.length; i++) {
            int left = 0;
            int right = num_servers[i];

            while (left < right) {
                int mid = left + (right - left) / 2;
                // Calculate money after selling 'mid' number of servers.
                int moneyAfterSelling = money[i] + mid * sell[i];
                // Calculate how many servers we can upgrade after selling.
                int upgradesAfterSelling = moneyAfterSelling / upgrade[i];

                if (upgradesAfterSelling >= (num_servers[i] - mid)) {
                    // We can upgrade all remaining servers, so try selling fewer servers.
                    right = mid;
                } else {
                    // We can't upgrade all remaining servers, so try selling more.
                    left = mid + 1;
                }
            }

            // Calculate the final number of upgrades using the result from binary search.
            int finalMoney = money[i] + left * sell[i];
            maxUpgrades[i] = finalMoney / upgrade[i];
        }

        return maxUpgrades;
    }

    public static void main(String[] args) {
        int[] num_servers = {4, 3};
        int[] money = {8, 9};
        int[] sell = {4, 2};
        int[] upgrade = {4, 5};

        int[] result = getMaxUpgradedServers(num_servers, money, sell, upgrade);
        for (int r : result) {
            System.out.print(r + " ");
        }
    }
}
