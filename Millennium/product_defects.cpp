#include <iostream>
#include <vector>

using namespace std;

int maximalSquare(vector<vector<int>>& matrix) {
    if (matrix.empty()) {
        return 0;
    }
    int m = matrix.size(), n = matrix[0].size(), sz = 0;
    vector<vector<int>> dp(m, vector<int>(n,0));
    for (int i=0;i<m;i++) {
        for (int j=0;j<n;j++) {
            if (i==0 || j==0 || matrix[i][j]==0) {
                dp[i][j] = matrix[i][j] - 0;
            } else {
                dp[i][j] = min(dp[i-1][j-1], min(dp[i-1][j], dp[i][j-1])) + 1;
            }
            sz = max(dp[i][j], sz);
        }
    }
    for (int i=0;i<dp.size();i++) {
        for (int j=0;j<dp[0].size();j++) {
            cout << dp[i][j] << '\t';
        }
        cout << endl;
    }
    return sz*sz;
}

int main() {
    vector<vector<int>> samples{
        {1,1,1,1,1},
        {1,1,1,0,0},
        {1,0,1,0,0},
        {1,1,1,0,0},
        {1,1,1,1,1},
    };
    int res = maximalSquare(samples);
    cout << res << endl;
    return 0;
}