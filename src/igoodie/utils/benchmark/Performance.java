package igoodie.utils.benchmark;

public final class Performance {
	
	/**
	 * BYTE_PER_MEGABYTE = 1024 * 1024 bytes = 1 MB
	 */
	public static final long BYTE_PER_MEGABYTE = 1024L * 1024L; //1MB = 1024*1024B
	
	private static final Runtime runtime_instance = Runtime.getRuntime();

	/**
	 * Returns a lowercase string representing the native operating system.
	 * @return One of {"windows", "mac", "solaris", "unix", "unknown"}
	 */
	public static String getOS() {
		String osname = System.getProperty("os.name").toLowerCase();
		if(osname.indexOf("win")>=0) return "windows";
		if(osname.indexOf("mac")>=0) return "mac";
		if(osname.indexOf("sunos")>=0) return "sularis";
		if(osname.indexOf("nix") + osname.indexOf("nux") + osname.indexOf("aix") + 3 != 0) return "unix";
		return "unknown";
	}
	
	/* Memory Related */
	/**
	 * Returns used memory in bytes. It uses <b><i>Runtime.getRuntime()</b></i>
	 * to determine used memory which is (totalMemory)-(freeMemory).
	 * @return Used memory in bytes
	 */
	public static long usedMemory() {
		return runtime_instance.totalMemory() - runtime_instance.freeMemory(); //bytes
	}
	
	/**
	 * Returns used memory in megabytes. It uses <b><i>Runtime.getRuntime()</b></i>
	 * to determine used memory which is (totalMemory)-(freeMemory)
	 * @return Used memory in megabytes
	 */
	public static long usedMemoryMB() {
		return toMegabytes(usedMemory());
	}

	/**
	 * Returns total memory in bytes. It uses <b><i>Runtime.getRuntime()</b></i>
	 * to determine total memory.
	 * @return Total memory in bytes
	 */
	public static long totalMemory() {
		return runtime_instance.totalMemory();
	}
	
	/**
	 * Returns total memory in megabytes. It uses <b><i>Runtime.getRuntime()</b></i>
	 * to determine total memory.
	 * @return Total memory in megabytes
	 */
	public static long totalMemoryMB() {
		return toMegabytes(totalMemory());
	}
	
	private static long toMegabytes(long bytes) {
		return bytes / BYTE_PER_MEGABYTE;
	}
	
	/* Benchmarking Methods */
	/**
	 * Executes given <b><i>Runnable</b></i> instance's <b><i>run()</b></i> method once 
	 * and returns past milliseconds.
	 * @param test Runnable instance to be tested
	 * @return Milliseconds spent executing given Runnable.
	 */
	public static long testTime(Runnable test) {
		return testTime(test, 1);
	}
	
	/**
	 * Executes given <b><i>Runnable</b></i> instance's <b><i>run()</b></i> method for given trial number times.
	 * In each trial, counts past time. Finally returns total past time in milliseconds.
	 * @param test Runnable instance to be tested
	 * @param trialNumber Trial number to be tested
	 * @return Total milliseconds spent executing given Runnable.
	 */
	public static long testTime(Runnable test, int trialNumber) {
		long totalTime=0;
		
		long t0, t1;
		for(int i=0; i<trialNumber; i++) {
			t0 = System.currentTimeMillis();
			test.run();
			t1 = System.currentTimeMillis();
			totalTime += (t1-t0); // += dt
		}
		
		return totalTime;
	}
	
	/**
	 * Executes given <b><i>Runnable</b></i> instance's <b><i>run()</b></i> method for given trial number times.
	 * In each trial, counts past time. Finally returns mean past time for one trial in milliseconds.
	 * @param test Runnable instance to be tested
	 * @param trialNumber Trial number to be tested
	 * @return Mean (Average) milliseconds spent executing given Runnable.
	 */
	public static long testTimeAvg(Runnable test, int trialNumber) {
		long totalTime=0;
		
		long t0, t1;
		for(int i=0; i<trialNumber; i++) {
			t0 = System.currentTimeMillis();
			test.run();
			t1 = System.currentTimeMillis();
			totalTime += (t1-t0); // += dt
		}
		
		return totalTime / trialNumber;
	}	
}
