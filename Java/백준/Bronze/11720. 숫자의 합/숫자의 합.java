import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String numString = br.readLine();
        br.close();

        char[] numChar = numString.toCharArray();

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += numChar[i] - '0';
        }

        bw.write(Integer.toString(sum));
        bw.flush();
        bw.close();
    }
}