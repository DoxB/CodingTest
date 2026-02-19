import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

class Solution {
    private static int[] cnt;
    private static int maxValue;
    private static ArrayList<Integer>[] adj;
    
    public int solution(int n, int[][] edge) {
        adj = new ArrayList[n + 1];
        cnt = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
            cnt[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < edge.length; i++) {
            int s = edge[i][0];
            int e = edge[i][1];
            adj[s].add(e);
            adj[e].add(s);
        }
        maxValue = 0;
        
        cnt[1] = 0;
        bfs(1);
        
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (cnt[i] == maxValue) {
                answer++;
            }
        }

        return answer;
    }
    
    private static void bfs(int s) {
        Queue<MyNode> q = new LinkedList<>();
        q.add(new MyNode(s, 0));
        while (!q.isEmpty()) {
            MyNode m = q.remove();
            int idx = m.num;
            int depth = m.cnt;
            if (maxValue < depth) {
                maxValue = depth;
            }
            for (int nxt : adj[idx]) {
                if (cnt[nxt] > depth + 1) {
                    cnt[nxt] = depth + 1;
                    q.add(new MyNode(nxt, depth + 1));
                }
            }
        }
    }
}

class MyNode {
    int num;
    int cnt;
    
    public MyNode(int num, int cnt) {
        this.num = num;
        this.cnt = cnt;
    }
}