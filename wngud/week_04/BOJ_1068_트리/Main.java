import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*설계 과정
* 1. 일반적인 인접 리스트 만들기와 DFS 돌리기
* 2. 삭제할 노드는 노드에 추가하지 않기
* 3. 하지만, 인접 리스트에 자리 한칸을 차지하기 때문에
* visted = 1로 만들어서 그쪽은 탐색하지 않게
* 4. DFS를 돌리면서, 이후의 원소가 없는 것(size = 0)을 기준으로 count++ 하기*/

/*예외 처리
* 1. 루트 노드를 삭제할 경우
* - 0을 출력하도록 함
* 2. 루트 노드가 처음에 나오지 않을 경우
* - DFS를 0으로 하면 안됨
* - 루트 노드부터 시작하게 루트 노드 변수를 저장해야 함*/

public class Main {

    static int[] visited;
    static ArrayList<ArrayList<Integer>> graph;
    static int count;
    static int root;


    static void dfs(int idx) {
        visited[idx] = 1;
				// 사이즈가 0으로 더 이상 갈 곳이 없는 노드면 count++
        if (graph.get(idx).size() == 0) {
            count++;
        }

        for (int i : graph.get(idx)) {
            if (visited[i] != 1) {
                dfs(i);
            }

        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int total = Integer.parseInt(br.readLine());

        // 인접 리스트 만들기
        // 주어진 조건 때문에 0부터 시작하는게 좋을듯
        graph = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            graph.add(new ArrayList<Integer>());
        }

        visited = new int[total];

        // 인접 리스트 채우기
        String[] inputs = br.readLine().split(" ");
        int del = Integer.parseInt(br.readLine());

        for (int i = 0; i < total; i++) {
            int parent = Integer.parseInt(inputs[i]); // -1
            int child = i;

            if (parent == -1) {
                root = child;
                continue;
            }
            if (child == del) continue; // 삭제 노드는 자식으로도 추가하지 않기

            graph.get(parent).add(child);
        }

        // 노드 삭제하기
				// 방문을 체크하여 가지 못하도록 하고, clear()로 지워버림
        visited[del] = 1;
        graph.get(del).clear();

				// 루트 노드를 지우는 경우는 0 -> 예외 처리
				// 나머지는 root부터 DFS 시작
        count = 0;
        if (del == root) {
            System.out.println(0);
        } else {
            dfs(root);
            System.out.println(count);
        }

    }
}