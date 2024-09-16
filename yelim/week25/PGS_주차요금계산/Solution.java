import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int[] answer;
        
        // 차 번호, 총 주차 시간 저장할 맵
        Map<String, Integer> map = new HashMap<>();
        
        // 계산 완료 했는지 체크할 배열
        boolean[] checks = new boolean[records.length];
        
        for(int i=0; i<records.length; i++) {
            // 공백으로 구분해서 정보 배열에 저장
            String[] one = records[i].split(" ");
            
            // 계산 완료된 기록이면 넘어가기
            if (checks[i]) continue;
            
            // 시간을 분으로 환산
            int minute = (Integer.parseInt(one[0].substring(0, 2))*60) + Integer.parseInt(one[0].substring(3, 5));
            // 차 번호
            String car = one[1];
            // 입차, 출차 내역
            String check = one[2];
            
            
            for(int j=i; j<records.length; j++) {
                // 비교할 다음 기록 정보 저장
                String[] comp = records[j].split(" ");
                
                int minute2 = (Integer.parseInt(comp[0].substring(0, 2))*60) + Integer.parseInt(comp[0].substring(3, 5));
                String car2 = comp[1];
                String check2 = comp[2];
                
                // 만약 두 차의 번호가 같고, 비교할 차량이 출차인 경우 (무조건 입차가 먼저니까)
                if (car.equals(car2) && check2.equals("OUT")) {
	                     
                    // 주차 시간 구해주기
                    int time = minute2-minute;
                    
                    // 이미 맵에 차번호가 저장되어 있으면 누적해서 더해주어 해당 value값 갱신
                     if (map.containsKey(car)) {
                        map.put(car, map.get(car)+time);
                    }
                    
                    // 아니면 새로 넣어주기
                    else {
                        map.put(car, time);
                    }
                        
                    // 계산 완료 표시
                    checks[i] = true;
                    checks[j] = true;
                    
                    break;
                } 
                
            }
        }
        
        // 계산 완료되었는지 체크하는 배열 돌면서 계산 완료되지 않은 것이 있는지 확인
        // 완료되지 않았다는 것 => 입차 후 출차 내역 없음. => 23:59 출차로 간주
        for(int i=0; i<checks.length; i++) {
            if (!checks[i]) {
                String[] one = records[i].split(" ");
                
                int minute = (Integer.parseInt(one[0].substring(0, 2))*60) + Integer.parseInt(one[0].substring(3, 5));
                String car = one[1];
                String check = one[2];
                
                // 23:59를 분으로 환산
                int minute2 = (23*60) + 59;
                
                // 주차 시간 구하기
                int time = minute2-minute;
                
                if (map.containsKey(car)) {
                    map.put(car, map.get(car)+time);
                }
                else {
                    map.put(car, time);
                }
                
                // 계산 완료 표시
                checks[i] = true;
                
               
            }
        }
        
        // 키를 리스트에 저장
        List<String> keys = new ArrayList<>(map.keySet());

        // 키 값 오름차순 정렬
        Collections.sort(keys);
        
        // key 개수만큼의 배열로 초기화
        answer = new int[keys.size()];
        
        // answer 배열의 인덱스
        int idx = 0;
        
        // 키 값 리스트 반복 돌며 맵에서 해당 value 값 가져와서 요금 계산
        for(String k : keys) {
            
            int fee = 0;
            // 총 주차시간 맵에서 key로 value값 가져오기
            int totalTime = map.get(k);
            
            // 총 주차시간이 기본 시간보다 크거나 같을 때
            if (totalTime >= fees[0]) {
                // 초과 시간이 단위 시간으로 나누어 떨어지지 않으면
                if ((totalTime-fees[0])%fees[2] != 0) {
                    // 나눈 것에 1 더해서 요금 계산
                    fee = fees[1] + (((totalTime-fees[0])/fees[2] + 1) * fees[3]);
                }
                // 나누어 떨어지면 그대로 요금 계산
                else {
                    fee = fees[1] + (((totalTime-fees[0])/fees[2]) * fees[3]);
                }
            }
            // 총 주차시간이 기본 시간보다 작으면 기본 요금
            else {
                fee = fees[1];
            }
            
            // answer 배열에 요금 저장
            answer[idx++] = fee;
        }
        
        return answer;
    }
}