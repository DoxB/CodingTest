# 10:20 ~ 10:55
import sys
from collections import deque

input = sys.stdin.readline

n, m = map(int, input().split())
adj = [input() for _ in range(n)]
visited = [[[0] * 2 for _ in range(m)] for _ in range(n)]
move_y = [1, 0, -1, 0]
move_x = [0, 1, 0, -1]

def check_outline(y, x):
    return 0 <= y < n and 0 <= x < m

def bfs(y, x):
    q = deque()
    q.append([y, x, 1, 0])

    while len(q) > 0:
        cur_y, cur_x, count, broken = q.popleft()
        if cur_y == n - 1 and cur_x == m - 1:
            return count

        for idx in range(4):
            nxt_y = cur_y + move_y[idx]
            nxt_x = cur_x + move_x[idx]
            if not check_outline(nxt_y, nxt_x):
                continue
            if adj[nxt_y][nxt_x] == '0':
                if visited[nxt_y][nxt_x][broken] == 0:
                    visited[nxt_y][nxt_x][broken] = 1
                    q.append([nxt_y, nxt_x, count + 1, broken])
            elif adj[nxt_y][nxt_x] == '1' and broken == 0:
                if visited[nxt_y][nxt_x][broken + 1] == 0 :
                    visited[nxt_y][nxt_x][broken + 1] = 1
                    q.append([nxt_y, nxt_x, count + 1, broken + 1])

    return -1

answer = bfs(0, 0)
print(answer)