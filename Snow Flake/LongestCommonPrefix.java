public class LongestCommonPrefix {
    public static void main(String[] args){
        String[] strings = {"flower","flow","flight","flo"};
        System.out.println(longestCommonPrefix(strings));
    }
    // recursion
    public static String longestCommonPrefix(String[] strings) {
        // if the array is empty, return empty string
        if (strings == null || strings.length == 0) return "";
        return longestCommonPrefix(strings, 0, strings.length-1);
    }
    // Divide and Conquer
    private static String longestCommonPrefix(String[] strings, int l, int r) {
        // base case: if there is only one string, return it
        if (l==r) {
            return strings[l];
        } else {
            // divide the array into two parts
            int mid = (l + r)/2;
            // will return string only when there is only one string in the array
            String lcpLeft = longestCommonPrefix(strings, l, mid);
            String lcpRight = longestCommonPrefix(strings, mid+1, r);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }
    // find the common prefix of two strings
    private static String commonPrefix(String left, String right) {
        int min = Math.min(left.length(), right.length());
        for (int i=0;i<min;i++) {
            if (left.charAt(i)!=right.charAt(i)) {
                return left.substring(0,i);
            }
        }
        return left.substring(0, min);
    }
}
