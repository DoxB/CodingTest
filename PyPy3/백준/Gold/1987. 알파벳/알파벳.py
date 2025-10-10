# 13:33 ~ 14:00
import sys
# 알파벳: 26개 'A'= 65
# sys.setrecursionlimit(100_000)
input = sys.stdin.readline

r, c = map(int, input().split())
adj = [input() for _ in range(r)]
visited = [0] * 26
move_y = [1, 0, -1, 0]
move_x = [0, 1, 0, -1]

global max_count
max_count = 0

def check_outline(y, x):
    return 0 <= y < r and 0 <= x < c

def dfs(y, x, count):
    global max_count
    visited[ord(adj[y][x]) - 65] = 1
    if max_count < count:
        max_count = count

    for idx in range(4):
        nxt_y = y + move_y[idx]
        nxt_x = x + move_x[idx]
        if check_outline(nxt_y, nxt_x) and visited[ord(adj[nxt_y][nxt_x]) - 65] == 0:
            dfs(nxt_y, nxt_x, count + 1)
            visited[ord(adj[nxt_y][nxt_x]) - 65] = 0

dfs(0, 0, 1)
print(max_count)