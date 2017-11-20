package test.api.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class FileUtility {
	
/*	public static void main(String [] args){
		 ArrayList<String> str= fileReader("images/valid1.txt");
		 System.out.println(str.size());
		 System.out.println(str.get(0));
	}*/
	
	public static void fileWriter(String content, String filePath, boolean appendContentFlag){
		try {
			File file = new File(filePath);
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), appendContentFlag);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			System.out.println("Completed file write operation at "+filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> fileReader(String filePath){
		BufferedReader br = null;
		ArrayList<String> fileStr=new ArrayList<String>();
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(filePath));

			while ((sCurrentLine = br.readLine()) != null) {
				fileStr.add((sCurrentLine));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return fileStr;
	}
}
