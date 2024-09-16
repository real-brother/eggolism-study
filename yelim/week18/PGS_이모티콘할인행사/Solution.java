import java.util.*;

/*
* 이모티콘이랑 할인율 중복 순열?
* 
*/

class Solution {
    
    static class Emoticon {
        double percent;
        double price;
        
        public Emoticon(double percent, double price) {
            this.percent = percent;
            this.price = price;
        }
    }
    
    // 할인율은 10, 20, 30, 40 중 하나
    static double[] sales = {0.1, 0.2, 0.3, 0.4};

    static List<Emoticon> emti = new ArrayList<>();
    static int maxJoin = 0;
    static int maxPrice = 0;
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        
        dfs(0, users, emoticons);
        
        answer[0] = maxJoin;
        answer[1] = maxPrice;
        
        return answer;
    }
    
    static void dfs(int depth, int[][] users, int[] emoticons) {
        if (depth == emoticons.length) {
            // 총 판매액 초기화
            int total = 0;
            // 가입자 초기화
            int join = 0;
            
            
            for(int i=0; i<users.length; i++) {
                // 해당 유저의 최소 할인율
                int userSale = users[i][0]; 
                // 해당 유저의 최대 구매 비용
                int userMax = users[i][1];
                
                // 현재 유저의 구매 비용의 합
                int sum = 0;

                for (int j=0; j<emti.size(); j++) {
                    // 현재 이모티콘의 할인율과 가격 꺼내기
                    Emoticon e = emti.get(j);
                    double ePercent = e.percent;
                    double ePrice = e.price;
                    
                    // 이모티콘 할인율이 현재 유저의 최소 할인율보다 크거나 같으면 구매
                    if (ePercent >= userSale) {
                        sum += ePrice;
                    }
                    
                }
                
                // 유저의 구매 비용 합이 최대 구매 비용보다 크거나 같아지면 구매 안하고 플러스 가입
                if (sum >= userMax) {
                    join++;
                } else {
                 
                    // 총 판매액에 해당 유저의 구매액 더해주기 else?
                    total += sum;
                }
                
                if (maxJoin < join) {
                    maxPrice = total;
                    maxJoin = join;
                }
                else if (maxJoin == join && maxPrice < total) {
                    maxPrice = total;
                }
            }
            
            return;
        }
        
        for(int i=0; i<sales.length; i++) {
            // emti 리스트에 이모티콘 할인율과 판매가격 저장
            emti.add(new Emoticon((sales[i]*100), (1-sales[i]) * emoticons[depth]));
            dfs(depth+1, users, emoticons);
            emti.remove(emti.size()-1);
        }
        
        
    }
    
}