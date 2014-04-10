/**
 * (c)Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * This class ...
 * 
 * @author <a href="mailto:phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
@Controller
public class UtilAction {
	private int sec;
	private InputStream inputStream;

	public String sleep2JS() {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
		}
		
		return ActionSupport.SUCCESS;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}

	public InputStream getInputStream() {
		if(inputStream==null) {
			inputStream=new ByteArrayInputStream(ActionSupport.SUCCESS.getBytes());
		}
		return inputStream;
	}
}
