import java.util.*;
class BinaryTreeNode<T>{
	T data;
	BinaryTreeNode<T> left;
	BinaryTreeNode<T> right;

	public BinaryTreeNode(T data){
		this.data = data;
	}
}


class Pair{
	int data;
	BinaryTreeNode<Integer> node;

    public Pair(int d , BinaryTreeNode<Integer> n){
        data = d;
        node = n;
    }
}

class BinarySerachTree{

	private BinaryTreeNode<Integer> root;



	private BinaryTreeNode insertHelper(int data , BinaryTreeNode<Integer> root){
		if(root==null){
			BinaryTreeNode<Integer> newNode = new BinaryTreeNode<Integer>(data);
			return newNode;
		}

		if(data<root.data){
			root.left = insertHelper(data,root.left);
		}else{
			root.right = insertHelper(data,root.right);
		}

		return root;
	}

	public void insert(int data){
		root = insertHelper(data,root);
	}



	private boolean searchHelper(int data , BinaryTreeNode<Integer> root){
		if(root==null){
			return false;
		}

		if(root.data==data){
			return true;
		}

		if(data<root.data){
			return searchHelper(data,root.left);
		}

		return searchHelper(data,root.right);
	}


	public boolean search(int data){
		return searchHelper(data,root);
	}


	private Pair findMaxInRightSubtree(BinaryTreeNode<Integer> root){
		if(root.left==null && root.right==null){
			Pair p = new Pair(root.data,null);
            return p;
		}

		Pair p = findMaxInRightSubtree(root.left);
		root.left = p.node;

		return new Pair(p.data,root);
	}

	private BinaryTreeNode<Integer> deleteHelper(int data , BinaryTreeNode<Integer> root){
		if(root==null){
			System.out.println(data + " Not found");
			return null;
		}

		if(data > root.data){
			root.right =  deleteHelper(data,root.right);
            return root;
		}

		if(data < root.data){
			root.left =  deleteHelper(data,root.left);
            return root;
		}

		if(root.left==null && root.right==null){
			return null;
		}

		if(root.left==null){
			return root.right;
		}

		if(root.right==null){
			return root.left;
		}

		Pair p  = findMaxInRightSubtree(root.right);
		root.data = p.data;
		root.right = p.node;
		return root;
	}

	public void delete(int data){
		root = deleteHelper(data,root);
	}


	public void printLevelWise(){
        if(root==null){
            System.out.println("Empty BST");
            return;
        }
		LinkedList<BinaryTreeNode<Integer>> q = new LinkedList<BinaryTreeNode<Integer>>();
		q.add(root);

		while(!q.isEmpty()){
			BinaryTreeNode<Integer> node = q.get(0);
			q.removeFirst();
			String str = node.data + ": ";
			if(node.left!=null){
				str += node.left.data + " ";
				q.addLast(node.left);
			}
			if(node.right!=null){
				str += node.right.data;
				q.addLast(node.right);
			}
			System.out.println(str);
		}
	}

}
public class BST{


	public static void main(String[] args){
		BinarySerachTree bst = new BinarySerachTree();
		bst.insert(5);
		bst.insert(3);
		bst.insert(4);
		bst.insert(2);
		bst.insert(8);
		bst.insert(7);
		bst.insert(9);
		bst.insert(6);
		bst.printLevelWise();
        System.out.println(bst.search(1));
        bst.delete(3);
        bst.printLevelWise();

	}
}
