import java.util.*;

class Solution {
    static int point;
    
    public String solution(String p) {

        // 빈 문자열인지 확인
        if (p.isEmpty()) {
            return p;
        }
        
        // 올바른 괄호인지 판별
        boolean isRight = check(p);
        String u = p.substring(0, point);
        String v = p.substring(point, p.length());
            
        // 올바른 괄호 문자열이 아니면
        if (!isRight) {
            String answer = "(";
            answer += solution(v);
            answer += ")";
            
            // u의 처음과 끝 제외하고 괄호 반대로 붙여주기
            for(int i=1; i<u.length()-1; i++) {
                if (u.charAt(i) == '(') {
                    answer += ")";
                } else {
                    answer += "(";
                }
            }
            
            return answer;
            
        } else {    // 올바른 괄호이면
            return u + solution(v);
        }
    }
    
    // 올바른 괄호인지 체크
    static boolean check(String s) {
        Stack<Character> stack = new Stack<>();
        boolean check = true;
        
        int open = 0;
        int close = 0;
        
        for(int i=0; i<s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
                open++;
            } else {
                close++;
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    check = false;
                }
            }
            if (open==close) {
                point = i+1;
                return check;
            }
        }
        
        return true;
    }
    
}