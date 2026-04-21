import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Collections;

public class Main {
    private static int[] parent;
    private static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        // 최소 트리 구하기
        PriorityQueue<Edge> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            long v = Long.parseLong(st.nextToken());

            pq.add(new Edge(s, e, v));
        }

        int cnt = 0;
        long answer = 0; // 이어지지 않는 경우
        while (!pq.isEmpty()) {
            if (cnt > n - 1) break;
            Edge cur = pq.remove();
            int curS = cur.start;
            int curE = cur.end;
            long curV = cur.value;

            if (find(curS) != find(curE)) {
                union(curS, curE);

                if (find(start) == find(end)) {
                    // 큰값부터 뽑았고, 처음으로 이어지는 걸 찾기 때문에
                    // 바로 대입해도 된다.
                    answer = curV;
                    break;
                }
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