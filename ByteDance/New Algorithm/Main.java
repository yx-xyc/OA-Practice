import java.util.*;


class Main {
    public static void main(String[] args) {
        int[] array1 = {4,3,3,4,2};
        List<Integer> result1 = getMinimumCommoIntegers(array1);
        System.out.println(result1);
        System.out.println(getMinimumCommoIntegers(new int[] {7,1,4,5,6,7,3,2}));
    }
    public static List<Integer> getMinimumCommoIntegers(int[] nums) {
        int n = nums.length;
        List<List<Integer>> occ = new ArrayList<>(); // value -> [indexes]
        for (int i = 0; i < n + 10; i++) {
            occ.add(new ArrayList<>());
        }

        int[] maxD = new int[n + 10]; // ??
        Arrays.fill(maxD, Integer.MAX_VALUE);

        // for each num in nums
        for (int i = 0; i < n; i++) {
            occ.get(nums[i]).add(i); // add num to value occurrence : value -> [indexes]
            if (occ.get(nums[i]).size() == 1) {
                // first num at this value, let max distance be index, from 0 to index
                maxD[nums[i]] = i;
            } else {
                // encounter the same num again
                int last = occ.get(nums[i]).size() - 1; // fetch the index of last num of given value
                maxD[nums[i]] = Math.max(maxD[nums[i]], occ.get(nums[i]).get(last) - occ.get(nums[i]).get(last - 1) - 1);
                // if the distance between curr index and prev index larger than the original distance, update it
            }
        }

        // for each num also compare the distance [index, n] to update the max distance
        for (int i = 0; i < n + 10; i++) {
            if (!occ.get(i).isEmpty()) {
                maxD[i] = Math.max(maxD[i], n - occ.get(i).get(occ.get(i).size() - 1) - 1);
            }
        }

        // use a 2D array to sort the distance and the corresponding index
        List<int[]> sortD = new ArrayList<>();
        for (int i = 0; i < n + 10; i++) {
            sortD.add(new int[] { maxD[i], i }); // {maxDistance of current value, current value}
        }
        sortD.sort(Comparator.comparingInt(arr -> arr[0])); // compare the index 0 element, the distance, as comparator
        // for (int[] pair : sortD) {
        //     System.out.println(pair[0]+"\t"+pair[1]);
        // }
        int j = 0;
        int minV = Integer.MAX_VALUE;
        List<Integer> ans = new ArrayList<>();
        // for array size fo [1, n]
        for (int i = 1; i <= n; i++) {
            // iterate through sortD list, where the max distances are in increasing order
            while (
                j < sortD.size() && // ensure index still inside sortD array
                sortD.get(j)[0] < i // ensure the maximum distance of a num is less than subarray size
                ) {
                minV = Math.min(minV, sortD.get(j)[1]);
                j++;
            }
            ans.add(minV == Integer.MAX_VALUE ? -1 : minV);
        }
        return ans;
    }
}