package java_concepts;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

class Node<E> {
	private String key;
	private E value;

	public Node(String key, E value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public E getValue() {
		return value;
	}
}

abstract class Cache<E> {
	protected final ConcurrentHashMap<String, Node<E>> map;
	protected final LinkedList<Node<E>> list;
	protected final int capacity;

	public Cache(int capacity) {
		this.capacity = capacity;
		this.list = new LinkedList<>();
		this.map = new ConcurrentHashMap<>();
	}

	abstract E get(String key);

	abstract void put(String key, E value);
}

class MRUCache<E> extends Cache<E> {

	public MRUCache(int capacity) {
		super(capacity);
	}

	@Override
	public E get(String key) throws MRUCacheException {
		synchronized (this) {
			if (map.containsKey(key)) {
				Node<E> node = map.get(key);
				// here used single linked list of but using CUSTOM double linked would help
				// alot for performance
				list.remove(node);
				list.addLast(node);
				return node.getValue();
			} else {
				throw new MRUCacheException("No Such Element Found.");
			}
		}
	}

	@Override
	public void put(String key, E value) {
		synchronized (this) {
			if (list.size() == capacity) {
				map.remove(list.removeLast().getKey());
			}
			list.addLast(new Node<E>(key, value));
			map.put(key, list.getLast());
		}
	}
}

class MRUCacheException extends RuntimeException {

	public MRUCacheException(String msg) {
		super(msg);
	}
}

public class MRUCacheImpl {

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		Cache<Integer> cache = new MRUCache<>(2);
		while (true) {
			try {
				int option = sc.nextInt();

				switch (option) {
				case 1: {
					String key = sc.next();
					int val = Integer.parseInt(sc.next());
					cache.put(key, val);
					continue;
				}
				case 2: {
					String key = sc.next();
					System.out.println(cache.get(key));
					continue;
				}
				default:
					throw new MRUCacheException("No Such Option available.");
				}
			} catch (MRUCacheException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
