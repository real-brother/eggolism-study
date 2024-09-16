package BOJ_2941;

import java.util.Scanner;

//전체 길이에서 한 글자로 취급되는 글자와 기호만 빼주려고 함
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//입력 받기
		String str = sc.next();
		
		//"dz", "lj", "nj"를 확인하기 위한 문자열 생성
		String str_two = "";
		String str_tri = "";
		
		//전체 문자열 길이 확인
		int count = str.length();
		
		//문자열 확인
		for(int i = 0; i < str.length(); i++) {
			
			// if문으로 처음에 인덱스 밖으로 나가는 것을 막아줌, ""를 넣어 String으로 만듦
			if(i>=1) str_two = str.charAt(i-1)+""+str.charAt(i);
			if(i>=2) str_tri = str.charAt(i-2)+""+str.charAt(i-1)+""+str.charAt(i);
			
			//기호는 단독으로 글자 취급 안 받아서 그냥 다 빼버림
			if(str.charAt(i) == '=' || str.charAt(i) == '-') {
				count--;
			}
			
			// "lj", "nj"를 확인하면 -1
			if(str_two.equals("lj") || str_two.equals("nj")) {
				count--;
			}
			
			// "dz="를 확인하면 -1 (이미 기호에서 1 뺐음)
			if(str_tri.equals("dz=")) {
				count--;
			}
		}
		System.out.println(count);
	}
}



