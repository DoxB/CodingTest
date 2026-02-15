class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        long[] w = new long[1001];
        int n = weights.length;
        
        for (int i = 0; i < n; i++) {
            w[weights[i]]++;
        }
        
        for (int i = 100; i <= 1000; i++) {
            if (w[i] == 0) continue;
            
            // 1:1
            if (w[i] > 1) {
                answer += (w[i] * (w[i] - 1)) / 2;
            }
            
            // 2:3, 1:2, 3:4
            // i : target = 2 : 3
            if (i * 3 % 2 == 0 && (i * 3) / 2 <= 1000) {
                answer += w[i] * w[(i * 3) / 2];
            }
            // i : target = 1 : 2
            if (i * 2 <= 1000) {
                answer += w[i] * w[i * 2];
            }
            // i : target = 3 : 4
            if (i * 4 % 3 == 0 && (i * 4) / 3 <= 1000) {
                answer += w[i] * w[(i * 4) / 3];
            }
        }
        
        
        return answer;
    }
}