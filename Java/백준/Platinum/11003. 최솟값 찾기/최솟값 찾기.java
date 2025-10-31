import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        // i-L+1 ~ i 구간 L
        Deque<Bucket> q = new LinkedList<>();
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            int curNum = Integer.parseInt(st.nextToken());
            Bucket bucket = new Bucket(i, curNum);
            while (!q.isEmpty() && q.getLast().num > curNum) {
                q.removeLast();
            }
            q.addLast(bucket);

            if (q.getFirst().index < i - l + 1) {
                q.removeFirst();
            }

            bw.write(Integer.toString(q.getFirst().num) + " ");
        }

        bw.flush();
    }
}

class Bucket {
    int index;
    int num;

    Bucket (int index, int num) {
        this.index = index;
        this.num = num;
    }
}