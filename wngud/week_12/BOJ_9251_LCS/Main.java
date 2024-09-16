import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String input1 = sc.nextLine();
        String input2 = sc.nextLine();

        char[] inputList1 = new char[input1.length()+1];
        char[] inputList2 = new char[input2.length()+1];

        // 실제 숫자
        for (int i = 1; i <= input1.length(); i++) {
            inputList1[i] = input1.charAt(i-1);
        }
        for (int i = 1; i <= input2.length(); i++) {
            inputList2[i] = input2.charAt(i-1);
        }

        int[][] dp = new int[input1.length()+1][input2.length()+1];
        for (int s1 = 1; s1 <= input1.length(); s1++) {
            for (int s2 = 1; s2 <= input2.length(); s2++) {

                // 같으면 이전 수열 받아서 연장
                if (inputList1[s1] == inputList2[s2]) {
                    dp[s1][s2] = dp[s1-1][s2-1] + 1;
                }
                // 다르면 그냥 의미없이 이전것 개수 최대값 받기
                else {
                    dp[s1][s2] = Math.max(dp[s1-1][s2], dp[s1][s2-1]);
                }

            }
        }

//		for (int i = 0; i < dp.length; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}

        System.out.println(dp[dp.length-1][dp[0].length-1]);

    }
}
