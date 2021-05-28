package bran.main;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.swing.Timer;

import bran.threads.Threading;

@SuppressWarnings({ "serial", "deprecation" })
public class GraphDrawerStreams extends Applet {

    private final int DELAY = 20;
//    private HashMap<String, HashMap<Collection<Object>, ArrayList<Long>>> collectionData;
//    private HashMap<Collection<Object>, Method> collectionMethod;
//    private HashMap<String, HashMap<Collection<Object>, Method>> methodCollections;
//    private HashMap<Collection<Object>, Color> collectionColors;
//    private HashSet<Object> collectionsMaps;
	private HashMap<String, IdentityHashMap<Object, Trio<Method, ArrayList<Long>, Color>>> collectionMapsBundle;
    private final int maxSize;
    private int trialAmount;
    private final int testCases;
    private long maxTimeValue;
    private final String[] xAxisLabels;
    private String[] yAxisLabels;
	private String[] methods;
//    private Method method;

	private static class Trio<A, B, C> {
		A a;
		B b;
		C c;

		public Trio(A a, B b, C c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}

	}

	public static final long MAX_TIME_VALUE_DEFAULT = 12000L;

    public GraphDrawerStreams(String[] methodStrings, int maxSize, int trialAmount, int testCases) {
//    	collectionsMaps = new HashSet<Object>();
    	collectionMapsBundle = new HashMap<String, IdentityHashMap<Object,Trio<Method,ArrayList<Long>,Color>>>();
    	this.maxSize = maxSize;
    	this.trialAmount = trialAmount;
    	this.testCases = testCases;
    	maxTimeValue = MAX_TIME_VALUE_DEFAULT;
    	xAxisLabels = new String[5];
    	yAxisLabels = new String[5];
    	this.methods = methodStrings;
    	scalar = Math.log10(maxSize) /testCases;
    }

    private void updateYAxisLabels() {
    	for (int i = 0; i < yAxisLabels.length; i++)
    		yAxisLabels[i] = String.valueOf(scaleCaseSize(i * maxTimeValue / 5));
    }

    private final double scalar;
    private int scaleCaseSize(long caseSize) {
    	return (int) (Math.pow(10, caseSize * scalar));
//    	return (int) (Math.log(caseSize) * testCases / Math.log(maxSize));
    }

	public synchronized void init() {
		List<String> x;
		
		Stream.of(methods).forEach(methodString -> { // #$stream
			IdentityHashMap<Object, Trio<Method, ArrayList<Long>, Color>> collectionMapsData = new IdentityHashMap<Object, GraphDrawerStreams.Trio<Method,ArrayList<Long>,Color>>(
//					(o1, o2) -> o1.equals(o2) ? 0 : -1
							);
			Simulation.collectionsMaps.stream().forEach(
					colMap -> {
						Stream.of(colMap.getClass().getMethods())
					.filter(m -> m.getName().equals(methodString))
					.findAny()
					.get();
//					collectionMapsData.put(new Trio<Method,ArrayList<Long>,Color>(m, new ArrayList<Long>(maxSize), null));
//					.flatMap(m -> collectionMapsData.put(colMap, m))
					}
					);
			for (Object colMap : Simulation.collectionsMaps)
				for (Method m : colMap.getClass().getMethods()) {
					if (m.getName().equals(methodString)) {
//						System.out.println(colMap.hashCode() + " " + colMap.getClass().getName());
						collectionMapsData.put(colMap, new Trio<Method,ArrayList<Long>,Color>(m, new ArrayList<Long>(maxSize), null));
						//					System.out.println(colMap.getClass().getName() + ":\t" + m + " ");
//						System.out.println(collectionMapsData.get(colMap).a);
						break;
					}
				}
			for (Entry<Object, Trio<Method, ArrayList<Long>, Color>> entry : collectionMapsData.entrySet()) {
				entry.getValue().c = new Color(Color.HSBtoRGB(nextHue() / collectionMapsData.size(), .5f - .1f * nextSet() / methods.length, .6F));
			}
			collectionMapsBundle.put(methodString, collectionMapsData);
//			System.out.println(methodString + ":");
//			for (Entry<Object, Trio<Method, ArrayList<Long>, Color>> entry : collectionMapsBundle.get(methodString).entrySet()) {
//				System.out.println("\t" + entry.getKey().getClass().getName() + ":\t" + entry.getValue().a
//						+ "\t" + entry.getValue().c);
//			}
		});
//		System.out.println(collectionMapsBundle.get("add").get(Simulation.collectionsMaps.get(0)).a);
    	for (int i = 0; i < xAxisLabels.length; i++)
    		xAxisLabels[xAxisLabels.length - 1 - i] = String.valueOf(i * maxSize / (xAxisLabels.length - 1));

    	updateYAxisLabels();

        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                repaint();
            }
        };
        new Timer(DELAY, taskPerformer).start();
    }

	private float satShift = 0;
	private float hueShift = 0;
    private float nextSet() {
		return satShift++;
	}

	private float nextHue() {
		return hueShift++;
	}

	public void update(Graphics g) {
        Graphics offgc;
        Image offscreen = null;
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        offscreen = createImage(d.width, d.height);
        offgc = offscreen.getGraphics();

        offgc.setColor(getBackground());
        offgc.fillRect(0, 0, d.width, d.height);
        offgc.setColor(getForeground());

        paint(offgc);
        g.drawImage(offscreen, 0, 0, this);
    }

    public final static int topMargin = 20;
    public final static int leftMargin = 50;
    public final static int bottomMargin = 100;
    public final static int rightMargin = 50;

