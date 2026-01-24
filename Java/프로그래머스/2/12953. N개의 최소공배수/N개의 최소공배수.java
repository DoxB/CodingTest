class Solution {
    public int solution(int[] arr) {
        int answer = 0;
        if (arr.length == 1) {
            answer = arr[0];
        } else {
            int a = Math.max(arr[0], arr[1]);
            int b = Math.min(arr[0], arr[1]);
            answer = lcm(a, b);
            for (int i = 2; i < arr.length; i++) {
                answer = lcm(answer, arr[i]);
            }
        }
        
        return answer;
    }
    
    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    
    private static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
}