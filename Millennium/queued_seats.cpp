#include <iostream>
#include <queue>
#include <vector>
#include <unordered_map>

using namespace std;

vector<int> queued_seats(int size, vector<int> arr) {
    queue<pair<int, int>> q;
    vector<int> res(size);
    unordered_map<int, int> m;
    for (int i=0;i<size;i++) {
        q.push({arr[i], i+1});
    }
    while (!q.empty()) {
        pair<int, int> p = q.front();
        if (m.count(p.first)) {
            q.push(pair<int, int>{p.first+1, p.second});
            q.pop();
        } else {
            m[p.first]++;
            q.pop();  
            res[p.first-1] = p.second;
        }
    }
    
    // while (!q.empty()) {
    //     cout << q.front() << '\t';
    //     q.pop();
    // }
    return res;
};

int main() {
    int n = 5;
    vector<int> arr{1,2,3,2,4};
    vector<int> res = queued_seats(n, arr);
    for (auto n:res) {
        cout << n << '\t';
    }
    cout << endl;
    return 0;
}