class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        int n = sticker.length;
        if (n <= 3) {
            for (int i = 0; i < n; i++) {
                if (answer < sticker[i]) {
                    answer = sticker[i];
                }
            }
        } else {
            // 1번을 때는 경우, 2번을 때는 경우
            int[] dpf = new int[n - 1];
            int[] dps = new int[n - 1];
            for (int i = 0; i < n - 1; i++) {
                dpf[i] = sticker[i];
                dps[i] = sticker[i + 1];
            }
            dpf[1] = Math.max(dpf[1], dpf[0]);
            dps[1] = Math.max(dps[1], dps[0]);
            for (int i = 2; i < n - 1; i++) {
                dpf[i] = Math.max(dpf[i - 1], dpf[i] + dpf[i - 2]);
                dps[i] = Math.max(dps[i - 1], dps[i] + dps[i - 2]);
            }
            
            answer = Math.max(dpf[n - 2], dps[n - 2]);
        }
        
        return answer;
    }
}