#include <vector>
#include <unordered_map>
#include <iostream>
#include <algorithm>
using namespace std;

long countInvestablePeriods(vector<int> price, int min_price, int max_price) ;

int main() {
    vector<int> arr {4,5,3,3,1,3,5};
    int max_price = 5, min_price = 3;
    long res = countInvestablePeriods(arr, min_price, max_price);
    cout << res << endl;
}

long countInvestablePeriods(vector<int> price, int min_price, int max_price) {
    // get the size of the price array
    int size = price.size();
    // set result variable and the left bound
    long res = 0, left = 0;
    // set the position variable of minValue and maxValue
    int min_pos=-1, max_pos=-1;
    // check each right value
    for (int right=0;right<size;right++) {
        // get the value at the right bound
        int num = price[right];
        // if the value equal to the min value, update position of minValue
        if (num==min_price) {
            min_pos = right;
        }
        // if the value equal to the max value, update position of maxValue
        if (num==max_price) {
            max_pos = right;
        }
        // if the value is out of bound, update the left bound
        // because any sub_array that contains the value out of bound is invalid
        if (num < min_price || num > max_price) {
            left = right + 1;
        }
        // the number of possible arrary would be the distance 
        // between left bound and the min(min_pos, max_pos)
        // plus 1
        int sub_num = min(min_pos, max_pos)-left+1;
        // add to the result count if the sub_num is larger than 0
        res += max(0, sub_num);
        // cout << "Res:" << res << endl;
        // if (sub_num>0){
        //     for (int i=left;i<=right;i++) {
        //         cout << price[i] << '\t';
        //     }
        //     cout << endl;
        // }
    }
    return res;
}