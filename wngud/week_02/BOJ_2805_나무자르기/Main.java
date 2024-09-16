import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 입력 받기
    // 최대 높이값도 같이 구하기
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    int[] tree = new int[N];

    int max_height = 0;
    for (int i = 0; i < N; i++) {
        int num = Integer.parseInt(st.nextToken());
        tree[i] = num;

        if (max_height < num) {
            max_height = num;
        }
    }

    long answer = binarySearch(tree, M, max_height);
    System.out.println(answer);

    }

    /*이진 탐색 함수 제작
    * 1. LB 개념을 이용
    * 값이 똑같으면 좋고, 아니라면 한칸만 더 높은 값을 반환하면 됨
    * 크거나 같은 인덱스를 반환하는 LB 개념을 이용
    * 
    * 2. 정렬이 내림차순으로 되어 있음 (기존은 오름차순)
    * 찾은 값이 높아서, pc값을 낮췄는데 오히려 target 값이 더 증가하는 결과
    * 
    * key값 보다 낮은 값을 찾으면 pl을 높이고, key값 보다 높은 값을 찾으면
    * pr을 낮추는 기본 아이디어를 뒤집기만 하고, 세부적인 내용 조율 필요.
    * 
    * LB를 사용하니, 둘이 같을 때 종료조건을 사용하는 것은 동일.
    * 
    * 목표는 같아질 때까지 계속 오른쪽으로 가다가 숫자가 바뀔 때 왼쪽으로 한 번 튕기는 것이다.
    * 따라서 같거나 작을 때 `pl` 을 계속 높이다가, 마지막에 `pr` 을 1 증가시켜 pl과 pr이 같게 한다.
    * 
    * 마지막으로, `pc` 를 내림으로 계산한다면, 숫자가 바뀌는 상황에서 pc가 pl을 바라보게 될 것이다. 
    * 그렇게 되면 무한순환에 빠지게 된다.
    * pc를 높임으로 계산하도록 하여, 숫자가 바뀌는 상황에서 pc가 (pl이 아닌) pr을 바라보게 하고, 
    * 숫자가 낮아져서 pr이 한 칸 내려가서 pl과 pc가 같아지게 해야 한다.
    * 
    */

    /*mistakes
    * - 단순히 뺀 다음 더해서, 음수값이면 0을 반환하여 하는데 음수값인데 sum을 더하고 있었음
    */
    
    static long binarySearch(int[] arr, int key, int length) {
        long pl = 0;
        long pr = length-1; // length로 해도 의미 없을 듯
        long sum = 0;

        while (pl < pr) {

            long pc = ((pl + pr) / 2)+1; // 높이

            sum = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] - pc > 0) sum += (arr[i] - pc);
            }

            if (key <= sum) {
                pl = pc;

            } else {
                pr = pc-1;
            }

        }
//        System.out.println(sum);
        return pl;
    }


}


/*반례 만들기
* 4 7
20 14 10 17*/