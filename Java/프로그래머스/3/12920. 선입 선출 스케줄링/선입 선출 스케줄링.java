class Solution {
    public int solution(int n, int[] cores) {
        int answer = 0;
        int minTime = 0;
        int maxTime = 50000 * 10000 / 2;
        int time = 0;
        long worked = 0;
                
        while (minTime <= maxTime) {
            long complete = cores.length;
            int midTime = (minTime + maxTime) / 2;
            for (int i = 0; i < cores.length; i++) {
                complete += (long)(midTime / cores[i]);
            }
            
            if (complete >= (long)n) {
                maxTime = midTime - 1;
            } else {
                minTime = midTime + 1;
                time = midTime;
                worked = complete;
            }
        }
        
        long remain = (long)n - worked;
        time++;
        
        while (remain > 0) {
            for (int i = 0; i < cores.length; i++) {
                if (time % cores[i] == 0) {
                    remain--;
                    answer = i + 1;
                    if (remain == 0) break;
                }
            }
            time++;
        }
        
        return answer;
    }
}

