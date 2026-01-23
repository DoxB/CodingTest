import java.util.Stack;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Stack<Character> stack = new Stack<>();
        char[] arr = s.toCharArray();
        
        for (int i = 0; i < arr.length; i++) {
            if(stack.isEmpty() || arr[i] == '(') {
                stack.add(arr[i]);
            } else if (arr[i] == ')' && stack.peek() == '('){
                stack.pop();
            } else {
                answer = false;
                break;
            }
        }
        if (!stack.isEmpty()) {
            answer = false;
        }

        return answer;
    }
}