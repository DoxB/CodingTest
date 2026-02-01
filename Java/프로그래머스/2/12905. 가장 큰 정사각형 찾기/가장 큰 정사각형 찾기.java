class Solution
{
    public int solution(int [][]board)
    {
        int answer = 0;
        int idxY = board.length;
        int idxX = board[0].length; 
        
        int[][] cal = new int[idxY][idxX];
        boolean isOne = false;
        for (int y = 0; y < idxY; y++) {
            for (int x = 0; x < idxX; x++) {
                if (y == 0) {
                    cal[y][x] = board[y][x];
                } else if (x == 0) {
                    cal[y][x] = board[y][x];
                } else {
                    if (board[y][x] == 1) {
                        cal[y][x] = Math.min(Math.min(cal[y - 1][x], cal[y][x - 1]), cal[y - 1][x - 1]) + 1;
                    } else {
                        cal[y][x] = 0;
                    }
                }
                
                if (!isOne && board[y][x] == 1) {
                    isOne = true;
                }
                
                if (cal[y][x] > answer) {
                    answer = cal[y][x];
                }
            }
        }
        
        if (!isOne) {
            answer = 0;
        } else {
            answer = (int)Math.pow(answer, 2);
        }
        
        return answer;
    }
}