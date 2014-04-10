/**
 * (c)Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.action.m;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.ityin.imaqu.action.GenericAction;
import net.ityin.imaqu.model.proxy.UserProxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;

/**
 * This class ...
 * 
 * @author <a href="mailto:phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
@Controller
@Scope("prototype")
public class SessionMAction extends GenericAction {
	private static final long serialVersionUID = 4282702922129869284L;

	@Autowired
	private SessionRegistry sessionRegistry;
	private List<Object> users;
	private String name;
	private InputStream inputStream;

	public String getOnlineUsers() {
		List<Object> onlineUsers = sessionRegistry.getAllPrincipals();
		users = new ArrayList<Object>();

		if (name != null && name.trim().length() > 0) {
			for (Object u : onlineUsers) {
				if (((UserProxy) u).getUser().getNickname().indexOf(name) >= 0) {
					users.addAll(sessionRegistry.getAllSessions(u, false));
				}
			}
		} else {
			for (Object u : onlineUsers) {
				users.addAll(sessionRegistry.getAllSessions(u, false));
			}
		}

		return SUCCESS;
	}

	public String kickoutUsers() {
		String[] ids = null;
		if (name != null && name.length() > 0) {
			ids = name.split(",");
		}

		if (ids != null) {
			for (String id : ids) {
				sessionRegistry.getSessionInformation(id).expireNow();
			}
		}

		return SUCCESS;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Object> getUsers() {
		return users;
	}

	public InputStream getInputStream() {
		if (inputStream == null) {
			inputStream = new ByteArrayInputStream(SUCCESS.getBytes());
		}
		return inputStream;
	}
}
