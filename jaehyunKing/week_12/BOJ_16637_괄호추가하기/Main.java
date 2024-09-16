package BOJ_16637_괄호추가하기;

/*
 *  조합 + 쌩구현
 *  조합으로 괄호를 사용할지 말지 정한다
 *  ex) 3+8*7-9*2
 *  양 끝의 숫자를 제외한 8 7 9를 조합으로 함
 *  8이 true라면 8과 7을 괄호로, 9가 true라면 9와 2를 괄호로 묶는다. -> 스택을 이용해 연산 처리
 *  0번 자리를 버리면
 *  sel[1] -> 8(문자열에서 index가 2)
 *  sel[2] -> 7(index 4)
 *  sel[3] -> 9(index 6)
 */

import java.util.Scanner;
import java.util.Stack;

public class Main {
    static boolean[] sel;
    static String S;
    static int N, max, result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        
        S = sc.next();
        sel = new boolean[N/2]; // 0 -> 2, 4
        max = Integer.MIN_VALUE;
        
        if(N != 1) combination(1);
        else max = S.charAt(0)-'0';
        
        System.out.println(max);
    }
    
    // 조합
    // sel true라면 괄호로 묶는다.
    static void combination(int idx) {
        if(idx == N/2) {
        	// 현재 값이 true일 때 이전 값이 true라면 return
        	// 중복 괄호 방지
            boolean before = false;
            for(int i = 1; i < sel.length; i++) {
                if(sel[i] && before == true) return;
                else if(sel[i]) before = sel[i];
                else before = false;
            }
            check();
            return;
        }
        
        sel[idx] = true;
        combination(idx+1);
        
        sel[idx] = false;
        combination(idx+1);
    }
    
    static void check() {
        Stack<Character> stack = new Stack<>();
        // 첫 번째 숫자를 결과에 넣는다
        result = S.charAt(0)-'0';
        
        for(int i = 1; i < S.length(); i++) {
            int temp = 0;
            
            // 홀수 번 째 -> 기호이므로 스택에 넣어준다.
            if(i % 2 == 1) stack.push(S.charAt(i));
            
            // 마지막 숫자가 아니고 괄호로 묶는 숫자라면
            else if(i != S.length()-1 && sel[i/2]) {
            	// 괄호로 묶는 애들의 연산을 먼저 해준다.
            	temp = cal(S.charAt(i), S.charAt(i+1), S.charAt(i+2));
            	
            	// 스택에서 기호를 꺼내서 result값과 temp값을 연산해준다.
                resultCal(temp, stack.pop());
                
                // i+3번 째 값이 문자열 최대 길이보다 작으면
                // 스택에 넣는다(숫자 다음은 기호)
                if(i+3 < S.length()-1) {
                	stack.push(S.charAt(i+3));
                	i = i+3;
                }
                // for문을 끝내기 위해 i값을 더 해준다
                else i = i+2;
            }
            // 괄호로 묶는 숫자가 아니라면 스택에서 기호를 꺼내서 연산
            else resultCal(S.charAt(i)-'0', stack.pop());
            
        }
        if(max < result) max = result; 
    }
    
    static int cal(char num1, char symbol, char num2) {
    	if(symbol == '+') return (num1-'0') + (num2-'0');
        else if(symbol == '-') return (num1-'0') - (num2-'0');
        else return (num1-'0') * (num2-'0');     	
    }
    
    static void resultCal(int num, char symbol) {
    	if(symbol == '+') result += num;
        else if(symbol == '-') result -= num;
        else result *= num;
    }

}