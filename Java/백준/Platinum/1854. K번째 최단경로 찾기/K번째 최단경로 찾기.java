import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        ArrayList<MyNode>[] adj = new ArrayList[n + 1];
        PriorityQueue<Integer>[] result = new PriorityQueue[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<MyNode>();
            result[i] = new PriorityQueue<Integer>(Collections.reverseOrder());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[s].add(new MyNode(e, v));
        }

        PriorityQueue<MyNode> q = new PriorityQueue<>();
        q.add(new MyNode(1, 0));
        result[1].add(0);
        while (!q.isEmpty()) {
            MyNode cur = q.remove();
            int curNum = cur.targetNum;
            int curValue = cur.value;
            for (MyNode myNode : adj[curNum]) {
                int nxtNum = myNode.targetNum;
                int value = myNode.value;
                if (result[nxtNum].size() < k) {
                    result[nxtNum].add(curValue + value);
                    q.add(new MyNode(nxtNum, curValue + value));
                } else if (result[nxtNum].peek() > curValue + value) {
                    result[nxtNum].remove();
                    result[nxtNum].add(curValue + value);
                    q.add(new MyNode(nxtNum, curValue + value));
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (result[i].size() == k) {
                bw.write(Integer.toString(result[i].peek()));
                bw.newLine();
            } else {
                bw.write("-1");
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

class MyNode implements Comparable<MyNode> {
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