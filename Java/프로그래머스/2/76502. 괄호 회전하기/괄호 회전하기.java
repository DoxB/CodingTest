import java.util.Stack;

class Solution {
    public int solution(String s) {
        int answer = 0;
        
        char[] arr = new char[s.length() * 2];
        char[] tmp = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = tmp[i % tmp.length]; 
        }
        
        for (int i = 0; i < tmp.length; i++) {
            if (isRight(arr, i, i + tmp.length)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private static boolean isRight(char[] arr, int start, int end) {
        Stack<Character> sk = new Stack<>();
        for (int i = start; i < end; i++) {
            if (!sk.isEmpty()) {
                if (arr[i] == ']' && sk.peek() == '[') {
                    sk.pop();
                } else if (arr[i] == '}' && sk.peek() == '{') {
                    sk.pop();
                } else if (arr[i] == ')' && sk.peek() == '(') {
                    sk.pop();
                } else {
                    sk.add(arr[i]);
                }
            } else {
                sk.add(arr[i]);
            } 
        }
        
        if (sk.isEmpty()) {
            return true;
        }
        return false;
    }
}