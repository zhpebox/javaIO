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
	 * ���ֽ�Ϊ��λ��ȡ�ļ�����
	 * 
	 * @param filePath
	 */
	public static void readFileByByte(String filePath) {
		File file = new File(filePath);

		// InputStream:�˳������Ǳ�ʾ�ֽ���������������ĳ���
		InputStream is = null;

		try {
			is = new FileInputStream(file);
			int temp;
			// read():���������ж�ȡ���ݵ���һ���ֽ�
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
	 * ���ַ�Ϊ��λ��ȡ�ļ�����
	 * 
	 * @param filePath
	 */
	public static void readFileByChar(String filePath) {
		File file = new File(filePath);
		// FileReader:������ȡ�ַ��ļ��ı����
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
	 * ����Ϊ��λ��ȡ�ļ�����
	 * @param filePath
	 */
	public static void readFileByLine(String filePath){
		File file = new File(filePath);
		//BufferedReader:���ַ��������ж�ȡ�ı�����������ַ����Ӷ�ʵ���ַ���������еĸ�Ч��ȡ
		BufferedReader buf = null;
		
		//FileReader:������ȡ�ַ��ļ��ı����
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
