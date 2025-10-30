import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        String dna = br.readLine();
        char[] arrDna = dna.toCharArray();

        // {‘A’, ‘C’, ‘G’, ‘T’}
        int[] indexCount = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            indexCount[i] = Integer.parseInt(st.nextToken());
        }

        // 첫번째 부분 서치
        int[] countDna = new int[4];
        for (int i = 0; i < p; i++) {
            countDna[convertNum(arrDna[i])]++;
        }

        int start = 0;
        int end = p - 1;
        int answer = 0;
        while (end < s) {
            if (checkDna(indexCount, countDna)) {
                answer++;
            }
            countDna[convertNum(arrDna[start])]--;
            start++;
            end++;
            if (end < s) {
                countDna[convertNum(arrDna[end])]++;
            }
        }

        bw.write(Integer.toString(answer));
        bw.flush();
    }

    private static boolean checkDna(int[] indexCount, int[] countDna) {
        for (int i = 0; i < 4; i++) {
            if (indexCount[i] > countDna[i]) return false;
        }
        return true;
    }

    private static int convertNum(char dna) {
        switch (dna) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            case 'T':
                return 3;
        }
        return 999;
    }
}
