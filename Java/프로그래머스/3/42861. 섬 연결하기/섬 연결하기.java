import java.util.PriorityQueue;

class Solution {
    private static int[] parent;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < costs.length; i++) {
            int start = costs[i][0];
            int end = costs[i][1];
            int value = costs[i][2];
            pq.add(new Edge(start, end, value));
        }
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        int cnt = 0;
        while (!pq.isEmpty() && cnt < n - 1) {
            Edge e = pq.remove();
            int parentA = find(e.start);
            int parentB = find(e.end);
            if (parentA != parentB) {
                answer += e.value;
                cnt++;
                union(e.start, e.end);
            }
        }
        
        return answer;
    }
    
    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
    
    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[a] = b;
        }
    }
}

class Edge implements Comparable<Edge> {
    int start;
    int end;
    int value;
    
    public Edge(int start, int end, int value) {
        this.start = start;
        this.end = end;
        this.value = value;
    }

    @Override
    public int compareTo(Edge e) {
        if (this.value - e.value > 0) {
            return 1;
        }
        return -1;
    }
}