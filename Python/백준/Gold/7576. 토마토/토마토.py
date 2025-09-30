# 15:26 ~
import sys
from collections import deque

input = sys.stdin.readline
w, h = map(int, input().split())

start_tomato = []
adj = []

for i in range(h):
    tmp = list(map(int, input().split()))
    for j in range(w):
        if tmp[j] == 1:
            start_tomato.append([i, j])
    adj.append(tmp)

move_y = [1, 0, -1, 0]
move_x = [0, 1, 0, -1]

def check_outline(y, x, w, h):
    return 0 <= y < h and 0 <= x < w

def check_complete(adj, w, h):
    for i in range(h):
        for j in range(w):
            if adj[i][j] == 0:
                return False
    return True


def bfs(w, h):
    answer = 0
    q = deque()
    for y, x in start_tomato:
        q.append([y, x, 0])
    
    while len(q) > 0:
        cur_y, cur_x, day = q.popleft()
        if day > answer:
            answer = day
        for idx in range(4):
            nxt_y = cur_y + move_y[idx]
            nxt_x = cur_x + move_x[idx]
            if check_outline(nxt_y, nxt_x, w, h) and adj[nxt_y][nxt_x] == 0:
                adj[nxt_y][nxt_x] = 1
                q.append([nxt_y, nxt_x, day + 1])

    if not check_complete(adj, w, h):
        return -1
    return answer

answer = bfs(w, h)
print(answer)