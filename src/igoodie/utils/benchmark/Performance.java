package igoodie.utils.benchmark;

public final class Performance {
	public static final long BYTE_PER_MEGABYTE = 1024L * 1024L; //1MB = 1024*1024B
	
	private static final Runtime runtime_instance = Runtime.getRuntime();
	
	public static String getOS() {
		String osname = System.getProperty("os.name").toLowerCase();
		if(osname.indexOf("win")>=0) return "windows";
		if(osname.indexOf("mac")>=0) return "mac";
		if(osname.indexOf("sunos")>=0) return "windows";
		if(osname.indexOf("nix") + osname.indexOf("nux") + osname.indexOf("aix") + 3 != 0) return "unix";
		return "unknown";
	}
	
	/* Memory Related */
	public static long usedMemory() {
		return runtime_instance.totalMemory() - runtime_instance.freeMemory(); //bytes
	}
	
	public static long usedMemoryMB() {
		return toMegabytes(usedMemory());
	}

	public static long totalMemory() {
		return runtime_instance.totalMemory();
	}
	
	public static long totalMemoryMB() {
		return toMegabytes(totalMemory());
	}
	
	private static long toMegabytes(long bytes) {
		return bytes / BYTE_PER_MEGABYTE;
	}
	
	/* Benchmarking Methods */
	public static long testTime(Runnable test) {
		return testTime(test, 1);
	}
	
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
