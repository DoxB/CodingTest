import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> sk = new Stack<>();
        for (int i = 0; i < n; i++) {
            int curNum = arr[i];
            while (!sk.isEmpty() && arr[sk.peek()] < curNum) {
                ans[sk.pop()] = curNum;
            }
            sk.push(i);
        }

        while (!sk.isEmpty()) {
            ans[sk.pop()] = -1;
        }

        for (int i = 0; i < n; i++) {
            bw.write(Integer.toString(ans[i]) + " ");
        }
        bw.flush();
    }
}