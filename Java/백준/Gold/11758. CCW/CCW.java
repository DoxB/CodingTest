import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        Location[] arr = new Location[3];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i] = new Location(x, y);
        }
        if (ccw(arr[0], arr[1], arr[2]) < 0) {
            System.out.println("-1");
        } else if (ccw(arr[0], arr[1], arr[2]) > 0) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }

    private static int ccw(Location l1, Location l2, Location l3) {
        return (l1.x * l2.y + l2.x * l3.y + l3.x * l1.y) - (l2.x * l1.y + l3.x * l2.y + l1.x * l3.y);
    }


}

class Location {
    int x;
    int y;
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}