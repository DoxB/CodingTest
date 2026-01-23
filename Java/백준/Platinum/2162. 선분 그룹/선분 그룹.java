import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int[] parent;
    private static int[] groupCount;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        MyLine[] myLines = new MyLine[n + 1];
        parent = new int[n + 1];
        groupCount = new int[n + 1];

        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            long x1 = Long.parseLong(st.nextToken());
            long y1 = Long.parseLong(st.nextToken());
            long x2 = Long.parseLong(st.nextToken());
            long y2 = Long.parseLong(st.nextToken());
            myLines[i] = new MyLine(x1, y1, x2, y2);
            parent[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (isCross(myLines[i], myLines[j])) {
                    union(i, j);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            groupCount[find(i)]++;
        }
        int groupNum = 0;
        int maxLines = 0;
        for (int i = 1; i <= n; i++) {
            if (groupCount[i] != 0) {
                groupNum++;
                if (maxLines < groupCount[i]) {
                    maxLines = groupCount[i];
                }
            }
        }

        System.out.println(groupNum);
        System.out.println(maxLines);
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

    private static long ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        long tmp = (x1 * y2 + x2 * y3 + x3 * y1) - (x2 * y1 + x3 * y2 + x1 * y3);
        if (tmp > 0) {
            return 1;
        } else if (tmp < 0) {
            return -1;
        }
        return 0;
    }

    private static boolean isOverlab(MyLine a, MyLine b) {
        if (Math.min(a.x1, a.x2) <= Math.max(b.x1, b.x2) && Math.max(a.x1, a.x2) >= Math.min(b.x1, b.x2) &&
            Math.min(a.y1, a.y2) <= Math.max(b.y1, b.y2) && Math.max(a.y1, a.y2) >= Math.min(b.y1, b.y2)) {
                return true;
            }
        return false;
    }

    private static boolean isCross(MyLine a, MyLine b) {
        long abc = ccw(a.x1, a.y1, a.x2, a.y2, b.x1, b.y1);
        long abd = ccw(a.x1, a.y1, a.x2, a.y2, b.x2, b.y2);
        long cda = ccw(b.x1, b.y1, b.x2, b.y2, a.x1, a.y1);
        long cdb = ccw(b.x1, b.y1, b.x2, b.y2, a.x2, a.y2);
        if (abc * abd == 0 && cda * cdb == 0) {
            return isOverlab(a, b);
        } else if (abc * abd <= 0 && cda * cdb <= 0) {
            return true;
        }
        return false;
    }
}
class MyLine {
    long x1;
    long y1;
    long x2;
    long y2;
    public MyLine(long x1, long y1, long x2, long y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}