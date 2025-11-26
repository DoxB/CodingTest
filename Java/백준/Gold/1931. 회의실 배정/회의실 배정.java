import java.io.*;
import java.util.Arrays;

import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        TimeTable[] arr = new TimeTable[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());

            TimeTable tb = new TimeTable(startTime, endTime);
            arr[i] = tb;
        }

        Arrays.sort(arr);

        int ans = 0;
        int curEndTime = -1;
        for (int i = 0; i < n; i++) {
            if (curEndTime <= arr[i].startTime) {
                ans += 1;
                curEndTime = arr[i].endTime;
            }
        }

        bw.write(Integer.toString(ans));
        bw.flush();
        br.close();
        bw.close();
    }
}

class TimeTable implements Comparable<TimeTable> {
    int startTime;
    int endTime;

    TimeTable (int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(TimeTable timeTable) {
        if (this.endTime > timeTable.endTime) {
            return 1;
        } else if (this.endTime == timeTable.endTime) {
            if (this.startTime > timeTable.startTime) {
                return 1;
            } else {
                return -1;
            }
        }
        return -1;
    }
}