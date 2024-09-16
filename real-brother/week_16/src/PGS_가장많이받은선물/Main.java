package PGS_가장많이받은선물;

import java.util.*;

class Solution {
    static int[][] arr; // 각 주고 받은 선물 개수
    static boolean[][] visited;
    static int[] countGift; // 다음달에 선물 받을 개수

    static public int solution(String[] friends, String[] gifts) {
        int answer = 0;

        arr = new int[friends.length][friends.length];
        visited = new boolean[friends.length][friends.length];
        countGift = new int[friends.length];
        // 사람 이름 -> index로 바꾸기 용이하기 위함
        HashMap<String, Integer> friendsDataMap = new HashMap<>(); 

        for (int i = 0; i < friends.length; i++) {
            friendsDataMap.put(friends[i], i);
        }

        for (String gift : gifts) {
            StringTokenizer st = new StringTokenizer(gift);
            String from = st.nextToken();
            String to = st.nextToken();
            arr[friendsDataMap.get(from)][friendsDataMap.get(to)]++;
        }


        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i == j) continue; // 본인 제거
                if (visited[i][j] || visited[j][i]) continue; // 중복 제거
                
                if (arr[i][j] > arr[j][i]) {
                    visited[i][j] = true;
                    countGift[i]++;
                } else if (arr[i][j] < arr[j][i]) {
                    visited[i][j] = true;
                    countGift[j]++;
                } else if ((arr[i][j] == 0 && arr[j][i] == 0) || arr[i][j] == arr[j][i]) {
                    visited[i][j] = true;
                    int giftScoreA, giftScoreB; // 선물 지수 = 자신이 준 선물 - 자신이 받은 선물
                    int fromA = 0, fromB = 0, toA = 0, toB = 0;

                    // 자신이 받은 선물들과 자신이 준 선물들의 합을 구함
                    for (int k = 0; k < arr.length; k++) {
                        toA += arr[i][k];
                        toB += arr[j][k];
                        fromA += arr[k][i];
                        fromB += arr[k][j];
                    }

                    // 각각의 선물지수
                    giftScoreA = toA - fromA;
                    giftScoreB = toB - fromB;

                    if (giftScoreA > giftScoreB) {
                        countGift[i]++;
                    } else if (giftScoreB > giftScoreA) {
                        countGift[j]++;
                    }
                }
            }
        }
        // 가장 선물을 많이 받는 수 구하기
        for (int i = 0; i < countGift.length; i++){
            if (answer < countGift[i]) answer = countGift[i];
        }

        return answer;
    }

}