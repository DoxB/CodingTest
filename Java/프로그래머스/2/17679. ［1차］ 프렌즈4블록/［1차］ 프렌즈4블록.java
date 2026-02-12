import java.util.Stack;

class Solution {
    private static char[][] game;
    private static boolean[][] erase;
    
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        game = new char[m][n];
        
        for (int i = 0; i < m; i++) {
            game[i] = board[i].toCharArray();
        }
        
        int prevAnswer = -1;
        while (prevAnswer != answer) {
            prevAnswer = answer;
            erase = new boolean[m][n];
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    if (game[i][j] != '.' && search(i, j)) {
                        erase[i][j] = true;
                        erase[i + 1][j] = true;
                        erase[i][j + 1] = true;
                        erase[i + 1][j + 1] = true;
                    }
                }
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (erase[i][j]) {
                        game[i][j] = '.';
                        answer++;
                    }
                }
            }

            Stack<Character> sk = new Stack<>();
            for (int j = 0; j < n; j++) {
                for (int i = 0; i < m; i++) {
                    if (game[i][j] != '.') {
                        sk.add(game[i][j]);
                        game[i][j] = '.';
                    }
                }
                int curIdx = m - 1;
                while (!sk.isEmpty()) {
                    game[curIdx--][j] = sk.pop();
                }
            }
        }
        
        return answer;
    }
    
    private static boolean search(int y, int x) {
        char idxChar = game[y][x];
        if (idxChar != game[y + 1][x]) return false;
        if (idxChar != game[y + 1][x + 1]) return false;
        if (idxChar != game[y][x + 1]) return false;
        return true;
    }
}