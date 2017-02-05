/* SIDHU RAJVEER 	cs610 PrP 5324 */

public class BTNode5324<E> {

	
		private E data;
		private BTNode5324<E> left;
		private BTNode5324<E> right;
		private BTNode5324<E> parent;
		
		public BTNode5324(E e,BTNode5324<E> l,BTNode5324<E>r,BTNode5324<E> p){
			data=e;
			left=l;
			right=r;
			parent=p;
		}
		
		// Accessory methods
		public E getElement5324(){
			return data;
		}
		
		public BTNode5324<E> getLeft5324(){
			return left;
		}
		
		public BTNode5324<E> getRight5324(){
			return right;
		}
		
		public BTNode5324<E> getParent5324(){
			return parent;
		}
		
		//Update Method
		
		public void setElement5324(E e){
			data=e;
		}
		
		public void setLeft5324(BTNode5324<E> BTNode){
			left=BTNode;
		}
		
		public void setRight5324(BTNode5324<E> BTNode){
			right=BTNode;
		}
		
		public void setParent5324(BTNode5324<E> BTNode){
			parent=BTNode;
		}
	} //BTNode class ends
	
