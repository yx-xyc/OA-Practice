#include <vector>
#include <unordered_map>
#include <iostream>
using namespace std;

int findMinimumLengthSubarray(vector<int> arr, int k);

int main() {
    vector<int> arr {2,2,1,1,3,5};
    int k = 3;
    int res = findMinimumLengthSubarray(arr, k);
    cout << res << endl;
}
int findMinimumLengthSubarray(vector<int> arr, int k) {
    unordered_map<int, int> m;
    int size = arr.size();
    int left=0, right=0, length=size;
    while (right<size) {
        while (m.size()<k) {
            int num = arr[right];
            right++;
            m[num]++;
        }
        while (m.size()>k-1){
            int toRemove = arr[left];
            left++;
            m[toRemove]--;
            if (m[toRemove]==0) m.erase(toRemove);
            // for (auto pair:m){
            //     cout << pair.first << '\t' << pair.second << endl;
            // }
            // cout << endl;
        }
        if ((right-left+1)<length&&m.size()==k-1) length = right-left+1;
    }
    return length;
}