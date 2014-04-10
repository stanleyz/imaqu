/**
 * (c)Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.action.m;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.ityin.imaqu.action.GenericAction;
import net.ityin.imaqu.model.User;
import net.ityin.imaqu.service.UserManager;
import net.ityin.imaqu.util.SystemConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * This class ...
 * 
 * @author <a href="mailto:phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
@Controller
@Scope("prototype")
public class UserMAction extends GenericAction {
	private static final long serialVersionUID = -8403555001246499188L;
	private List<User> users;
	private String name;
	private int n;
	@Autowired
	private UserManager userManager;
	private int first = 0;
	private int max = SystemConstant.MAX_RESULTS_2TEN;
	private String field;
	private String order;
	private String days;
	private boolean active;
	private InputStream inputStream;

	public String findUsers() {
		User user = new User();
		if (name != null && name.trim().length() > 0
				&& (!name.trim().equals("*"))) {
			user.setNickname(name);
		}
		if (n == SystemConstant.ACTIVE_TRUE) {
			user.setBlocked(true);
			user.setBlockedTill(new Date());
		} else if (n == SystemConstant.ACTIVE_FALSE) {
			user.setBlocked(false);
			user.setBlockedTill(new Date());
		}

		StringBuilder s = new StringBuilder();
		if (field != null && field.trim().length() > 0) {
			s.append(field);
			s.append("|");
			s.append(order);
		}
		users = userManager.getUsersByCondition(user, s.toString(), first, max);

		return SUCCESS;
	}

	/**
	 * @return
	 */
	public String switchUserState() {
		String[] ids = null;
		if (name != null && name.length() > 0) {
			ids = name.split(",");
		}

		if (ids != null) {
			if (!active) {
				Calendar c = Calendar.getInstance();
				// This is the biggest date MySQL accept: 9999-12-31 23:59.
				// And actually it's the indeed big time we can use to represent
				// "forever".
				Calendar biggestDate = Calendar.getInstance();
				biggestDate.set(9999, 11, 31, 23, 59);
				try {
					c.add(Calendar.DAY_OF_YEAR, Integer.parseInt(days));
					if (c.after(biggestDate)) {
						c = biggestDate;
					}
				} catch (Exception e) {
					c.set(9999, 11, 31, 23, 59);
				}

				userManager.switchUserState(ids, c.getTime(), active);
			} else {
				userManager.switchUserState(ids, null, active);
			}

		}

		return SUCCESS;
	}

	public String deleteUsers() {
		String[] ids = null;
		if (name != null && name.length() > 0) {
			ids = name.split(",");
		}

		if (ids != null) {
			boolean cascadeQ = ("q".equals(days) ? true : false);
			userManager.deleteUsers(ids, cascadeQ, active);
		}

		return SUCCESS;
	}
	
	public String switchRole() {
		String[] ids = null;
		if (name != null && name.length() > 0) {
			ids = name.split(",");
		}

		if (ids != null) {
			userManager.switchRole(ids);
		}

		return SUCCESS;
	}

	public List<User> getUsers() {
		return users;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public InputStream getInputStream() {
		if (inputStream == null) {
			inputStream = new ByteArrayInputStream(SUCCESS.getBytes());
		}
		return inputStream;
	}
}
