/* SIDHU RAJVEER 	cs610 PrP 5324 */

import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayList5324<E>  {
	private static final int DEFAULTCAPACITY=32;
	E[] element;
	private int size=0;
	
	//*** Constructor with Default Capacity
	public ArrayList5324(){
		this(DEFAULTCAPACITY);
	};
	
	//*** Constructor with User Supplied Capacity
	@SuppressWarnings("unchecked")
	public ArrayList5324(int customCapacity){
		//System.out.println(capacity);
		element=(E[]) new  Object[customCapacity];
	}
	//Nested Iterator class
	@SuppressWarnings("unused")
	private class ArrayIterator5324 implements Iterator<E>{
		private int j=0;
		private boolean isRemovable=false;
		
		
		//Has Next
		public boolean hasNext(){
			return j<size;
		}
		
		//Next
		public E next() throws NoSuchElementException{
			if(j==size){
				throw new NoSuchElementException("Error! No such Element in the list");
			}
			isRemovable=true;
			return element[j++];
		}
		
		//Remove 
		public void remove() throws IllegalStateException{
			if(!isRemovable) throw new IllegalStateException("No such Element to remove in list");
			ArrayList5324.this.remove5324(j-1);
			j--;
			isRemovable=false;
		}
		
		// Returns an iterator for the stored elements in list 
		@SuppressWarnings("unused")
		public Iterator<E> iterator(){
			return new ArrayIterator5324();			
		}
	} //Nested class ends
	
	//**** Utility Methods
		public int size5324(){
			return size;
		}
		
		public E get5324(int i) throws IndexOutOfBoundsException{
			isValidIndex5324(i,size);
			return element[i];
		}
		
		public void add5324(int i, E e){
			//checkIndex(i,size);
			if(size==element.length){
				resize5324((2*element.length));
			}
			for(int j=size-1;j>=i;j--){
				element[j]=element[j-1];
			}
			element[i]=e;
			size++;
		}
		
		public E remove5324(int i) throws IndexOutOfBoundsException{
			isValidIndex5324(i,size);
			E temp=element[i];
			for(int j=i;j<size-1;j++){
				element[j]=element[j+1];
			}
			element[size-1]=null;
			size--;
			return temp;
		}
		
		protected void isValidIndex5324(int i, int n) throws IndexOutOfBoundsException {
			if (i < 0 || i >= n)
				throw new IndexOutOfBoundsException("Error!Illegal index of list: " + i);
			}
	
		
		public boolean isEmpty5324() {
			return size==0;
		}
	
		
		public E set5324(int i, E e) throws IndexOutOfBoundsException {
			isValidIndex5324(i, size);
			E temp = element[i];
			element[i] = e;
			return temp;
	
		}
		
		@SuppressWarnings("unchecked")
		public void resize5324(int newCapacity){
			E[] temp=(E[]) new Object[newCapacity];
			for(int i=0;i<element.length;i++){
				temp[i]=element[i];
			}
			element=temp;
		}
	
}

