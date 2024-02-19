import java.util.*;

public class GridLand{
    public static void main(String[] args) {
        String[] journeys = {"3,3,2", "6,6,4"};
        List<String> res = getSafePaths(journeys);
        for (String r : res) {
            System.out.println(r);
        }
    }
    public static List<String> getSafePaths(String[] journeys){
        int[][] dp = new int[11][11];
        for (int i=0;i<11;i++) {
            dp[0][i]=1;
            dp[i][0]=1;
        }
        for (int i=1;i<11;i++){
            for (int j=1;j<11;j++){
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        } 
        List<String> res = new ArrayList<>();
        for (String journey : journeys) {
            String[] parameters = journey.split(",");
            int x = Integer.parseInt(parameters[0]);
            int y = Integer.parseInt(parameters[1]);
            int k = Integer.parseInt(parameters[2])+1;
            String result = "";

            // if dp[x-1][y] >= k -> the path is start from H
            // else from V
            while (x>0 && y>0) {
                if (dp[x-1][y]>=k) {
                    result += "H";
                    x -= 1;
                } else {
                    result += "V";
                    k -= dp[x-1][y];
                    y -= 1;
                }
            }
            while (y>0) {
                result += "V";
                y -= 1;
            }
            while (x>0) {
                result += "H";
                x -= 1;
            }
            res.add(result);
        }
        return res;
    }
}
