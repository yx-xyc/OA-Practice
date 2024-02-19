#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int subsegment_sort(vector<int> arr, int size) {
    vector<int> sorted_arr(arr);
    sort(sorted_arr.begin(), sorted_arr.end());
    // for (auto n:arr) {
    //     cout << n << '\t';
    // }
    // cout << endl;
    // for (auto n:sorted_arr) {
    //     cout << n << '\t';
    // }
    // cout << endl;
    int ans = 0;
    int max_so_far = 0;
    for (int i=0;i<size;i++) {
        max_so_far = max(max_so_far, arr[i]);
        if (max_so_far==sorted_arr[i]) {
            ans++;
        }
    }
    return ans;
}

int main() {
    vector<int> arr{2,5,1,9,7,6};
    int n = 6;
    int res = subsegment_sort(arr, n);
    cout << res << endl;
    return 0;
}