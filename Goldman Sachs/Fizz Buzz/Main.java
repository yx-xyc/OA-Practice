import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution.fizzBuzz(15);
    }
}

class Solution {
    public static void fizzBuzz(int n) {
        HashMap<Integer, String> fizzBuzzMap = new HashMap<>() {
            {
                put(3, "Fizz");
                put(5, "Buzz");
            }
        };
        List<Integer> divisors = new ArrayList<>(Arrays.asList(3, 5));
        for (int i=1;i<=n;i++) {
            String ans = "";
            for (Integer key : divisors) {
                if (i%key == 0) {
                    ans += fizzBuzzMap.get(key);
                }
            }
            if (ans.equals("")) {
                ans += Integer.toString(i);
            }
            System.out.println(ans);
        }
    }
}