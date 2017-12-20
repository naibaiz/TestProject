package com.sql.interview;

public class MyHashMap<K, V> {
	private int capacity = 4;
	private Entry<K, V>[] tableOfData; // array of key value pairs

	@SuppressWarnings("unchecked")
	public MyHashMap() {
		tableOfData = new Entry[capacity];
	}

	public void put(K key, V value) {
		int hash = 0;
		if (key != null) { // not allow null key
			hash = hash(key);
		}
		// create new entry first
		Entry<K, V> newEntry = new Entry<K, V>(key, value, null);
		if (tableOfData[hash] == null) {
			tableOfData[hash] = newEntry;
		} else { // there is something already there, hash collision happens...
			Entry<K, V> previousEntry = null;
			Entry<K, V> currentEntry = tableOfData[hash];
			while (currentEntry != null) {
				if (currentEntry.key.equals(key)) { // same key, overwrites
					newEntry.next = currentEntry.next;
					if (previousEntry == null) { // first element
						tableOfData[hash] = newEntry;
						return;
					} else {
						previousEntry.next = newEntry;
						return;
					}
				}
				previousEntry = currentEntry;
				currentEntry = currentEntry.next;
			}
			// reached the end
			previousEntry.next = newEntry;
		}
	}

	public V get(K key) {
		int hash = 0;
		if (key != null) {
			hash = hash(key);
		}
		Entry<K, V> entry = tableOfData[hash];
		if (entry == null) {
			return null; // empty cell
		} else {
			while (entry != null) {
				if ((key == null && entry.key == null) || entry.key.equals(key)) {
					return entry.value;
				}
				entry = entry.next;
			}
		}
		return null; // not found
	}

	private int hash(K key) {
		return key.hashCode() % capacity;
	}

	public String toString() {
		String ret = "";
		for (int i = 0; i < capacity; i++) {
			if (tableOfData[i] != null) {
				Entry<K, V> entry = tableOfData[i];
				while (entry != null) {
					ret += ("{" + entry.key + "=" + entry.value + "} ");
					entry = entry.next;
				}
			}
		}
		return ret;
	}

	class Entry<K, V> {
		K key;
		V value;
		Entry<K, V> next;

		public Entry(K key, V value, Entry<K, V> next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}

	public boolean remove(K key) {
		boolean result = false;
		int hash = hash(key);
		if (tableOfData[hash] == null) {
			return result; // can not remove
		} else {
			Entry<K, V> previousEntry = null;
			Entry<K, V> currentEntry = tableOfData[hash];
			while (currentEntry != null) {
				if (currentEntry.key.equals(key)) {
					if (previousEntry == null) { // first node
						tableOfData[hash] = tableOfData[hash].next;
						return true;
					} else {
						previousEntry.next = currentEntry.next;
						return true;
					}
				}
				previousEntry = currentEntry;
				currentEntry = currentEntry.next;
			}

		}
		return result;
	}
}
