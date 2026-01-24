import java.util.Stack;

class Solution
{
    public int solution(String s)
    {
        int answer = -1;
        Stack<Character> sk = new Stack<>();
        char[] arr = s.toCharArray();
        sk.add(arr[0]);
        
        for (int i = 1; i < arr.length; i++) {
            if (!sk.isEmpty() && sk.peek() == arr[i]) {
                sk.pop();
            } else {
                sk.add(arr[i]);
            }
        }
        
        if (sk.isEmpty()) {
            answer = 1;
        } else {
            answer = 0;
        }
        return answer;
    }
}