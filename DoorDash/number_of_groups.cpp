#include <vector>

using namespace std;

class Solution {
public:
    int findCircleNum(vector<vector<int>>& isConnected) {
        if (isConnected.empty()) return 0;
        int size = isConnected.size();

        vector<int> parents(size, 0);
        for (int i=0;i<size;i++) {parents[i]=i;}
        int groups = size;
        for (int i=0;i<size;i++) {
            for (int j=i+1;j<size;j++) {
                if (isConnected[i][j]) {
                    int parent1 = find(i, parents);
                    int parent2 = find(j, parents);
                    if (parent1!=parent2) {
                        parents[parent1] = parent2;
                        groups--;
                    }
                }
            }
        }
        return groups;
    }
private:
    int find(int x, vector<int>& parents) {
        if (parents[x]==x) return x;
        else return parents[x] = find(parents[x], parents);
    }
};