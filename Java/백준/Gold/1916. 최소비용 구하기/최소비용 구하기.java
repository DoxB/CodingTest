import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        ArrayList<MyNode>[] adj = new ArrayList[n + 1];
        long[] result = new long[n + 1];
        boolean[] visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<MyNode>();
            result[i] = Long.MAX_VALUE;
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[s].add(new MyNode(e, v));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        PriorityQueue<MyNode> q = new PriorityQueue<MyNode>();
        q.add(new MyNode(start, 0));
        result[start] = 0;
        while (!q.isEmpty()) {
           MyNode cur = q.remove();
           int curNum = cur.targetNum;
           if (visited[curNum]) continue;
           visited[curNum] = true;

           for (MyNode myNode : adj[curNum]) {
                int nxtNum = myNode.targetNum;
                long nxtValue = myNode.value;
                if (result[nxtNum] > result[curNum] + nxtValue) {
                    result[nxtNum] = result[curNum] + nxtValue;
                    q.add(new MyNode(nxtNum, result[nxtNum]));
                }
           }
        }
        System.out.println(result[end]);
    }
}

class MyNode implements Comparable<MyNode> {
    int targetNum;
    long value;

    public MyNode(int targetNum, long value) {
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