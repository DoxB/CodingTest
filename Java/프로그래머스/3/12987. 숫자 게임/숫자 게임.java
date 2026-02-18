import java.util.Arrays;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        // 선공개니깐 정렬해도 상관없음.
        Arrays.sort(A);
        Arrays.sort(B);
        int aIdx = 0;
        int bIdx = 0;
        
        // 작은수부터 체크 : A도 오름차순 정렬된 상태라 B가 못이기는 수는 앞으로도 못이김 -> 버리면 됨
        while (bIdx < B.length) {
            if (A[aIdx] < B[bIdx]) {
                answer++;
                aIdx++;
            }
            bIdx++;
        }
        
        return answer;
    }
}