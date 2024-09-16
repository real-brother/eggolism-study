package BOJ_16563_어려운소인수분해;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int N, max;
	static int[] numbers;
	static List<Integer> primes = new ArrayList<>();
	
	public static void main(String[] args) {
        N = sc.nextInt();

        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }

        // 최대 값 5000000까지의 소수 구하기
        max = 5000000;
        primes = generatePrimes(max);

        StringBuilder sb = new StringBuilder();
        for (int num : numbers) {
            List<Integer> factors = factorize(num, primes);
            for (int factor : factors) {
                sb.append(factor).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }

    // 에라토스테네스의 체로 소수 생성
    public static List<Integer> generatePrimes(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    // 주어진 수를 소인수분해합니다.
    public static List<Integer> factorize(int number, List<Integer> primes) {
        List<Integer> factors = new ArrayList<>();
        for (int prime : primes) {
            if (prime * prime > number) break;
            while (number % prime == 0) {
                factors.add(prime);
                number /= prime;
            }
        }
        if (number > 1) {
            factors.add(number);
        }
        return factors;
    }
}