# 09:22 ~ 09:47
import sys

sys.setrecursionlimit(100_000)
input = sys.stdin.readline

n = int(input())
visited = [[0] * n for _ in range(n)]
adj = []

for _ in range(n):
    arr = list(map(int, input().split()))
    adj.append(arr)

move_y = [1, 0 ,-1, 0]
move_x = [0, 1, 0, -1]

def check_outline(y, x):
    if 0 <= y < n and 0 <= x < n:
        return True
    return False

def dfs(y, x, h):
    visited[y][x] = 1
    for idx in range(4):
        g_y = y + move_y[idx]
        g_x = x + move_x[idx]
        if check_outline(g_y, g_x) and adj[g_y][g_x] > h:
            if visited[g_y][g_x] == 0:
                dfs(g_y, g_x, h)

cc = []

for h in range(0, 101):
    group_count = 0
    for y in range(n):
        for x in range(n):
            if adj[y][x] > h and visited[y][x] == 0:
                dfs(y, x, h)
                group_count += 1
    cc.append(group_count)

    if group_count == 0:
        break

    visited = [[0] * n for _ in range(n)]
    

print(max(cc))