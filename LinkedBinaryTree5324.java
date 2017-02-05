/* SIDHU RAJVEER 	cs610 PrP 5324 */

import java.util.HashMap;

//import java.util.*;
public class LinkedBinaryTree5324<E> {
	
	private int size;
	private BTNode5324<E> root;
	
	//Constructor
	
	public LinkedBinaryTree5324(){}
	
	//BT ADT methods
	public BTNode5324<E> getRoot5324(){
		return root;
	}
	// Creating a node with all values
	public BTNode5324<E> createNode5324(E e, BTNode5324<E> left, BTNode5324<E> right,BTNode5324<E> parent){
		BTNode5324<E> temp=new BTNode5324<E>(e,left,right,parent);
		return temp;
	}
	
	// BTNode Validity
	
	protected BTNode5324<E> isValidNode5324(BTNode5324<E> n) throws IllegalArgumentException{
		if(!(n instanceof BTNode5324)){
			throw new IllegalArgumentException("Not a valid Node");
		}
		BTNode5324<E> tempNode=n;
		if(tempNode.getParent5324()==tempNode){
			throw new IllegalArgumentException("Node is already deleted");
		}
		
		return tempNode;
	}
	
	
	// Getting BTNode's parts
	public BTNode5324<E> parent5324(BTNode5324<E> n) throws IllegalArgumentException {
		BTNode5324<E> tempNode=isValidNode5324(n);
		return tempNode.getParent5324();
	}
	
	
	public BTNode5324<E> left5324(BTNode5324<E> n) throws IllegalArgumentException {
		BTNode5324<E> tempNode=isValidNode5324(n);
		return tempNode.getLeft5324();
	}
	
	
	public BTNode5324<E> right5324(BTNode5324<E> n) throws IllegalArgumentException {
		BTNode5324<E> tempNode=isValidNode5324(n);
		return tempNode.getRight5324();
	}
	

	//numOfChildren overridden to define for BT
	
	public int numOfChildren5324(BTNode5324<E> p){
		int count=0;
		if(left5324(p)!=null){
			count++;
		}
		
		if(right5324(p)!=null){
			count++;
		}
		
		return count;
	}
	
	//**************  Boolean Checker Methods
	public boolean isEmpty5324(){
		return size==0;
	}
	
	public boolean isRoot5324(BTNode5324<E> n){
		return n==root;
	}
	
	public boolean isInternal5324(BTNode5324<E> n){
		BTNode5324<E> tempNode=isValidNode5324(n);
		if(numOfChildren5324(tempNode)!=0){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isExternal5324(BTNode5324<E> n){
		BTNode5324<E> tempNode=isValidNode5324(n);
		if(numOfChildren5324(tempNode)!=0){
			return false;
		}else{
			return true;
		}
	}
	
	//*****BTNode Creation Methods
	
	//Adds root to empty tree
	public BTNode5324<E> addRoot5324(E e){
		BTNode5324<E> temp=createNode5324(e,null,null,null);
		root=temp;
		size++;
		return temp;
	}
	
	//Adds Left child to BTNode n
	public BTNode5324<E> addLeft5324(BTNode5324<E> n, E e)throws IllegalArgumentException{
		BTNode5324<E> parent=isValidNode5324(n);
		if(parent.getLeft5324()!=null){
			throw new IllegalArgumentException(" P already has a left child");
		}
		BTNode5324<E> temp=createNode5324(e,null,null,parent);
		parent.setLeft5324(temp);
		size++;
		return temp;
	}

	//Adds Right child to BTNode 
	public BTNode5324<E> addRight5324(BTNode5324<E> n, E e)throws IllegalArgumentException{
		BTNode5324<E> parent=isValidNode5324(n);
		if(parent.getRight5324()!=null){
			throw new IllegalArgumentException(" P already has a Right child");
		}
		BTNode5324<E> temp=createNode5324(e,null,null,parent);
		parent.setRight5324(temp);
		size++;
		return temp;
	}

	// Attach subtrees to left and right
	public void attach5324(BTNode5324<E> n, LinkedBinaryTree5324<E> tree1, LinkedBinaryTree5324<E> tree2)
			throws IllegalArgumentException{
		BTNode5324<E> node=isValidNode5324(n);
		if(isInternal5324(n)){
			throw new IllegalArgumentException("Invalid Node Position aupplied, Node must be leaf");
		}
		size=tree1.size+tree2.size;
		if(!tree1.isEmpty5324()){
			tree1.root.setParent5324(node);
			node.setLeft5324(tree1.root);
			tree1.root=null;
			tree1.size=0;
		}
		
		if(!tree2.isEmpty5324()){
			tree2.root.setParent5324(node);
			node.setRight5324(tree2.root);
			tree2.root=null;
			tree2.size=0;
		}
	}
		
} // Class Ends
