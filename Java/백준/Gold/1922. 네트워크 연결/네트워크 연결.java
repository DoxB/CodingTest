import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class Main {
    private static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            long v = Long.parseLong(st.nextToken());

            pq.add(new Edge(s, e, v));
        }

        long answer = 0;
        int cnt = 0;
        while (!pq.isEmpty()) {
            if (cnt == n - 1) break;
            Edge cur = pq.remove();
            int start = cur.start;
            int end = cur.end;
            long value = cur.value;
            if (find(start) != find(end)) {
                union(start, end);
                answer += value;
                cnt++;
            }
        }

        System.out.println(answer);
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

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }


}

class Edge implements Comparable<Edge> {
    int start;
    int end;
    long value;

    public Edge(int start, int end, long value) {
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