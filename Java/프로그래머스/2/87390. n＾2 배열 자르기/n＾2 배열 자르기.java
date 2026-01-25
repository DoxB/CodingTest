class Solution {
    public int[] solution(int n, long left, long right) {

        int[] answer = new int[(int)(right - left) + 1];
        
        long cur = left;
        for (int i = 0; i < right - left + 1; i++) {
            int col = (int) (cur / n);
            int row = (int) (cur % n);
            
            answer[i] = Math.max(col + 1, row + 1);
            cur++;
        }
        
        return answer;
    }
}