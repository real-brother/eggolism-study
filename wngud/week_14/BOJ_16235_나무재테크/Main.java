import java.lang.reflect.Array;
import java.util.*;

public class Main {
    /*설계 방식
     * 1. 봄,여름,가을, 겨울 구현 자체는 어렵지 않음
     * 2. 동시에 같은 구역에 나무가 있는 문제가 중요
     * - 땅을 표시하기 위한 매트릭스
     * - 기계가 땅에게 양분을 주기 위한 매트릭스
     * - 나무를 표시할 매트릭스 + 우선순위 큐 -> 잘 안되서 클래스
     * 3. 나무를 하나의 리스트에 넣고 관리
     * - 처음에만 나이순으로 정렬하면 이후에는 할 필요 없음 -> 가능하면 계속 +1만 되어서
     * 4. 시간 복잡도를 위해 최대한 for문 한개로 끝내려고 함
     */

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};


        // 기계 설정
        int[][] machine = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int num = sc.nextInt();
                machine[i][j] = num;
            }
        }

        // 나무 심기
        List<Tree> trees = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            int life = sc.nextInt();
            trees.add(new Tree(r, c, life));
        }
       
        

        // 땅 설정
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = 5;
            }
        }
        

        for (int test = 0; test < K; test++) {
        	
        	// 정렬 -> 일년에 한번씩
        	Collections.sort(trees);
        	
        	// 출력해보기
//        	for (Tree t : trees) {
//				System.out.print(t.life + " ");
//			}
//        	System.out.println();

            // 봄
            // 성공시 +1, 실패시 제거한다.
        	List<Tree> newTrees = new ArrayList<>();
            List<Tree> summer = new ArrayList<>();
            List<Tree> autumn = new ArrayList<>();

            int tmp = trees.size();
            for (int i = 0; i < tmp; i++) {
				Tree tree = trees.get(i);
				int r = tree.r;
				int c = tree.c;
				int life = tree.life;
				
				// 성공시
				if (matrix[r][c] >= life) {
					matrix[r][c] -= life;
					tree.life = life + 1;
					newTrees.add(tree);
					
					// 가을 대비
					if ((life + 1) % 5 == 0) {
						autumn.add(tree);
					}
				} else {
					// 실패
					// 여름 대비 -> 삭제하면 동시성 문제
					summer.add(tree);
					
				}
			}
            
            // 여름
            for (int i = 0; i < summer.size(); i++) {
            	Tree tree = summer.get(i);
				int r = tree.r;
				int c = tree.c;
				int life = tree.life;
				
				matrix[r][c] += life / 2;
			}

            // 가을
            // 5의 배수 나무를 가지고 진행 -> 동시성 조심(여러 구역 영향)
            for (int k = 0; k < autumn.size(); k++) {
                int r = autumn.get(k).r;
                int c = autumn.get(k).c;

                // 8방향 추가
                for (int s = 0; s < 8; s++) {
                    int nr = r + dr[s];
                    int nc = c + dc[s];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                    newTrees.add(new Tree(nr, nc, 1));
                }
            }
            
            // 겨울
            for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					matrix[i][j] += machine[i][j];
				}
			}
            
            // 옮기기
            trees = newTrees;
            
        }

        // 정답 출력하기
        int answer = 0;
//        for (Tree tree : trees) {
//			answer += tree.life;
//		}

        System.out.println(trees.size());

    }
    
    
    
    public static class Tree implements Comparable<Tree> {
    	int r;
    	int c;
    	int life;
    	
    	Tree(int r, int c, int life) {
    		this.r = r;
    		this.c = c;
    		this.life = life;
    	}

		@Override
		public int compareTo(Tree o) {
			return this.life - o.life;
		}
    }
    
}
