import java.util.HashMap;
import java.util.Stack;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        HashMap<Character, Character> map = new HashMap<>();
        Stack<Character> sk;
        char start = skill.charAt(0);
        for (int i = 1; i < skill.length(); i++) {
            map.put(skill.charAt(i), skill.charAt(i - 1));
        }
        
        for (int i = 0; i < skill_trees.length; i++) {
            sk = new Stack<>();
            boolean flag = true;
            for (int j = 0; j < skill_trees[i].length(); j++) {
                if (sk.isEmpty() && skill_trees[i].charAt(j) == start) {
                    sk.push(skill_trees[i].charAt(j));
                } else if (map.containsKey(skill_trees[i].charAt(j))) {
                    if (!sk.isEmpty() && sk.peek() == map.get(skill_trees[i].charAt(j))) {
                        sk.push(skill_trees[i].charAt(j));
                    } else {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                answer++;
            }
        }
        return answer;
    }
}