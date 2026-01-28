import java.util.HashSet;

class Solution {
    public int solution(String dirs) {
        int answer = 0;
        int[] mY = {1, 0, -1, 0};
        int[] mX = {0, 1, 0, -1};
        int n = dirs.length();
        
        int curY = 0;
        int curX = 0;
        HashSet<String> s = new HashSet<>();
        
        for (int i = 0; i < n; i++) {
            char cmd = dirs.charAt(i);
            int prevY = curY;
            int prevX = curX;
            if (cmd == 'U') {
                if (!isOk(curY + mY[0], curX + mX[0])) {
                    continue;
                }
                curY += mY[0];
                curX += mX[0];
            } else if (cmd == 'R') {
                if (!isOk(curY + mY[1], curX + mX[1])) {
                    continue;
                }
                curY += mY[1];
                curX += mX[1];
            } else if (cmd == 'D') {
                if (!isOk(curY + mY[2], curX + mX[2])) {
                    continue;
                }
                curY += mY[2];
                curX += mX[2];
            } else {
                if (!isOk(curY + mY[3], curX + mX[3])) {
                    continue;
                }
                curY += mY[3];
                curX += mX[3];
            }
            
            s.add(change(prevY, prevX, curY, curX));
        }
        
        answer = s.size();
        
        return answer;
    }
    private static boolean isOk(int curY, int curX) {
        return -5 <= curY && curY <= 5 && -5 <= curX && curX <= 5;
    }
    
    private static String change(int curY, int curX, int nxtY, int nxtX) {
        StringBuilder sb = new StringBuilder();
        if (curY > nxtY || curX > nxtX) {
            sb.append(Integer.toString(nxtY));
            sb.append(Integer.toString(nxtX));
            sb.append(Integer.toString(curY));
            sb.append(Integer.toString(curX));
        } else {
            sb.append(Integer.toString(curY));
            sb.append(Integer.toString(curX));
            sb.append(Integer.toString(nxtY));
            sb.append(Integer.toString(nxtX));
        }
        
        return sb.toString();
    }
}