//    private HashMap<Collection<Object>, HashMap<String, Method>> collectionMethods;
//    private HashMap<Method, ArrayList<Long> collectionData;
//    private HashMap<Method, Color> collectionColors;
    
    public void paint(Graphics g) {

    	int height = getHeight();
    	int width = getWidth();
        Graphics2D g2 = (Graphics2D) g;
        g2.drawLine(leftMargin, topMargin, leftMargin, height - bottomMargin);
        g2.drawLine(leftMargin, height - bottomMargin, width - rightMargin, height - bottomMargin); // TODO
        for (int i = 0; i < xAxisLabels.length; i++) {
        	
//        	int loc = leftMargin + testCases * i * xAxisLabels.length / 4;
//        	int loc = leftMargin + i * maxSize / 4;
        	int loc = width - leftMargin - (width - leftMargin - rightMargin) * i / (xAxisLabels.length - 1);
        	g2.drawString(xAxisLabels[i], loc, height - bottomMargin + 20);
        	g2.drawLine(loc, height - bottomMargin, loc, height - bottomMargin + 5);
        }
        for (int i = 0; i < yAxisLabels.length; i++) { // TODO
        	int loc = height - bottomMargin - (height - bottomMargin - topMargin) * i / (yAxisLabels.length - 1);
        	g2.drawString(Long.toString(maxTimeValue * i / (yAxisLabels.length - 1)), 5, loc + 6);
//        	g2.drawString(yAxisLabels[i], leftMargin - 10, loc);
        	g2.drawLine(leftMargin - 5, loc, leftMargin, loc);
        }
        
    	for (String methodString : methods) // #$stream
    		for (Entry<Object, Trio<Method, ArrayList<Long>, Color>> entry : collectionMapsBundle.get(methodString).entrySet()) {
//	    		if (entry.getValue().a != null) {
	        		g2.setColor(entry.getValue().c);
	        		ArrayList<Long> data = entry.getValue().b;
	        		for (int i = 0; i < data.size(); i++) {
		        			Long value = data.get(i);
	        			g2.fillRect((int) (leftMargin + (double) (i) / testCases * (width - leftMargin - rightMargin)),
	        					(int) (height - bottomMargin - value * (height - bottomMargin - topMargin) / maxTimeValue),
	        					(int) (1d / testCases * (width - leftMargin - rightMargin) + 1),
	        				(int) ((double) 1 * (height - bottomMargin - topMargin) / (maxTimeValue)) + 1);

//	        			g2.drawRect((int) (leftMargin + (double) (i) / testCases * (width - leftMargin - rightMargin)),
//	        					(int) (height - bottomMargin - Math.toIntExact(value
//	        							* (height - bottomMargin - topMargin) / maxTimeValue)),
//	        					(int) (leftMargin + (double) (i) / testCases * (width - leftMargin - rightMargin)),
//	        					(int) (height - bottomMargin - Math.toIntExact((value)
//	        							* (height - bottomMargin - topMargin) / maxTimeValue)));
	        		}
//	    		}
        	}

//        for (Entry<String, HashMap<Collection<Object>, ArrayList<Long>>> entry : collectionData.entrySet()) {
//	       	g2.setColor(collectionColors.get(entry.getKey()));
//	        for (int i = 0; i < entry.getValue().size(); i++) {
//	        	int v = Math.toIntExact(entry.getValue().get(i));
//	        	g2.drawLine(leftMargin + 1 + i, v, leftMargin + 1 + i, v);
//        	}
//        }
    }

	public void runTests() {
		for (int i = 0; i <= testCases; i++) {
//			System.out.println(i);
//			System.out.println(testCases);
			int collectionSize = i;
	    	for (String methodString : methods) { // #$stream
	    		for (Entry<Object, Trio<Method, ArrayList<Long>, Color>> entry : collectionMapsBundle.get(methodString).entrySet()) {
					try {
	    			fixCollectionMapSize(entry.getKey(), 1); // in case the method is .remove and to initialize
					} catch (ConcurrentModificationException e) {
						e.printStackTrace();
					}
	    		}
	    		for (Entry<Object, Trio<Method, ArrayList<Long>, Color>> entry : collectionMapsBundle.get(methodString).entrySet()) {
					Object collectionMap = entry.getKey();

					Method method = entry.getValue().a;
					Class<?>[] types = method.getParameterTypes();
					Object[] args = new Object[types.length];
					for (int j = 0; j < types.length; j++)
						try {
							args[j] = DUMMY;
									types[j].getConstructor().newInstance();
						} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
								| InvocationTargetException | NoSuchMethodException | SecurityException e) {
							e.printStackTrace();
						}
					long timeSum = 0; 
					for (int j = 0; j < trialAmount; j++) {
						try {
							fixCollectionMapSize(collectionMap, collectionSize);
						} catch (ConcurrentModificationException e) {
							e.printStackTrace();
							timeSum = -5000000;
							break;
						}
						Threading.DRAWING.suspend();
						long startTime = System.nanoTime();
						try {
							method.invoke(collectionMap, args);
//							invoke(method, collectionMap, args);

							timeSum += System.nanoTime() - startTime;
	//						time -= System.nanoTime();
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace(); //remove method from everything?
//							System.out.println(entry.getKey().getClass().getName());
//							System.out.println(entry.getValue().a);
						}
						Threading.DRAWING.resume();
					}
					long time = timeSum / trialAmount;
//					if (time > maxTimeValue) {
//						maxTimeValue = time;
//						updateYAxisLabels();
//					}
//					System.out.println("inv " + time + " " + collectionSize); 
					entry.getValue().b.add(time);
				}
	    	}
		}

		
		for (String methodString : methods) { // #$stream
    		IdentityHashMap<Object, Long> averages = new IdentityHashMap<Object, Long>(collectionMapsBundle.get(methodString).entrySet().size());
    		for (Entry<Object, Trio<Method, ArrayList<Long>, Color>> entry : collectionMapsBundle.get(methodString).entrySet()) {
				long avg = 0;
				for (Long x : entry.getValue().b)
					avg += x;
				averages.put(entry.getKey(), avg / entry.getValue().b.size());
    		}
    		ArrayList<Entry<Object, Trio<Method, ArrayList<Long>, Color>>> sortedAverages =
    				new ArrayList<Entry<Object, Trio<Method, ArrayList<Long>, Color>>>(collectionMapsBundle.get(methodString).entrySet());
    		Collections.sort(sortedAverages, (o1, o2) -> signum(averages.get(o1.getKey()), averages.get(o2.getKey())));
			System.out.println(methodString + ":");
			for (Entry<Object, Trio<Method, ArrayList<Long>, Color>> entry : sortedAverages) {
				System.out.println("\t" + entry.getKey().getClass().getName() + ":\t" + averages.get(entry.getKey()));
//				System.out.println("\t" + entry.getKey().getClass().getName() + ":\t" + entry.getValue().a + "\t" + averages.get(entry.getKey())
//				+ "\t" + entry.getValue().c);
			}
    	}
		Map<Object, Integer> partialMethods = new HashMap<Object, Integer>();
		IdentityHashMap<Object, Long> averages = new IdentityHashMap<Object, Long>();
		for (String methodString : methods) {
			for (Entry<Object, Trio<Method, ArrayList<Long>, Color>> entry : collectionMapsBundle.get(methodString).entrySet()) {
				partialMethods.put(entry.getKey(),
						partialMethods.get(entry.getKey()) == null ? 1 : partialMethods.get(entry.getKey()) + 1);
				long avg = 0;
				for (Long x : entry.getValue().b)
					avg += x;
				averages.put(entry.getKey(),
						averages.get(entry.getKey()) == null ? avg / entry.getValue().b.size() : averages.get(entry.getKey()) + (avg / entry.getValue().b.size()));

			}
		}

		for (Entry<Object, Integer> entry : partialMethods.entrySet()) {
			if (entry.getValue() != methods.length)
				averages.remove(entry.getKey());
			else
				System.out.println(entry.getKey().getClass().getName() + ":\t" + averages.get(entry.getKey()));
		}

		ArrayList<Object> sortedAverages =
				new ArrayList<Object>(averages.entrySet());
		Collections.sort(sortedAverages, (o1, o2) -> o1 == null || o2 == null ? -1 : signum(averages.get(o1), averages.get(o2)));
		System.out.println("summations:");
		for (Object entry : sortedAverages) {
			System.out.println(entry.getClass().getName() + ":\t" + averages.get(entry));
		}
	}

	private int signum(long a, long b) {
		return a > b ? 1 : a < b ? -1 : 0;  
	}

	private final Generic DUMMY = new Generic(); // don't use with sets

