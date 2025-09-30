# 12:48 ~ 13:32

import sys
sys.setrecursionlimit(100_000)
input = sys.stdin.readline
t = int(input())

def find(a, p):
    if p[a] != a:
        p[a] = find(p[a], p)
    return p[a]

def union(a, b, p):
    a = find(a, p)
    b = find(b, p)
    p[a] = b

answer = []

for _ in range(t):
    n = int(input())
    p = [i for i in range(0, n+1)]
    perm = list(map(int, input().split()))
    for idx in range(1, n+1):
        union(idx, perm[idx-1], p)
    
    for i in range(1, n+1):
        find(i, p)
    
    answer.append(len(set(p)) - 1)

for idx in range(t):
    print(answer[idx])