class Solution {
    public int[] solution(String s) {
        // 이진 변환 횟수, 제거한 0의 갯수
        int convertCnt = 0;
        int removeCnt = 0;
        while (s.length() != 1)  {
            int cur = s.length();
            s = s.replace("0", "");
            int nxt = s.length();
            
            removeCnt += cur - nxt;
            s = Integer.toBinaryString(s.length());
            convertCnt++;
        }
        
        int[] answer = {convertCnt, removeCnt};
        return answer;
    }
}