package bran.main;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.jar.Attributes;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.concurrent.*;

public class Simulation {

	public Simulation() {
		
	}

//	public static final ArrayList<Collection<Object>> collections = new ArrayList<Collection<Object>>();
//	public static final ArrayList<Map<Object, Object>> maps = new ArrayList<Map<Object, Object>>();
	public static final ArrayList<Object> collectionsMaps = new ArrayList<Object>();
	static {
		init();
	}

	@SuppressWarnings("unchecked")
	private static <E extends Object, F extends Object & Comparable<F>, K extends Object, V extends Object> void init() {
		for (Collection<Object> c : new Collection[] {
				// new HashSet<E>(), new LinkedHashSet<E>(), new TreeSet<E>(),
				new ArrayList<E>(),
				// new Vector<E>(), new Stack<E>(),
				new LinkedList<E>(),
				// new PriorityQueue<E>(), new ArrayDeque<E>(), new CopyOnWriteArraySet<E>(), new ConcurrentSkipListSet<E>(),
//				new CopyOnWriteArrayList<E>(), 
// 				new LinkedBlockingQueue<E>(), new ConcurrentLinkedDeque<E>(), // new ArrayBlockingQueue<F>(100),
// 				new ConcurrentLinkedQueue<E>(), new PriorityBlockingQueue<E>(), new LinkedTransferQueue<E>()
		}) // DelayQueue SynchronousQueue<E>()
//			collections.add(c);
			collectionsMaps.add(c);
		for (Map<Object, Object> m : new Map[] {
				// new Hashtable<K, V>(), new HashMap<K, V>(), new IdentityHashMap<K, V>(),
				// new ConcurrentHashMap<K, V>(),
//				new EnumMap<GraphDrawer.GenericEnum, V>(GraphDrawer.GenericEnum.class),
// 				new TreeMap<K, V>(), new WeakHashMap<K, V>(),
// 				new Properties(),
//				new Attributes()
				})
//			maps.add(m);
		collectionsMaps.add(m);
	}

//	public static void run(GraphDrawer graphDrawer) {
//		long maxTime = 0;
//		final int scaler = (int) (graphDrawer.getTestCases() / Math.log(graphDrawer.getMaxSize()));
//		for (int i = 0; i <= graphDrawer.getTestCases(); i++) {
//			int collectionSize = (int) (Math.log(i) * scaler);
//			for (Entry<String, HashMap<Collection<Object>, Method>> methodEntry : graphDrawer.getCollectionMethods().entrySet()) {
//				for (Entry<Collection<Object>, Method> entry : methodEntry.getValue().entrySet()) {
//					long time = System.nanoTime();
////					collectionSize
//					try {
//						entry.getValue().invoke(entry.getKey(), entry.getValue().getParameters());
//						time -= System.nanoTime();
//					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//						e.printStackTrace(); //remove method from everything?
//					}
//
//					if (time > maxTime) {
//						maxTime = time;
//						graphDrawer.updateMaxTimeValue(time);
//					}
//					graphDrawer.getCollectionData().get(entry.getValue()).add(time);
//				}
//			}
//		}
//	}

//	public static void run() {
//		for (Collection c : collections)
//			search(c);
//		for (Object o : elements)
//			System.out.println(o instanceof Class ? o : o.getClass());
//	}

	static ArrayList<Object> elements = new ArrayList<Object>();
	private static void search(Object element) {
		if (!elements.contains(element)) {
//			if (element instanceof Class)
				elements.add(element);
			if (!(element instanceof Class) || !((Class) element).equals(Object.class)) {
				search(element.getClass().getSuperclass());
				for (Class<?> cl : element.getClass().getInterfaces())
					search(cl);
			}
		}
	}

//	public enum GenericEnum {
//
//	}

//	public static class Array {
//		int size;
//		Object[] arr;
//		public Array(int size) {
//			this.size = size;
//			arr = new Object[size];
//		}
//		public Array() {
//			arr = new Object[16];
//		}
//	}
}
