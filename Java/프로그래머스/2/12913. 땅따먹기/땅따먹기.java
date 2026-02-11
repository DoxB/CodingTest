class Solution {
    int solution(int[][] land) {
        int n = land.length;
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                int maxValue = 0;
                for (int k = 0; k < 4; k++) {
                    if (j != k && maxValue < land[i - 1][k]) {
                        maxValue = land[i - 1][k];
                    }
                }
                land[i][j] += maxValue; 
            }
        }
        
        int answer = 0;
        for (int i = 0 ; i < 4; i++) {
            if (land[n - 1][i] > answer) {
                answer = land[n - 1][i];
            }
        }
        return answer;
    }
}