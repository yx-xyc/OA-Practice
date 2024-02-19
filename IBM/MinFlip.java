class MinFlip {
    public static void main(String[] args) {
        System.out.println(testMinFlips("00110011", 0)); // Standard case; Expected output: 2
        System.out.println(testMinFlips("0011", 0)); // Shorter string; Expected output: 1
        System.out.println(testMinFlips("01", 1)); // Minimum length; Expected output: 1
        System.out.println(testMinFlips("0000", 0)); // All zeros; Expected output: 0
        System.out.println(testMinFlips("1111", 0)); // All ones; Expected output: 0
        System.out.println(testMinFlips("01010101", 4)); // Alternating characters; Expected output: 4
        System.out.println(testMinFlips("10101010", 4)); // Alternating characters starting with 1; Expected output: 4
        System.out.println(testMinFlips("", 0)); // Empty string; Expected output: 0 (no pairs)
        System.out.println(testMinFlips("0110", 2)); // Two separate flips; Expected output: 2
    }

    public static int getMinFlips(String pwd) {
        char[] chars = pwd.toCharArray();
        if (chars.length < 2)
            return 0; // No pairs
        int flips = 0;
        for (int i = 0; i < chars.length; i += 2) {
            if (chars[i] != chars[i + 1]) {
                flips++;
            }
        }
        return flips;
    }

    public static boolean testMinFlips(String s, int expected) {
        int result = getMinFlips(s);
        if (result != expected) {
            System.out.println("Failed for input " + s + ". Expected " + expected + " but got " + result + ".");
            return false;
        }
        return true;
    }
}