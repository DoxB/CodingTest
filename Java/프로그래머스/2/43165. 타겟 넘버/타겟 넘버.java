class Solution {
    private static int[] arr;
    private static int result;
    private static int answer;
    
    public int solution(int[] numbers, int target) {
        answer = 0;
        arr = numbers;
        result = target;
        
        dfs(0, arr[0]);
        dfs(0, -arr[0]);
        
        return answer;
    }
    
    private static void dfs(int s, int curSum) {
        if (s == arr.length - 1) {
            if (curSum == result) {
                answer++;
            }
            return;
        } 
        dfs(s + 1, curSum + arr[s + 1]);
        dfs(s + 1, curSum - arr[s + 1]);
    }
}