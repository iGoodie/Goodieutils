package igoodie.utils.io;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import igoodie.utils.log.ConsolePrinter;

public class FileUtils {
	
	/* External path & Static variables */
	/**
	 * File path seperator used in FileUtils
	 */
	public static final String SEPERATOR = File.pathSeparator;
	
	/**
	 * External data path used by <b><i>writeExternalFoo(..)/readExternalBar(..)</b></i> methods
	 */
	private static String externalDataPath = System.getProperty("user.dir") + SEPERATOR + "data";
	
	/**
	 * Replaces external data path used by <b><i>writeExternalFoo(..)/readExternalBar(..)</b></i> methods
	 * with given path.
	 * @param path New path of external data path
	 */
	public static void setExternalDataPath(String path) {
		externalDataPath = path;
	}
	
	/* Write string */
	/**
	 * Writes given string to given path. Given path is parsed
	 * relative to the external data path.
	 * @param str String data to be written on given path
	 * @param externalPath Path relative to external data path
	 * @see {@link #setExternalDataPath(String)}
	 */
	public static void writeExternalString(String str, String externalPath) {
		writeString(str, externalDataPath + SEPERATOR + externalPath);
	}
	
	/**
	 * Writes given string to given file path.
	 * @param str String data to be written on given path
	 * @param path File path to be written.
	 */
	public static void writeString(String str, String path) {
		writeString(str, new File(path));
	}
	
	/**
	 * Writes given string to given file.
	 * @param str String data to be written on given path
	 * @param file File to be overwritten.
	 */
	public static void writeString(String str, File file) {
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(str);
			bw.close();
		} catch (IOException e) {
			ConsolePrinter.error("An IO error occurred writing string: %s", str);
		}
	}
	
	/* Read String */
	/**
	 * Reads binary from given file path and returns it as a String. Given path is parsed
	 * relative to the external data path.
	 * @param externalPath Path relative to external data path
	 * @return Read String data
	 * @see {@link #setExternalDataPath(String)}
	 */
	public static String readExternalString(String externalPath) {
		return readString(externalDataPath + SEPERATOR + externalPath);
	}

	/**
	 * Reads binary from given file path and returns it as a String.
	 * @param path File path to be read.
	 * @return Read String data
	 */
	public static String readString(String path) {
		return readString(new File(path));
	}
	
	/**
	 * Reads binary from given file and returns it as a String.
	 * @param file File to be read.
	 * @return Read String data
	 */
	public static String readString(File file) {
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String str="", line;
			while((line=br.readLine()) != null) {
				str += line + "\n"; 
			}
			br.close();
			return str;
		}
		catch(FileNotFoundException e) {
			ConsolePrinter.error("File cannot be found: %s", file.getAbsolutePath());
			return null;
		}
		catch(IOException e) {
			ConsolePrinter.error("An IO exception occurred reading file: %s", file.getAbsolutePath());
			return null;
		}
	}

	/* Write Image */
	//TODO impl write methods
	
	/* Read Image */
	
	/**
	 * Reads binary from given file path and returns it as a Image. Given path is parsed
	 * relative to the external data path.
	 * @param externalPath Path relative to external data path
	 * @return Read Image data
	 * @see {@link #setExternalDataPath(String)}
	 */
	public static Image readExternalImage(String externalPath) {
		return readImage(externalDataPath + SEPERATOR + externalPath);
	}
	
	/**
	 * Reads binary from given file path and returns it as a Image.
	 * @param path File path to be read.
	 * @return Read Image data
	 */
	public static Image readImage(String path) {
		return readImage(new File(path));
	}
	
	/**
	 * Reads binary from given file and returns it as a Image.
	 * @param file File to be read.
	 * @return Read Image data
	 */
	public static Image readImage(File file) {
		try {
			return ImageIO.read(file);
		}
		catch (IOException e) {
			return null;
		}
	}
}