package BOJ_1406_에디터;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        String initialString = sc.nextLine();
        int m = sc.nextInt();
        sc.nextLine(); // 개행 문자 처리
        
        Stack<Character> leftStack = new Stack<>();
        Stack<Character> rightStack = new Stack<>();
        
        // 초기 문자열을 왼쪽 스택에 모두 넣기
        for (char ch : initialString.toCharArray()) {
            leftStack.push(ch);
        }
        
        // 명령어 처리
        for (int i = 0; i < m; i++) {
            String command = sc.nextLine();
            char cmd = command.charAt(0);
            
            switch (cmd) {
                case 'L':
                    if (!leftStack.isEmpty()) {
                        rightStack.push(leftStack.pop());
                    }
                    break;
                case 'D':
                    if (!rightStack.isEmpty()) {
                        leftStack.push(rightStack.pop());
                    }
                    break;
                case 'B':
                    if (!leftStack.isEmpty()) {
                        leftStack.pop();
                    }
                    break;
                case 'P':
                    char addChar = command.charAt(2);
                    leftStack.push(addChar);
                    break;
            }
        }
        
        // 결과 생성
        StringBuilder result = new StringBuilder();
        while (!leftStack.isEmpty()) {
            rightStack.push(leftStack.pop());
        }
        while (!rightStack.isEmpty()) {
            result.append(rightStack.pop());
        }
        
        System.out.println(result.toString());
        sc.close();
    }
}
