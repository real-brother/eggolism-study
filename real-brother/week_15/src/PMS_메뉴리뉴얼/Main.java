package PMS_메뉴리뉴얼;

import java.util.*;

class Solution {
    List<String> answerList = new ArrayList<>();
    Map<String, Integer> hashMap = new HashMap<>();

    public String[] solution(String[] orders, int[] course) {

        // 1. orders 정렬
        for (int i = 0; i < orders.length; i++) {
            char[] arr = orders[i].toCharArray();
            Arrays.sort(arr);
            orders[i] = String.valueOf(arr);
        }

        // 2. 각 메뉴를 기준으로 course 길이만큼의 조합 생성
        for (int courseLength : course) {
            for (String order : orders)
                combination("", order, courseLength);

            // 3. 가장 많이 주문한 메뉴 조합 answer에 저장
            if (!hashMap.isEmpty()) {
                List<Integer> countList = new ArrayList<>(hashMap.values());
                int max = Collections.max(countList);

                if (max > 1)
                    for (String key : hashMap.keySet())
                        if (hashMap.get(key) == max)
                            answerList.add(key);
                hashMap.clear(); //해시맵 비워줘야함
            }
        }
        // 오름차순 정렬
        Collections.sort(answerList);
        String[] answer = new String[answerList.size()];
        for (int i = 0; i < answer.length; i++)
            answer[i] = answerList.get(i);

        return answer;
    }

    public void combination(String order, String others, int depth) {
        // depth 줄여나가며 0일때 커트
        if (depth == 0) {
            hashMap.put(order, hashMap.getOrDefault(order, 0) + 1);
            return;
        }
        
        // order에 각 메뉴 경우를 저장하여 백트래킹 구현하기
        for (int i = 0; i < others.length(); i++)
            combination(order + others.charAt(i), others.substring(i + 1), depth - 1);
    }
}