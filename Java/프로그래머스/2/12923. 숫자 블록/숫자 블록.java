class Solution {
    public int[] solution(long begin, long end) {
        int size = (int)(end - begin + 1);
        int[] answer = new int[size];
        int start = (int)begin;
        
        // begin ~ end
        for (int i = 0; i < size; i++) {
            int num = start + i;
            for (int k = 2; k <= (int)Math.sqrt(num); k++) {
                if (num % k == 0) {
                    if (num / k > 10000000) {
                        answer[i] = k;
                        continue;
                    } else {
                        answer[i] = num / k;
                        break;
                    }
                }
            }
            if (answer[i] == 0) {
                answer[i] = 1;
            }
        }
        if (begin == 1) {
            answer[0] = 0;
        }
        return answer;
    }
}