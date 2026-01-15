import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] result = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        int start = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            result[i] = Integer.MAX_VALUE;
        }

        ArrayList<MyNode>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<MyNode>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
           adj[s].add(new MyNode(e, v));
        }

        PriorityQueue<MyNode> q = new PriorityQueue<>();
        q.add(new MyNode(start, 0));
        result[start] = 0;
        while (!q.isEmpty()) {
            MyNode cur = q.remove();
            int curNum = cur.targetNum;
            if (visited[curNum]) continue;
            visited[curNum] = true;

            for (MyNode myNode : adj[curNum]) {
                int nxtNum = myNode.targetNum;
                int nxtValue = myNode.value;
                if (result[nxtNum] > result[curNum] + nxtValue) {
                    result[nxtNum] = result[curNum] + nxtValue;
                    q.add(new MyNode(nxtNum, result[nxtNum]));
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i]) {
                System.out.println(result[i]);
            } else {
                System.out.println("INF");
            }
        }
    }
}

class MyNode implements Comparable<MyNode>{
    int targetNum;
    int value;

    public MyNode(int targetNum, int value) {
        this.targetNum = targetNum;
        this.value = value;
    }

    @Override
    public int compareTo(MyNode myNode) {
        if (this.value > myNode.value) {
            return 1;
        } else if (this.value == myNode.value) {
            return 0;
        }
        return -1;
    }
}