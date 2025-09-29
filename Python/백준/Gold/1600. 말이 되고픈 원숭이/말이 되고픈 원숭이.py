# 10:17 ~ one_day
import sys
from collections import deque

input = sys.stdin.readline

k = int(input())
w, h = map(int, input().split())

adj = []
for _ in range(h):
    arr = list(map(int, input().split()))
    adj.append(arr)

visited = [[[0] * (k+1) for _ in range(w)] for _ in range(h)] # 말 점프이후 방문여부 따로 생각해야함, 다 계산해야되니

move_y = [1, 0, -1, 0]
move_x = [0, 1, 0, -1]

h_move_y = [2, 1, -1, -2, -2, -1, 1, 2]
h_move_x = [1, 2, 2, 1, -1, -2, -2, -1]

def chech_outline(y, x):
    if 0 <= y < h and 0 <= x < w:
        return True
    return False

answer = []

def bfs(y, x):
    q = deque()
    q.append([y, x, 0, 0]) # 마지막 변수는 말처럼 움직인 횟수
    visited[y][x][0] = 1
    while len(q) > 0:
        cur_y, cur_x, cur_c, cur_h = q.popleft()
        if cur_y == h - 1 and cur_x == w - 1:
            answer.append(cur_c)
        
        if cur_h < k:
            for j in range(8):
                next_y = cur_y + h_move_y[j]
                next_x = cur_x + h_move_x[j]
                if chech_outline(next_y, next_x) and adj[next_y][next_x] == 0:
                    if visited[next_y][next_x][cur_h + 1] == 0:
                        visited[next_y][next_x][cur_h + 1] = 1
                        q.append([next_y, next_x, cur_c + 1, cur_h + 1])

        for i in range(4):
            next_y = cur_y + move_y[i]
            next_x = cur_x + move_x[i]
            if chech_outline(next_y, next_x) and adj[next_y][next_x] == 0:
                if visited[next_y][next_x][cur_h] == 0:
                    visited[next_y][next_x][cur_h] = 1
                    q.append([next_y, next_x, cur_c + 1, cur_h])
        
        

bfs(0, 0)

if len(answer) == 0:
    print(-1)
else:
    print(min(answer))