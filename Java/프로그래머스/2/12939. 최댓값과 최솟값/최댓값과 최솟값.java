class Solution {
    public String solution(String s) {
        String answer = "";
        
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        
        String[] sArr = s.split(" ");
        for (int i = 0; i < sArr.length; i++) {
            int idxNum = Integer.parseInt(sArr[i]);
            if (idxNum < minValue) minValue = idxNum;
            if (idxNum > maxValue) maxValue = idxNum;
        }
        answer = Integer.toString(minValue) + " " + Integer.toString(maxValue);
        return answer;
    }
}