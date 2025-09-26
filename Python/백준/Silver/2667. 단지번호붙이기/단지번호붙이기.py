# 08:25 ~ 09:08
import sys

sys.setrecursionlimit(100_000)
input = sys.stdin.readline

n = int(input())
adj = [input() for _ in range(n)]
visited = [[0] * n for _ in range(n)]

group_count = 0
global member_count
m = []

move_y = [1, 0, -1, 0]
move_x = [0, 1, 0, -1]

def check_outline(y, x):
    if 0 <= y < n and 0 <= x < n:
        return True
    return False

def dfs(y, x):
    global member_count
    visited[y][x] = 1
    if adj[y][x] == '1':
        member_count += 1
        for idx in range(4):
            g_y = y + move_y[idx]
            g_x = x + move_x[idx]
            if check_outline(g_y, g_x) and adj[g_y][g_x] == '1':
                if visited[g_y][g_x] == 0:
                    dfs(g_y, g_x)


member_count = 0

for y in range(n):
    for x in range(n):
        if adj[y][x] == '1' and visited[y][x] == 0:
            group_count += 1
            dfs(y, x)
            m.append(member_count)
            member_count = 0

print(group_count)
m.sort()
for ans in m:
    print(ans)