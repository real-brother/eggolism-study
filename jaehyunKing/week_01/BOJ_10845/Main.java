package BOJ_10845;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//명령의 개수 N개
		int N = Integer.parseInt(br.readLine());
		
		//큐ㅜ선언
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			// push x 때문에 String을 공백 기준으로 자름
			String[] s = br.readLine().split(" ");
			switch (s[0]) { 
			case "push": 
				//push가 들어오면 s[1] (x)을 가져온다.
				queue.offer(Integer.parseInt(s[1]));
				break;
			case "pop" :
				//버퍼.write은 개행 문자("\n")를 따로 써줘야 함 
				if(queue.isEmpty()) bw.write(-1+"\n");
				else bw.write(queue.poll()+"\n");
				break;
			case "size" :
				bw.write(queue.size()+"\n");
				break;
			case "empty" :
				if(queue.isEmpty()) bw.write(1+"\n");
				else bw.write(0+"\n");
				break;
			case "front" :
				if(queue.isEmpty()) bw.write(-1+"\n");
				else bw.write(queue.peek()+"\n");
				break;
			case "back" :
				if(queue.isEmpty()) bw.write(-1+"\n");
				else {
					for(int j = 1; j < queue.size(); j++) {
						queue.offer(queue.poll());
					}
					bw.write(queue.peek()+"\n");
					queue.offer(queue.poll());
				}
				break;
			}
		}
		bw.close();
	}
}