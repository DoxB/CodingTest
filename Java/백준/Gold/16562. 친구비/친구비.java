import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int[] parent;
    private static int[] value;
    private static int[] groupMin;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        value = new int[n + 1];
        groupMin = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            value[i] = Integer.parseInt(st.nextToken());
            parent[i] = i;
            groupMin[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            union(s, e);
        }

        for (int i = 1; i <= n; i++) {
            groupMin[find(i)] = Math.min(groupMin[find(i)], value[i]);
        }

        int total = 0;
        for (int i = 1; i <= n; i++) {
            if (groupMin[i] != Integer.MAX_VALUE) {
                total += groupMin[i];
            }
        }

        if (k < total) {
            System.out.println("Oh no");
        } else {
            System.out.println(total);
        }
    }

    private static int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }
}