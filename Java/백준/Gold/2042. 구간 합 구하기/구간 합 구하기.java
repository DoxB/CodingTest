import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static long[] tree;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int treeheight = 0;
        int length = n;
        while (length != 0) {
            length /= 2;
            treeheight++;
        }
        int treeSize = (int) Math.pow(2, treeheight + 1);
        tree = new long[treeSize + 1];
        int leftNodeStartIdx = treeSize / 2;
        for (int i = leftNodeStartIdx; i < leftNodeStartIdx + n; i++) {
            tree[i] = Long.parseLong(br.readLine());
        }
        setTree(treeSize - 1);

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (a == 1) {
                changeVal(leftNodeStartIdx + b - 1, c);
            } else if (a == 2) {
                b = b + leftNodeStartIdx - 1;
                c = c + leftNodeStartIdx - 1;
                System.out.println(getSum(b, (int) c));
            }
        }
    }

    private static long getSum(int s, int e) {
        long partSum = 0;
        while (s <= e) {
            if (s % 2 == 1) {
                partSum += tree[s];
            }
            if (e % 2 == 0) {
                partSum += tree[e];
            }
            s = (s + 1) / 2;
            e = (e - 1) / 2;
        }
        return partSum;
    }

    private static void changeVal(int index, long val) {
        long diff = val - tree[index];
        while (index > 0) {
            tree[index] = tree[index] + diff;
            index = index / 2;
        }
    }

    private static void setTree(int i) {
        while (i != 1) {
            tree[i / 2] += tree[i];
            i--;
        }
    }
}