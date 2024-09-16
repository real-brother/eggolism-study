import java.util.Scanner;

public class BOJ_1157_단어공부 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /*설계 아이디어
        * 1. 알파벳 갯수 세기
        * 카운트 배열의 논리 사용
        * char의 아스키 코드를 활용하여 카운트 배열의 인덱스 찾기*/

        // 입력 받기
        String target = sc.nextLine();
        int[] arr = new int[26]; // 알파벳 개수

        // 1. 알파벳 갯수 세기
        // count 배열처럼, char 하나씩 알파벳 배열 인덱스에 +1
        // 인덱스는 아스키코드를 이용

        // 대소문자를 구분하지 않으니 전부 대분자로 만들고 시작
        target = target.toUpperCase();

        for (int i = 0; i < target.length(); i++) {
            arr[target.charAt(i) - 'A']++; //0부터 카운팅
        }

        // 2. 정답 만들기
        // 카운트 배열에서 가장 큰 숫자를 찾기
        // 카운트 배열에 가장 큰 숫자를 찾았으면 다시 아스키코드를 더해서 형변환
        // max와 값이 같은 것이 1개 더 있다면, answer에 '?'를 대입
        int max = 0;
        char answer = '0';
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                answer = (char) ('A' + i);
            } else if (arr[i] == max) {
                answer = '?';
            }

        }

        // 3. 정답 출력하기
        System.out.println(answer);

    }
}