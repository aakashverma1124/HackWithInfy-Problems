# Problem: Max Funds
# Video Link: https://youtu.be/t26uc1PGm7U
# Instagram: https://www.instagram.com/aakashverma1102/
# LinkedIn: https://www.linkedin.com/in/aakashverma1124/

def dfs(temp, node, visited, graph):
    temp.append(node)
    visited[node] = True
    for neighbour in graph[node]:
        if visited[neighbour] is not True:
            temp = dfs(temp, neighbour, visited, graph)
            
    return temp

def max_funds(n, arr, p, pairs):
    graph = dict()
    for i in range(1, n + 1):
        graph[i] = list()
    for pair in pairs:
        graph[pair[0]].append(pair[1])
        graph[pair[1]].append(pair[0])

    visited = [False] * (n + 1)
    components = []   
    
    for v in range(1, n + 1):
        if len(graph[v]) > 0 and visited[v] is not True:
            temp = []
            components.append(dfs(temp, v, visited, graph))
        elif (len(graph[v]) == 0):
            components.append([v])

    max_fund = 0
    for component in components:
        total = 0
        for i in component:
            total += arr[i - 1]
        max_fund = max(max_fund, total)
    return max_fund

if __name__ == '__main__':
    
    n = int(input())
    arr = list(map(int, input().split()))
    p = int(input())
    pairs = list()
    for i in range(p):
        a, b = tuple(map(int, input().split()))
        pairs.append([a, b])
    ans = max_funds(n, arr, p, pairs)
    print(ans)