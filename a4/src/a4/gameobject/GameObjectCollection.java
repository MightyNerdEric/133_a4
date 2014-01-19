package a4.gameobject;

import java.util.*;

public class GameObjectCollection implements Collection, Iterable{
	private Vector<GameObject> myCollection;
	
	public GameObjectCollection() {
		myCollection = new Vector<GameObject>();
	}

	public boolean add(Object newObject) {
		myCollection.addElement((GameObject)newObject);
		return true;
	}

	public boolean addAll(Collection collec) {
		for (GameObjectIterator i = (GameObjectIterator) collec.iterator(); i.hasNext();) {
			if (!myCollection.add((GameObject) i.next()))
				return false;
		}
		return true;
	}

	public void clear() {
		myCollection.clear();
	}

	public boolean contains(Object myObject) {
		return myCollection.contains(myObject);
	}

	public boolean containsAll(Collection collec) {
		for (GameObjectIterator i = (GameObjectIterator) collec.iterator(); i.hasNext();) {
			if (!collec.contains(i))
				return false;
		}
		return true;
	}

	public boolean isEmpty() {
		return myCollection.isEmpty();
	}
	
	public int indexOf(Object obj) {
		return myCollection.indexOf(obj);
	}

	public Iterator iterator() {
		return new GameObjectIterator();
	}

	public boolean remove(Object myObject) {
		return myCollection.remove(myObject);
	}

	public boolean removeAll(Collection collec) {
		for (Iterator i = collec.iterator(); i.hasNext();) {
			i.remove();
		}
		return false;
	}

	public boolean retainAll(Collection arg0) {
		return false;
	}

	public int size() {
		return myCollection.size();
	}

	public Object[] toArray() {
		return myCollection.toArray();
	}

	public Object[] toArray(Object[] theArray) {
		return myCollection.toArray(theArray);
	}
	
	private class GameObjectIterator implements Iterator {
		int currElementIndex;
		
		public GameObjectIterator() {
			currElementIndex = -1;
		}
		
		public boolean hasNext() {
			if (myCollection.size() <= 0)
				return false;
			if (currElementIndex == myCollection.size() -1)
				return false;
			return true;
		}

		public Object next() {
			currElementIndex++;
			return (myCollection.elementAt(currElementIndex));
		}

		public void remove() {
			myCollection.remove(currElementIndex);
			currElementIndex--;
		}	
	}
}
