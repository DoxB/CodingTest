import java.util.Stack;

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        char[] arr = number.toCharArray();
        
        Stack<Character> sk = new Stack<>();
        sk.push(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            while (!sk.isEmpty() && sk.peek() < arr[i] && k > 0) {
                sk.pop();
                k--;
            }
            sk.push(arr[i]);
        }
        while (k > 0) {
            sk.pop();
            k--;
        }
        
        char[] ansArr = new char[sk.size()];
        for (int i = sk.size() - 1; i >= 0; i--) {
            ansArr[i] = sk.pop();
        }
        answer = String.valueOf(ansArr);
        return answer;
    }
}