// Leet code 945
#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>
using namespace std;

int minIncrementForUnique(vector<int>& nums);

int main() {
    vector<int> arr {3,2,1,2,7}; 
    cout << minIncrementForUnique(arr) << endl;
    return 0;
}

int minIncrementForUnique(vector<int>& nums) {
    // get size for iteration
    int size = nums.size();
    // target: to make all the element in the array to be unique
    // assume the array is unique, sort it will make all the value different
    // we sort the array first, since no operation has been performed, 
    // there would be continuous same value
    // whenever we encounter situation:
    //      the next value is smaller or equal to the previous one
    //      (smaller is possible since to make cur value large enough to be unique, 
    //      it can be increased to even larger than the next one)
    //      we do:
    //          increase the next one to be current + 1
    // After we loop through all element in the array, the array become unique
    // then sum up all the value in the array
    // it is minimal increment since the array is sorted, we are increasing the closest 
    // original to the value to be unique
    sort(nums.begin(), nums.end());
    for (int i=1;i<size;i++) {
        if (nums[i] <= nums[i-1]) {
            nums[i] = nums[i-1] + 1;
        }
    }
    return accumulate(nums.begin(), nums.end(),0);
}