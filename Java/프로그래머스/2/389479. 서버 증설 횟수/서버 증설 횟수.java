import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        Queue<MyServer> q = new LinkedList<>();
        int n = players.length;
        int serverNum = 0;
        
        for (int i = 0; i < n; i++) {
            while (!q.isEmpty()) {
                MyServer s = q.peek();
                if (s.maxTime <= i) {
                    serverNum -= s.num;
                    q.remove();
                } else {
                    break;
                }
            }
            
            if (players[i] / m > serverNum) {
                int need = (players[i] / m) - serverNum;
                serverNum += need;
                answer += need;
                q.add(new MyServer(need, i + k));
            }
        }
        
        return answer;
    }
}

class MyServer {
    int num;
    int maxTime;
    
    public MyServer(int num, int maxTime) {
        this.num = num;
        this.maxTime = maxTime;
    }
}