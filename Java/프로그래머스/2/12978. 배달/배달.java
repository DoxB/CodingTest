import java.util.ArrayList;
import java.util.PriorityQueue;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        ArrayList<MyEdge>[] adj = new ArrayList[N + 1];
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }
        
        for (int i = 0; i < road.length; i++) {
            int s = road[i][0];
            int e = road[i][1];
            int v = road[i][2];
            adj[s].add(new MyEdge(e, v));
            adj[e].add(new MyEdge(s, v));
        }
        
        PriorityQueue<MyEdge> pq = new PriorityQueue<>();
        pq.add(new MyEdge(1, 0));
        dist[1] = 0;
        
        while (!pq.isEmpty()) {
            MyEdge tmp = pq.remove();
            int cur = tmp.end;
            if (visited[cur] == true) continue;
            visited[cur] = true;
            
            for (MyEdge me : adj[cur]) {
                int nxt = me.end;
                int value = me.value;
                if (dist[cur] + value < dist[nxt]) {
                    dist[nxt] = dist[cur] + value;
                    pq.add(new MyEdge(nxt, dist[nxt]));
                }
            }
        }
        
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                answer++;
            }
        }

        return answer;
    }
}

class MyEdge implements Comparable<MyEdge> {
    int end;
    int value;
    
    public MyEdge(int end, int value) {
        this.end = end;
        this.value = value;
    }
    
    @Override
    public int compareTo(MyEdge e) {
        if (value - e.value > 0) {
            return 1;
        } else if (value - e.value == 0) {
            return 0;
        }
        return -1;
    }
}