import java.util.*;

class Solution {
    public String solution(String new_id) {
        
        // char로 변환
        List<Character> inputs = new ArrayList<>();
        for (int i = 0; i < new_id.length(); i++) {
            inputs.add(new_id.charAt(i));
        }
        
        // 1. 소문자 전환 미리
        for (int i = 0; i < new_id.length(); i++) {
            char input = inputs.get(i);
            
            if (input >= 'A' && input <= 'Z') {
                inputs.set(i, (char) (((int) input) + ('a' - 'A')));                
            }
        }
        
        // 제거가 되니 길이 조심 -> 또한, 제거에 연속이 발생되는 등의 변화 조심 -> 우선 치환하기
        for (int i = 0; i < new_id.length(); i++) {
            char input = inputs.get(i);
            
            // 2. 유효 문자만 남기기
            boolean check = availInput(inputs.get(i)); // 바뀐 것으로 해야 함
            if (!check) inputs.set(i, '@');
            
            // 3. 마침표 연속 -> 제거 전제 -> 아직 @로 치환되지 않는 것이 있음 => 사실상 2개씩 계속 없애주면 됨
            if (input == '.') {
                int cur = i+1; // . 이후부터 전부 제거
                while (cur < new_id.length()) {
                    if (inputs.get(cur) == '.' || !availInput(inputs.get(cur))) {
                        inputs.set(cur, '@');
                        cur++;
                    } else {
                        break;
                    }
                }
            }
            
        }
        
        // 제거할 거 없애기
        int cur = 0;
        int tmp = inputs.size();
        while (cur < tmp) {
            if (cur >= inputs.size()) break; // 변동 길이 막기
            
            if (inputs.get(cur) == '@') {
                inputs.remove(cur);
            } else {
                cur++;
            }
        }
        
        // 4. 마침표 처음과 끝 제거 -> 중복은 없는 상태
        if (inputs.get(0) == '.') {
            inputs.remove(0);
        }
        
        
        if (inputs.size() > 0 && inputs.get(inputs.size()-1) == '.') {
            inputs.remove(inputs.size()-1);
        }
        
        
        
        // 5. 빈문자열 치환 -> 그냥 아예 없어진 경우이구나
        if (inputs.size() == 0) {
            inputs.add('a');
        }
        
        // 6. 길이 제거 => substring()
        if (inputs.size() > 15) {
            while (inputs.size() > 15) {
                inputs.remove(15); // 인덱스 숫자 넣어야 함
            }
            
            // 마지막만 보면 됨
            if (inputs.get(inputs.size()-1) == '.') {
                inputs.remove(inputs.size()-1);
            }
        }
        
        
        // 7. 2자 이하인 경우(0, 1, 2자) -> 아무것도 없는 경우 조심
        if (inputs.size() == 2) {
            inputs.add(inputs.get(1));
        } else if (inputs.size() == 1) {
            inputs.add(inputs.get(0));
            inputs.add(inputs.get(0));
        } else if (inputs.size() == 0) {
            int tmp2 = 3;
            while (tmp2 --> 0) {
                inputs.add('a');
            }
        }
        
        
        // System.out.println(inputs.toString());
        
        // 정답 출력하기
        String answer = "";
        for (int i = 0; i < inputs.size(); i++) {
            answer += inputs.get(i);
        }
        
        return answer;
    }
                      
    // 유효 숫자 메서드
    public boolean availInput(char input) {
        if ((input >= 'a' && input <= 'z') || (input == '-' || input == '_' || input == '.') 
            || (input >= '0' && input <= '9')) {
            return true;
        }
        
        return false;
        
    }
}