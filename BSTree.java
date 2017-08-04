package week11;

class BSTree<T extends Comparable <T>> {
	private BSTNode<T> root;
	private boolean insertSuccess;
	private boolean removeSuccess;

	public BSTree() {root = null;} // 생성자

	// 반복적인 방법으로 삽입
	public boolean insert(T data){
		BSTNode<T> newNode= new BSTNode<T>(data);
		if(root== null){	//root에 아무것도 없을 때
			root=newNode;
			insertSuccess = true;
			return insertSuccess;
		}
		else{
			BSTNode<T> parent = null;
			BSTNode<T> curr = root;
			//삽입 위치 curr을 찾음
			while(curr!=null){
				parent= curr;
				if(data.compareTo(curr.item)<0)
					curr = curr.left;
				else if(data.compareTo(curr.item)>0)
					curr = curr.right;
				else{
					insertSuccess = false;
					return insertSuccess;
				}
			}
			//curr과 데이터를 비교하고 어느쪽에 넣을 것인지 결정함
			if(parent.item.compareTo(data)>0){
				parent.left = newNode;
				insertSuccess=true;
			}
			else{
				parent.right = newNode;
				insertSuccess = true;
			}
			return insertSuccess;
		}
	}

	// 재귀적인 방법으로 삽입
	public boolean insertByRecursion(T data){
		BSTNode<T> newNode = new BSTNode<T>(data);
		BSTNode<T> tree = root;
		return insertByRecursion(newNode,tree);
	}

	public boolean insertByRecursion(BSTNode<T> newNode, BSTNode<T> tree) {
		//tree가 비어있을 때.
		if(isEmpty()){
			tree = root;
			root = newNode;
			insertSuccess = true;
			return insertSuccess;
		}
		//tree의 item보다 newNode의 item이 더 작을 때.
		else if(tree.item.compareTo(newNode.item)>0){
			if(tree.left == null){	//비어있으면 삽입
				tree.left = newNode;
				insertSuccess = true;
				return insertSuccess;
			}
			return insertByRecursion(newNode, tree.left);
		}
		//tree의 item보다 newNode의 item이 더 클 때.
		else if(tree.item.compareTo(newNode.item)<0){
			if(tree.right == null){
				tree.right = newNode;
				insertSuccess = true;
				return insertSuccess;
			}
			return insertByRecursion(newNode, tree.right);
		}
		else{
			insertSuccess = false;
			return insertSuccess;
		}
	}
	// 반복적인 방법으로 삭제
	public boolean remove(T data){
		//tree가 비어있을 때
		BSTNode<T> tree = root;
		if(root== null){	//root에 아무것도 없을 때
			removeSuccess = false;
			return removeSuccess;
		}
		BSTNode<T> treeCurr = null;
		
		BSTNode<T> temp;
		BSTNode<T> tempCurr = null;
		
		boolean compareRootTemp = false;
		
		while(true){
			if(tree.item.compareTo(data)==0){
				temp = tree;
				if(temp.left == null){
					if(treeCurr == null)
						root = tree.right;
					else if(treeCurr.item.compareTo(temp.item)>0)
						treeCurr.left = temp.right;
					else
						treeCurr.right = temp.right;
				}
				else{
					temp = temp.left;
					while(temp.right != null){
						compareRootTemp = true;
						tempCurr = temp;
						temp = temp.right;
					}
					if(compareRootTemp == true){
						tempCurr.right = null;
						temp.right = tree.right;
						temp.left = tree.left;
					}
					else
						temp.right = tree.right;
					if(tree==root)
						root = temp;
					else{
						if(treeCurr.item.compareTo(temp.item)>0)
							treeCurr.left = temp;
						else
							treeCurr.right = temp;
					}
				}
				return true;
			}
			else if(tree.item.compareTo(data)>0){
				if(tree.left == null)
					return false;
				treeCurr = tree;
				tree = tree.left;
			}
			else{
				if(tree.right == null)
					return false;
				treeCurr = tree;
				tree = tree.right;
			}
		}
	}

	public boolean removeByRecursion(T data) { // 재귀적인 방법으로 삭제
		root = removeByRecursion(root, data);
		return removeSuccess;
	}
 
	public BSTNode<T> removeByRecursion(BSTNode<T> tree,T data) {
		if(tree == null)
			removeSuccess = false;
		else if(data.compareTo(tree.item)<0)
			tree.left = removeByRecursion(tree.left, data);
		else if(data.compareTo(tree.item)>0)
			tree.right = removeByRecursion(tree.right, data);
		else {
			removeSuccess = true;
			if(tree.left == null)
				tree = tree.right;
			else if(tree.right == null)
				tree = tree.left;
			else {	// 자식노드 모두가 null이 아닐때
				BSTNode<T> maxNode = tree.left;
				while(maxNode.right != null)
					maxNode = maxNode.right;
				tree.item = maxNode.item;
				tree.left = removeByRecursion(tree.left, maxNode.item);
			}
		}
		return tree;
	}
	
	// 반복적인 방법으로 탐색
	public boolean search(T data){
		BSTNode<T> tree = root;
		while(true){
			//같으면 true 반환
			if(tree.item.compareTo(data)==0)
				return true;
			/**
			 * 이동하는 부분
			 */
			else if(tree.item.compareTo(data)>0){
				if(tree.left == null)
					return false;
				tree = tree.left;
			}
			else if(tree.item.compareTo(data)<0){
				if(tree.right == null)
					return false;
				tree = tree.right;
			}
		}
	}
	// 재귀적인 방법으로 탐색
	public boolean searchByRecursion(T data){
		return searchByRecursion(root, data);
	}
	public boolean searchByRecursion(BSTNode<T> tree, T data){
		if(tree==null)
			return false;
//		else if(data.compareTo(tree.item)==0)
//			return true;
		else if(data.compareTo(tree.item)<0)
			return searchByRecursion(tree.left, data);
		else if(data.compareTo(tree.item)>0)
			return searchByRecursion(tree.right, data);
		else{	//같은 부분
			return true;
		}
	}

	// 중위 순회
	public void inorderTraverse() {
		inorderTraverse(root);
		System.out.println();
	}
	public void inorderTraverse(BSTNode<T> tree) {
		if (tree != null) {
			inorderTraverse(tree.left);
			System.out.print(tree.item .toString()+ " ");
			inorderTraverse(tree.right);
		}
	}

	// 트리의 구조를 알 수 있도록 출력
	public void print(){
		print(root,countNode(root));
		System.out.println("");
	}
	public void print(BSTNode<T> node, int treeFloor){
		if(node != null){
			print(node.right,treeFloor+1);
			
			for(int i =0; i<treeFloor; i++)
				System.out.print("\t");
			System.out.print(node.item);
			
			if(node.left != null)
				System.out.print(",L");
			if(node.right != null)
				System.out.println(",R");
			System.out.println();
			
			print(node.left,treeFloor+1);
		}
	}
	
	//tree의 층이 어디인지 찾기.
	public int countNode() {
		return countNode(root);
	}
	private int countNode(BSTNode<T> tree) {
		if (tree == null)
			return 0;
		else
			return countNode(tree.left) + countNode(tree.right);
	}
	
	//비어있는지 검사
	public boolean isEmpty() {
		return root == null;
	}

}

