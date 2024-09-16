package BOJ_21736_헌내기는친구가필요해;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class Main_bfs {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.nextLine();
		int start_r = -1;
		int start_c = -1;
		int[] dx = {-1, 1, 0, 0};
	    int[] dy = {0, 0, -1, 1};
	    int count = 0;
	    boolean[][] visited = new boolean[n][m];
		List<List<String>> campus = new ArrayList<List<String>>();
		
		for (int i = 0; i < n; i++) {
			List<String> tempCampus = new ArrayList<String>();
			String text = sc.nextLine();
			for (int j = 0; j < text.length(); j++) {
				tempCampus.add(Character.toString(text.charAt(j)));
				if (Character.toString(text.charAt(j)).equals("I")) {
					start_r = i;
					start_c = j;
				}
	        }
			campus.add(tempCampus);
		}
		

		Deque<int[]> deque = new ArrayDeque<>();
		deque.add(new int[]{start_r, start_c});
		visited[start_r][start_c] = true;
		while (!deque.isEmpty()) {
            int[] current = deque.poll();
            int x = current[0];
            int y = current[1];
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                // 유효한 범위인지 확인하고 방문하지 않은 노드인지 확인
                if (nx < 0 || nx >= n || ny < 0 || ny >= m ||
                    visited[nx][ny]) continue;
                
                if (campus.get(nx).get(ny).equals("X")) {
                    // 방문 표시 및 큐에 삽입
                    visited[nx][ny] = true;
                } 
                else if (campus.get(nx).get(ny).equals("P")) {
                	// 방문 표시 및 큐에 삽입
                	visited[nx][ny] = true;
                	deque.offer(new int[]{nx, ny});
                	count++;
                } else {
                	visited[nx][ny] = true;
                	deque.offer(new int[]{nx, ny});
                }
            }
		}
		
		if (count > 0) {
			System.out.println(count);
		} else {
			System.out.println("TT");
		}
		
	}
}