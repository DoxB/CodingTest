import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Location[] aLine = new Location[2]; // a - b
        Location[] bLine = new Location[2]; // c - d
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2; i++) {
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            aLine[i] = new Location(x, y);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2; i++) {
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            bLine[i] = new Location(x, y);
        }

        if (isCross(aLine, bLine)) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }

    private static long ccw(Location l1, Location l2, Location l3) {
        long tmp = (l1.x * l2.y + l2.x * l3.y + l3.x * l1.y) - (l2.x * l1.y + l3.x * l2.y + l1.x * l3.y);
        if (tmp > 0) {
            return 1;
        } else if (tmp < 0) {
            return -1;
        }
        return 0;
    }

    private static boolean isOverlab(Location[] a, Location[] b) {
        if (Math.min(a[0].x, a[1].x) <= Math.max(b[0].x, b[1].x) && Math.max(a[0].x, a[1].x) >= Math.min(b[0].x, b[1].x) &&
            Math.min(a[0].y, a[1].y) <= Math.max(b[0].y, b[1].y) && Math.max(a[0].y, a[1].y) >= Math.min(b[0].y, b[1].y)) {
                return true;
            }
        return false;
    }

    private static boolean isCross(Location[] a, Location[] b) {
        long abc = ccw(a[0], a[1], b[0]);
        long abd = ccw(a[0], a[1], b[1]);
        long cda = ccw(b[0], b[1], a[0]);
        long cdb = ccw(b[0], b[1], a[1]);
        if (abc * abd == 0 && cda * cdb == 0) {
            return isOverlab(a, b);
        } else if (abc * abd <= 0 && cda * cdb <= 0) {
            return true;
        }
        return false;
    }
}

class Location {
    long x;
    long y;
    public Location(long x, long y) {
        this.x = x;
        this.y = y;
    }
}