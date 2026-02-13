class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while (storey > 0) {
            int curNum = storey % 10;
            int nxtNum = (storey / 10) % 10;
            boolean up = false;
            if (curNum < 5) {
                answer += curNum;
            } else if (curNum > 5) {
                answer += 10 - curNum;
                up = true;
            } else {
                if (nxtNum < 5) {
                    answer += curNum;
                } else {
                    answer += 10 - curNum;
                    up = true;
                }
            }
            
            storey /= 10;
            if (up) {
                storey += 1;
            }
        }
        
        return answer;
    }
}