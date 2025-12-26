import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
public class Main {
    private static long lcm;
    private static boolean[] visited;
    private static long[] answer;
    private static ArrayList<ArrayList<MyNode>> adj = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        answer = new long[n];
        visited = new boolean[n];
        lcm = 1;
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<MyNode>());
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            adj.get(start).add(new MyNode(end, p, q));
            adj.get(end).add(new MyNode(start, q, p));
            lcm *= (p * q / gcd(p, q));
        }

        answer[0] = lcm;
        dfs(0);
        long mgcd = answer[0];
        for (int i = 1; i < n; i++) {
            mgcd = gcd(mgcd, answer[i]);
        }

        for (int i = 0; i < n; i++) {
            System.out.print(answer[i] / mgcd + " ");
        }
    }

    private static void dfs(int start) {
        visited[start] = true;
        for (MyNode myNode : adj.get(start)) {
            int nxt = myNode.getB();
            if (!visited[nxt]) {
                answer[nxt] = answer[start] * myNode.getQ() / myNode.getP();
                dfs(myNode.getB());
            }
        }
    }

    private static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}

class MyNode {
    private int b;
    private int p;
    private int q;

    MyNode(int b, int p, int q) {
        this.b = b;
        this.p = p;
        this.q = q;
    }

    public int getB() {
        return b;
    }

    public int getP() {
        return p;
    }

    public int getQ() {
        return q;
    }
}