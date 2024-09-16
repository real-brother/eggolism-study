import java.util.*;

class Solution {
    
    static Map<String, Integer> map;
    static List<String> list_final;
    static char[] sel;
    static int courseMax;
    
    public String[] solution(String[] orders, int[] course) {
        
        list_final = new ArrayList<>();
        
        for (int t = 0; t < course.length; t++) {
            int cor = course[t];
     
        // course 하나 시작
        map = new HashMap<>();
        courseMax = 0;
        
        for (int i = 0; i < orders.length; i++) {
            String input = orders[i];
            
            sel = new char[cor];
            comb(input, 0, 0, cor);
        }
        
        // 최종 하나 추가하기 (최대값이 같으면 어러개)
        for(String key : map.keySet()) {
            if (map.get(key) == courseMax) {
                list_final.add(key);
            }
        }
       }
        
        
        // 정답 출력하기
        // System.out.println(">>>>>>>>>> 1 " + map.toString());
        // System.out.println(">>>>>>>>>> 2 " + list_final.toString());
        
        String[] answer = new String[list_final.size()];
        for (int i = 0; i < list_final.size(); i++) {
            answer[i] = list_final.get(i);
        }
        Arrays.sort(answer);
        
        return answer;
    }
    
    public static void comb(String input, int idx, int sidx, int cor) {
        
        if (sidx == cor) {
            
            // System.out.println(Arrays.toString(sel));
            // System.out.println(Arrays.toString(sel2));
                
            // 문자로 추가
            String tmp = "";
            char[] sel3 = new char[cor];
            sel3 = sel.clone();
            Arrays.sort(sel3);
            for (int i = 0; i < cor; i++) {
                tmp += sel3[i];
            }
            // System.out.println(tmp);

            
            // 없으면 맵에 추가
            if (!map.containsKey(tmp)) {
                map.put(tmp, 1);
            }
            // 있으면 맵의 숫자 추가
            else {
                map.replace(tmp, map.get(tmp)+1);
                
                // 최대값 갱신
                courseMax = Math.max(courseMax, map.get(tmp));
            }
  
            
            return;
        }
        
        if (idx == input.length()) {
            return;
        }
        
        // 조합 만들기
        sel[sidx] = input.charAt(idx);
        comb(input, idx+1, sidx+1, cor);
        comb(input, idx+1, sidx, cor);
        
        
    }
    
    
}