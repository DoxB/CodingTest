import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int[] tree;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int treeheight = 0;
        int length = n;
        while (length != 0) {
            length /= 2;
            treeheight++;
        }
        int treeSize = (int) Math.pow(2, treeheight + 1);
        int startIdx = treeSize / 2;
        tree = new int[treeSize];
        for (int i = 1; i < treeSize; i++) {
            tree[i] = Integer.MAX_VALUE;
        }
        for (int i = startIdx; i < startIdx + n; i++) {
            tree[i] = Integer.parseInt(br.readLine());
        }
        setTree(treeSize - 1);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) + startIdx - 1;
            int e = Integer.parseInt(st.nextToken()) + startIdx - 1;
            System.out.println(getMin(s, e));
        }
    }

    private static int getMin(int s, int e) {
        int minValue = Integer.MAX_VALUE;
        while (s <= e) {
            if (s % 2 == 1) {
                minValue = Math.min(minValue, tree[s]);
            }
            if (e % 2 == 0) {
                minValue = Math.min(minValue, tree[e]);
            }
            s = (s + 1) / 2;
            e = (e - 1) / 2;
        }
        return minValue;
    }

    private static void setTree(int i) {
        while (i != 1) {
            if (tree[i / 2] > tree[i]) {
                tree[i / 2] = tree[i];
            }
            i--;
        }
    }
}