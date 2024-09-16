package BOJ_1406_에디터;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String S = br.readLine();
		
		List<Character> list = new ArrayList<>();	
		for(int i = 0; i < S.length(); i++) list.add(S.charAt(i));
		
		ListIterator<Character> iterator = list.listIterator(list.size());
		
		int M = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < M; i++) {
			String order = br.readLine();
			
			switch(order.charAt(0)) {
			case 'L': 
				if(iterator.hasPrevious()) iterator.previous();
				break;
			case 'D':
				if(iterator.hasNext()) iterator.next();
				break;
			case 'B':
				if(iterator.hasPrevious()) {
					iterator.previous();
					iterator.remove();
				}
				break;
			case 'P': 
				iterator.add(order.charAt(2));
			}
		}
		
		String result = "";
		for(int i = 0; i < list.size(); i++) result += list.get(i);
		bw.write(result);
		bw.close();
	}

}