package wh.code.java.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Logger;

public class FileUtils {
	
	private static Logger logger = Logger.getLogger(FileUtils.class.toString());
	
	// get all file paths
	public static ArrayList<String> getAllFilePath(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			return null;
		}
		ArrayList<String> allFilePath = new ArrayList<String>();
		File[] files = file.listFiles();
		for (File f : files) {
			if (!f.isDirectory()) {
				allFilePath.add(f.getPath());
			} else {
				for (String s : getAllFilePath(f.getAbsolutePath())) {
					allFilePath.add(s);
				}
			}
		}
		return allFilePath;
	}

	// delete file
	public boolean deleteFile(String filePath) {
		boolean flag = false;
		File file = new File(filePath);
		if (file.exists() && file.isFile()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	// delete directory
	public boolean deleteDirectory(String filePath) {
		boolean flag = false;
		File dirFile = new File(filePath);
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return flag;
		}
		File[] files = dirFile.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				flag = deleteFile(file.getAbsolutePath());
				if (!flag)
					break;
			} else {
				flag = deleteDirectory(file.getAbsolutePath());
				if (!flag) {
					break;
				}
			}

		}
		if (!flag) {
			return false;
		}
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	// delete folder or file
	public boolean deleteDirectoryOrFile(String filePath) {
		boolean flag = false;
		File file = new File(filePath);
		if (!file.exists()) {
			return flag;
		} else {
			if (file.isFile()) {
				return deleteFile(filePath);
			} else if (file.isDirectory()) {
				return deleteDirectory(filePath);
			}
		}
		return flag;
	}

	// read to buffer
	public static StringBuffer readToBuffer(String filePath) {
		StringBuffer stringBuffer = new StringBuffer();
		try {
			File file = new File(filePath);
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = bufferReader.readLine();
			while (line != null) {
				stringBuffer.append(line);
				line = bufferReader.readLine();
			}
			bufferReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringBuffer;
	}

	// inputstream to string
	public static String inputStreamToString(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}
	
	//save str to file
//	public static boolean saveFile(String str,String filePath,String fileType){
//		
//		try {
//            PrintWriter printWriter = new PrintWriter(new FileWriter(filePath+fileType));
//            printWriter.write(JSON.toJSONString(str));
//            printWriter.close();
//        } catch (IOException e) {
//            logger.warn("write file error", e);
//        }
//		
//		return true;
//	}

}
