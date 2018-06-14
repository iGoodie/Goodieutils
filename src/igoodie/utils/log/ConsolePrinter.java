package igoodie.utils.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class ConsolePrinter {
	
	/* Fields */
	private static boolean debugMode = false; //Debug:OFF initally
	private static PrintStream consoleStream = System.out;
	private static PrintStream loggingStream = null;
	
	/* Setters */
	/**
	 * Toggles debug mode for {@link #debug(String, Object...)}
	 */
	public static void toggleDebugMode() {
		debugMode = !debugMode;
	}
	
	/**
	 * Sets debug mode for {@link #debug(String, Object...)}
	 * @param mode Mode to be set
	 */
	public static void setDebugMode(boolean mode) {
		debugMode = mode;
	}
	
	/**
	 * Sets logging stream as given file
	 * @param file File to be set
	 */
	public static void setLoggingStream(File file) {
		try {
			loggingStream = new PrintStream(file);
		} 
		catch (FileNotFoundException e) {
			ConsolePrinter.error("Input file cannot be found: %s", file.getAbsolutePath());
		}
	}
	
	/**
	 * Sets logging stream as given PrintStream
	 * @param out PrintStream to be set
	 */
	public static void setLoggingStream(PrintStream out) {
		loggingStream = out;
	}
	
	/**
	 * Sets console stream as given PrintStream
	 * @param out PrintStream to be set
	 */
	public static void setConsoleStream(PrintStream out) {
		consoleStream = out;
	}
	
	/* Printing Methods */
	/**
	 * Prints given message or format with "[WARN]" prefix on 
	 * console stream and logging stream if possible.
	 * @param msg Message to be printed or format to be formatted with given args
	 * @param args Arguments to format given string
	 */
	public static void warn(String msg, Object...args) {
		print(consoleStream, "[WARN]", msg, args);
		print(loggingStream, "[WARN]", msg, args);
	}
	
	/**
	 * Prints given message or format with "[ERROR]" prefix on 
	 * console stream and logging stream if possible.
	 * @param msg Message to be printed or format to be formatted with given args
	 * @param args Arguments to format given string
	 */
	public static void error(String msg, Object...args) {
		print(consoleStream, "[ERROR]", msg, args);
		print(loggingStream, "[ERROR]", msg, args);
	}
	
	/**
	 * Prints given message or format with "[INFO]" prefix on 
	 * console stream and logging stream if possible.
	 * @param msg Message to be printed or format to be formatted with given args
	 * @param args Arguments to format given string
	 */
	public static void info(String msg, Object...args) {
		print(consoleStream, "[INFO]", msg, args);
		print(loggingStream, "[INFO]", msg, args);
	}
	
	/**
	 * Prints given message or format with "[DEBUG]" prefix on 
	 * console stream and logging stream if they're not null and also debug mode is true.
	 * @param msg Message to be printed or format to be formatted with given args
	 * @param args Arguments to format given string
	 * @see {@link #setDebugMode(boolean)}
	 * @see {@link #toggleDebugMode()}
	 */
	public static void debug(String msg, Object...args) {
		if(!debugMode) return;
		print(consoleStream, "[DEBUG]", msg, args);
		print(loggingStream, "[DEBUG]", msg, args);
	}

	/**
	 * Prints given message or format with "[DEBUG]" prefix on 
	 * console stream and logging stream if they're not null and given parameter allows. <br/>
	 * Enabled parameter overrides the debug mode
	 * @param enabled Message will be printed if this parameter is true
	 * @param msg Message to be printed or format to be formatted with given args
	 * @param args Arguments to format given string
	 * @see {@link #setDebugMode(boolean)}
	 * @see {@link #toggleDebugMode()}
	 */
	public static void debug(boolean enabled, String msg, Object...args) {
		if(!enabled) return;
		else debug(msg, args);
	}
	
	public static void suppressPrints(Runnable r) {
		PrintStream out = System.out;
		PrintStream nullPrinter = new PrintStream(new OutputStream(){public void write(int b) throws IOException {}});
		System.setOut(nullPrinter);
		r.run();
		System.setOut(out);
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
