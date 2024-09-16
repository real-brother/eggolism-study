package BOJ_11286_절댓값힙;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>(myComparator());

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                if (queue.size() != 0)
                    System.out.println(queue.poll());
                else
                    System.out.println(0);
            } else {
            	queue.add(num);
            }
        }
    }
    
    // 절댓값힙에 맞춤 Comparator 새롭게 오버라이딩
    private static Comparator<Integer> myComparator() {
        return new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int abs1 = Math.abs(o1);
                int abs2 = Math.abs(o2);

                if (abs1 == abs2) {
                    return Integer.compare(o1, o2);
                } else {
                    return Integer.compare(abs1, abs2);
                }
            }
        };
    }

}
/*
	//본래 우선순위큐의 Comparator 구현 방법
	PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
	@Override
	public int compare(Integer o1, Integer o2) {
	    return Integer.compare(o1, o2); // 작은 숫자가 더 우선됨 (오름차순)
	}
	});
	
*/