import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] indegree = new int[n + 1];
        ArrayList<myNode>[] adj = new ArrayList[n + 1];
        ArrayList<myNode>[] reverseAdj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<myNode>();
            reverseAdj[i] = new ArrayList<myNode>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            adj[start].add(new myNode(end, value));
            reverseAdj[end].add(new myNode(start, value));
            indegree[end]++;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int startCity = Integer.parseInt(st.nextToken());
        int endCity = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        q.add(startCity);
        int[] result = new int[n + 1];
        while(!q.isEmpty()) {
            int cur = q.remove();
            for (myNode nxt : adj[cur]) {
                indegree[nxt.targetNode]--;
                result[nxt.targetNode] = Math.max(result[nxt.targetNode], result[cur] + nxt.value);
                if (indegree[nxt.targetNode] == 0) {
                    q.add(nxt.targetNode);
                }
            }
        }

        int answerCount = 0;
        boolean[] visited = new boolean[n + 1];
        q = new LinkedList<>();
        q.add(endCity);
        visited[endCity] = true;
        while(!q.isEmpty()) {
            int cur = q.remove();
            for (myNode nxt : reverseAdj[cur]) {
                if (result[nxt.targetNode] + nxt.value == result[cur]) {
                    answerCount++;
                    if (visited[nxt.targetNode] == false) {
                        visited[nxt.targetNode] = true;
                        q.add(nxt.targetNode);
                    }
                }
            }
        }

        System.out.println(result[endCity]);
        System.out.println(answerCount);
    }
}

class myNode {
    int targetNode;
    int value;

    public myNode(int end, int value) {
        this.targetNode = end;
        this.value = value;
    }
}