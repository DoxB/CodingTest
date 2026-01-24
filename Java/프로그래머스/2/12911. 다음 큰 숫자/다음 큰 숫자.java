class Solution {
    public int solution(int n) {
        int answer = 0;
        int idxCount = countOne(n);
        while (true) {
            n++;
            if (idxCount == countOne(n)) break;
        }
        answer = n;
        return answer;
    }
    
    public int countOne(int n) {
        String tmp = Integer.toBinaryString(n);
        tmp = tmp.replace("0", "");
        return tmp.length();
    }
}