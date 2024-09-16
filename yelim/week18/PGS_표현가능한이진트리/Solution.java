import java.util.*;

class Solution {
    
    static int res; // 결과값
    
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for(int i=0; i<numbers.length; i++){
            String binaryNum = Long.toBinaryString(numbers[i]);
            
            int fullNode = 0;   // 포화 이진트리 노드 수
            int n = 1;  // 트리 높이
            
            // 포화 이진트리 만들기 위한 최소 노드 수 구하기
            while (fullNode < binaryNum.length()){
                fullNode = (int) Math.pow(2, n++) - 1;
            }
            
            // 포화 이진트리
            int[] fullTree = new int[fullNode];
            
            // 더미 노드 개수
            int dummy = fullNode - binaryNum.length();
            
            // 더미노드 개수만큼 앞에 0을 채운다고 가정, 그 이후부터 이진수 값 넣어주기
            for(int j=0; j<binaryNum.length(); j++){
                fullTree[dummy++] = Character.getNumericValue(binaryNum.charAt(j));
            }
            
            // 이제 탐색!
            // 자식이 1인데, 부모가 0이면 안됨
            res = 1;    // 결과값 초기화
            dfs(0, fullTree.length-1, fullTree, false);
            answer[i] = res;
            
        }
        
        return answer;
    }
    
    static void dfs(int s, int e, int[] fullTree, boolean isParentZero){
        if (res == 0) return;   // 기저조건
        
        // 현재 서브트리 내의 부모노드
        int mid = (s+e)/2;
        
        // 현재 서브트리의 부모가 0인데, 자식(내의 부모)이 1이면 끝
        if (isParentZero && fullTree[mid]==1){
            res = 0;
            return;
        }
        
        // s와 e가 다르면 계속 재귀 탐색
        if (s != e) {
            // 왼쪽 서브트리
            dfs(s, mid-1, fullTree, fullTree[mid]==0);
            // 오른쪽 서브트리
            dfs(mid+1, e, fullTree, fullTree[mid]==0);
        }
    }
}