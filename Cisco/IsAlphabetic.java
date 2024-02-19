package Cisco;

public class IsAlphabetic {
    public static void main(String[] args) {
        System.out.println(isAlphabetic("abc"));
        System.out.println(isAlphabetic("asd"));

    }

    public static String isAlphabetic(String s) {
        if (s.length()<2) return "0";
        char largestChar = s.charAt(0);
        for (int i=1;i<s.length();i++) {
            if (s.charAt(i)-largestChar>=0) {
                largestChar = s.charAt(i);
            } else {
                return String.valueOf(i);
            }
        }
        return "0";
    }

}
