package BOJ_13549_숨바꼭질3;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    static int[] time = new int[100001];
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        // 수빈이가 0 위치에 있을 경우, 
        // 수빈이와 동생의 위치를 찾을 때 한 번의 순간이 걸리므로 
        // 최종 시간에 1을 더하기
        if (n == 0) {
            System.out.println(bfs(n, k) + 1);
        } else {
            System.out.println(bfs(n, k));
        }
    }

    static int bfs(int x, int y) {
        Deque<Integer> queue = new ArrayDeque<>();
        // 수빈이의 초기 위치가 0일 경우, 
        // 1에서 출발하는 것과 같은 효과를 위해 큐에 1 추가 (한칸 옆으로)
        if (x == 0) queue.add(1);
        else queue.add(x);
        

        while (!queue.isEmpty()) {
            x = queue.poll();
            // 동생의 위치에 도달했을 경우 해당 위치까지의 최소 시간 반환
            if (y == x) return time[x];
            
            // 순간이동 및 이동 탐색하며 큐에 계속 넣기
            int[] nextX = {x - 1, x + 1, x * 2};
            for (int nx : nextX) {
            	// 범위 내 && time에 방문하지 않았다면
                if (0 <= nx && nx < 100001 && time[nx] == 0) {
                    if (nx == 2 * x) {
                        time[nx] = time[x];
                        queue.addFirst(nx);
                    } else {
                        time[nx] = time[x] + 1;
                        queue.addLast(nx);
                    }
                }
            }
        }
        return -1; // 도달할 수 없음
    }
}
