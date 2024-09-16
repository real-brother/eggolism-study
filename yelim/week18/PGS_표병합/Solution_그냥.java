import java.util.*;

class Solution {
    
    static class Cell {
		String value = null;
		
		@Override
		public String toString() {
			return "Cell [value=" + value + "]";
		}
	}

	static Cell[][] table;
    
    public String[] solution(String[] commands) {
        
        String[] answer = {};
        List<String> ans = new ArrayList<>();
        
        table = new Cell[51][51];
		
		for(int i=0; i<=50; i++) {
			for(int j=0; j<=50; j++) {
				table[i][j] = new Cell();
			}
		}
		
		for(int i=0; i<commands.length; i++) {
			// 띄어쓰기로 구분하여 하나의 command를 배열로 저장
			String[] one = commands[i].split(" ");
			
			// 명령어가 UPDATE 일 때
			if (one[0].equals("UPDATE")) {
				// 다음 명령어가 숫자이면
				if (one.length == 4) {
					int r = Integer.parseInt(one[1]);
					int c = Integer.parseInt(one[2]);
					table[r][c].value = one[3];
				}
				// 숫자가 아니면 one[1] 값을 가진 모든 셀을 one[2] 값으로 변경
				else {
					for(int r=1; r<=50; r++) {
						for(int c=1; c<=50; c++) {
							if (table[r][c].value != null && table[r][c].value.equals(one[1])) {
								table[r][c].value = one[2];
							}
						}
					}
				}
			}
			
			// 명령어가 MERGE 일 때
			else if (one[0].equals("MERGE")) {
				int r1 = Integer.parseInt(one[1]);
				int c1 = Integer.parseInt(one[2]);
				int r2 = Integer.parseInt(one[3]);
				int c2 = Integer.parseInt(one[4]);
				// 두 셀이 다른 셀일 경우
				if (r1 != r2 || c1 != c2) {
					// 병합
					if (table[r1][c1].value == null && table[r2][c2].value != null) {
						merge(r1, c1, r2, c2);
					}
					else if (table[r1][c1].value != null && table[r2][c2].value == null) {
						merge(r2, c2, r1, c1);
					}
					else {
						merge(r2, c2, r1, c1);
					}
					
						
				}
			}
			
			// 명령어가 UNMERGE 일 때
			else if (one[0].equals("UNMERGE")) {
				int r1 = Integer.parseInt(one[1]);
				int c1 = Integer.parseInt(one[2]);
				
				Cell cell = table[r1][c1];
				for(int r=1; r<=50; r++) {
					for(int c=1; c<=50; c++) {
						if (table[r][c] == cell) {
							table[r][c] = new Cell();
						}
					}
				}
				if (cell != null) {
					table[r1][c1].value = cell.value;
				}
			}
			
			// 명령어가 PRINT 일 때
			else if (one[0].equals("PRINT")) {
				int r = Integer.parseInt(one[1]);
				int c = Integer.parseInt(one[2]);
				
				if (table[r][c].value == null) {
					ans.add("EMPTY");
				} else {
					ans.add(table[r][c].value);
				}
			}
			
		}
        
        answer = new String[ans.size()];
        for(int i=0; i<ans.size(); i++){
            answer[i] = ans.get(i);
        }
        return answer;
    }
    
    
    // 병합하는 메소드
	static void merge(int r1, int c1, int r2, int c2) {
		Cell c = table[r1][c1];
		
		for(int i=1; i<=50; i++) {
			for(int j=1; j<=50; j++) {
				if (table[i][j] == c) {
					table[i][j] = table[r2][c2];
				}
			}
		}
		
	}
    
}