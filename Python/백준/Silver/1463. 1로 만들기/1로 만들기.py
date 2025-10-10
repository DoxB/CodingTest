# 14:16 ~ 14:25
import sys
from collections import deque

input = sys.stdin.readline

n = int(input())
visited = [0] * n

def bfs(n):
    q = deque()
    q.append([n, 0])

    while len(q) > 0:
        cur_n, count = q.popleft()
        if cur_n == 1:
            return count

        if cur_n % 3 == 0 and visited[cur_n // 3] == 0:
            visited[cur_n // 3] = 1
            q.append([cur_n // 3, count + 1])
        if cur_n % 2 == 0 and visited[cur_n // 2] == 0:
            visited[cur_n // 2] = 1
            q.append([cur_n // 2, count + 1])
        if visited[cur_n - 1] == 0:
            visited[cur_n - 1] = 1
            q.append([cur_n - 1, count + 1])

answer = bfs(n)
print(answer)