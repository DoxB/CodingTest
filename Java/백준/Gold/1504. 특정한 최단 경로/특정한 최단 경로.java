import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;

public class Main {
    private static int n;
    private static int e;
    private static ArrayList<Node>[] adj;
    private static int INF = 200_000_001;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj[a].add(new Node(b, c));
            adj[b].add(new Node(a, c));
        }

        // 첫번째에서 다익스트라 시작점, 두번째에서 다익스트라 도착점
        // 첫번째에서 두번째 더하기
        // 그리고 두 정점 순서 바꿔서도 생각
        st = new StringTokenizer(br.readLine());
        int f1 = Integer.parseInt(st.nextToken());
        int f2 = Integer.parseInt(st.nextToken());
        int[] ans0 = dks(1);
        int[] ans1 = dks(f1);
        int[] ans2 = dks(f2);

        long path1 = (long)ans0[f1] + (long)ans1[f2] + (long)ans2[n];
        long path2 = (long)ans0[f2] + (long)ans2[f1] + (long)ans1[n];
        long answer = Math.min(path1, path2);

        if (answer >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static int[] dks(int start) {
        int[] calc = new int[n + 1];
        Arrays.fill(calc, INF);
        calc[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.remove();
            if (calc[cur.idx] < cur.value) continue;

            for (Node nxt : adj[cur.idx]) {
                if (calc[nxt.idx] > cur.value + nxt.value) {
                    calc[nxt.idx] = cur.value + nxt.value;
                    pq.add(new Node(nxt.idx, cur.value + nxt.value));
                }
            }
        }

        return calc;
    }
}

class Node implements Comparable<Node>{
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