import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        String num = Integer.toString(n, k);
        System.out.println(num);
        
        StringTokenizer st = new StringTokenizer(num, "0");
        
        while (st.hasMoreTokens()) {
            if (isPrime(Long.parseLong(st.nextToken()))) {
                answer++;
            }
        }
        
        return answer;
    }
    
    static boolean isPrime(long n) {
        if (n < 2) {
            return false;
        }
        
        else if (n==2) return true;
        
        for(long i=2; i<=Math.sqrt(n); i++) {
            if (n%i == 0) {
                return false;
            }
        }
        return true;
    }
}