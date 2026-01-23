import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        MyMatrix[] myMatrixs = new MyMatrix[n + 1];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            myMatrixs[i] = new MyMatrix(x, y);
        }
        myMatrixs[n] = myMatrixs[0];
        double result = 0.0;

        // x1 x2 0 x1
        // y1 y2 0 y1
        for (int i = 0; i < n; i++) {
            result += myMatrixs[i].x * myMatrixs[i + 1].y - myMatrixs[i].y * myMatrixs[i + 1].x;
        }

        System.out.println(String.format("%.1f", Math.abs(result / 2.0)));
    }
}

class MyMatrix {
    long x;
    long y;

    public MyMatrix(long x, long y) {
        this.x = x;
        this.y = y;
    }
}