#include <vector>
#include <unordered_map>
#include <iostream>
using namespace std;

long countInvestablePeriods(vector<int> price, int min_price, int max_price) ;

int main() {
    vector<int> arr {3,2,2,1,3,3,4,3,2,3,2};
    int max_price = 4, min_price = 1;
    long res = countInvestablePeriods(arr, min_price, max_price);
    cout << res << endl;
}

long countInvestablePeriods(vector<int> price, int min_price, int max_price) {
    int size = price.size();
    long res = 0;
    int left, right;
    // cout << left << '\t' << right << endl;
    for (int i=0;i<size;i++) {
        left = i;
        if (price[left]<min_price || price[left]>max_price) {
            continue;
        } else {
            right = left;
            long theMax = price[right];
            long theMin = price[right];
            while (price[right]<=max_price && price[right]>=min_price) {
                long num = price[right];
                if (num<theMin) {
                    theMin = num;
                }
                if (num>theMax) {
                    theMax = num;
                }
                if (theMin==min_price && theMax==max_price) {
                    res++;
                }
                right++;
            }
        }
    }
    return res;
}