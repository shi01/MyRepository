package com.per.application.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

	/**
	 * 将文件转换成InputStream
	 * 
	 * @param filePath
	 * @return
	 */
	public static InputStream file2InputStream(String filePath) {
		try {
			return new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 将文件转换成OutputStream
	 * 
	 * @param filePath
	 * @return
	 */
	public static OutputStream file2OutputStream(String filePath) {
		try {
			return new FileOutputStream(filePath);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 将文件转换成byte数组
	 * 
	 * @param filePath 文件File类 通过new File(文件路径)
	 * @return byte数组
	 */
	public static byte[] File2byte(File filePath) {
		byte[] buffer = null;
		try {
			FileInputStream fis = new FileInputStream(filePath);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	/**
	 * byte转File
	 * 
	 * @param b
	 * @return
	 */
	@SuppressWarnings("resource")
	public static File byte2File(byte[] b) {
		try {
			File file = new File("");
			OutputStream output = new FileOutputStream(file);
			BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
			bufferedOutput.write(b);
			return file;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * byte转InputStream
	 * 
	 * @param b
	 * @return
	 */
	public static InputStream byte2InputStream(byte[] b) {
		try {
			return new ByteArrayInputStream(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * InputStream转byte
	 * 
	 */
	public static final byte[] input2byte(InputStream inStream) throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = inStream.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		byte[] in2b = swapStream.toByteArray();
		return in2b;
	}

	public static String readFileContext(String filePath) {
		String result = "";
		try {
			File file = new File(filePath);
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				result += s;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getResoucesPath() {
		return Thread.currentThread().getContextClassLoader().getResource("").getPath();
	}
}
