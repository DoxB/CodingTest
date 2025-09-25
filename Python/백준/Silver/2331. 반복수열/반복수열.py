import sys

input = sys.stdin.readline

a, p = input().split()

store = [int(a)]

def dfs(s):
    tmp = 0
    for idx in range(len(s)):
        tmp += int(s[idx]) ** int(p)
    if not tmp in store:
        store.append(tmp)
        dfs(str(tmp))
    else:
        store.append(tmp)

dfs(a)

for idx in range(len(store)):
    if store[-1] == store[idx]:
        answer = idx
        break

print(answer)