//	private synchronized void invoke(Method method, Object collectionMap, Object... args) throws IllegalAccessException, InvocationTargetException {
//		method.invoke(collectionMap, args);
//	}

	private void fixCollectionMapSize(Object collectionMap, int collectionSize) {
//		if (collectionMap instanceof EnumMap) {
//			Map<GenericEnum, Object> map = (Map<GenericEnum, Object>) collectionMap;
//			if (map.size() > collectionSize) {
//				for (Object obj : map.keySet()) {
//					map.remove(obj);
//					if (map.size() == collectionSize)
//						break;
//				}
//			}
//			else
//				while (map.size() < collectionSize)
//					map.put(GenericEnum.generic, DUMMY);
//		}
//		else 
		if (collectionMap instanceof Collection) { // #$stream
			Collection<Object> collection = (Collection<Object>) collectionMap;
			if (collection.size() > collectionSize) {
				for (Object obj : collection) {
					collection.remove(obj);
					if (collection.size() == collectionSize)
						break;
				}
			}
			else
				while (collection.size() < collectionSize)
					collection.add(new Generic());
		}
		else if (collectionMap instanceof Map) { // #$stream
			Map<Object, Object> map = (Map<Object, Object>) collectionMap;
			if (map.size() > collectionSize) {
				for (Object obj : map.keySet()) {
					map.remove(obj);
					if (map.size() == collectionSize)
						break;
				}
			}
			else
				while (map.size() < collectionSize) // #$stream
					map.put(new Generic(), DUMMY);
		}
		else
			System.err.println("Non-Collection/Map input");
	}

	private final static Random RAND = new Random();

	private static class Generic implements Comparable<Generic> {

		@Override
		public int compareTo(Generic o) {
			return RAND.nextInt(3) - 1;
		}

	}

//	public static enum GenericEnum {
//
//		generic();
//
//		public int compareTo(Generic o) {
//			return RAND.nextInt(3) - 1;
//		}
//
//	}

//    public HashMap<String, HashMap<Collection<Object>, Method>> getCollectionMethods() {
//    	return collectionMethods;
//    }
//
//    public HashMap<Method, ArrayList<Long>> getCollectionData() {
//    	return collectionData;
//    }
//
//	public int getMaxSize() {
//		return maxSize;
//	}
//
//	public int getTrialAmount() {
//		return trialAmount;
//	}
//
//	public int getTestCases() {
//		return testCases;
//	}

}
