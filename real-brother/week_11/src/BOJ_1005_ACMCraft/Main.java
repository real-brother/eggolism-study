package BOJ_1005_ACMCraft;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int TEST_CASE, n, k;
    static int[] buildingTime, indegree, result;
    static List<List<Integer>> graph;
    // 간단한 위상정렬 문제입니다~
    // 배운거 복습도 하고, SWEA대비 문제가 꽤나 어려울거 같아
    // 물골3 문제를 가져와봤지

    public static void main(String[] args) {
        TEST_CASE = sc.nextInt();
        for (int tc = 0; tc < TEST_CASE; tc++) {
            n = sc.nextInt();
            k = sc.nextInt();
            buildingTime = new int[n + 1]; // 건물 시간 배열
            indegree = new int[n + 1]; // 진입 차수 배열
            result = new int[n + 1]; // 결과 배열
            graph = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 1; i <= n; i++) {
                buildingTime[i] = sc.nextInt();
            }

            // 그래프 및 진입 차수 설정
            for (int i = 0; i < k; i++) {
                int start = sc.nextInt();
                int dest = sc.nextInt();
                graph.get(start).add(dest);
                indegree[dest]++;
            }

            int targetNum = sc.nextInt();

            topologicalSort(targetNum);

            System.out.println(result[targetNum]);
        }
    }

    // 위상 정렬 수행
    static void topologicalSort(int target) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                result[i] = buildingTime[i];
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // 현재 건물로부터 이동 가능한 다음 건물들에 대해 처리
            // 건물의 총 소요시간 = 이전까지의 소요시간  + 현재건물 소요시간
            for (int next : graph.get(current)) {
                result[next] = Math.max(result[next], result[current] + buildingTime[next]);
                --indegree[next];
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
    }
}
