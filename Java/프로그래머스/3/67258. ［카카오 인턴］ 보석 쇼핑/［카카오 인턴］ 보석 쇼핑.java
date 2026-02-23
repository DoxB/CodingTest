import java.util.HashSet;
import java.util.HashMap;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        HashSet<String> hs = new HashSet<>();
        for (String g : gems) {
            hs.add(g);
        }
        int n = hs.size();
        HashMap<String, Integer> hm = new HashMap<>();
        int start = 0;
        int end = n - 1;
        for (int i = 0; i < n; i++) {
            hm.put(gems[i], hm.getOrDefault(gems[i], 0) + 1);
        }
        
        int minTotal = Integer.MAX_VALUE;
        while (start <= end) {
            if (hm.keySet().size() == n) {
                if (minTotal > (end - start + 1)) {
                    answer[0] = start + 1;
                    answer[1] = end + 1;
                    minTotal = end - start + 1;
                }
                if (hm.get(gems[start]) == 1) {
                    hm.remove(gems[start]);
                } else {
                    hm.put(gems[start], hm.get(gems[start]) - 1);
                }
                start++;
            } else {
                end++;
                if (end < gems.length) {
                    hm.put(gems[end], hm.getOrDefault(gems[end], 0) + 1);
                } else {
                    break;
                }
            }
            
        }
        return answer;
    }
}