import java.util.*;

public class Main {

    static int[] parent;

    public static class Edge implements Comparable<Edge> {

        int from;
        int to;
        long weight;

        // 인덱스를 위해 from/to는 int형
        public Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int V = sc.nextInt();
        int E = sc.nextInt();

        // 간선 순서대로 넣기
        List<Edge> edges = new ArrayList<>();

        // 간선 정보 입력하기
        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            long weight = sc.nextLong();

            Edge edge = new Edge(from, to, weight);
            edges.add(edge);
        }

        // 부모 초기화
        parent = new int[V+1]; // 현실 숫자
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        // 간선 정렬하기
        Collections.sort(edges);

        // 크루스칼 알고리즘
        int pick = 0;
        long answer = 0;
        for (int i = 0; i < edges.size(); i++) {

            Edge cur = edges.get(i);

            int px = findSet(cur.from);
            int py = findSet(cur.to);

            // 유니온
            if (px != py) {
                parent[py] = px;

                pick++;
                answer += cur.weight;
            }

            if (pick == V-1) break;

        }


        // 결과 출력하기
        System.out.println(answer);


    }

    static int findSet(int idx) {
        if (idx == parent[idx]) return idx;
        return parent[idx] = findSet(parent[idx]);
    }

}