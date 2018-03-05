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
	
	public static final String SEPERATOR = File.pathSeparator;
	
	private static String externalDataPath = System.getProperty("user.dir") + SEPERATOR + "data";
	
	public static void setExternalDataPath(String path) {
		externalDataPath = path;
	}
	
	/* Write string */
	public static void writeExternalString(String str, String externalPath) {
		writeString(str, externalDataPath + SEPERATOR + externalPath);
	}
	
	public static void writeString(String str, String path) {
		writeString(str, new File(path));
	}
	
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
	public static String readExternalString(String externalPath) {
		return readString(externalDataPath + SEPERATOR + externalPath);
	}

	public static String readString(String path) {
		return readString(new File(path));
	}
	
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
	public static Image readExternalImage(String externalPath) {
		return readImage(externalDataPath + SEPERATOR + externalPath);
	}
	
	public static Image readImage(String path) {
		return readImage(new File(path));
	}
	
	public static Image readImage(File file) {
		try {
			return ImageIO.read(file);
		}
		catch (IOException e) {
			return null;
		}
	}
}