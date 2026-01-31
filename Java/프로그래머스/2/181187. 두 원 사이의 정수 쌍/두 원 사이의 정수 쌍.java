class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        long r2Sum = 0;
        for (int y = 0; y < r2; y++) {
            r2Sum += (int)Math.pow(Math.pow(r2, 2) - Math.pow(y, 2), 0.5);
        }
        r2Sum *= 4;
        
        long r1Sum = 0;
        long checkInt = 0;
        for (int y = 0; y < r1; y++) {
            int tmp = (int)Math.pow(Math.pow(r1, 2) - Math.pow(y, 2), 0.5);
            if (Math.pow(tmp, 2) + Math.pow(y, 2) == Math.pow(r1, 2)) {
                checkInt++;
            }
            r1Sum += tmp;
        }
        r1Sum *= 4;
        answer = r2Sum - r1Sum + checkInt * 4;
        
        return answer;
    }
}