import java.util.PriorityQueue;
import java.util.ArrayList;

class Solution {
    private static ArrayList<Node>[] adj;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < fares.length; i++) {
            int one = fares[i][0];
            int two = fares[i][1];
            int value = fares[i][2];
            adj[one].add(new Node(two, value));
            adj[two].add(new Node(one, value));
        }
        
        int answer = 0;
        
        // 각자 출발하는 경우
        int[] costS = dks(n, s);
        
        // 방향없으니깐 거꾸로
        // A 출발 최단 거리 -> A에 도착하는 최단 거리
        int[] costA = dks(n, a);
        // B 출발 최단 거리 -> B에 도착하는 최단 거리
        int[] costB = dks(n, b);
        
        answer = costS[a] + costS[b];
        for (int i = 1; i <= n; i++) {
            if (i == s) continue;
            answer = Math.min(answer, costA[i] + costB[i] + costS[i]);
        }
        
        return answer;
    }
    
    private static int[] dks(int n, int start) {
        int[] cost = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            cost[i] = Integer.MAX_VALUE;
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        cost[start] = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.remove();
            for (Node nxt : adj[cur.target]) {
                if (nxt.value + cur.value < cost[nxt.target]) {
                    cost[nxt.target] = nxt.value + cur.value;
                    pq.add(new Node(nxt.target, nxt.value + cur.value));
                }
            }
        }
        
        return cost;
    }
}

class Node implements Comparable<Node> {
    int target;
    int value;
    
    public Node(int target, int value) {
        this.target = target;
        this.value = value;
    }
    
    @Override
    public int compareTo(Node node) {
        if (this.value - node.value > 0) {
            return 1;
        }
        return -1;
    }
}