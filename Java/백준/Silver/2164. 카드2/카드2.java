import java.io.*;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Deque<Integer> dq = new LinkedList<>();
        int n = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            dq.addLast(i);
        }

        int count = 0;
        int answer = 0;
        while (!dq.isEmpty()) {
            if (count % 2 == 0) {
                answer = dq.removeFirst();
                count++;
            } else {
                int tmp = dq.removeFirst();
                dq.addLast(tmp);
                count++;
            }
        }
        bw.write(Integer.toString(answer));
        bw.flush();
    }
}