# 11:09 ~ 11:18
import sys

input = sys.stdin.readline
n = int(input())
v = int(input())
adj = [[0] * n for _ in range(n)]
for _ in range(v):
    s1, s2 = map(int, input().split())
    s1 -= 1
    s2 -= 1
    adj[s1][s2] = adj[s2][s1] = 1
visited = [0] * n

def dfs(start):
    for idx in range(n):
        if visited[idx] == 0 and adj[start][idx] != 0:
            visited[idx] = 1
            dfs(idx)

visited[0] = 1
dfs(0)
print(sum(visited) - 1)