package BOJ_1072_게임;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int play_count = sc.nextInt();
		int win_count = sc.nextInt();
		
		int win_rate = WinRate(play_count, win_count);
		
		if(win_rate == 100 || win_rate == 99) System.out.println("-1");
		else System.out.print(newPlayCount(play_count, win_count, win_rate));

	}
	
	public static int WinRate(long play_count, long win_count) {
		long win_rate = (win_count * 100) / play_count;
		return (int)win_rate;
	}
	
	public static long newPlayCount(int play_count, int win_count, int win_rate) {
		long left = 0;
		long right = Integer.MAX_VALUE; 
		while(left <= right) {
			long new_play = (left + right) / 2;
			
			if(WinRate(play_count + new_play, win_count + new_play) <= win_rate) left = new_play + 1;
			else right = new_play - 1;	
		}
		return left;
	}

}
