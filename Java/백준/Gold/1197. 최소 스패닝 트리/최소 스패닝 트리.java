import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class Main {
    private static int[] parent;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        PriorityQueue<Edge> q = new PriorityQueue<Edge>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            q.add(new Edge(s, e, v));
        }

        int useEdge = 0;
        int result = 0;
        while (useEdge < n - 1) {
            Edge curEdge = q.remove();
            if (find(curEdge.start) != find(curEdge.end)) {
                union(curEdge.start, curEdge.end);
                result += curEdge.value;
                useEdge++;
            }
        }

        System.out.println(result);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
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
    public int compareTo(Edge edge) {
        if (this.value > edge.value) {
            return 1;
        } else if (this.value == edge.value) {
            return 0;
        }
        return -1;
    }
}