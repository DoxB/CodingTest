class Solution {
    private static boolean[][] check;
    
    public int[] solution(int[][] arr) {
        int n = arr.length;
        int[] answer = new int[2];
        check = new boolean[n][n];
        int addNum = n;
        while (addNum > 0) {
            for (int i = 0; i < n; i += addNum) {
                for (int j = 0; j < n; j += addNum) {
                    if (!check[i][j] && zipper(i, j, addNum, arr)) {
                        if (arr[i][j] == 0) {
                            answer[0] += 1;
                        } else {
                            answer[1] += 1;
                        }
                    }
                }
            }
            addNum /= 2;
        }
        
        return answer;
    }
    
    private static boolean zipper(int y, int x, int l, int[][] arr) {
        int idx = arr[y][x];
        for (int i = y; i < y + l; i++) {
            for (int j = x; j < x + l; j++) {
                if (idx != arr[i][j]) return false;
            }
        }
        for (int i = y; i < y + l; i++) {
            for (int j = x; j < x + l; j++) {
                check[i][j] = true;
            }
        }
        return true;
    }
}