import java.util.ArrayList;
import java.util.HashSet;

class Solution {
    private static boolean[] isOk;
    
    public String[] solution(String[] expressions) {
        isOk = new boolean[10];
        for (int i = 0; i <= 9; i++) {
            isOk[i] = true;
        }
        
        ArrayList<String> sol = new ArrayList<>();
        for (int i = 0; i < expressions.length; i++) {
            if (expressions[i].contains("X")) {
                sol.add(expressions[i]);
            } else if (expressions[i].contains("+")) {
                cal(expressions[i], "+");
            } else {
                cal(expressions[i], "-");
            }
        }
        
        ArrayList<String> ans = new ArrayList<>();
        HashSet<String> hs = new HashSet<>();
        
        // 가능한 진법 sol도 넣고 체크
        for (String solExp : sol) {
            String solResult = "";
            for (int i = 2; i <= 9; i++) {
                if (isOk[i]) {
                    if (solExp.contains("+")) {
                        solResult = calSol(solExp, "+", i);
                    } else {
                        solResult = calSol(solExp, "-", i);
                    }
                    if (solResult.equals("NO")) {
                        isOk[i] = false;
                    }
                }
            }
        }
        
        // 답안 도출
        for (String solExp : sol) {
            String solResult = "";
            for (int i = 2; i <= 9; i++) {
                if (isOk[i]) {
                    if (solExp.contains("+")) {
                        solResult = calSol(solExp, "+", i);
                    } else {
                        solResult = calSol(solExp, "-", i);
                    }
                    if (!solResult.equals("NO")) {
                        hs.add(solResult);
                    }
                }
            }
            
            if (hs.size() == 1) {
                for (String a : hs) {
                    ans.add(solExp.replace("X", a));
                }
            } else {
                ans.add(solExp.replace("X", "?"));
            }
            
            hs.clear();
        }
        
        String[] answer = new String[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }
    
    private static boolean check(String num, int radix) {
        char[] tmp = num.toCharArray();
        for (int i = 0; i < tmp.length; i++) {
            if (radix <= tmp[i] - '0') {
                return false;
            }
        }
        return true;
    }
    
    private static String calSol(String exp, String m, int radix) {
        String[] tmp1 = exp.split(" \\" + m + " ");
        String[] tmp2 = tmp1[1].split(" = ");
        String first = tmp1[0];
        String second = tmp2[0];
        
        if (!check(first, radix) || !check(second, radix)) {
            return "NO";
        }
            
        String calResult = "";
        if (m.equals("+")) {
            calResult = Integer.toString(Integer.parseInt(first, radix) + Integer.parseInt(second, radix), radix);
        } else {
            calResult = Integer.toString(Integer.parseInt(first, radix) - Integer.parseInt(second, radix), radix);
        }
        return calResult;
    }
    
    private static void cal(String exp, String m) {
        String[] tmp1 = exp.split(" \\" + m + " ");
        String[] tmp2 = tmp1[1].split(" = ");
        
        String first = tmp1[0];
        String second = tmp2[0];
        String result = tmp2[1];
        
        for (int i = 2; i <= 9; i++) {
            if (!check(first, i) || !check(second, i) || !check(result, i)) {
                isOk[i] = false;
                continue;
            }
            
            String calResult = "";
            if (m.equals("+")) {
                calResult = Integer.toString(Integer.parseInt(first, i) + Integer.parseInt(second, i), i);
            } else {
                calResult = Integer.toString(Integer.parseInt(first, i) - Integer.parseInt(second, i), i);
            }
            if (!calResult.equals(result)) {
                isOk[i] = false;
            }
        }
    }
}