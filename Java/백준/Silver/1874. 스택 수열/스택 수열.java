import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Stack<Integer> sk = new Stack<>();

        int n = Integer.parseInt(br.readLine());
        int curNum = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int targetNum = Integer.parseInt(br.readLine());
            if (targetNum >= curNum) {
                while (curNum <= targetNum) {
                    sk.push(curNum);
                    curNum++;
                    sb.append("+\n");
                }
                sk.pop();
                sb.append("-\n");
            } else {
                int peekNum = sk.pop();
                if (peekNum == targetNum) {
                    sb.append("-\n");
                } else {
                    sb.setLength(0);
                    sb.append("NO");
                    break;
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }
}