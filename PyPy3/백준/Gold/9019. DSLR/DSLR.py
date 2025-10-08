# 14:51 ~ 15:40
import sys
from collections import deque

input = sys.stdin.readline
t = int(input())

def func_d(n):
    return (2 * n) % 10000

def func_s(n):
    if n == 0:
        return 9999
    return n - 1

def func_l(n):
    return ((n % 1000) * 10) + (n // 1000)

def func_r(n):
    return ((n % 10) * 1000) + (n // 10)

def bfs(start, end):
    # visited[end] = [start, 이니셜]
    visited = {}

    q = deque()
    visited[start] = [-1 , '']
    q.append(start)

    while len(q) > 0:
        cur_n = q.popleft()
        if cur_n == end:
            break

        for nxt_n, command in [[func_d(cur_n), 'D'], [func_s(cur_n), 'S'], [func_l(cur_n), 'L'], [func_r(cur_n), 'R']]:
            if not nxt_n in visited.keys():
                visited[nxt_n] = [cur_n, command]
                q.append(nxt_n)

    answer = []
    current_idx = end
    while True:
        prev_n, command = visited[current_idx]
        if prev_n == -1:
            break
        answer.append(command)
        current_idx = prev_n

    return answer[::-1]

result = []
for _ in range(t):
    start, end = map(int, input().split())
    comm = bfs(start, end)
    result.append(''.join(comm))

for ans in result:
    print(ans)