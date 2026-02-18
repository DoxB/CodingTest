class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        
        int idx = s / n;
        int addCnt = s % n;
        if (idx == 0) {
            return new int[]{-1};
        }
        for (int i = 0; i < n; i++) {
            if (n - addCnt > i) {
                answer[i] = idx;
            } else {
                answer[i] = idx + 1;
            }
        }
        
        return answer;
    }
}