package BOJ_11399;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    int N = sc.nextInt();
    int sum = 0;
    
    List<Integer> number_list = new ArrayList<>();
    
    for(int i = 0; i < N; i++) number_list.add(sc.nextInt());
    
    Collections.sort(number_list);

    for(int i = 0; i < N; i++) { 
        sum += number_list.get(i)*(N-i);
    }
    
    System.out.println(sum);
}
}