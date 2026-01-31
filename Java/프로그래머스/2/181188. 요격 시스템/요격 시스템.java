import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        Arrays.sort(targets, Comparator.comparingInt(x -> x[1]));
        
        int shot = 0;
        for (int[] cur : targets) {
            if (shot <= cur[0]) {
                shot = cur[1];
                answer++;
            }
        }
        return answer;
    }
}