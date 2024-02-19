#include <vector>
#include <cmath>

using namespace std;

int minReorder(int n, vector<vector<int>> &connections);
int dfs(vector<vector<int>> &al, vector<bool> &visited, int from);

int main() {

}

int minReorder(int n, vector<vector<int>> &connections) {
    vector<vector<int>> graph(n);
    for (auto &connection : connections) {
        graph[connection[0]].push_back(connection[1]);
        graph[connection[1]].push_back(-connection[0]);
    }
    return dfs(graph, vector<bool>(n)={}, 0);
}

int dfs(vector<vector<int>> &graph, vector<bool> &visited, int from) {
    auto change = 0;
    visited[from] = true;
    for (auto to : graph[from]) {
        if (!visited[abs(to)])
            change += dfs(graph, visited, abs(to)) + (to>0);
    }
    return change;
}