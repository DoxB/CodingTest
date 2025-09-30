# 10:24 ~ 11:08
import sys

input = sys.stdin.readline
n = int(input())
adj = [list(map(int, input().split())) for _ in range(n)]
global min_cost
min_cost = 1e9
visited = [0] * n

def dfs(cur, start, cost):
    global min_cost
    if cost >= min_cost:
        return
    if sum(visited) == n and adj[cur][start] != 0:
        if cost + adj[cur][start] < min_cost:
            min_cost = cost + adj[cur][start]

    for idx in range(n):
        if visited[idx] == 0 and adj[cur][idx] != 0:
            visited[idx] = 1
            dfs(idx, start, cost + adj[cur][idx])
            visited[idx] = 0

visited[0] = 1
dfs(0, 0, 0)
print(min_cost)