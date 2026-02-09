import java.util.PriorityQueue;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        k = Math.min(enemy.length, k);
        
        for (int i = 0; i < k; i++) {
            pq.add(enemy[i]);
            answer++;
        }
        
        int start = k;
        
        while (start < enemy.length) {
            pq.add(enemy[start++]);
            int cur = pq.remove();
            if (n < cur) {
                break;
            } else {
                n -= cur;
                answer++;
            }
        }
        
        return answer;
    }
}