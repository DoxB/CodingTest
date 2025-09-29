# 12:30 ~ 12:57
import sys

sys.setrecursionlimit(100_000)
input = sys.stdin.readline
n, v = map(int, input().split())

visited = [0] * n
adj = [[0] * n for _ in range(n)]

for _ in range(v):
    s1, s2 = map(int, input().split())
    s1 -= 1
    s2 -= 1
    adj[s1][s2] = adj[s2][s1] = 1

def dfs(start):
    for idx in range(len(adj[start])):
        if adj[start][idx] == 1 and visited[idx] == 0:
            visited[idx] = 1
            dfs(idx)

answer = 0
for i in range(n):
    if visited[i] == 0:
        visited[i] = 1
        answer += 1
        dfs(i)

print(answer)