package BOJ_1939_중량제한;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static int m;
    static List<List<Edge>> graph;
    static boolean[] visited;
    static int startIndex;
    static int endIndex;
    /*
     * 1. 전체 다리가 버틸 수 있는 무게의 최소, 최대를 구해서
     * 2. 그 사이를 이분탐색
     * 3. 탐색하며 mid값으로 목적지까지 갈 수 있는지 BFS
     */
    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt(); 
        visited = new boolean[n + 1]; 
        graph = new ArrayList<>(); 

        // 그래프의 각 정점에 대한 인접 리스트 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        // 이분탐색의 기준이 될 최소, 최대값 초기화
        int end = 0;
        int start = Integer.MAX_VALUE; 

        // 간선 정보 입력
        for (int i = 0; i < m; i++) {
            int now = sc.nextInt(); 
            int destination = sc.nextInt();
            int cost = sc.nextInt();
            end = Math.max(end, cost);
            start = Math.min(start, cost); 
            graph.get(now).add(new Edge(destination, cost));
            graph.get(destination).add(new Edge(now, cost));
        }

        startIndex = sc.nextInt();
        endIndex = sc.nextInt();
        
        int result = 0; // 결과값 초기화
        // 이분 탐색 시작
        while (start <= end) {
            int mid = (start + end) / 2; // 중량 제한의 중간값 계산
            visited = new boolean[n + 1]; // 방문 배열 초기화
            
            // 중간값 이상의 중량으로 시작 섬부터 도착 섬까지 탐색하여 연결 여부 확인
            if (bfs(mid)) { // 연결되어 있다면
                start = mid + 1; // 중량을 늘려서 다시 탐색
                result = mid; // 현재 중량을 결과값으로 저장
            } else { // 연결되어 있지 않다면
                end = mid - 1; // 중량을 줄여서 다시 탐색
            }
        }
        System.out.println(result);
    }

    // BFS를 통해 중량 제한 이상의 다리로 연결된지 확인하는 메서드
    private static boolean bfs(int limit) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(startIndex); // 시작 섬을 큐에 추가
        visited[startIndex] = true;
        
        // BFS
        while (!queue.isEmpty()) {
            int temp = queue.poll();
            
            if (temp == endIndex) // 도착 섬에 도착했다면 연결되어 있음을 반환
                return true;
            
            // 현재 섬과 연결된 다리의 중량 제한을 충족하고 아직 방문하지 않은 섬을 큐에 추가
            for (int i = 0; i < graph.get(temp).size(); i++) {
                if (graph.get(temp).get(i).cost >= limit && !visited[graph.get(temp).get(i).destination]) {
                    visited[graph.get(temp).get(i).destination] = true; // 방문 표시
                    queue.add(graph.get(temp).get(i).destination); // 다음 섬 큐에 추가
                }
            }
        }
        return false; // 도착 섬까지 도달하지 못했으면 연결되어 있지 않음을 반환
    }

    // 간선 클래스
    static class Edge {
        int destination;
        int cost;

        public Edge(int destination, int cost) {
            this.destination = destination;
            this.cost = cost;
        }
    }
}