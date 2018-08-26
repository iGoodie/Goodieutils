package com.programmer.igoodie.utils.io;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Properties;

import javax.imageio.ImageIO;

import com.programmer.igoodie.utils.log.ConsolePrinter;

public class FileUtils {
	
	/* External path & Static variables */
	/**
	 * File path seperator used in FileUtils
	 */
	public static final String SEPERATOR = File.separator;
	
	/**
	 * External data path used by <b><i>writeExternalFoo(..)/readExternalBar(..)</b></i> methods
	 */
	private static String externalDataPath = System.getProperty("user.dir") + SEPERATOR + "data";
	
	/**
	 * Replaces external data path used by <b><i>writeExternalFoo(..) / readExternalBar(..)</b></i> methods
	 * with given path.
	 * @param path New path of external data path
	 */
	public static void setExternalDataPath(String path) {
		externalDataPath = path;
	}
	
	/**
	 * Returns the external data path used by <b><i>writeExternalX(..) / readExternalX(..)</i></b> methods
	 * @return External data path
	 */
	public static String getExternalDataPath() {
		return externalDataPath;
	}
	
	public static File getExternalFile(String fileName) {
		return new File(externalDataPath + SEPERATOR + fileName);
	}
	
	/* String */
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
			file.getParentFile().mkdirs();
			if(!file.exists()) file.createNewFile();
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(str);
			bw.close();
		} catch (IOException e) {
			ConsolePrinter.error("An IO error occurred writing string: %s", str);
		}
	}

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
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(isr);
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

	/* Image */
	//TODO impl write methods

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

	/* Binary */
	/**
	 * Writes given binary to given path. Given path is parsed
	 * relative to the external data path.
	 * @param str Binary data to be written on given path
	 * @param externalPath Path relative to external data path
	 * @see {@link #setExternalDataPath(String)}
	 */
	public static void writeExternalBin(byte[] data, String externalPath) {
		writeBin(data, externalDataPath + SEPERATOR + externalPath);
	}
	
	/**
	 * Writes given binary to given file.
	 * @param str String data to be written on given path
	 * @param path Path to the file to be overwritten.
	 */
	public static void writeBin(byte[] data, String path) {
		writeBin(data, new File(path));
	}
	
	/**
	 * Writes given binary to given file.
	 * @param str String data to be written on given path
	 * @param file File to be overwritten.
	 */
	public static void writeBin(byte[] data, File file) {
		try {
			file.getParentFile().mkdirs();
			if(!file.exists()) file.createNewFile();
			Files.write(file.toPath(), data);
		} 
		catch (IOException e) {
			ConsolePrinter.error("An IO error occurred writing byte array: %s", NetUtils.asHexString(data));
		}
	}

	/**
	 * Reads binary from given file path. Given path is parsed
	 * relative to the external data path.
	 * @param externalPath Path relative to external data path
	 * @return Read byte data
	 * @see {@link #setExternalDataPath(String)}
	 */
	public static byte[] readExternalBin(String externalPath) {
		return readBin(externalDataPath + SEPERATOR + externalPath);
	}
	
	/**
	 * Reads binary from given file.
	 * @param path File to be read.
	 * @return Read byte data
	 */
	public static byte[] readBin(String path) {
		return readBin(new File(path));
	}
	
	/**
	 * Reads binary from given file..
	 * @param file File to be read.
	 * @return Read byte data
	 */
	public static byte[] readBin(File file) {
		try {
			return Files.readAllBytes(file.toPath());
		} 
		catch (IOException e) {
			return null;
		}
	}

	/* Object */
	
	/* Properties */
	public static void writeExternalProperties(Properties prop, String externalPath) {
		writeProperties(prop, new File(externalDataPath + SEPERATOR + externalPath));
	}
	
	public static void writeProperties(Properties prop, File file) {
		try {
			prop.store(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"), null);
		}
		catch(IOException e) {
			ConsolePrinter.error("An IO error occurred writing properties object: %o", prop);
		}
	}
	
	public static Properties readExternalProperties(String externalPath) {
		return readProperties(new File(externalDataPath + SEPERATOR + externalPath));
	}
	
	public static Properties readProperties(File file) {
		try {
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(file);
			prop.load(fis);
			return prop;
		}
		catch(IOException e) {
			return null;
		}
	}

	/* InputStream */
	public static InputStream readAsInputStream(File file) {
		try {
			return new FileInputStream(file);
		} 
		catch (FileNotFoundException e) {
			return null;
		}
	}
	
	public static InputStream readExternalAsInputStream(String externalPath) {
		return readAsInputStream(new File(externalDataPath + SEPERATOR + externalPath));
	}
}