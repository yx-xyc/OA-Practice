public class RegularExpressionMatching{
    public static void main(String[] args) {
        boolean[] a = new boolean[2];
        System.out.println(a[0]);
    }
    public boolean isMatchRecur(String text, String pattern) {
        // if the pattern is empty, only empty string match
        // return whether text.isEmpty()
        if (pattern.isEmpty()) return text.isEmpty();
        // check whether the first character match
        // the first character only match
            // when text is not empty
            // the first character is same for both text and pattern
            // the first character of pattern is '.' which stands for any chracter
        boolean firstMatch = (
            !text.isEmpty() && 
            (pattern.charAt(0)==text.charAt(0) || pattern.charAt(0)=='.')
        );
        // if pattern is longer or equal to 2 and the second character of pattern is '*' which stands for zero or more
        if (pattern.length()>=2 && pattern.charAt(1)=='*') {
            // condition 1:
            // the first character of pattern appear zero times, keep match the text with following pattern
            // condition 2:
            // the first character matched and keep matching following text
                // since the second character of pattern is '*', 
                // we still need to check the first character of pattern with the text substring
                // untile we get to condition 1, the first character of pattern no longer appear
            return (isMatchRecur(text, pattern.substring(2)) || 
            (firstMatch && isMatchRecur(text.substring(1), pattern)));
        // if there is no '*', 
        } else {
            // check whether the first character match and match the following pattern
            return firstMatch && isMatchRecur(text.substring(1), pattern.substring(1));
        }
    }

    private int[][] memo;
    public boolean isMatchDP(String text, String pattern) {
        memo = new int[text.length()+1][pattern.length()+1];
        return dp(0,0,text,pattern);
    }
    public boolean dp(int i, int j, String text, String pattern) {
        // when we encounter a seen i,j combination, query the dp table directly
        if (memo[i][j]!=0) {
            return memo[i][j]==1;
        }
        // true or false for current i, j combination until end
        boolean ans;
        // base case, if we get done with all the pattern character,
        // we check whether there is still text left
        if (j==pattern.length()) {
            ans = i==text.length();
        // otherwise
        } else {
            // match the first character of pattern and text
            boolean first_match = (
                i<text.length() &&
                (pattern.charAt(j)==text.charAt(i) || pattern.charAt(j)=='.')
            );
            // for the first character of pattern if we have a '*' follow it
            // condition 1: 
            // assume the any character appear 0 times, skip two character for pattern
            // condition 2:
            // assume the first characters are matched, skip one character for text
            // first_match must be true under this situation
            if (j+1 < pattern.length() && pattern.charAt(j+1)=='*') {
                ans = dp(i,j+2,text,pattern) || (first_match && dp(i+1,j, text, pattern));
            // if no '*' appear, keep matching following characters if first_match is true
            } else {
                ans = first_match && dp(i+1, j+1, text, pattern);
            }
        }
        memo[i][j] = ans ? 1 : -1;
        return ans;
    }
}