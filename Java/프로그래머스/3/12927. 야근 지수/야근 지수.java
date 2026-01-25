import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < works.length; i++) {
            pq.add(works[i]);
        }
        
        while (n > 0) {
            int cur = pq.remove();
            cur--;
            if (cur == -1) {
                return 0;
            }
            n--;
            pq.add(cur);
        }

        
        while (!pq.isEmpty()) {
            int cur = pq.remove();
            answer += Math.pow(cur, 2);
        }
        
        return answer;
    }
}