package BOJ_14890;
/*
Step 00. 입력을 받을 때, 열과 행을 관리할 수 있는 2차원 배열을 2개 입력받는다.

Step 01. 경사로를 설치할 위치를 행과 열로 끊어서 확인
		 시작 위치를 이전 높이로 저장하고 난 후 4가지의 경우를 판단한다.

         1. 현재 위치가 이전과 같은 높이라면 count+1.
         2. 이전 칸이 현재 칸보다 한 칸 낮은 경우 count가 L보다 작다면 설치 불가. false 리턴. 
            아니라면 설치하고 count를 1로 초기화한 후 이전 높이를 현재 높이로 저장한다.
         3. 이전 칸이 현재 칸보다 한 칸 높은 경우 현재 위치에서 남은 위치를 살펴본다. 
            남은 위치에서 평 탄로가 아닌 위치가 나타난다면 반복을 멈추고 몇 칸이 나왔는지 확인한다.
            만약 개수가 L보다 작다면 설치 불가. 
            아니라면 이전 위치에 해당 높이를 설정하고 다음 위치를 현재 위치 + L - 1로 바꿔준다.
         4. 1, 2, 3번에 해당하지 않는다면 높이차이가 2 이상이므로 설치 불가능

Step 02. Step01의 과정을 모든 행과 열에 대해 반복한다.

Step 03. Step 02의 결과로 나온 합이 정답!

*/
import java.util.*;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int answer = 0;
		int n = sc.nextInt();
		int l = sc.nextInt();
		sc.nextLine();
		ArrayList<ArrayList<Integer>> mapArrayList = new ArrayList();
		
		// 2차원 배열 초기화
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> rowList = new ArrayList<>();
            mapArrayList.add(rowList);
        }

        // 2차원 배열에 값 입력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 각 행에 해당하는 ArrayList를 얻어와서 값 추가
                int value = sc.nextInt();
                mapArrayList.get(i).add(value);
            }
        }
        
        ArrayList<Integer> testArrayList = new ArrayList();
        // 가로줄 6개 돌면서 계단이 놓일 수 있는지?
        for (int i = 0; i < n; i++) {
        	testArrayList.clear();
        	for (int j = 0; j < n; j++) {
        		testArrayList.add(mapArrayList.get(i).get(j))	;
			}
        	if (canBeStairLaid(n, l, testArrayList)) {
//        		System.out.println(testArrayList);
        		answer++;
        	}
		}
        
        // 새로줄 6개 돌면서 계단이 놓일 수 있는지?
        for (int i = 0; i < n; i++) {
        	testArrayList.clear();
        	for (int j = 0; j < n; j++) {
        		testArrayList.add(mapArrayList.get(j).get(i))	;
			}
        	if (canBeStairLaid(n, l, testArrayList)) {
//        		System.out.println(testArrayList);
        		answer++;
        	}
		}
        
        System.out.println(answer);
	}
	
	// 과연 이 길에는 계단이 놓여서 잘 지나갈 수 있을 것인가?
	public static boolean canBeStairLaid(int n, int l, ArrayList<Integer> arrayList) {
		int beforeHeight = arrayList.get(0); // 이전 높이 - 처음에는 첫번째 값으로 초기화
		int count = 1; // 
		for (int j = 1; j < n; j++) {
			// 같은 높이
			if (beforeHeight == arrayList.get(j)) {
				count += 1;
			}
			
			// 이전 칸이 한 칸 낮음
			else if (beforeHeight + 1 == arrayList.get(j)) {
				if(count < l) return false;
				beforeHeight += 1;
				count = 1;
			}

			// 이전 칸이 한 칸 높음
			else if (beforeHeight - 1 == arrayList.get(j)) {
				// 앞으로 평지의 수를 세어야 한다.
				int plainRemain = 0;
				for(int k = j; k < n; k++) {
					// 평지가 어긋난다면 바로 break
					if(beforeHeight-1 != arrayList.get(k)) break;
					plainRemain += 1;
				}
				if(plainRemain < l) return false;
				
				// 설치 가능
				j += l-1; // 경사로를 설치한만큼 앞으로 가기 - 경사로를 놓은 이상 그 자리에는 놓을 수 없기 때문
				count = 0;
				beforeHeight -= 1;
			}

			// 높이 차이 2이상
			else {
				return false;
			}
		}
		return true;
	}
	
}
	

