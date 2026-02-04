import java.util.Stack;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Stack<Integer> sk = new Stack<>();
        
        int curNum = 1;
        int curIdx = 0;
        while (curIdx < order.length && curNum <= order.length) {
            if (curNum == order[curIdx]) {
                answer++;
                curNum++;
                curIdx++;
            } else if (!sk.isEmpty() && sk.peek() == order[curIdx]) {
                sk.pop();
                answer++;
                curIdx++;
            } else {
                sk.push(curNum);
                curNum++;
            }
        }
        
        // 컨베이어 다 돌고 스택에 남아있는 경우
        while (!sk.isEmpty() && sk.peek() == order[curIdx]) {
            sk.pop();
            answer++;
            curIdx++;
        }
        
        return answer;
    }
}