import java.io.*;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    private static int[] sender = {0, 0, 1, 1, 2, 2};
    private static int[] receiver = {1, 2, 0, 2, 0, 1};
    private static boolean[][] visited = new boolean[201][201];
    private static boolean[] answer = new boolean[201];
    private static int[] idx = new int[3];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        idx[0] = Integer.parseInt(st.nextToken());
        idx[1] = Integer.parseInt(st.nextToken());
        idx[2] = Integer.parseInt(st.nextToken());

        bfs();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 201; i++) {
            if (answer[i]) {
                sb.append(Integer.toString(i) + " ");
            }
        }

        bw.write(sb.toString());
        bw.flush();
    }

    private static void bfs() {
        Queue<AB> q = new LinkedList<>();
        q.add(new AB(0,0));
        visited[0][0] = true;
        answer[idx[2]] = true;
        while (!q.isEmpty()) {
            AB cur = q.remove();
            int a = cur.a;
            int b = cur.b;
            int c = idx[2] - a - b;

            for (int i = 0; i < 6; i++) {
                int[] nxt = {a, b, c};
                nxt[receiver[i]] += nxt[sender[i]];
                nxt[sender[i]] = 0;
                if (nxt[receiver[i]] > idx[receiver[i]]) {
                    nxt[sender[i]] = nxt[receiver[i]] - idx[receiver[i]];
                    nxt[receiver[i]] = idx[receiver[i]];
                }
                if (!visited[nxt[0]][nxt[1]]) {
                    visited[nxt[0]][nxt[1]] = true;
                    q.add(new AB(nxt[0], nxt[1]));
                    if (nxt[0] == 0) {
                        answer[nxt[2]] = true;
                    }
                }
            }
        }
    }
}

class AB {
    int a;
    int b;
    public AB(int a, int b) {
        this.a = a;
        this.b = b;
    }
}