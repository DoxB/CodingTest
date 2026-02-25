import java.util.PriorityQueue;
import java.util.ArrayList;

class Solution {
    private static ArrayList<MyNode>[] r;
    private static int[] ans;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        ans = new int[n + 1];
        r = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            r[i] = new ArrayList<>();
            ans[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < roads.length; i++) {
            int s = roads[i][0];
            int e = roads[i][1];
            r[s].add(new MyNode(e, 1));
            r[e].add(new MyNode(s, 1));
        }   
        ans[destination] = 0;
        dks(destination);
        
        for (int i = 0; i < sources.length; i++) {
            if (ans[sources[i]] == Integer.MAX_VALUE) {
                answer[i] = -1;
            } else {
                answer[i] = ans[sources[i]];
            }
        }
        return answer;
    }
    
    private static void dks(int s) {
        PriorityQueue<MyNode> pq = new PriorityQueue<>();
        pq.add(new MyNode(s, 0));
        while (!pq.isEmpty()) {
            MyNode m = pq.remove();
            for (MyNode tmp : r[m.end]) {
                int nxt = tmp.end;
                int nxtV = tmp.value;
                if (ans[nxt] > ans[m.end] + nxtV) {
                    ans[nxt] = ans[m.end] + nxtV;
                    pq.add(new MyNode(nxt, ans[nxt]));
                }
            }
        }
    }
}

class MyNode implements Comparable<MyNode> {
    int end;
    int value;
    
    public MyNode(int end, int value) {
        this.end = end;
        this.value = value;
    }
    
    @Override
    public int compareTo(MyNode m) {
        if (this.value - m.value > 0) {
            return 1;
        }
        return -1;
    }
}