
public class Main {
    public static void main (String[] args) {

    }
}

class Solution {
    public static boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx >= sx && ty >= sy) {
            if (tx == ty) {
                // further move will cause either x or y to be 0
                break;
            }
            if (tx > ty) {
                if (ty > sy) {
                    tx %= ty;
                } else {
                    return (tx-sx)%ty == 0;
                }
            } else {
                if (tx > sx) {
                    ty %= tx;
                } else {
                    return (ty-sy)%tx == 0; 
                }
            }
        }
        return (tx==sx && ty==sy);
    }
}
