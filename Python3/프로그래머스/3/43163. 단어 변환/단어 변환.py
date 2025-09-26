from collections import deque

def word_compare(w1, w2):
    count = 0
    for idx in range(len(w1)):
        if w1[idx] != w2[idx]:
            count += 1
        if count > 1:
            return False
    return True

def solution(begin, target, words):
    answer = 0
    
    q = deque()
    q.append([begin, 0])
    
    visited = [0] * len(words)
    while len(q) > 0:
        cur, count = q.popleft()
        if cur == target:
            answer = count
            break
        for idx, word in enumerate(words):
            if word_compare(cur, word) and visited[idx] == 0:
                visited[idx] = 1
                q.append([word, count + 1])
    
    return answer