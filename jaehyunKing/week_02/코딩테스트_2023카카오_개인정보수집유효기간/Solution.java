package 코딩테스트_2023카카오_개인정보수집유효기간;

import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        // 답을 담을 리스트
        List<Integer> result = new ArrayList<>();
        
        // 종류 유효기간을 담을 맵
        Map<String, Integer> map = new HashMap<>();
        
        //년 월 일로 구분
        String[] date = today.split("\\.");
        int today_year = Integer.parseInt(date[0]);
        int today_month = Integer.parseInt(date[1]);
        int today_day = Integer.parseInt(date[2]);
        
        // 맵에 넣어줌 key -> A, value -> 유효기간
        for(int i = 0; i < terms.length; i++){
            String[] S = terms[i].split(" ");
            map.put(S[0], Integer.parseInt(S[1]));
        }
        
        // 수집일자와 유효기간 짝 지어주기 + 계산
        for(int i = 0; i < privacies.length; i++){
            String[] S = privacies[i].split(" ");
            String[] start_date = S[0].split("\\.");
            
            int start_year = Integer.parseInt(start_date[0]);
            int start_month = Integer.parseInt(start_date[1]);
            int start_day = Integer.parseInt(start_date[2]);
            
            int expire_terms = map.get(S[1]);
            
            // 연도 더 해주기
            start_year += expire_terms / 12;
            start_month += expire_terms % 12;
            
            // 시작 날짜 + 유효기간 구하기(날짜 28일, 날짜 -1 해줘야함,
            start_day -= 1;
            
            // 일이 0이면 28로 만들어주고 월을 1 빼줌 뒤에서 월 더 할꺼여서 
            // 연은 나중에 생각
            if(start_day == 0) {
                start_day = 28;
                start_month--;
            }  
            //월이 12를 넘으면 12만큼 빼주고 연 1 추가
            if(start_month > 12) {
                start_month -= 12;
                start_year++;
            }
                
            //일이 0일 때 월을 1 빼는데 이 때 월이 0이하면 1년 전으로 되돌림
            if(start_month == 0){
                start_month = 12;
                start_year--;
            }
                
            // 유효기간과 현재 날짜 비교 유효기간이 현재날짜보다 작으면 ok
            if(start_year > today_year) continue;
            else if(start_year == today_year){
                if(start_month > today_month) continue;
                else if(start_month == today_month){
                    if(start_day >= today_day) continue;
                }
            }
            result.add(i+1);
        
        }
        
        int[] answer = new int[result.size()];
        for(int i = 0; i < result.size(); i++) answer[i] = result.get(i);
        return answer;
    }
}