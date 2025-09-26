from itertools import combinations

def solution(orders, course):
    answer = []

    for num in course:
        find = {}
        for order in orders:
            o_find = list(combinations(order, num))
            for k in o_find:
                k = list(k)
                k.sort()
                k = tuple(k)
                if k in find.keys():
                    find[k] += 1
                else:
                    find[k] = 1
        if len(find.values()) != 0:
            max_order = max(find.values())
        
        if max_order == 1:
            continue
        
        candi = []
        for k, v in find.items():
            if v == max_order:
                candi.append(''.join(k))
        answer += candi
        
        answer.sort()
            
    return answer