package W16_PRG_신규아이디추천;

import java.util.ArrayList;
import java.util.List;

public class Solution {

	public static void main(String[] args) {
		/* 문제) 프로그래머스_신규 아이디 추천
		 * 
		 */
		
		String answer = "";
		
		String new_id = "...!@BaT#*..y.abcdefghijklm";
        
        // 1. 소문자로 치환
        new_id = new_id.toLowerCase();
        
        // 2. 소문자, 숫자, 빼기, 밑줄, 마침표 제외하고 다 제거
        List<Character> list = new ArrayList<>();
        
        for(int i=0; i<new_id.length(); i++) {
            list.add(new_id.charAt(i));
        }
        
        for(int i=list.size()-1; i>=0; i--) {
            char c = list.get(i);
            
            if (!Character.isLowerCase(c) && !Character.isDigit(c) && c!='-' && c!='_' && c!='.') {
                list.remove(i);
            }
        }
        
        // 3. 마침표가 2번 연속된 부분 하나로 치환
        for(int i=list.size()-2; i>=0; i--) {
            if(list.get(i)=='.' && list.get(i+1)=='.') {
                list.remove(i+1);
            }
        }
        
        // 4. 마침표가 처음이나 끝에 있다면 제거
        if(list.get(0)=='.') {
            list.remove(0);
        }
        
        if (list.size()>0 && list.get(list.size()-1)=='.') {
            list.remove(list.size()-1);
        }
        
        // 5. 빈 문자열이면 'a' 대임
        if (list.size() == 0){
            list.add('a');
        }
        
        // 6. 길이가 16자 이상이면 첫 15개 문제 제외하고 제거
        // 만약 제거 후 마침표가 끝에 위치하면 제거
        if (list.size() >= 16) {
            for(int i=list.size()-1; i>=15; i--) {
                list.remove(i);
            }
            
            if (list.get(list.size()-1) == '.') {
                list.remove(list.size()-1);
            }
        }
        
        // 7. new_id 길이가 2자 이하면 new_id의 마지막 문자를 new_id에 붙인다. 길이가 3이 될 때까지 반복
        if (list.size() <= 2) {
            while(list.size() < 3) {
                list.add(list.get(list.size()-1));
            }
        }
        
        for(int i=0; i<list.size(); i++) {
            answer += list.get(i);
        }   
        
        System.out.println(answer);

	}

}
