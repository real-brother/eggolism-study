package PGM_도넛과막대;

//결국 86.5점 풀이였으나 풀어버림
//35개 테케 중 (29개 정답, 6개 런타임 에러)
//아무리봐도 런타임이 왜 나는지 모르겠음

//int edgeNum = edges.length; + 1 하면 정답 풀이 (100점)
import java.util.*;

class Solution {
 static ArrayList<List<Integer>> graph = new ArrayList<List<Integer>>();
 static int[] inCount, outCount, answer;
 static boolean[] visited;
 
 public int[] solution(int[][] edges) {
     answer = new int[4];
     int edgeNum = edges.length + 1;
     inCount = new int[edgeNum+1];
     outCount = new int[edgeNum+1];
     visited = new boolean[edgeNum+1];
     // 그래프 구축하기
     for (int i = 0; i <= edgeNum; i++) {
         graph.add(new ArrayList<>());
     }
     for (int[] edge: edges){
         int start = edge[0];
         int dest = edge[1];
         graph.get(start).add(dest);
     }
     // 각 점의 들어오는, 나가는 수 구하기
     // 2중 for문 쓰면 안됨 -> 테스트 케이스 100만 * 100만 = 펑
     for (int i = 1; i <= edgeNum; i++) {
         outCount[i] = graph.get(i).size();
         for (int dest: graph.get(i)){
             inCount[dest]++;
         }
     }
     
     for (int start = 1; start <= edgeNum; start++) {
         // 끝이 막혀있는 막대그래프 판별
         // 나가는거 0개, 들어오는거 1개 이상
         if (outCount[start] == 0 && inCount[start] >= 1){
             answer[2]++;
         } 
         // 8자 모양 그래프 판별
         // 나가는거 2개, 들어오는거 2개 or 3개
         else if (outCount[start] == 2 && inCount[start] >= 2){
             answer[3]++;
         }
         // 정점 판별
         // 나가는거 2개 이상이며 최대, 들어오는거 0개
         else if (outCount[start] >= 2 && inCount[start] == 0){
             if (outCount[answer[0]] < outCount[start]){
                 answer[0] = start;
             }
         }
     }
     
     
     // dfs풀이 점수 31.4점 ㅠㅠ
     // 예를들어 막대그래프가 999998 길이라면 바로 stackOverFlow 발생 -> 프로그래머스에서 런타임 에러
     // 정점에서 출발하는 모든 점들을 시작으로 dfs 그래프 탐색
     // for (int startNode: graph.get(startNum)){
     //     visited = new boolean[edgeNum+1];
     //     // 각 그래프가 도넛, 막대, 8자 그래프 판별하여 count
     //     dfs(startNode);
     // }
     
     // 도넛 모양 그래프 = 정점 출발 하는 간선 개수 - 8자 그래프 - 막대 그래프
     answer[1] = outCount[answer[0]] - answer[2] - answer[3];
     return answer;
 }
 
//  public static void dfs(int start){
//      visited[start] = true;
//      // 끝이 막혀있는 막대그래프 판별
//      // 나가는거 0개
//      if (outCount[start] == 0){
//          answer[2]++;
//          return;
//      } 
//      // 8자 모양 그래프 판별
//      // 나가는거 2개, 들어오는거 2개 or 3개
//      else if (outCount[start] == 2 && inCount[start] >= 2){
//          answer[3]++;
//          return;
//      }
         
//      // 아직 어떤 그래프인지 불분명한데 목적지가 있다면 전부 다 dfs탐색
//      for (int dest: graph.get(start)){
//          if (!visited[dest]){
//              dfs(dest);
//          }
//      }
//  }
}



//System.out.println(graph);
//for (int i = 0; i <= edgeNum; i++) {
//  System.out.print(inCount[i] + " ");
//}
//System.out.println();
//for (int i = 0; i <= edgeNum; i++) {
//  System.out.print(outCount[i] + " ");
//}
//System.out.println();