import java.util.*;

class Main{
    public static void main(String[] args) {
        Integer[] arr1 = {0, 3, 6, 5};
        Integer[] arr2 = {3, 7, 4, 0};
        System.out.println(getChannelRating(new ArrayList<>(Arrays.asList(arr1))));
        System.out.println(getChannelRating(new ArrayList<>(Arrays.asList(arr2))));

    }

    public static int getChannelRating(List<Integer> views) {
        Map<Integer, Integer> freq = new HashMap<>(); // map: XOR value -> frequency
        int curr = 0; // value of views[0] xor views[1] xor ... xor views[i]
        int prev = 0; // values of views[0] xor views[1] xor ... xor views[i-2]
        int mod = 1000000007;
        int idx = 0;
        int res = 0;
        for (int v : views) {
            curr ^= v;
            // after idex >=, then it is possible to construct special array
            if (idx >= 2) {
                // record all the prev values appeared
                freq.put(prev, freq.getOrDefault(prev, 0) + 1);
                prev ^= views.get(idx - 2);
                // we found a prev value that equals to the curr value
                // all the prev position that have this value could construct a special array
                // math proof based on the property of XOR
                if (freq.containsKey(curr)) {
                    // add counts to result and then mod it
                    res += freq.get(curr);
                    res %= mod;
                }
            }
            idx++;
        }
        return res;
    }
}