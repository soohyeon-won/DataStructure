package week11;

class BSTree<T extends Comparable <T>> {
	private BSTNode<T> root;
	private boolean insertSuccess;
	private boolean removeSuccess;

	public BSTree() {root = null;} // ������

	// �ݺ����� ������� ����
	public boolean insert(T data){
		BSTNode<T> newNode= new BSTNode<T>(data);
		if(root== null){	//root�� �ƹ��͵� ���� ��
			root=newNode;
			insertSuccess = true;
			return insertSuccess;
		}
		else{
			BSTNode<T> parent = null;
			BSTNode<T> curr = root;
			//���� ��ġ curr�� ã��
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
			//curr�� �����͸� ���ϰ� ����ʿ� ���� ������ ������
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

	// ������� ������� ����
	public boolean insertByRecursion(T data){
		BSTNode<T> newNode = new BSTNode<T>(data);
		BSTNode<T> tree = root;
		return insertByRecursion(newNode,tree);
	}

	public boolean insertByRecursion(BSTNode<T> newNode, BSTNode<T> tree) {
		//tree�� ������� ��.
		if(isEmpty()){
			tree = root;
			root = newNode;
			insertSuccess = true;
			return insertSuccess;
		}
		//tree�� item���� newNode�� item�� �� ���� ��.
		else if(tree.item.compareTo(newNode.item)>0){
			if(tree.left == null){	//��������� ����
				tree.left = newNode;
				insertSuccess = true;
				return insertSuccess;
			}
			return insertByRecursion(newNode, tree.left);
		}
		//tree�� item���� newNode�� item�� �� Ŭ ��.
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
	// �ݺ����� ������� ����
	public boolean remove(T data){
		//tree�� ������� ��
		BSTNode<T> tree = root;
		if(root== null){	//root�� �ƹ��͵� ���� ��
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

	public boolean removeByRecursion(T data) { // ������� ������� ����
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
			else {	// �ڽĳ�� ��ΰ� null�� �ƴҶ�
				BSTNode<T> maxNode = tree.left;
				while(maxNode.right != null)
					maxNode = maxNode.right;
				tree.item = maxNode.item;
				tree.left = removeByRecursion(tree.left, maxNode.item);
			}
		}
		return tree;
	}
	
	// �ݺ����� ������� Ž��
	public boolean search(T data){
		BSTNode<T> tree = root;
		while(true){
			//������ true ��ȯ
			if(tree.item.compareTo(data)==0)
				return true;
			/**
			 * �̵��ϴ� �κ�
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
	// ������� ������� Ž��
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
		else{	//���� �κ�
			return true;
		}
	}

	// ���� ��ȸ
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

	// Ʈ���� ������ �� �� �ֵ��� ���
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
	
	//tree�� ���� ������� ã��.
	public int countNode() {
		return countNode(root);
	}
	private int countNode(BSTNode<T> tree) {
		if (tree == null)
			return 0;
		else
			return countNode(tree.left) + countNode(tree.right);
	}
	
	//����ִ��� �˻�
	public boolean isEmpty() {
		return root == null;
	}

}

