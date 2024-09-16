import java.util.Scanner;

public class BOJ_2941_크로아티아알파벳 {
    public static void main(String[] args) {

        /*설계 아이디어
        * 1. 입력은 알파벳 출력은 크로아디아 알파벳 개수
        * 크로아티아의 단어를 기준으로 순회해야 함
        * 2. 단순 삭제하면 예외 발생
        * 특수문자로 교체해야 함
        * 3. 배운 메서드
        * contains, replace*/

        // 입력 받기
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine(); // 한 줄 받기

        // 크로아티아 알파벳 리스트 생성
        String[] refs = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

        // 1. 입력 데이터 판별
        // 크로아티아 알파벳을 기준으로, 각 알파벳이 input에 들어가 있는지 확인
        // 만약 들어 있다면('contain'), 똑같은 알파벳 모든 것을 @로 교체('replace')
        // @의 특수문자로 교체를 해준 이유는 단순 삭제시
        // 삭제된 단어 기준 양쪽 단어가 새로운 단어를 만들 수 있기 때문
        for (int i = 0; i < refs.length; i++) {
            if (input.contains(refs[i])) {
                input = input.replace(refs[i], "@");
            }
        }

        // 2. 결과 출력
        // @의 갯수가 크로아티아 알파벳 개수를 의미
        System.out.println(input.length());
    }
}