package modelo;

import java.util.LinkedList;
import java.util.ListIterator;

public class ListaEncadeadaOrdenada<T extends Comparable<T>> extends
		LinkedList<T> {

	private static final long serialVersionUID = 1L;

	public boolean adicionarOrdenado(T element) {
		ListIterator<T> itr = listIterator();
		while (true) {
			if (itr.hasNext() == false) {
				itr.add(element);
				return (true);
			}

			T elementInList = itr.next();
			if (elementInList.compareTo(element) > 0) {
				itr.previous();
				itr.add(element);
				return (true);
			}
		}
	}
}