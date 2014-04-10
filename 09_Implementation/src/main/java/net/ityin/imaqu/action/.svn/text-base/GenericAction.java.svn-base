/**
 * (c)Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.action;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ityin.imaqu.model.User;
import net.ityin.imaqu.model.proxy.UserProxy;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.opensymphony.xwork2.ActionSupport;

/**
 * This class ...
 * 
 * @author <a href="mailto:phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
public class GenericAction extends ActionSupport {
	private static final long serialVersionUID = -5728687562852339172L;
	protected Map<String, String> errMsg = new LinkedHashMap<String, String>();
	protected String loginMsg = "";
	protected String GET_REQUEST = "GET";

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	protected HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	public User getAuthenticatedUser() {
		Object o = SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (o instanceof UserProxy) {
			return ((UserProxy) o).getUser();
		} else {
			return null;
		}
	}

	public String getMethod() {
		return ServletActionContext.getRequest().getMethod();
	}

	public Map<String, String> getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(Map<String, String> errMsg) {
		this.errMsg = errMsg;
	}

	public String getLoginMsg() {
		return loginMsg;
	}

	public void setLoginMsg(String loginMsg) {
		this.loginMsg = loginMsg;
	}
}
