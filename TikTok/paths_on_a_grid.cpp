#include <vector>
#include <iostream>

using namespace std;

int uniquePaths(int m, int n);

int main() {
    int n = 2, m = 2;
    cout << uniquePaths(2, 2);
    return 0;
}

int uniquePaths(int m, int n) {
    vector<vector<int>> dp (m, vector<int>(n,1));
    for (auto &m:dp){
        for (auto &n:m){
            cout << n << '\t';
        }
        cout << endl;
    }
    for (int i=1;i<m;i++){
        for (int j=1;j<n;j++){
            dp[i][j] = dp[i-1][j] + dp[i][j-1];
        }
    }
    return dp[m-1][n-1];
}