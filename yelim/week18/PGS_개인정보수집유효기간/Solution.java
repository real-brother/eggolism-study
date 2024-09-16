import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> ans = new ArrayList<>();
        
        boolean[] check = new boolean[privacies.length];
        String[] todays = today.split("[.]");
        int[] todayDate = new int[3];
        
        for(int i=0; i<todays.length; i++) {
            todayDate[i] = Integer.parseInt(todays[i]);
        }
        
         // 일수로 변환
        int todayDays = (todayDate[0] * 12 *28) + (todayDate[1] * 28) + todayDate[2];
        
        for(int i=0; i<privacies.length; i++) {
            String[] one = privacies[i].split(" ");
            
            for(int j=0; j<terms.length; j++) {
                String[] term = terms[j].split(" ");
                int expire = Integer.parseInt(term[1]);
                
                if (one[1].equals(term[0])) {
                    String[] dates = one[0].split("[.]");
                    int[] date = new int[3];
                
                    
                    for(int d=0; d<dates.length; d++) {
                        date[d] = Integer.parseInt(dates[d]);
                    }
                    
                    int expireDays = (date[0]*12*28) + ((date[1]+expire)*28) + date[2] -1;
                    
                    if (todayDays > expireDays) {
                    	ans.add(i+1);
                    }
                    
                }
                
                
            }
        }
        
        System.out.println(ans);
        
        int[] answer = new int[ans.size()];
        
        for(int i=0; i<ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        
        return answer;
    }
}