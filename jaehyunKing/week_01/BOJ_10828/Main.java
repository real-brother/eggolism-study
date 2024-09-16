package BOJ_10828;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//명령의 개수 N개
		int N = Integer.parseInt(br.readLine());
		
		//스택 선언
		Stack<Integer> stack = new Stack<>();
		
		for(int i = 0; i < N; i++) {
			// push x 때문에 String을 공백 기준으로 자름
			String[] s = br.readLine().split(" ");
			switch (s[0]) { 
			case "push": 
				//push가 들어오면 s[1] (x)을 가져온다.
				stack.push(Integer.parseInt(s[1]));
				break;
			case "pop" :
				//버퍼.write은 개행 문자("\n")를 따로 써줘야 함 
				if(stack.isEmpty()) bw.write(-1+"\n");
				else bw.write(stack.pop()+"\n");
				break;
			case "size" :
				bw.write(stack.size()+"\n");
				break;
			case "empty" :
				if(stack.isEmpty()) bw.write(1+"\n");
				else bw.write(0+"\n");
				break;
			case "top" :
				if(stack.isEmpty()) bw.write(-1+"\n");
				else bw.write(stack.peek()+"\n");
				break;
			}
		}
		bw.close();
	}
}