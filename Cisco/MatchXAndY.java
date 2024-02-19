package Cisco;

public class MatchXAndY {
    public static void main(String[] args) {
        System.out.println(matchXAndY(20, 5));
    }

    public static int matchXAndY(int x, int y) {
        int counter = 0;
        for (int i=0;i<=x;i++) {
            if (checkTarget(i, y)) counter++;
        }
        return counter;
    }

    private static boolean checkTarget(int value, int target) {
        int digitsSum = 0;
        String digits = String.valueOf(value);
        for (int i=0;i<digits.length();i++) {
            digitsSum += digits.charAt(i) - '0';
        }
        if (digitsSum==target) return true;
        else return false;
    }

}
