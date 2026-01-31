class Solution {
    public int solution(String[] board) {
        int answer = -1;
        int cntO = 0;
        int cntX = 0;
        char win = '.';
        // 카운트
        for (int i = 0; i < 3; i++) {
            for (int j = 0 ; j < 3; j++) {
                char cur = board[i].charAt(j);
                if (cur == 'O') {
                    cntO++;
                } else if (cur == 'X') {
                    cntX++;
                }
            }
        }
        // 승리여부 8가지 체크
        // 세로
        for (int i = 0; i < 3; i++) {
            if (board[0].charAt(i) == board[1].charAt(i) && board[1].charAt(i) == board[2].charAt(i)) {
                if (win == '.') {
                    win = board[0].charAt(i);
                } else if (win != board[0].charAt(i)){
                    return 0;
                }
            }
        }
        // 가로
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == board[i].charAt(1) && board[i].charAt(1) == board[i].charAt(2)) {
                if (win == '.') {
                    win = board[i].charAt(0);
                } else if (win != board[i].charAt(0)) {
                    return 0;
                }
            }
        }
        // 대각
        if (board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2)) {
            if (win == '.') {
                win = board[0].charAt(0);
            } else if (win != board[0].charAt(0)){
                return 0;
            }
        }
        if (board[2].charAt(0) == (int)board[1].charAt(1) && (int)board[1].charAt(1) == board[0].charAt(2)) {
            if (win == '.') {
                win = board[2].charAt(0);
            } else if (win != board[2].charAt(0)){
                return 0;
            }
        }
        
        // 판단
        if (win == '.') {
            if (cntO == cntX || cntO - 1 == cntX) {
                return 1;
            }
        } else if (win == 'O') {
            if (cntO - 1 == cntX) {
                return 1;
            }
        } else {
            if (cntO == cntX) {
                return 1;
            }
        }

        return 0;
    }
}