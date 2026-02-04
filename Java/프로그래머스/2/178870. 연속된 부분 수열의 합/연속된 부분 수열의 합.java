class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int minValue = Integer.MAX_VALUE;
        int n = sequence.length;
            
        int start = 0;
        int end = 0;
        int curSum = sequence[0];
        
        while (start <= end) {
            if (curSum == k) {
                if (minValue > (end - start + 1)) {
                    answer[0] = start;
                    answer[1] = end;
                    minValue = end - start + 1;
                }
                curSum -= sequence[start];
                start++;
            } else if (end < n - 1 && curSum < k) {
                end++;
                curSum += sequence[end];
            } else {
                curSum -= sequence[start];
                start++;
            }
        }
        
        return answer;
    }
}