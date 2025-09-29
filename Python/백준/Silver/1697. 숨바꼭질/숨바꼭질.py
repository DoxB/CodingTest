# 13:05 ~ 13:24
import sys
from collections import deque

input = sys.stdin.readline
n, k = map(int, input().split())
visited = [0] * 100001

def bfs(n, k):
    q = deque()
    q.append([n, 0])

    while len(q) > 0:
        cur, time = q.popleft()
        if cur == k:
            return time
        if 0 <= cur + 1 < 100001 and visited[cur + 1] == 0:
            q.append([cur + 1, time + 1])
            visited[cur + 1] = 1
        if 0 <= cur - 1 < 100001 and visited[cur - 1] == 0:
            q.append([cur - 1, time + 1])
            visited[cur - 1] = 1
        if 0 <= cur * 2 < 100001 and visited[cur * 2] == 0:
            q.append([cur * 2, time + 1])
            visited[cur * 2] = 1

answer = bfs(n, k)
print(answer)