package BOJ_2573_빙산;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int n;
	static List<Coord> polygon;

	public static void main(String[] args) {
		n = sc.nextInt();
		polygon = new ArrayList<Coord>();
		for (int i = 0; i < n; i++) {
			long x = sc.nextLong();
			long y = sc.nextLong();
			Coord coord = new Coord(x, y);
			polygon.add(coord);
		}
		// 신발끈 공식을 활용하기 위해 첫 요소를 마지막에 추가하기
		polygon.add(polygon.get(0));
		
		long xSum = 0;
		long ySum = 0;
		// 신발끈 공식 합 계산하기
		for (int i = 0; i < n; i++) {
			xSum += polygon.get(i).x * polygon.get(i+1).y; 
			ySum += polygon.get(i+1).x * polygon.get(i).y;
		}
		// 소수 첫째까지까지 출력 - 이런 무식한 방법밖에 없는것인가,,,
		double answer = Math.round(Math.abs(xSum - ySum) / 2.0 * 10.0) / 10.0;
		System.out.printf("%.1f", answer);
	}
}

class Coord {
	long x;
	long y;
    public Coord(long x, long y) {
        this.x = x;
        this.y = y;
    }
}