import java.util.*;

class Solution {
    
    public static String[] sel, answer, friendsList;
    public static int[] answers;
    public static int N;
    public static Map<String, Integer> giftsMap, from, to;
    
    
    public int solution(String[] friends, String[] gifts) {
        
        answers = new int[friends.length]; // 정답을 저장을 위한 배열
        friendsList = friends; // static을 위함
        
        // 선물 로그 처리하기
        giftsMap = new HashMap<>();
        from = new HashMap<>();
        to = new HashMap<>();
        
        for (int i = 0; i < gifts.length; i++) {
            // 선물 주고 받는 로그 처리
            if (!giftsMap.containsKey(gifts[i])) {
                giftsMap.put(gifts[i], 1);
            } else {
                giftsMap.put(gifts[i], giftsMap.get(gifts[i])+1);
            }
            
            // 선물 인덱스 처리
            String[] inputs = gifts[i].split(" "); // split를 알면 쉬운 문제!
            
            // from
            if (!from.containsKey(inputs[0])) {
                from.put(inputs[0], 1);
            } else {
                from.put(inputs[0], from.get(inputs[0])+1);
            }
            
            // to
            if (!to.containsKey(inputs[1])) {
                to.put(inputs[1], 1);
            } else {
                to.put(inputs[1], to.get(inputs[1])+1);
            }
            
        }
        
        System.out.println(giftsMap.toString());
        System.out.println(from.toString());
        System.out.println(to.toString());
        
        
        // 조합 만들기
        N = friends.length;
        sel = new String[2];
        
        comb(0, 0);
        
        // 정답 출력하기
        System.out.println("정답: " + Arrays.toString(answers));
        int answer = 0;
        for (int i = 0; i < answers.length; i++) {
            answer = Math.max(answers[i], answer);
        }
        
        
        return answer;
    }
    
    // 조합
    public static void comb(int idx, int sidx) {
        
        // 종료 조건
        if (sidx == 2) {
            System.out.println(Arrays.toString(sel));
            
            // 주고받은 선물로 판별
            String check1 = sel[0] + " " + sel[1];
            String check2 = sel[1] + " " + sel[0];
            
            // 초기 세팅
            if (!giftsMap.containsKey(check1)) {
                giftsMap.put(check1, 0);
            }
            
            if (!giftsMap.containsKey(check2)) {
                giftsMap.put(check2, 0);
            }
            
            // 판별
            if (giftsMap.get(check1) > giftsMap.get(check2)) {
                answers[Arrays.asList(friendsList).indexOf(sel[0])] += 1; // 블로그 찾아봄
                return;
            } else if (giftsMap.get(check1) < giftsMap.get(check2)) {
                answers[Arrays.asList(friendsList).indexOf(sel[1])] += 1; // 블로그 찾아봄
                return;
            }
            
            // 선물 지수로 판별
            // 초기 세팅
            if (!from.containsKey(sel[0])) {
                from.put(sel[0], 0);
            }
            
            if (!to.containsKey(sel[0])) {
                to.put(sel[0], 0);
            }
            
            if (!from.containsKey(sel[1])) {
                from.put(sel[1], 0);
            }
            
            if (!to.containsKey(sel[1])) {
                to.put(sel[1], 0);
            }
            
            int check3 = from.get(sel[0]) - to.get(sel[0]);
            int check4 = from.get(sel[1]) - to.get(sel[1]);
            
            // 판별
            if (check3 > check4) {
                answers[Arrays.asList(friendsList).indexOf(sel[0])] += 1; // 블로그 찾아봄
            } else if (check3 < check4) {
                answers[Arrays.asList(friendsList).indexOf(sel[1])] += 1; // 블로그 찾아봄
            }
            
            // 어떤 경우도 없는 상황
            return;
        }
        
        if (idx == N) {
            return;
        }
        
        // 조합 뽑기
        sel[sidx] = friendsList[idx];
        comb(idx+1, sidx+1);
        comb(idx+1, sidx);
        
    }
}