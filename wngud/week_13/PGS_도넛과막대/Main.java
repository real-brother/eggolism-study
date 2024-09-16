import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        
        int N = edges.length+2; // 노드 개수 무조건 넘음
        
        List<ArrayList<Integer>> graph = new ArrayList<>();
        int[] count_from = new int[N+1];
        int[] count_to = new int[N+1];
        int[] answer = new int[4];
    
        for (int i = 0; i <= N+1; i++) {
            graph.add(new ArrayList<>());
        }
        
        // 입력 받기 -> 그래프 채우기, 기준 노드 찾기
        int tmp = -1;
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            
            graph.get(from).add(to);
            
            // 기준 노드 찾기
            // 지목이 없는 노드가 시작 노드
            count_from[from]++;
            count_to[to]++;
            
            // 노드 총 개수 찾기
            tmp = Math.max(tmp, Math.max(from, to));
        }
        
        N = tmp;
        // System.out.println("전체 노드수: " + N);
        
        // 시작 노드 찾기
        int startNode = -1;
        for (int i = 1; i <= N; i++) {
            // 1인 경우는 어떤 것이 되어도 상관없나?
            if (count_from[i] >= 2 && count_to[i] == 0) startNode = i;
        }
        // System.out.println("시작 노드: " + startNode);
        answer[0] = startNode;
        
        // 그래프 출력해보기
        // for (int i = 0; i < graph.size(); i++) {
        //     System.out.println(graph.get(i).toString());
        // }
        
        // 시작 노드로 각각 탐색 -> 노드와 간선 개수? / 각각의 특성?
        for (int i = 0; i < graph.get(startNode).size(); i++) {
            // System.out.println("탐색 시작합니다: " + graph.get(startNode).get(i));
            
            // 그래프 탐색 시작
            int go = graph.get(startNode).get(i);
            int move = go;
            boolean dou = true;
            
            
            do {
                 // 사이즈가 있다면 이동!!
                if (graph.get(move).size() == 1) {
                    move = graph.get(move).get(0);
                } else if (graph.get(move).size() == 2) {
                    answer[3]++;
                    dou = false;
                    break; // 8자인 것 찾음
                } else {
                    answer[2]++;
                    dou = false;
                    break; // 막대기 경우에는 갈 곳이 없어서 끝 (어디든 시작해도 똑같은 곳 못옴)
                }
            } while(go!= move);
            
            if (dou) answer[1]++;
            
            // System.out.println(Arrays.toString(answer));
            
        }
        
        
        return answer;
    }
}