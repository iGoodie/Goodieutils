package igoodie.utils.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class ConsolePrinter {
	
	private static boolean debugMode = false; //False initially
	private static PrintStream consoleStream = System.out;
	private static PrintStream loggingStream = null;
	
	/* Setters */
	public static void toggleDebugMode() {
		debugMode = !debugMode;
	}
	
	public static void setDebugMode(boolean mode) {
		debugMode = mode;
	}
	
	public static void setLoggingStream(File file) {
		try {
			loggingStream = new PrintStream(file);
		} 
		catch (FileNotFoundException e) {
			ConsolePrinter.error("Input file cannot be found: %s", file.getAbsolutePath());
		}
	}
	
	public static void setLoggingStream(PrintStream out) {
		loggingStream = out;
	}
	
	public static void setConsoleStream(PrintStream out) {
		consoleStream = out;
	}
	
	/* Printing Methods */
	public static void warn(String msg, Object...args) {
		print(consoleStream, "[WARN]", msg, args);
		print(loggingStream, "[WARN]", msg, args);
	}
	
	public static void error(String msg, Object...args) {
		print(consoleStream, "[ERROR]", msg, args);
		print(loggingStream, "[ERROR]", msg, args);
	}
	
	public static void info(String msg, Object...args) {
		print(consoleStream, "[INFO]", msg, args);
		print(loggingStream, "[INFO]", msg, args);
	}
	
	public static void debug(String msg, Object...args) {
		if(!debugMode) return;
		print(consoleStream, "[DEBUG]", msg, args);
		print(loggingStream, "[DEBUG]", msg, args);
	}

	/* Helper Methods */
	private static void print(PrintStream stream, String prefix, String x, Object...args) {
		if(stream == null) return;
		if(args.length != 0) x = String.format(x, args);
		stream.print(prefix);
		stream.println(x);
	}
	
	/*public static void main(String[] args) { //Logger test
		ConsolePrinter.error("Some error msg");
		ConsolePrinter.debug("Some debug %s #%d", "msg", 1);
		ConsolePrinter.info("Some info msg");
		
		ConsolePrinter.toggleDebugMode();
		ConsolePrinter.debug("Some debug %s #%d", "msg", 2);
		
		ConsolePrinter.setLoggingStream(new File("C:/ASDF.txt"));
		ConsolePrinter.info("Some info msg");
		ConsolePrinter.info("Some info msg");
		ConsolePrinter.info("Some info msg");
	}*/
}
