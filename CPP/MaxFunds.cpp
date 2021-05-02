/*
* Problem: Max Funds
* Video Link: https://youtu.be/t26uc1PGm7U
* Instagram: https://www.instagram.com/aakashverma1102/
* LinkedIn: https://www.linkedin.com/in/aakashverma1124/
*/

#include <bits/stdc++.h>
using namespace std;

vector<int> dfs(vector<int> temp, int node, bool visited[], map<int, vector<int>> graph) {
    temp.push_back(node);
    visited[node] = true;
    for(int neighbour : graph[node]) {
		if(!visited[neighbour]) {
			temp = dfs(temp, neighbour, visited, graph);
        }
    }
	return temp;
}

int maxFunds(int n, int arr[], int p, vector<vector<int>> &pairs) {
	map<int, vector<int>> graph;
	for(int i = 1; i <= n; i++) {
		graph[i] = vector<int>();
    }

	for(vector<int> pair : pairs) {
		graph[pair[0]].push_back(pair[1]);
		graph[pair[1]].push_back(pair[0]);
	}

	bool visited[n + 1] = {false};
	vector<vector<int>> components;

	for(int v = 1; v <= n; v++) {
		if (graph[v].size() > 0 && !visited[v]) {
			vector<int> temp;
			components.push_back(dfs(temp, v, visited, graph));
		}
		else if(graph[v].size() == 0) {
			components.push_back(vector<int> {v});
		}
	}

	int maxFund = 0;
	for(vector<int> component : components) {
		int total = 0;
		for(int i : component) {
			total += arr[i - 1];
		}
		maxFund = max(maxFund, total);
	}
	return maxFund;
}

int main() {
    int n;
    cin >> n;
	int arr[n];
	for(int i = 0; i < n; i++) {
		cin >> arr[i];
	}
	int p;
	cin >> p;
    vector<vector<int>> pairs(p, vector<int> (2,0));
	for(int i = 0; i < p; i++) {
		cin >> pairs[i][0];
		cin >> pairs[i][1];
	}
	int ans = maxFunds(n, arr, p, pairs);
	cout << ans << endl;
    return 0;
}