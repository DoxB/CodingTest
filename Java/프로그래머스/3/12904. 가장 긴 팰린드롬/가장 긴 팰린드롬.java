class Solution
{
    public int solution(String s)
    {
        int answer = 0;
        char[] arr = s.toCharArray();
        
        // 길이가 1일때
        if (arr.length == 1) return 1;
        
        // 짝수, 홀수 나눠서 생각
        for (int i = 1; i < arr.length; i ++) {
            // 짝수
            int even = isPal(i - 1, i, arr);
            // 홀수
            int odd = isPal(i - 1, i + 1, arr) + 1;
            
            answer = Math.max(answer, Math.max(even, odd));
        }
        
        return answer;
    }
    
    private static boolean isOk(int start, int end, int n) {
        return 0 <= start && end < n;
    }
    
    private static int isPal(int start, int end, char[] arr) {
        int cnt = 0;
        while (isOk(start, end, arr.length) && arr[start] == arr[end]) {
            start--;
            end++;
            cnt += 2;
        }
        return cnt;
    }
}