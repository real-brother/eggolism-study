package BOJ_11000_강의실배정;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<List<Integer>> timeTable = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> time = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                time.add(sc.nextInt());
            }
            timeTable.add(time);
        }

        Collections.sort(timeTable, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> a, List<Integer> b) {
                int result = a.get(1).compareTo(b.get(1));
                if (result == 0) {
                    result = a.get(0).compareTo(b.get(0));
                }
                return result;
            }
        });


        int cnt = 0;
        while (timeTable.size() > 0) {
        	int endTime = timeTable.get(0).get(1);
        	timeTable.remove(0);
        	for (int i = 0; i < timeTable.size(); i++) {
                if (timeTable.get(i).get(0) >= endTime) {
                    endTime = timeTable.get(i).get(1);
                    timeTable.remove(i);
                }
            }
        	cnt++;
        }
        

        System.out.println(cnt);
        
        
        
        
    }
}
