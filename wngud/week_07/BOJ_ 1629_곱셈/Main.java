public class Main2 {

    static int c;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        long answer = mul(a, b);
        System.out.println(answer);

    }

    static long mul(int a, int b) {

        if (b == 1) {
            return a % c; // 이것 예외
        }

        long tmp = 0;
        if (b % 2 == 0) {
            tmp = mul(a, b / 2);
            return (tmp * tmp) % c;
        } else {
            tmp = mul(a, (b-1) / 2);
            return ((tmp * tmp % c) * a) % c;
        }

    }
}