package com.test.nio;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * compare io and nio <b>�ļ���ȡ��</b><br />
 * 1�����ֽڶ�ȡ�ļ�����<br />
 * 2�����ַ���ȡ�ļ�����<br />
 * 3�����ж�ȡ�ļ�����<br />
 * 
 * @author Administrator
 */
public class CompareFileIO {
	private static final String FILE_PATH = "E:/testFile/java.txt";
	private static final String FILE_OUT_PATH = "E:/testFile/java_out.txt";

	/**
	 * ���ֽ�Ϊ��λ��д�ļ�����
	 * 
	 * @param filePath
	 */
	public static void readFileByByte(String filePath, String outFile) {
		File file = new File(filePath);

		// �ֽ������������������ĳ���
		InputStream is = null;
		OutputStream os = null;

		try {
			is = new FileInputStream(file);
			os = new FileOutputStream(outFile);

			int temp;
			while ((temp = is.read()) != -1) {
				os.write(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ���ַ�Ϊ��λ��д�ļ�����
	 * 
	 * @param filePath
	 */
	public static void readFileByChar(String filePath, String outFile) {
		File file = new File(filePath);

		FileReader reader = null;
		FileWriter writer = null;

		try {
			reader = new FileReader(file);
			writer = new FileWriter(new File(outFile));
			
			int temp;
			while ((temp = reader.read()) != -1) {
				writer.write(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null && writer != null) {
				try {
					reader.close();
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void readFileByLine(String filePath, String outFile) {
		File file = new File(filePath);

		// BufferedReader:���ַ��������ж�ȡ�ı�����������ַ����Ӷ�ʵ���ַ���������еĸ�Ч��ȡ��
		BufferedReader bufReader = null;
		BufferedWriter bufWriter = null;

		try {
			bufReader = new BufferedReader(new FileReader(file));
			bufWriter = new BufferedWriter(new FileWriter(outFile));

			String temp = null;
			while ((temp = bufReader.readLine()) != null) {
				bufWriter.write(temp);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (bufReader != null && bufWriter != null) {
				try {
					bufReader.close();
					bufWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * ʹ�� java.nio ByteBUffer�ֽڽ�һ���ļ��������һ���ļ�
	 * 
	 * @param filePath
	 * @param outFileStr
	 */
	public static void readFileByBybeBuffer(String filePath, String outFileStr) {
		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {
			// ��ȡԴ�ļ���Ŀ���ļ������������
			fis = new FileInputStream(filePath);
			fos = new FileOutputStream(outFileStr);

			// ��ȡ�������ͨ��
			FileChannel fcIn = fis.getChannel();
			FileChannel fcOut = fos.getChannel();

			ByteBuffer buffer = ByteBuffer.allocate(1024);

			while (true) {
				// clear�������軺������ʹ�����Խ��ܶ��������
				buffer.clear();
				// ������ͨ���н����ݶ���������
				int r = fcIn.read(buffer);
				if (r == -1) {
					break;
				}

				// flip �����û��������Խ��¶��������д����һ��ͨ��
				buffer.flip();
				fcOut.write(buffer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null && fos != null) {
				try {
					fis.close();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static long getTime() {
		return System.currentTimeMillis();
	}

	public void  TestMethod() {
		long time1 = getTime();
		// readFileByByte(FILE_PATH);// 8734,8281,8000,7781,8047
		// readFileByCharacter(FILE_PATH);// 734, 437, 437, 438, 422
		// readFileByLine(FILE_PATH);// 110, 94, 94, 110, 93
		readFileByBybeBuffer(FILE_PATH, FILE_OUT_PATH);// 125, 78, 62, 78, 62
		long time2 = getTime();
		System.out.println(time2 - time1);
		assertTrue("someLibraryMethod should return 'true'", (time2 - time1)>0);
	}
}
