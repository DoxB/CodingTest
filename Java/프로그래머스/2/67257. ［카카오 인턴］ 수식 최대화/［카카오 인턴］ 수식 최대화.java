class Solution {
    private static char[][] sol = {{'*', '+', '-'}, {'*', '-', '+'},
                                  {'+', '*', '-'}, {'+', '-', '*'},
                                  {'-', '*', '+'}, {'-', '+', '*'}};
    
    public long solution(String expression) {
        long answer = 0;
        
        for (int i = 0; i < 6; i++) {
            answer = Math.max(answer, Math.abs(dfs(expression, i, 0)));
        }
        
        return answer;
    }
    
    private static long calc (long a, long b, char how) {
        if (how == '*') {
            return a * b;
        } else if (how == '+') {
            return a + b;
        }
        return a - b;
    }
    
    
    private static long dfs(String exp, int idx, int num) {
        String[] tmp = exp.split("\\" + sol[idx][num]);
        long result = 0;
        
        if (tmp.length == 1 && num < 2) {
            result = dfs(tmp[0], idx, num + 1);
        } else if (num < 2) {
            result = dfs(tmp[0], idx, num + 1);
            for (int i = 1; i < tmp.length; i++) {
                result = calc(result, dfs(tmp[i], idx, num + 1), sol[idx][num]);
            }
        }
        
        if (tmp.length == 1 && num == 2) {
            return Long.parseLong(tmp[0]);
        } else if (num == 2) {
            result = Long.parseLong(tmp[0]);
            for (int i = 1; i < tmp.length; i++) {
                result = calc(result, Long.parseLong(tmp[i]), sol[idx][num]);
            }
        }
        return result;
        
    }
}