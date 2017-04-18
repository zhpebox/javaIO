package com.test.nio;

public class NIOMain {
	private static final String FILE_PATH = "E:/testFile/java.txt";
	private static final String FILE_OUT_PATH = "E:/testFile/java_out.txt";
	public static void main(String[] args) {
		 long time1 = CompareFileIO.getTime() ;
	        // readFileByByte(FILE_PATH);// 8734,8281,8000,7781,8047
	        // readFileByCharacter(FILE_PATH);// 734, 437, 437, 438, 422
	        // readFileByLine(FILE_PATH);// 110, 94,  94,  110, 93
		 	CompareFileIO.readFileByBybeBuffer(FILE_PATH,FILE_OUT_PATH);// 125, 78,  62,  78, 62
	        long time2 = CompareFileIO.getTime() ;
	        System.out.println(time2-time1);
	}
}
