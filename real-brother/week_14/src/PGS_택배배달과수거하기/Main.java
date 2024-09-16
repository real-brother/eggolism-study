package PGS_택배배달과수거하기;

//생각의 뒤집기가 참 어렵다는 것을 보여주는 문제
//정방향으로 풀면 무조건 시간초과가 날 수 밖에 없음

class Solution {
 public long solution(int cap, int n, int[] deliveries, int[] pickups) {
     long answer = 0;
     int deliver = 0;
     int pickup = 0;
     for (int i = n-1; i >= 0; i--){
         deliver -= deliveries[i];
         pickup -= pickups[i];
         
         // 해당 위치까지 배달, 픽업 잔여가 남아있다면 = 그 지점까지 찍고 돌아가야함
         // 트럭의 용량만큼 잔여를 줄여주기
         while (deliver < 0 || pickup < 0){
             deliver += cap;
             pickup += cap;
             answer += (i + 1) * 2;
         }
     }
     
     return answer;
 }
}