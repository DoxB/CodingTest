class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int start = 0;
        int end = 200_000_000;
        
        while (start <= end) {
            int mid = (end + start) / 2;
            if (isOk(stones, k, mid)) {
                answer = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        
        return answer;
    }
    
    private static boolean isOk(int[] stones, int k, int member) {
        int cnt = 0;
        
        for (int i = 0; i < stones.length; i++) {
            if (stones[i] - member < 0) {
                cnt++;
                if (cnt == k) return false;
            } else {
                cnt = 0;
            }
        }
        
        return true;
    }
}