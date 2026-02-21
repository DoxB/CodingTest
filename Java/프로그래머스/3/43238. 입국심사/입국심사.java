class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long minTime = 0;
        long maxTime = 1_000_000_000L * 100_000L;
        
        while (minTime <= maxTime) {
            long sol = 0;
            long midTime = (minTime + maxTime) / 2;
            for (int i = 0; i < times.length; i++) {
                sol += midTime / times[i];
            }
            
            if (sol >= n) {
                maxTime = midTime - 1;
                answer = midTime;
            } else {
                minTime = midTime + 1;
            }
        }
        
        
        return answer;
    }
}