import java.util.Queue;
import java.util.LinkedList;

class Solution {
    private static int[] move;
    private static int answer;
    private static boolean[] visited;
    
    public int solution(int x, int y, int n) {
        answer = -1;
        move = new int[]{n, 2, 3};
        visited = new boolean[y * 3 + 1];
        
        bfs(x, y);
        
        return answer;
    }
    
    private static void bfs(int start, int end) {
        Queue<MyNum> q = new LinkedList<>();
        q.add(new MyNum(start, 0));
        visited[start] = true;
        while (!q.isEmpty()) {
            MyNum m = q.remove();
            int curN = m.num;
            int curC = m.cnt;
            if (curN == end) {
                answer = curC;
                break;
            } else {
                int nxt0 = curN + move[0];
                int nxt1 = curN * move[1];
                int nxt2 = curN * move[2];
                
                if (nxt0 <= end && !visited[nxt0]) {
                    q.add(new MyNum(nxt0, curC + 1));
                    visited[nxt0] = true;
                }
                if (nxt1 <= end && !visited[nxt1]) {
                    q.add(new MyNum(nxt1, curC + 1));
                    visited[nxt1] = true;
                }
                if (nxt2 <= end && !visited[nxt2]) {
                    q.add(new MyNum(nxt2, curC + 1));
                    visited[nxt2] = true;
                }
            }
        }
    }
}

class MyNum {
    int num;
    int cnt;
    
    public MyNum(int num, int cnt) {
        this.num = num;
        this.cnt = cnt;
    }
}