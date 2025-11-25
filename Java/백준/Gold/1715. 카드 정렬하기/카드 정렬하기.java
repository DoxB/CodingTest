import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int ans = 0;

        while (pq.size() > 1) {
            int firstNum = pq.remove();
            int secondNum = pq.remove();
            ans += firstNum + secondNum;

            pq.add(firstNum + secondNum);
        }

        bw.write(Integer.toString(ans));
        bw.flush();
        br.close();
        bw.close();
    }
}