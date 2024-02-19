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
    vector<int> cur(n+1,1);
    for (int i=1;i<=m;i++){
        for (int j=1;j<=n;j++){
            cur[j]+=cur[j-1];
        }
    }
    return cur[n];
}