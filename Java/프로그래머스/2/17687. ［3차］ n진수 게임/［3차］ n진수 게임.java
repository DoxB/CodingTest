class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        char[] arr = new char[t];
        int cnt = 0;
        int num = 0;
        int curIdx = 0;
        p -= 1;
        while (curIdx < t) {
            char[] tmp = Integer.toString(num, n).toCharArray();
            for (int i = 0; i < tmp.length; i++) {
                if (curIdx < t && cnt % m == p) {
                    arr[curIdx] = tmp[i];
                    curIdx++;
                }
                cnt++;
            }
            num++;
        }
        
        answer = String.valueOf(arr).toUpperCase();
        
        return answer;
    }
}