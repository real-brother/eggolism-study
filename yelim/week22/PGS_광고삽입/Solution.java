
class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        
        int playTime = toSec(play_time);
        int advTime = toSec(adv_time);
        
        // 플레이타임 만큼의 배열 생성 (누적합 저장)
        long[] arr = new long[playTime+1];
        
        for(int i=0; i<logs.length; i++) {
            String[] sArr = logs[i].split("-");
            
            int start = toSec(sArr[0]);
            int end = toSec(sArr[1]);
            
            // 누적합 배열에 시작시간은 +1, 끝나는 시간은 -1 해준다.
            arr[start]++;
            arr[end]--;
        }
        
        // 각 초마다 몇 명이 봤는지
        for(int i=1; i<arr.length; i++) {
            arr[i] += arr[i-1];
        }
        
        // i초까지 누적 시청 횟수
        for(int i=1; i<arr.length; i++) {
            arr[i] += arr[i-1];
        }
        
        long maxTime = arr[advTime-1];
        long maxS = 0;
        
        for(int i=0; i<=playTime-advTime; i++) {
            long tmp = arr[i+advTime] - arr[i];
            
            if (tmp > maxTime) {
                maxTime = tmp;
                maxS = i+1;
            }
        }
        
        return String.format("%02d:%02d:%02d", maxS/3600, (maxS/60)%60, maxS%60);
    }
    
    // 초로 변환하는 메소드
    static int toSec(String sTime) {
        String[] sArr = sTime.split(":");
        int time = 3600*Integer.parseInt(sArr[0]) + 60*Integer.parseInt(sArr[1]) + Integer.parseInt(sArr[2]);
        
        return time;
    }
}