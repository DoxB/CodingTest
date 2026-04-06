import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    private static int n, m, w;
    private static ArrayList<Edges> edges;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        int cnt = 0;
        while (cnt < TC) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            edges = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                edges.add(new Edges(s, e, v));
                edges.add(new Edges(e, s, v));
            }
            for (int i = 0; i < w; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                edges.add(new Edges(s, e, (-1) * v));
            }

            String ans = bf(1);
            System.out.println(ans);
            cnt++;
        }
    }

    private static String bf(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 0);
        dist[1] = 0;

        for (int i = 1; i <= n; i ++) {
            for (int j = 0; j < edges.size(); j++) {
                Edges edge = edges.get(j);
                if (dist[edge.end] > dist[edge.start] + edge.value) {
                    dist[edge.end] = dist[edge.start] + edge.value;
                    if (i == n) return "YES";
                }
            }
        }
        return "NO";
    }
}

class Edges {
    int start;
    int end;
    int value;

    public Edges(int start, int end, int value) {
        this.start = start;
        this.end = end;
        this.value = value;
    }
}