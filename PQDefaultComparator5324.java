/* SIDHU RAJVEER 	cs610 PrP 5324 */

import java.util.Comparator;

public class PQDefaultComparator5324<E> implements Comparator<E> {

	@SuppressWarnings("unchecked")
	public int compare(E elem1, E elem2) throws ClassCastException{
		return ((Comparable<E>)elem1).compareTo(elem2);
	}
	
}
