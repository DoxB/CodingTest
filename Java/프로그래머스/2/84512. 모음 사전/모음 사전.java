class Solution {
    private static int answer;
    private static char[] curArr;
    private static char[] dict = {'A', 'E', 'I', 'O', 'U'};
    private static String target;
    private static boolean hit;
    
    public int solution(String word) {
        answer = 0;
        curArr = new char[5];
        target = word;
        hit = false;
        
        dfs(0);
        
        return answer;
    }
    
    private static void dfs(int curLength) {
        if (curLength > 0 && new String(curArr, 0, curLength).equals(target)) {
            hit = true;
            return;
        } else if (curLength == 5) {
            return;
        }
        for (int i = 0; i < 5; i++) {
            if (!hit) {
                curArr[curLength] = dict[i];
                answer++;
                dfs(curLength + 1);
            }
        }
    }
}