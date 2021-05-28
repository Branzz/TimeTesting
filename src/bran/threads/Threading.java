package bran.threads;

import bran.main.GraphDrawer;
import bran.main.Run;

public class Threading {

	public static final GraphDrawer graphDrawer = Run.run();
	public static final Thread DRAWING = new Thread(() -> graphDrawer.start());
	public static final Thread CALCULATING = new Thread(() -> graphDrawer.runTests());

	public static void startThreads() {
		Threading.DRAWING.start();
		Threading.CALCULATING.start();
	}

}
