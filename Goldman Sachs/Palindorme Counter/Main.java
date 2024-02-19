
public class Main {
    public static void main(String[] args) {
        String s = "abccba";
        System.out.println(Solution.countPalindromes(s));
    }
}

class Solution {
    public static int countPalindromes(String s) {
        int result = 0;
        for (int i=0;i<s.length();i++) {
            // as itself
            result++;
            // as odd string;
            int l = i-1, r = i+1;
            while (l>=0 && r<s.length()) {
                if (s.charAt(l)==s.charAt(r)) {
                    result++;
                    l--;
                    r++;
                } else {
                    break;
                }
            }
            // as even string;
            l = i;
            r = i+1;
            while (l>=0 && r<s.length()) {
                if (s.charAt(l)==s.charAt(r)) {
                    result++;
                    l--;
                    r++;
                } else {
                    break;
                }
            }
        }
        return result;
    }
}