import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Edge[] edges = new Edge[m];
        long[] dist = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = Long.MAX_VALUE;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(s, e, v);
        }

        dist[1] = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Edge edge = edges[j];
                if (dist[edge.start] != Long.MAX_VALUE) {
                    if (dist[edge.end] > dist[edge.start] + edge.value) {
                        dist[edge.end] = dist[edge.start] + edge.value;
                    }
                }
            }
        }

        boolean flag = false;
        for (int i = 0; i < m; i++) {
            Edge edge = edges[i];
            if (dist[edge.start] != Long.MAX_VALUE) {
                if (dist[edge.end] > dist[edge.start] + edge.value) {
                    flag = true;
                }
            }
        }

        if (!flag) {
            for (int i = 2; i <= n; i++) {
                if (dist[i] == Long.MAX_VALUE) {
                    System.out.println("-1");
                } else {
                    System.out.println(dist[i]);
                }
            }
        } else {
            System.out.println("-1");
        }
    }
}

class Edge {
    int start;
    int end;
    int value;

    public Edge(int start, int end, int value) {
        this.start = start;
        this.end = end;
        this.value = value;
    }
}