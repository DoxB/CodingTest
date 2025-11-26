import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String formula = br.readLine();
        String[] minusGroup = formula.split("-");

        int ans = 0;
        for (int i = 0; i < minusGroup.length; i++) {
            int sum = 0;
            String[] plusGroup = minusGroup[i].split("[+]");
            for (int j = 0; j < plusGroup.length; j++) {
                sum += Integer.parseInt(plusGroup[j]);
            }

            if (i == 0) {
                ans += sum;
            } else {
                ans -= sum;
            }

            sum = 0;
        }

        bw.write(Integer.toString(ans));
        bw.flush();
        br.close();
        bw.close();
    }
}