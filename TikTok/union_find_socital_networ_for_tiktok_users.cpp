#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

// int countComponents(int n, vector<vector<int>>& edges) {
//     vector<int> set(n);
//     for (int i=0;i<n;i++){
//         set[i] = i;
//     }
//     for (auto &edge : edges) {
//         int v = edge[0], w = edge[1];
//         while (set[v]!=v) {
//             v = set[v];
//             set[v] = set[set[v]];
//         }
//         while (set[w]!=w) {
//             w = set[w];
//             set[w] = set[set[w]];
//         }
//         set[v] = w;
//         if (v!=w) {
//             n--;
//         }
        
//     }
//     return n;
// }

class UnionFind {
public:
    vector<int> set;
    int N = 100000;
    int size;
    UnionFind(int size) {
        this->size = size;
        this->set.resize(size+1);
        for (int i=0;i<size;i++) {
            this->set[i] = -1;
        }
    }
    void Union(int a, int b) {
        int pa = Find(a);
        int pb = Find(b);
        int asize = -this->set[a];
        int bsize = -this->set[b];
        if (asize<bsize) {
            set[pa] += set[pb];
            set[pb] = pa;
        } else {
            set[pb] += set[pa];
            set[pa] = pb;
        }
    }
    int Find(int k) {
        if (this->set[k]<0) return k;
        return this->set[k] = Find(this->set[k]);
    }

};

int main() {
    int T;
    cin >> T;
    for (int i=0;i<T;i++) {
        int M;
        UnionFind uf(100000);
        cin >> M;
        for (int j=0;j<M;j++) {
            int a, b;
            cin >> a >> b;
            uf.Union(a,b);
        }
        int max = 1;
        for (int k=0;k<uf.set.size();k++){
            if (-uf.set[k]>max){
                max = -uf.set[k];
            }
        }
        cout << max << endl;
    }
}