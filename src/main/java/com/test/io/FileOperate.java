package com.test.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileOperate {
	
	/**
	 * 以字节为单位读取文件内容
	 * 
	 * @param filePath
	 */
	public static void readFileByByte(String filePath) {
		File file = new File(filePath);

		// InputStream:此抽象类是表示字节输入流的所有类的超类
		InputStream is = null;

		try {
			is = new FileInputStream(file);
			int temp;
			// read():从输入流中读取数据的下一个字节
			while ((temp = is.read()) != -1) {
				System.out.write(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 以字符为单位读取文件内容
	 * 
	 * @param filePath
	 */
	public static void readFileByChar(String filePath) {
		File file = new File(filePath);
		// FileReader:用来读取字符文件的便捷类
		FileReader reader = null;

		try {
			reader = new FileReader(file);
			int temp;
			while ((temp = reader.read()) != -1) {
				if (((char) temp) != '\r') {
					System.out.println((char) temp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 以行为单位读取文件内容
	 * @param filePath
	 */
	public static void readFileByLine(String filePath){
		File file = new File(filePath);
		//BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取
		BufferedReader buf = null;
		
		//FileReader:用来读取字符文件的便捷类
		try {
			buf = new BufferedReader(new FileReader(file));
			//buf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String temp = null;
			while((temp = buf.readLine())!=null){
				System.out.println(temp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(buf != null){
				try {
					buf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
