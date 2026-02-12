import java.util.HashSet;

class Solution {
    private static HashSet<Integer> hs;
    private static boolean[] visited;
    private static char[] arr;
    private static int answer = 0;
    private static int n;
    
    public int solution(String numbers) {
        hs = new HashSet<>();
        n = numbers.length();
        visited = new boolean[n];
        arr = numbers.toCharArray();
        
        for (int i = 0; i < n; i++) {
            int curNum = Integer.parseInt(String.valueOf(arr[i]));
            visited[i] = true;
            dfs(curNum);
            visited[i] = false;
        }
        
        return answer;
    }
    
    private static void dfs(int num) {
        if (!hs.contains(num)) {
            hs.add(num);
            if (num != 0 && num != 1 && isPrime(num)) {
                answer++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int nxtNum = num * 10 + Integer.parseInt(String.valueOf(arr[i]));
                dfs(nxtNum);
                visited[i] = false;
            }
        }
    }
    
    private static boolean isPrime(int num) {
        for (int i = 2; i <= (int)Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}