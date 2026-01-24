class Solution {
    public int[] solution(int brown, int yellow) {
        int idxB = (brown - 2) / 2;
        int calY = idxB - 2;
        
        int yellowIdx = 1;
        while (calY != yellow) {
            yellowIdx++;
            idxB -= 1;
            calY = (idxB - 2) * yellowIdx;
        }
        
        int[] answer = {idxB, yellowIdx + 2};
        return answer;
    }
}