import java.io.*;
import java.util.PriorityQueue;

public class Main {
    private static int[] parent;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int giveCable = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < n; j++) {
                int cable = converter(tmp.charAt(j));
                if (cable == 0) continue;
                if (i == j) {
                    giveCable += cable;
                } else {
                    pq.add(new Edge(i, j, cable));
                }
            }
        }

        int useCable = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.remove();
            if (find(edge.start) != find(edge.end)) {
                union(edge.start, edge.end);
                useCable++;
            } else {
                giveCable += edge.value;
            }
        }

        if (useCable != n - 1) {
            System.out.println("-1");
        } else {
            System.out.println(giveCable);
        }
    }

    private static int converter(char a) {
        if ('A' <= a && a <= 'Z') {
            return a - 38;
        } else if ('a' <= a && a <= 'z') {
            return a - 96;
        }
        return 0;
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    private static int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]);
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
    public int compareTo(Edge edge) {
        if (this.value > edge.value) return 1;
        else if (this.value == edge.value) return 0;
        return -1;
    }
}