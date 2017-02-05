/* SIDHU RAJVEER 	cs610 PrP 5324 */

import java.util.Comparator;

public abstract class AbstractPQ5324<K,V> {
	
	//** Private Node class
	protected class PQNode5324<K,V> implements PQEntry5324<K,V>{
		private K key;
		private V value;
		//** Constructor
		public PQNode5324(K k,V v){
			key=k;
			value=v;
		}
		 public K getKey5324(){
			return key;
		}
		
		public V getValue5324(){
			return value;
		}
		
		public void setKey5324(K k){
			key=k;
		}
			
		public void setValue5324(V v){
		    value=v;
		}
		
	} //** Inner Node Class ends
	
	private int size;
	private PQNode5324<K,V> head=null;
	
	// For comparing keys
	private Comparator<K> keyComparator;
	
	//** Creates an empty PQ  using a given Comparator
	protected AbstractPQ5324(Comparator<K> cmp) { keyComparator = cmp; }
	
	//** Creates an empty PQ based on the natural ordering of the keys. 
	protected AbstractPQ5324(){ this(new PQDefaultComparator5324<K>( )); }
    
	//** Overloaded Method for comparing two entries based on the key 
	protected int compare(PQEntry5324<K,V> a, PQEntry5324<K,V> b) {
		return keyComparator.compare(a.getKey5324(), b.getKey5324( )); // comparing keys using comparator
	}
	
	private int size5324(){
		return size;
	}
	
	//** Determines whether a key is valid. 
	
	protected boolean isValidKey5324(K key) throws IllegalArgumentException {
		try {
			return (keyComparator.compare(key,key) == 0); // see if key can be compared to itself
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Not a Valid key");
		}
	}
	//∗∗ Tests whether the priority queue is empty. ∗/
	public boolean isEmpty5324( ) { return size5324() == 0; }
}
