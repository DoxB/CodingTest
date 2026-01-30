import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();
        char[] ans = br.readLine().toCharArray();
        Stack<MyChar> sk = new Stack<>();

        if (ans.length == 1) {
            String tmp = String.valueOf(arr);
            tmp = tmp.replace(String.valueOf(ans[0]), "");
            if (tmp.length() == 0) {
                System.out.println("FRULA");
            } else {
                System.out.println(tmp);
            }
            return;
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (sk.isEmpty()) {
                    if (arr[i] == ans[0]) {
                        sk.push(new MyChar(arr[i], 1));
                    } else {
                        sk.push(new MyChar(arr[i], 0));
                    }
                } else {
                    if (arr[i] == ans[sk.peek().idx]) {
                        if (sk.peek().idx == ans.length - 1) {
                            for (int k = 0; k < ans.length - 1; k++) {
                                sk.pop();
                            }
                            continue;
                        } else {
                            sk.push(new MyChar(arr[i], sk.peek().idx + 1));
                        }
                    } else {
                        if (arr[i] == ans[0]) {
                            sk.push(new MyChar(arr[i], 1));
                        } else {
                            sk.push(new MyChar(arr[i], 0));
                        }
                    }
                }
            }
        }

        if (sk.isEmpty()) {
            System.out.println("FRULA");
        } else {
            char[] t = new char[sk.size()];
            for (int i = sk.size() - 1; i >= 0; i--) {
                t[i] = sk.pop().s;
            }
            System.out.println(String.valueOf(t));
        }
    }
}

class MyChar {
    char s;
    int idx;
    public MyChar (char s, int idx) {
        this.s = s;
        this.idx = idx;
    }
}