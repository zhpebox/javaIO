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
 * compare io and nio <b>文件读取类</b><br />
 * 1、按字节读取文件内容<br />
 * 2、按字符读取文件内容<br />
 * 3、按行读取文件内容<br />
 * 
 * @author Administrator
 */
public class CompareFileIO {
	private static final String FILE_PATH = "E:/testFile/java.txt";
	private static final String FILE_OUT_PATH = "E:/testFile/java_out.txt";

	/**
	 * 以字节为单位读写文件内容
	 * 
	 * @param filePath
	 */
	public static void readFileByByte(String filePath, String outFile) {
		File file = new File(filePath);

		// 字节输出输入流的所有类的超类
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
	 * 以字符为单位读写文件内容
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

		// BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
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
	 * 使用 java.nio ByteBUffer字节将一个文件输出到另一个文件
	 * 
	 * @param filePath
	 * @param outFileStr
	 */
	public static void readFileByBybeBuffer(String filePath, String outFileStr) {
		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {
			// 获取源文件和目标文件的输入输出流
			fis = new FileInputStream(filePath);
			fos = new FileOutputStream(outFileStr);

			// 获取输入输出通道
			FileChannel fcIn = fis.getChannel();
			FileChannel fcOut = fos.getChannel();

			ByteBuffer buffer = ByteBuffer.allocate(1024);

			while (true) {
				// clear方法重设缓冲区，使它可以接受读入的数据
				buffer.clear();
				// 从输入通道中将数据读到缓冲区
				int r = fcIn.read(buffer);
				if (r == -1) {
					break;
				}

				// flip 方法让缓冲区可以将新读入的数据写入另一个通道
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
