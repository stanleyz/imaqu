/**
 * (c)Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.test.main;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * This class ...
 * 
 * @author <a href="mailto:phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
public class TestURLDecoder {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		String url = "/w/list_q.shtml?order=createdAt_desc&status=0&cid=1";
		String ENCODE = "UTF-8";
		url=URLEncoder.encode(url, ENCODE);
		System.out.println(url);
		url=URLDecoder.decode(url,ENCODE);
		System.out.println(url);
	}
}
