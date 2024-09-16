import java.util.*;

public class BOJ_1966_프린터큐 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /*큐에 들어갈 숫자를 4가지로 나눌 수 있음
        * 1. target 숫자보다 낮은 숫자 --> 아무 의미가 없기에 삭제해도 무방
        * 2. target 숫자보다 큰 숫자 --> 이 숫자들이 queue의 로직으로 전부 사라졌을 때 중복 숫자의 위치를 계산해야 함
        * 3. target 숫자와 같은 숫자 --> 큰 숫자가 사라지고, 순서가 중요해짐
        * 4. target 고유의 숫자
        *
        * 1차 목표: target의 숫자와 target보다 높은 숫자만 queue에 넣기
        * 2차 목표: target 보다 높은 숫자들이 사라졌을 때, target 고유 숫자의 위치 찾기
        *
        * target 고유의 숫자를 고유하게 할지 안할지 하다가, 1~9까지 밖에 없길래 재미없지만 10을 넣어줌
        * 1. queue를 만들고 target 숫자를 찾음(target 고유 숫자와 다름. 단순 숫자)
        * 2. 높은 숫자의 개수를 찾고, 낮은 숫자를 제거해줌
        * 낮은 숫자는 필요가 없어서 제거해주고
        * 높은 숫자는 뒤의 과정에서 queue를 반복할텐데, queue.poll()을 높은 숫자의 개수만큼 진행하면,
        * 자동적으로 남은 것은 target 숫자들 밖에 없음
        * 3. 큐를 작동해줌. 이전에 만들어 놓은 내림차순으로 정렬된 리스트를 참고하여,
        * 그 리스트와 동일한 숫자이면(높은 숫자들) queue에서 제거하고, 아니면 queue의 뒤로 추가하는 방식으로 진행
        * 이렇게 하면 남는 것은 target 숫자들 밖에 없음
        * 4. 이제 10의 숫자의 위치만 파악하면 됨
        * 5. 결과적으로 target 숫자보다 높은 숫자들의 개수가 target 고유 숫자에 앞서서 빠져나간 것
        * 여기에 더하여, target 숫자들만 있는 상황에서 숫자 10의 인덱스를 더해줘야 함. */


        int TEST = sc.nextInt();

        for (int t = 0; t < TEST; t++) {


            int N = sc.nextInt();
            int M = sc.nextInt(); // 0부터 주의 -> 리스트에서 먼저 추출해야 함

            // 1. 큐 만들기
            // + 타켓 숫자 찾기
            Queue<Integer> queue = new LinkedList<>();
            Queue<Integer> newQueue = new LinkedList<>();
            List<Integer> list = new ArrayList<>();

            int targetNum = 0;

            for (int i = 0; i < N; i++) {
                int num = sc.nextInt();
                if (i == M) {
                    // 타겟넘버 찾기 완료
                    targetNum = num;
                    queue.offer(10);
                    list.add(num);
                } else {
                    queue.offer(num);
                    list.add(num);
                }


            }

            Collections.sort(list, Collections.reverseOrder()); // 내림차순

            // 2. target 보다 높은 숫자 개수 찾기
            // + 낮은 숫자들은 제거
            int upperCount = 0; // 이 숫자 만큼 queue의 poll()이 되어야 함
            for (Integer i : queue) {
                if (i > targetNum) {
                    upperCount += 1;
                    newQueue.offer(i);
                } else if (i == targetNum) {
                    newQueue.offer(i);
                }
            }

            // 3. queue 작동하기
            // upperCount까지 하면, target넘버까지만 존재
            int listCount = 0;
            int repetition = --upperCount; // 10이 포함되어 카운팅 됨

            while (repetition != 0) {
                if (list.get(listCount) == newQueue.peek()) {
                    newQueue.poll();
                    listCount++;
                    repetition--;
                } else {
                    newQueue.offer(newQueue.poll());
                }
//            System.out.println(newQueue.toString());
            }

            // 4. 10의 위치 찾기
            // queue.size()가 변함 --> for로 쓰면 안됨
            int sameCount = 1; // 자신 출력할 때 +1
            while (newQueue.peek() != 10) {
                newQueue.poll();
                sameCount++;
//            System.out.println(newQueue.toString());
            }


            // 4. 결과 출력하기
//        System.out.println("높은 숫자: " + upperCount);
//        System.out.println("앞에 같은 숫자: " + sameCount);
            System.out.println(upperCount + sameCount);

        }

    }
}