import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Arrays;

public class Main {
    private static ArrayList<Node>[] returnMap;
    private static ArrayList<Node>[] goMap;
    private static int n;
    private static int m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        // X 로 가는 건 경로 뒤집고, 한번만 계산
        returnMap = new ArrayList[n + 1];
        goMap = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            returnMap[i] = new ArrayList<>();
            goMap[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            returnMap[s].add(new Node(e, v));
            goMap[e].add(new Node(s, v));
        }

        int[] returnAns = dks(start, returnMap);
        int[] goAns = dks(start, goMap);
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (i == start) continue;
            answer = Math.max(answer, returnAns[i] + goAns[i]);
        }

        System.out.println(answer);
    }   

    private static int[] dks(int start, ArrayList<Node>[] adj) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.remove();
            for (Node nxt : adj[cur.idx]) {
                if (dist[nxt.idx] > cur.value + nxt.value) {
                    dist[nxt.idx] = cur.value + nxt.value;
                    pq.add(new Node(nxt.idx, cur.value + nxt.value));
                }
            }
        }

        return dist;
    }
}

class Node implements Comparable<Node> {
    int idx;
    int value;

    public Node(int idx, int value) {
        this.idx = idx;
        this.value = value;
    }

    @Override
    public int compareTo(Node n) {
        if (this.value - n.value > 0) {
            return 1;
        }
        return -1;
    }
}