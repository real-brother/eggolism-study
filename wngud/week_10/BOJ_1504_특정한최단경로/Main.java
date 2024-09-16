import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node> {

        int to, weight; // 노드는 to만 사용

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static List<ArrayList<Node>> graph;
    static int[] visited;
    static int[] dist;

    static int V, E;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        V = sc.nextInt();
        E = sc.nextInt();


        // 1. 그래프 만들기
        graph = new ArrayList<>();

        // 그래프 초기화
        for (int i = 0; i <= V; i++) { // 현실 숫자
            graph.add(new ArrayList<>());
        }

        // 간선 정보 저장 -> 인접 리스트
        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();

            // 양방향 그래프
            graph.get(from).add(new Node(to, weight)); // 노드 생성
            graph.get(to).add(new Node(from, weight)); // 노드 생성
        }

        int v1 = sc.nextInt();
        int v2 = sc.nextInt();

        // 입력 받기 끝 //

        // CASE 1

        int answer = 0;
        int[] travel = new int[] {1, v1, v2, V};

        // 3번 순회
        for (int i = 0; i < travel.length-1; i++) {
            int start = travel[i];
            int end = travel[i+1];

            // 다익스트라
            dijkstra(start);

            // 정답 구하기 -> 오버풀로우 방지
            int tmp = dist[end];
            if (tmp == 987654321) {
                answer = -1;
                break;
            } else {
                answer += tmp;
            }
        }

        // CASE 2

        int answer2 = 0;
        travel = new int[] {1, v2, v1, V};

        // 3번 순회
        for (int i = 0; i < travel.length-1; i++) {
            int start = travel[i];
            int end = travel[i+1];

            // 다익스트라
            dijkstra(start);

            // 정답 구하기
            int tmp = dist[end];
            if (tmp == 987654321) {
                answer2 = -1;
                break;
            } else {
                answer2 += tmp;
            }
        }

        if (answer == -1 && answer2 == -1) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(answer, answer2));
        }


    }


    public static void dijkstra(int start) {

        visited = new int[V + 1]; // 현실 숫자
        dist = new int[V + 1]; // 현실 숫자
        for (int j = 0; j < dist.length; j++) {
            dist[j] = 987654321;
        }

        // 세팅 끝

        PriorityQueue<Node> queue = new PriorityQueue<>();

        // 처음 노드는 거리를 0으로 하고 시작하도록 세팅
        queue.add(new Node(start, 0)); // 생성

        while (!queue.isEmpty()) {

            // 1) 노드 선택
            Node cur = queue.poll(); // 거리가 가장 가까운 것 선택
            if (visited[cur.to] == 1) continue;
            dist[cur.to] = cur.weight;
            visited[cur.to] = 1;

            // 2) 다음 노드 선택
            // 저장되어 있는 것(거리 배열) vs. 현재 선택된 노드를 통해서 가는 경우(현재 노드 저장 + 현재 노드로 가는 가중치)
            for (Node node : graph.get(cur.to)) {
                if (dist[node.to] > dist[cur.to] + node.weight) {
                    dist[node.to] = dist[cur.to] + node.weight;
                    queue.add(new Node(node.to, dist[node.to])); // 생성
                }
            }

        } // while 끝

    }
}