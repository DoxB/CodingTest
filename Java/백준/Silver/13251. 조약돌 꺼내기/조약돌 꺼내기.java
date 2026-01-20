import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] category = new int[n];
        double[] probability = new double[n];
        int total = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            category[i] = num;
            total += num;
        }
        int k = Integer.parseInt(br.readLine());

        double ans = 0.0;
        for (int i = 0; i < n; i++) {
            if (category[i] >= k) {
                probability[i] = 1.0;
                for (int j = 0; j < k; j++) {
                    probability[i] *= (double) (category[i] - j) / (double) (total - j);
                }
            }
            ans += probability[i];
        }

        System.out.println(ans);
    }
}