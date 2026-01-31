class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        for (int y = 0; y <= d; y += k) {
            answer = answer + 1 + (int) Math.pow((Math.pow(d, 2) - Math.pow(y, 2)), 0.5) / k;
        }
        return answer;
    }
}