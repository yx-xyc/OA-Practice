#include <vector>

using namespace std;

struct node{
    int value;
    struct node* left;
    struct node* right;
};

vector<int> quicksort(vector<int> nums) {
    int pivot = nums[nums.size()/2];
    int left = 0;
    int right = nums.size()-1;
    int temp;
    while (left < right){
        while (nums[left] < pivot) {
            left++;
        }
        while (nums[right] > pivot) {
            right--;
        }
        temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }


}

int main() {
    vector<int> arr {2,3,5,2,5,3};
    vector<int> res  = quicksort(arr);
}