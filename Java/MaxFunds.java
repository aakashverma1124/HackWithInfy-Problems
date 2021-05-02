/*
* Problem: Max Funds
* Video Link: 
* Instagram: https://www.instagram.com/aakashverma1102/
* LinkedIn: https://www.linkedin.com/in/aakashverma1124/
*/

import java.util.*;

class MaxFunds {

	public static ArrayList<Integer> dfs(ArrayList<Integer> temp, int node, boolean visited[], Map<Integer, ArrayList<Integer>> graph) {
		temp.add(node);
		visited[node] = true;
		for(int neighbour : graph.get(node)) {
			if(!visited[neighbour]) {
				temp = dfs(temp, neighbour, visited, graph);
			}
		}
		return temp;
	}

	public static int maxFunds(int n, int arr[], int p, int pairs[][]) {
		Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
		for(int i = 1; i <= n; i++) {
			graph.put(i , new ArrayList<>());
		}
		for(int pair[] : pairs) {
		    graph.get(pair[0]).add(pair[1]);
		    graph.get(pair[1]).add(pair[0]);
		    
			graph.put(pair[0], graph.get(pair[0]));
			graph.put(pair[1], graph.get(pair[1]));
		}
		boolean visited[] = new boolean[n + 1];
		ArrayList<ArrayList<Integer>> components = new ArrayList<>();

		for(int v = 1; v <= n; v++) {
			if(graph.get(v).size() > 0 && !visited[v]) {
				ArrayList<Integer> temp = new ArrayList<>();
				components.add(dfs(temp, v, visited, graph));
			} 
			else if (graph.get(v).size() == 0){
				ArrayList<Integer> temp = new ArrayList<>();
				temp.add(v);
				components.add(temp);
			}
		}

		int maxFund = 0;
		for(ArrayList<Integer> component : components) {
			int total = 0;
			for(int i : component) {
				total += arr[i - 1];
			}
			maxFund = Math.max(maxFund, total);
		}
		return maxFund;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int arr[] = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = scan.nextInt();
		}
		int p = scan.nextInt();
		int pairs[][] = new int[p][2];
		for(int i = 0; i < p; i++) {
			pairs[i][0] = scan.nextInt();
			pairs[i][1] = scan.nextInt();
		}
		int ans = maxFunds(n, arr, p, pairs);
		System.out.println(ans);
	}
}