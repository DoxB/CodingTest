import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static long[] tree;
    private static int mod;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        mod = 1000000007;

        int treeheight = 0;
        int length = n;
        while (length != 0) {
            length /= 2;
            treeheight++;
        }

        int treeSize = (int) Math.pow(2, treeheight + 1);
        tree = new long[treeSize];
        for (int i = 1; i < treeSize; i++) {
            tree[i] = 1;
        }
        int startIdx = treeSize / 2;
        for (int i = startIdx; i < startIdx + n; i++) {
            tree[i] = Long.parseLong(br.readLine());
        }

        setTree(treeSize - 1);
        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (a == 1) {
                changeVal(startIdx + b - 1, c);
            } else if (a == 2) {
                b = startIdx + b - 1;
                c = startIdx + c - 1;
                System.out.println(getMul(b, (int)c));
            }
        }

    }

    private static long getMul(int s, int e) {
        long partMul = 1;
        while (s <= e) {
            if (s % 2 == 1) {
                partMul = partMul * tree[s] % mod;
            }
            if (e % 2 == 0) {
                partMul = partMul * tree[e] % mod;
            }

            s = (s + 1) / 2;
            e = (e - 1) / 2;
        }
        return partMul;
    }


    private static void changeVal(int index, long val) {
        tree[index] = val;
        while (index > 1) {
            index = index / 2;
            tree[index] = (tree[index * 2] % mod) * (tree[index * 2 + 1] % mod) % mod;
        }
    }

    private static void setTree(int i) {
        while (i != 1) {
            tree[i / 2] = tree[i / 2] * tree[i] % mod;
            i--;
        }
    }
}