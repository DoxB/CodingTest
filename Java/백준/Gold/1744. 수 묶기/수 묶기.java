import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<Integer> plusPq = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minusPq = new PriorityQueue<>();

        int n = Integer.parseInt(br.readLine());
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 1) {
                plusPq.add(num);
            } else if (num < 1) {
                minusPq.add(num);
            } else {
                ans += 1;
            }
        }

        while (plusPq.size() > 1) {
            int pFirst = plusPq.remove();
            int pSecond = plusPq.remove();
            ans += pFirst * pSecond;
        }
        if (plusPq.size() == 1) {
            ans += plusPq.remove();
        }

        while (minusPq.size() > 1) {
            int mFirst = minusPq.remove();
            int mSecond = minusPq.remove();
            ans += mFirst * mSecond;
        }
        if (minusPq.size() == 1) {
            ans += minusPq.remove();
        }

        bw.write(Integer.toString(ans));
        bw.flush();
        br.close();
        bw.close();
    }
}