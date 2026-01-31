class Solution {
    private static int idxN;
    private static int[][] board;
    private static int[] mY = {1, 1, 1};
    private static int[] mX = {-1, 0, 1};
    private static int answer = 0;
    
    public int solution(int n) {
        idxN = n;
        board = new int[idxN][idxN];
        
        for (int i = 0; i < n; i++) {
            dfs(0, i, 1);
        }
        
        return answer;
    }
    
    private static boolean isOk(int y, int x) {
        return 0 <= y && y < idxN && 0 <= x && x < idxN;
    }
    
    private static void dfs(int y, int x, int cnt) {
        int[] c = new int[3];
        
        if (cnt == idxN) {
            answer++;
            return;
        }
        
        for (int i = 0; i < 3; i++) {
            int idxY = y;
            int idxX = x;
            while (isOk(idxY + mY[i], idxX + mX[i])) {
                idxY += mY[i];
                idxX += mX[i];
                board[idxY][idxX]++;
                c[i]++;
            }
        }
        
        for (int i = 0; i < idxN; i++) {
            if(isOk(y + 1, i) && board[y + 1][i] == 0) {
                dfs(y + 1, i, cnt + 1);
            }
        }
        
        for (int i = 0; i < 3; i++) {
            int idxY = y;
            int idxX = x;
            while (isOk(idxY + mY[i], idxX + mX[i])) {
                idxY += mY[i];
                idxX += mX[i];
                board[idxY][idxX]--;
                c[i]--;
            }
        }
    }
}