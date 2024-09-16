// 실패코드



import java.util.ArrayList;
import java.util.Scanner;

/*설계 아이디어
* 1. 결국 선택해야 하는 노드는 리프 노드임
* 2. root를 기준으로 가장 긴 리프 노드 2개를 찾음
* 3. 두개를 더하고 둘의 공통 부모의 누적합을 뺌
* -> 여기서의 문제: 중간의 노드를 구한다면 역으로 가능 노드를 고려하지 않음*/

public class Main {
    static ArrayList<ArrayList<Integer>> tree;
    static int[] visited;
    static int[] weights;
    static int[] prefixSum;
    static int[] terminals;

    static void dfs(int idx) {

        visited[idx] = 1;

        for (int i : tree.get(idx)) {
            if (visited[i] != 1) {
                prefixSum[i] = prefixSum[idx] + weights[i]; // 부모와 자기 자신
                dfs(i);
            }
        }

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int total = sc.nextInt();

        // 트리 선언하기
        tree = new ArrayList<>();
        visited = new int[total + 1];
        weights = new int[total + 1];
        terminals = new int[total + 1];
        prefixSum = new int[total + 1];
        // 1부터 시작
        for (int i = 0; i < total + 1; i++) {
            tree.add(new ArrayList<Integer>());
        }

        // 루트 제외
        for (int i = 0; i < total - 1; i++) {
            int parent = sc.nextInt();
            int child = sc.nextInt();
            int weight = sc.nextInt();

            tree.get(parent).add(child);
            weights[child] = weight; // 자식을 기준으로 가중치 저장
        }

        // 각 노드별로 dfs 돌리기
        dfs(1);

        // 최대값 2개인 리프 노드 찾기
        int first = 0;
        int firstIdx = 0;
        int second = 0;
        int secondIdx = 0;
        for (int i = 0; i < prefixSum.length; i++) {
            if (first < prefixSum[i]) {
                first = prefixSum[i];
                firstIdx = i;
            }
            if ((first != prefixSum[i]) && (second < prefixSum[i])) {
                second = prefixSum[i];
                secondIdx = i;
            }
        }

        // 분기 노드(공통 부모) 찾기
        int parent1 = firstIdx;
        int parent2 = secondIdx;

        while (parent1 != parent2) {
            for (int i = 1; i < tree.size(); i++) {
                for (int t : tree.get(i)) {
                    if (parent1 == t) parent1 = i;
                    if (parent2 == t) parent2 = i;
                }
            }
        }

        // 정답 출력
        int answer = (first + second) - prefixSum[parent1]*2;
        System.out.println(answer);

    }
}