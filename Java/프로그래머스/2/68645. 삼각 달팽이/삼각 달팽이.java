class Solution {
    public int[] solution(int n) {
        int[][] arr = new int[n][n];
        int[] my = {1, 0, -1};
        int[] mx = {0, 1, -1};
        
        int curY = 0;
        int curX = 0;
        int num = 1;
        int idx = 0;
        while (true) {
            if (curY >= n || curX >= n || arr[curY][curX] != 0) {
                break;
            }
            arr[curY][curX] = num++;
            int nxtY = curY + my[idx % 3];
            int nxtX = curX + mx[idx % 3];
            if (nxtY >= n || nxtX >= n || arr[nxtY][nxtX] != 0) {
                idx++;
                nxtY = curY + my[idx % 3];
                nxtX = curX + mx[idx % 3];
            }
            curY = nxtY;
            curX = nxtX;
        }
        
        int[] answer = new int[num - 1];
        int curIdx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                answer[curIdx] = arr[i][j];
                curIdx++;
            }
        }
        return answer;
    }
}