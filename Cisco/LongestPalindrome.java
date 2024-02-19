package Cisco;

public class LongestPalindrome {
    public static void main(String[] args) {
        System.out.println(findLongestPalindrome("cabacdc"));
    }

    public static String findLongestPalindrome(String s) {
        int max = 0, idx = 0;
        for (int i=0; i<s.length();i++) {
            int oddLength = extendLength(s, i, i);
            int evenLength = extendLength(s, i, i+1);
            int oddIdx = i - oddLength/2;
            int evenIdx = i - evenLength/2 + 1;
            if (max < Math.max(oddLength, evenLength)) {
                max = Math.max(oddLength, evenLength);
                if (oddLength > evenLength) {
                    idx = oddIdx;
                } else if (evenLength < oddLength) {
                    idx = evenIdx;
                } else if (evenLength == oddLength) {
                    idx = s.charAt(oddIdx) < s.charAt(evenIdx) ? oddIdx : evenIdx;
                }
            }
        }
        if (max<2) return "None";
        return s.substring(idx, idx+max);
    }   
    private static int extendLength(String s, int i , int j) {
        for (;i>=0 && j<s.length(); i--, j++) {
            if (s.charAt(i)!=s.charAt(j)) {
                break;
            }
        }
        return j - i + 1 - 2;
    }

}
