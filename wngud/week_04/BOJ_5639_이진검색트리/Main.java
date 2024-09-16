import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Node {
    int data;
    Node left;
    Node right;

    Node() {};

    Node(int data) {
        this.data = data;
    }
}


class BinarySearchTree {
    Node root;
    // int size 도 있으면 좋음

    BinarySearchTree() {
        this.root = null;
    }

    // 1. 추가
    // 1) 루트 노드가 없으면, 루트 노드로 대체하고
    // 루트 노드가 있으면, addNode() 실행
    // 2) 기존 노드와 새로운 노드를 비교하여 왼쪽과 오른쪽 재귀
    // 더 이상 갈 곳이 없으면 그 자리를 newNode가 채움 (단말 노드)
    public void add(int data) {
        Node newNode = new Node(data);

        if (root == null) {
            root = newNode;
        } else {
            root = addNode(root, newNode);
        }
    }

    private Node addNode(Node node, Node newNode) {
        if (node == null) {
            return newNode;
        } else if (node.data > newNode.data) {
            node.left = addNode(node.left, newNode);
        } else if (node.data < newNode.data) {
            node.right = addNode(node.right, newNode);
        }

        return node;
    }


    // 2. 검색
    // 1) 처음에는 root를 넣기 위해서 2가지 메서드를 분리해서 생성
    // 맨 처음 search에서는 루트와 데이터와 비교
    // 2) 
    public void search(int data) {
        searchNode(root, data);
    }

    private Node searchNode(Node node, int data) {
        if (node == null) {
            System.out.println("검색하고자 하는 데이터를 가진 노드를 찾지 못했습니다.");
            return node; // 종료?
        }

				// 왼쪽을 봐야할 것 같은데 반대로 되어있어서 일단 수정함
        if (node.data > data) {
            node.left = searchNode(node.left, data);
        } else if (node.data < data) {
            node.right = searchNode(node.right, data);
        }

        return node;
    }

    // 3. 삭제
    // 삭제하고 싶은 노드의 데이터를 파라미터로 삽입
    // 처음에는 루트를 넣어서 데이터와 비교
    public void remove(int data) {
        root = removeNode(root, data);
    }

    private Node removeNode(Node node, int data) {
        // else 구문을 만나지 못한다면
        if (node == null) {
            System.out.println("삭제하고 싶은 데이터를 가진 노드를 찾지 못했습니다.");
            return node; // 종료?
        }

        // 검색처럼 node.left.data or node.right.data...로 점점 들어가면서 탐색
        // 데이터가 작으면 왼쪽으로 이동, 데이터가 크면 오른쪽으로 이동
        if (node.data > data) {
            node.left = removeNode(node.left, data);
        } else if (node.data < data) {
            node.right = removeNode(node.right, data);
        }
        // 삭제할 노드를 찾았다면
        else {
            // 1) 자식이 아무것도 없다면, 단순히 삭제하기
            // node.left 또는 node.right에 null값이 들어가서 (위의 if와 else if의 대입 연산자)
            // 부모의 node의 자식 node를 null값으로 연결을 끊음
            if (node.left == null && node.right == null) {
                return null;
            }
            // 2) 오른쪽 서브트리에서 가장 작은 값 찾아 대체 (가장 왼쪽에 있는 값)
            // 계층 구조를 유지하기 위해 값만 변경함
            else if (node.right != null) {
                Node child = findMinNode(node.right);

                int removeData = child.data; // temp
                node.data = child.data;
                child.data = removeData;

                // 다시 옮겨진 위치에서 서브트리에 대해 재귀적으로 실행 -> ???
                node.right = removeNode(node.right, data);
            }
            // 3) 왼쪽 서브트리에서 가장 큰 값 찾아 대체 (가장 오른쪽에 있는 값)
            // 계층 구조를 유지하기 위해 값만 변경함
            else if (node.left != null) {
                Node child = findMaxNode(node.left);

                int removeData = node.data; // temp
                node.data = child.data;
                child.data = removeData;

                // 다시 옮겨진 위치에서 서브트리에 대해 재귀적으로 실행 -> ???
                node.left = removeNode(node.left, data);
            }

        } // else 끝
        return node;
    }

    // 3-1. 최대값
    // 계속 오른쪽 순회
    // successor 을 찾기 위함
    private Node findMaxNode(Node node) {
        if (node.right == null) {
            return node;
        } else {
            return findMaxNode(node.right);
        }
    }

    // 3-2. 최소값
    // 계속 왼쪽 순회
    // predecessor 을 찾기 위함
    private Node findMinNode(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return findMaxNode(node.left);
        }
    }

    // 4-1. 전위 순회
    void preOrder(Node node) {
        // 아무것도 없으면 종료
        if (node == null) return;

        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 4-2. 중위 순회
    void inOrder(Node node) {
        // 아무것도 없으면 종료
        if (node == null) return;

        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }

    // 4-3. 후위 순회
    void postOrder(Node node) {
        // 아무것도 없으면 종료
        if (node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.data);
    }

    // 0. 사이즈

}

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BinarySearchTree bst = new BinarySearchTree();

        // 추가
        String input = "";
        while ((input = br.readLine()) != null && !input.isEmpty()) {
            bst.add(Integer.parseInt(input));
        }

        // 순회
//        bst.preOrder(bst.root);
//        bst.inOrder(bst.root);
        bst.postOrder(bst.root);

    }
}