import java.io.*;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String numStr = br.readLine();
        Integer[] arr = new Integer[numStr.length()];
        for (int i = 0; i < numStr.length(); i++) {
            arr[i] = Integer.parseInt(numStr.substring(i, i + 1));
        }

        Arrays.sort(arr, Collections.reverseOrder());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numStr.length(); i++) {
            sb.append(Integer.toString(arr[i]));
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}