// package bran.old;
//
// import java.applet.Applet;
// import java.awt.Color;
// import java.awt.Dimension;
// import java.awt.Graphics;
// import java.awt.Graphics2D;
// import java.awt.Image;
// import java.awt.Toolkit;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.lang.reflect.InvocationTargetException;
// import java.lang.reflect.Method;
// import java.lang.reflect.Parameter;
// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.HashMap;
// import java.util.HashSet;
// import java.util.Map;
// import java.util.Map.Entry;
// import java.util.Random;
//
// import javax.swing.Timer;
//
// @SuppressWarnings({ "serial", "deprecation" })
// public class GraphDrawerX extends Applet {
//
//     private final int DELAY = 20;
// //    private HashMap<String, HashMap<Collection<Object>, ArrayList<Long>>> collectionData;
// //    private HashMap<Collection<Object>, Method> collectionMethod;
// //    private HashMap<String, HashMap<Collection<Object>, Method>> methodCollections;
// //    private HashMap<Collection<Object>, Color> collectionColors;
//     private HashSet<Collection<? extends Object>> collections;
//     private HashMap<String, HashMap<Collection<? extends Object>, Method>> collectionMethods;
//     private HashMap<Entry<Collection<? extends Object>, Method>, ArrayList<Long>> collectionData;
//     private HashMap<Entry<Collection<? extends Object>, Method>, Color> collectionColors;
// //	private HashMap<Entry<Collection<? extends Object>, String>, Trio<Method, ArrayList<Long>, Color>> collectionBundle;
// 	private HashMap<String, HashMap<Collection<? extends Object>, Trio<Method, ArrayList<Long>, Color>>> collectionBundle;
//     private final int maxSize;
//     private int trialAmount;
//     private final int testCases;
//     private long maxTimeValue;
//     private final String[] xAxisLabels;
//     private String[] yAxisLabels;
// 	private String[] methods;
// //    private Method method;
//
// 	private static class Trio<A, B, C> {
// 		A a;
// 		B b;
// 		C c;
//
// 		public Trio(A a, B b, C c) {
// 			super();
// 			this.a = a;
// 			this.b = b;
// 			this.c = c;
// 		}
//
// 	}
//
//     public GraphDrawerX(String[] methods, int maxSize, int trialAmount, int testCases) {
//     	collections = new HashSet<Collection<? extends Object>>();
//     	collectionMethods = new HashMap<String, HashMap<Collection<? extends Object>, Method>>();
//     	collectionData = new HashMap();
//     	collectionColors = new HashMap();
//     	this.maxSize = maxSize;
//     	this.trialAmount = trialAmount;
//     	this.testCases = testCases;
//     	maxTimeValue = 0L;
//     	xAxisLabels = new String[5];
//     	yAxisLabels = new String[5];
//     	this.methods = methods;
//     	scalar = Math.log10(maxSize) /testCases;
//     }
//
// //    public GraphDrawer(String[] methods, int maxSize, int trialAmount, int testCases) {
// //    	collectionData = new HashMap<String, HashMap<Collection<Object>, ArrayList<Long>>>();
// //    	methodCollections = new HashMap<String, HashMap<Collection<Object>,Method>>();
// //    	HashSet<Collection<Object>> collectionsWithMethod = new HashSet<Collection<Object>>();
// //    	for (String method : methods) {
// //    		HashMap<Collection<Object>, Method> collectionMethod = new HashMap<Collection<Object>, Method>();
// //    		methodCollections.put(method, collectionMethod);
// //    		collectionData.put(method, new HashMap<Collection<Object>, ArrayList<Long>>());
// //    		for (Collection<Object> c : Simulation.collections)
// //    			for (Method m : c.getClass().getMethods())
// //    				if (m.getName().equals(method)) {
// //    					collectionsWithMethod.add(c);
// //    					collectionData.get(method).put(c, new ArrayList<Long>(maxSize));
// //    					collectionMethod.put(c, m);
// //    					break;
// //    				}
// //    	}
// //    	int index = 0;
// //    	collectionColors = new HashMap<Collection<Object>, Color>();
// //    	for (Collection<Object> c : collectionsWithMethod) {
// ////    		for (Entry<String, HashMap<Collection<Object>, ArrayList<Long>>> k : collectionData.entrySet())
// ////    			collectionData.put(collectionsWithMethod.get(i), new ArrayList<Long>(maxSize));
// //    		collectionColors.put(c, new Color(Color.HSBtoRGB(index / collectionsWithMethod.size(), 1F, .6F)));
// //    		index++;
// //    	}
// //    	this.maxSize = maxSize;
// //    	this.testCases = testCases;
// //    	this.methods = methods;
// //    	xAxisLabels = new String[5];
// //    	for (int i = 0; i < xAxisLabels.length; i++)
// //    		xAxisLabels[i] = String.valueOf(scaleCaseSize(i * xAxisLabels.length / 5));
// //    	maxTimeValue = 100;
// //    	updateYAxisLabels();
// //    }
//
// //    public HashSet<Collection<Object>> getCollections() {
// //    	return collections;
// //    }
//
//     private void updateYAxisLabels() {
//     	for (int i = 0; i < yAxisLabels.length; i++)
//     		yAxisLabels[i] = String.valueOf(scaleCaseSize(i * maxTimeValue / 5));
//     }
//
//     private final double scalar;
//     private int scaleCaseSize(long caseSize) {
//     	return (int) (Math.pow(10, caseSize * scalar));
// //    	return (int) (Math.log(caseSize) * testCases / Math.log(maxSize));
//     }
//
// 	public synchronized void init() {
// 		for (String method : methods) {
// 			HashMap<Collection<? extends Object>, Method> collectionMethod = new HashMap<Collection<? extends Object>, Method>();
// 			for (Collection<? extends Object> c : Simulation.collections)
// 				for (Method m : c.getClass().getMethods())
// 					if (m.getName().equals(method)) {
// 						collections.add(c);
// 		    			collectionData.put(m, new ArrayList<Long>(maxSize));
// 		    			if (collectionData.get(m) != null)
// 		    				System.out.println("collision: " + m.getName() + " " + collectionMethod.get(c));
// 		    			collectionMethod.put(c, m);
// //						break;
// 					}
// 			collectionMethods.put(method, collectionMethod);
// 		}
// 		HashMap<Collection<? extends Object>, Float> collectionHues = new HashMap<Collection<? extends Object>, Float>();
// 		float hueShift = 0;
// 		for (Collection<? extends Object> collection : collections)
// 			collectionHues.put(collection, hueShift++ / collections.size());
// 		float satShift = 0;
// 		for (String method : methods)
// 			for (Collection<? extends Object> collection : collections)
// 				collectionColors.put(collectionMethods.get(method).get(collection),
// 						new Color(Color.HSBtoRGB(collectionHues.get(collection), .2f + .6f * satShift++ / methods.length, .6F)));
//     	for (int i = 0; i < xAxisLabels.length; i++)
//     		xAxisLabels[i] = String.valueOf(i * maxSize / 4);
//     	updateYAxisLabels();
//
//         ActionListener taskPerformer = new ActionListener() {
//             public void actionPerformed(ActionEvent evt) {
//                 repaint();
//             }
//         };
//         new Timer(DELAY, taskPerformer).start();
//     }
//
//     public void update(Graphics g) {
//         Graphics offgc;
//         Image offscreen = null;
//         Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
//
//         offscreen = createImage(d.width, d.height);
//         offgc = offscreen.getGraphics();
//
//         offgc.setColor(getBackground());
//         offgc.fillRect(0, 0, d.width, d.height);
//         offgc.setColor(getForeground());
//
//         paint(offgc);
//         g.drawImage(offscreen, 0, 0, this);
//     }
//
//     private final int leftMargin = 20;
//     private final int bottomMargin = 100;
//     private final int topMargin = 20;
//
// //    private HashMap<Collection<Object>, HashMap<String, Method>> collectionMethods;
// //    private HashMap<Method, ArrayList<Long> collectionData;
// //    private HashMap<Method, Color> collectionColors;
//
//     public void paint(Graphics g) {
//
//     	int height = getHeight();
//     	int width = getWidth();
//         Graphics2D g2 = (Graphics2D) g;
//         g2.drawLine(leftMargin, topMargin, leftMargin, height - bottomMargin);
//         g2.drawLine(leftMargin, height - bottomMargin, width - bottomMargin, height - bottomMargin); // TODO
//         for (int i = 0; i < xAxisLabels.length; i++) {
//
// //        	int loc = leftMargin + testCases * i * xAxisLabels.length / 4;
//         	int loc = leftMargin + i * maxSize / 4;
//         	g2.drawString(xAxisLabels[i], loc, height - bottomMargin + 20);
//         	g2.drawLine(loc, height - bottomMargin, loc, height - bottomMargin + 5);
//         }
//         for (int i = 0; i < yAxisLabels.length; i++) { // TODO
//         	int loc = 120 - 100 * i * yAxisLabels.length / 5;
// //        	g2.drawString(yAxisLabels[i], leftMargin - 10, loc);
//         	g2.drawLine(leftMargin - 5, loc, leftMargin, loc);
//         }
//
//         for (Collection<? extends Object> collection : collections)
//         	for (String method : methods) {
//         		Method m = collectionMethods.get(method).get(collection);
//         		if (m != null) {
// 	        		g2.setColor(collectionColors.get(m));
// 	        		ArrayList<Long> data = collectionData.get(m);
// 	        		for (int i = 0; i < data.size(); i++) {
// 	        			int value = (int) (height - bottomMargin - Math.toIntExact(data.get(i)
// 	        					* (height - bottomMargin - topMargin) / maxTimeValue));
// //	        			int value = (int) (bottomMargin - Math.toIntExact(data.get(i))
// //	        					* (height - bottomMargin - topMargin) / maxTimeValue);
// 	        			g2.drawLine(leftMargin + 1 + i, value, leftMargin + 1 + i, value);
// 	        		}
//         		}
//         	}
//
// //        for (Entry<String, HashMap<Collection<Object>, ArrayList<Long>>> entry: collectionData.entrySet()) {
// //	       	g2.setColor(collectionColors.get(entry.getKey()));
// //	        for (int i = 0; i < entry.getValue().size(); i++) {
// //	        	int v = Math.toIntExact(entry.getValue().get(i));
// //	        	g2.drawLine(leftMargin + 1 + i, v, leftMargin + 1 + i, v);
// //        	}
// //        }
//     }
//
// 	public void runTests() {
// 		for (int i = 0; i <= testCases; i++) {
// 			int collectionSize = scaleCaseSize(i);
// 			for (Entry<String, HashMap<Collection<? extends Object>, Method>> methodEntry : collectionMethods.entrySet()) {
// 				for (Entry<Collection<? extends Object>, Method> entry : methodEntry.getValue().entrySet()) {
// //					collectionSize
// 					Collection<? extends Object> collection = entry.getKey();
// 					Method method = entry.getValue();
// //					Parameter[] parameters = method.getParameters();
// 					Class<?>[] types = method.getParameterTypes();
// 					Object[] args = new Object[types.length];
// 					for (int j = 0; j < types.length; j++)
// 						try {
// 							args[j] = DUMMY;
// 									types[j].getConstructor().newInstance();
// 						} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
// 								| InvocationTargetException | NoSuchMethodException | SecurityException e) {
// 							e.printStackTrace();
// 						}
// 					long timeSum = 0;
// 					for (int j = 0; j < trialAmount; j++) {
// 						if (collection.size() != collectionSize)
// 							fixCollectionSize(collection, collectionSize);
// 						long startTime = System.nanoTime();
// 						try {
// 							method.invoke(collection, args);
// 							timeSum += System.nanoTime() - startTime;
// 	//						time -= System.nanoTime();
// 						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
// 							e.printStackTrace(); //remove method from everything?
// 						}
// 					}
// 					long time = timeSum / trialAmount;
// 					if (time > maxTimeValue) {
// 						maxTimeValue = time;
// 						updateYAxisLabels();
// 					}
// 					System.out.println("inv " + time + " " + collectionSize);
// 					collectionData.get(method).add(time);
// 				}
// 			}
// 		}
// 	}
//
// 	private final Generic DUMMY = new Generic(); // don't use with sets
//
// 	private void fixCollectionSize(Collection collection, int collectionSize) {
// 		//if collectionSize is less than 0, throw error. shouldn't happen.
// 		while (collection.size() > collectionSize)
// 			collection.remove(DUMMY);
// 		while (collection.size() < collectionSize)
// 			collection.add(DUMMY);
// 	}
//
// 	private static class Generic implements Comparable<Generic> {
//
// 		private final static Random RAND = new Random();
//
// 		@Override
// 		public int compareTo(Generic o) {
// 			return RAND.nextInt(3) - 1;
// 		}
//
// 	}
// //    public HashMap<String, HashMap<Collection<Object>, Method>> getCollectionMethods() {
// //    	return collectionMethods;
// //    }
// //
// //    public HashMap<Method, ArrayList<Long>> getCollectionData() {
// //    	return collectionData;
// //    }
// //
// //	public int getMaxSize() {
// //		return maxSize;
// //	}
// //
// //	public int getTrialAmount() {
// //		return trialAmount;
// //	}
// //
// //	public int getTestCases() {
// //		return testCases;
// //	}
//
// }
