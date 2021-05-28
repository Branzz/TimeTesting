package bran.main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import bran.threads.Threading;

public class Main {

	@SuppressWarnings({ "deprecation" })
	public static void main(String[] args) {

		Threading.startThreads();

	}
	
}

//		try {
//			Method m = null;
//			for (Method m0 : X.class.getMethods()) {
//				if (m0.getName().equals("a")) {
//					m = m0;
//					break;
//				}
//			}
//			m.invoke(new Z(), new Object[] {});
//		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
//				| SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		Simulation s = new Simulation();
//		drawing.

//class X {
//	
//	public void a() {
//		System.out.println("X");
//	}

//	public static int z(int b) {
//		return 1;
//	}
//}

//class Z extends X {
//
//	public void a() {
//		System.out.println("Z");
//	}
//	
//}

//for (int i = 0; i <= 101; i += 100 / 4)
//	System.out.println(i + " " + Math.log(i) + " " + Math.log(10_000) + " " + (Math.log(i) * 100 / Math.log(10_000)));
//for (int i = 1; i <= 100; i += 100 / 5)
//	System.out.println(i + " " + Math.log10(i) + " " + Math.log10(10_000) + " " + (Math.log10(i) * 100 / Math.log10(10_000)));
//for (int i = 0; i <= 100; i += 100 / 4)
//	System.out.println((Math.pow(10, i * Math.log10(10_000) / 100)));

//		ArrayList<Collection<Object>> collections = new ArrayList<Collection<Object>>();
//		for (Collection<Object> c : Simulation.collections)
//			for (Method m : c.getClass().getMethods())
//				if (m.getName().equals("add")) {
//					collections.add(c);
//					System.out.println(m.toString());
//					break;
//				}
//		Method method = null;
//		for (Method m : collections.get(0).getClass().getMethods())
//			if (m.getName().equals("add")) {
//				method = m;
//				break;
//			}
//		System.out.println("\n" + method.toString());
//
//		try {
//			for (Collection<Object> c : Simulation.collections)
//				method.invoke(c, new Object());
//		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//			e.printStackTrace();
//		}
