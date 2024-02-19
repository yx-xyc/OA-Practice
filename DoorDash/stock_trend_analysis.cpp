#include <iostream>
#include <vector>
#include <set>
#include <queue>
#include <unordered_map>

using namespace std;

vector<int>findKthNextGreaterElement(vector<int> prices, int k);

int main() {
    vector<int> prices {1,4,2,5,3};
    int k = 2;
    vector<int> ret = findKthNextGreaterElement(prices, k);
}

vector<int>findKthNextGreaterElement(vector<int> prices, int k) {
    unordered_map<int, int> valueToIdx;
    for (int i=0;i<=prices.size();i++) {
        valueToIdx[prices[i]] = i+1;
    }
    vector<int> res(prices.size());
    bool notFound = false;
    for (int i=0;i<=prices.size();i++) {
        queue<int> mq;
        mq.push(prices[i]);
        int j=i;
        while (mq.size()<(k+1)) {
            if (j==prices.size()) {
                notFound = true;
                break;
            }
            j++;
            if (prices[j]>mq.front()) {
                mq.push(prices[j]);
            } else {
                continue;
            }
        }
        if (notFound) {res[i]=-1;}
        else {
            res[i] = mq.back();
        }
        notFound = false;
    }
    for (int i=0;i<res.size();i++) {
        if (res[i]!=-1) {
            res[i] = valueToIdx[res[i]];
        }
    }
    for (auto n:res) {
        cout << n << '\t' << endl;
    }
    return res;
}