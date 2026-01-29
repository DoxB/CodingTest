import java.util.Stack;

class Solution {
    public int[] solution(int[] numbers) {
        int n = numbers.length;
        Stack<MyNode> sk = new Stack<>();
        int[] answer = new int[n];
        
        for (int i = 0; i < n; i++) {
            if (sk.isEmpty() || sk.peek().num >= numbers[i]) {
                sk.push(new MyNode(i, numbers[i]));
            } else {
                while (!sk.isEmpty() && sk.peek().num < numbers[i]) {
                    MyNode m = sk.pop();
                    answer[m.idx] = numbers[i];
                }
                sk.push(new MyNode(i, numbers[i]));
            }
        }
        
        while (!sk.isEmpty()) {
            MyNode m = sk.pop();
            answer[m.idx] = -1;
        }
        return answer;
    }
}

class MyNode {
    int idx;
    int num;
    
    public MyNode(int idx, int num) {
        this.idx = idx;
        this.num = num;
    }
}