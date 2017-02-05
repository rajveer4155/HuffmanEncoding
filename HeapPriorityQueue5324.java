/* SIDHU RAJVEER 	cs610 PrP 5324 */

import java.util.Comparator;

public class HeapPriorityQueue5324<K,V> extends AbstractPQ5324<K,V>{
	//*** Primary collection
	protected ArrayList5324<PQEntry5324<K,V>> heapPQ=new ArrayList5324<PQEntry5324<K,V>>();
	
	//*** Constructors
	
	public HeapPriorityQueue5324(){super();};
	
	public HeapPriorityQueue5324(Comparator<K> keyComp){super(keyComp);};
	
	//**** Protected Utilities
	
	protected int parent5324(int i){
		return ((i-1)/2);
	}
	
	protected int left5324(int i){
		return ((2*i)+1);
	}
	
	protected int right5324(int i){
		return ((2*i)+2);
	}

	protected boolean hasLeft5324(int i){
		return (left5324(i) <heapPQ.size5324());
	}
	
	protected boolean hasRight5324(int i){
		return (right5324(i) <heapPQ.size5324());
	}
	
	protected void swap5324(int i, int j){
		PQEntry5324<K,V> tempStore=heapPQ.get5324(i);
		heapPQ.set5324(i, heapPQ.get5324(j));
		heapPQ.set5324(j, tempStore);
	}
	
	//*** Bubbling up after insertion
	protected void bubbleUp5324(int i){
		while(i>0){
			int temp=parent5324(i);
			if(compare(heapPQ.get5324(i),heapPQ.get5324(temp))>0){
				break;
			}
			swap5324(i,temp);
			i=temp;
		}
	}
	
	//*** Down-heap after removal
	protected void downHeap5324(int i){
		
		while(hasLeft5324(i)){
			int leftIndex=left5324(i);
			int smallChildIndex=leftIndex;
			if(hasRight5324(i)){
				int rightChildIndex=right5324(i);
				if(compare(heapPQ.get5324(rightChildIndex),heapPQ.get5324(leftIndex))<0){
					smallChildIndex=rightChildIndex;
				}
			}
			if(compare(heapPQ.get5324(smallChildIndex),heapPQ.get5324(i))>=0){
				break;
			}
			swap5324(i,smallChildIndex);
			i=smallChildIndex;
			
		}
	}
	
	//public methods
	
	public int size5324(){
		return heapPQ.size5324();
	}
	
	public PQEntry5324<K,V> min5324(){
		if(size5324()==0){
			return null;
		}else{
			return heapPQ.get5324(0);
		}
	}
	
	public PQEntry5324<K,V> insert5324(K key, V value){
		int size=heapPQ.size5324();
		PQEntry5324<K,V> newest= new PQNode5324<>(key,value);
		heapPQ.add5324(size, newest);
		bubbleUp5324(heapPQ.size5324()-1);
		return newest;
	}
	
	
	
	public PQEntry5324<K,V> removeMin5324(){
		PQEntry5324<K,V> temp=heapPQ.get5324(0);
		
		if(size5324()==0){
			return null;
		}else{
			swap5324(0,size5324()-1);
			heapPQ.remove5324(size5324()-1);
			downHeap5324(0);
			return temp;
		}
	}

}
