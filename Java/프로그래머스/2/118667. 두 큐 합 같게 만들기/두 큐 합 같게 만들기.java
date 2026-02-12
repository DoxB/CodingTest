import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        long qSum1 = 0;
        long qSum2 = 0;
        int n = queue1.length;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            int qNum1 = queue1[i];
            int qNum2 = queue2[i];
            q1.add(qNum1);
            q2.add(qNum2);
            qSum1 += qNum1;
            qSum2 += qNum2;
        }
        
        while (qSum1 != qSum2 && answer < n * 3) {
            if (qSum1 > qSum2) {
                int tmp = q1.remove();
                qSum1 -= (long)tmp;
                q2.add(tmp);
                qSum2 += (long)tmp;
            } else if (qSum1 < qSum2) {
                int tmp = q2.remove();
                qSum2 -= (long)tmp;
                q1.add(tmp);
                qSum1 += (long)tmp;
            }
            answer++;
        }
        
        if (answer == n * 3 && qSum1 != qSum2) {
            answer = -1;
        }
        
        return answer;
    }
}