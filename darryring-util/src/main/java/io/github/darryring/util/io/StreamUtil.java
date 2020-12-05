package io.github.darryring.util.io;

import io.github.darryring.util.string.StringUtil;

import java.io.*;

/**
 * @author darryring
 * @date 2017年7月7日 下午4:25:06
 *
 * @explain 流操作
 */
public class StreamUtil {

	// close OutputStream
	public static void close(OutputStream os) {
		if (null != os) {
			try {
				os.close();
			} catch (IOException e1) {
			}
		}
	}

	// close InputStream
	public static void close(InputStream is) {
		if (null != is) {
			try {
				is.close();
			} catch (IOException e1) {
			}
		}
	}

	// close Reader
	public static void close(Reader r) {
		if (null != r) {
			try {
				r.close();
			} catch (IOException e1) {
			}
		}
	}

	// close Writer
	public static void close(Writer w) {
		if (null != w) {
			try {
				w.close();
			} catch (IOException e1) {
			}
		}
	}

	// write by reader
	public static String getStringByReader(InputStream is, String encoding) {
		StringBuilder str_result = new StringBuilder();
		BufferedReader br = null;

		try {
			br = new BufferedReader(new InputStreamReader(is, encoding));
			String str_buf = null;
			while ((str_buf = br.readLine()) != null) {
				str_result.append(str_buf).append(StringUtil.split_line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(br);
		}
		return str_result.toString();
	}

	// write by byte
	public static String getStringByByte(InputStream is) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte b[] = new byte[1024];
		try {
			while (is.read(b) > 0) {
				baos.write(b);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(baos);
		}
		return baos.toString();
	}

}
