/**
 * (c)Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.action.u;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import net.ityin.imaqu.action.GenericAction;
import net.ityin.imaqu.model.Answer;
import net.ityin.imaqu.model.Question;
import net.ityin.imaqu.model.User;
import net.ityin.imaqu.service.UserManager;
import net.ityin.imaqu.util.SystemConstant;
import net.ityin.imaqu.util.captcha.CaptchaUtil;
import net.ityin.imaqu.util.spring.CaptchaErrorException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * This class ...
 * 
 * @author <a href="mailto:phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
@Controller
@Scope("prototype")
public class UserAction extends GenericAction {
	private static final long serialVersionUID = 2513267066323904419L;
	private User user;
	// uid used by request parameter.
	private String u;
	private Byte status = null;
	private Boolean recommended = null;
	private List<Question> questions;
	private List<Answer> answers;
	private int first = 0;
	private int max = SystemConstant.MAX_RESULTS_2TEN;
	@Autowired
	private UserManager userManager;
	private boolean captcha = false;
	private String confirmPassword;
	private String captchaAnswer;
	private List<User> users;
	private Map<Date, Question> activity;

	public String login() {
		HttpSession session = getSession();
		Object username = session
				.getAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY);
		if (username != null) {
			if (user == null) {
				user = new User();
			}
			user.setNickname(username.toString());
		}
		boolean err = session.getAttribute(SystemConstant.LOGIN_ERROR) != null;
		AuthenticationException ex = (AuthenticationException) session
				.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

		if (err) {
			session.removeAttribute(SystemConstant.LOGIN_ERROR);
			if (ex instanceof UsernameNotFoundException) {
				errMsg.put("username", getText("login.err.usernamenotfound"));
			}
			if (ex instanceof BadCredentialsException) {
				errMsg.put("password", getText("login.err.passwordincorrect"));
			}
			if (ex instanceof CaptchaErrorException) {
				errMsg.put("captcha", getText("err.captchaincorrect"));
			}
			if (ex instanceof LockedException) {
				errMsg.put("username", "你的帐户已被禁言，请与管理员联系！");
			}
		} else {
			errMsg.clear();
		}

		/*
		 * Set up captcha switch to on or off.
		 */
		if (ex != null
				&& (Integer) session
						.getAttribute(SystemConstant.LOGIN_ERROR_TIMES) > SystemConstant.CAPTCHA_THRESHOLD) {
			captcha = true;
		} else {
			captcha = false;
		}

		return ActionSupport.INPUT;
	}

	public String findPassword() {
		if (getMethod().equalsIgnoreCase(GET_REQUEST)) {
			errMsg.clear();
		} else {
			if (user == null || user.getNickname() == null
					|| user.getNickname().length() == 0) {
				errMsg.put("user.nickname", "用户名不能为空！");
			} else {
				if (!isNicknameExisted()) {
					errMsg.put("user.nickname", "用户名不存在！");
				} else {
					errMsg.clear();
					first = 1;
				}
			}
		}

		return SUCCESS;
	}

	public String signup() {
		if (signupValidate()) {
			return INPUT;
		}

		String oldPassword = user.getPassword();
		userManager.createUser(user);

		user = userManager.autoLogin(user.getNickname(), oldPassword,
				getRequest());
		if (user == null) {
			return LOGIN;
		}

		return SUCCESS;
	}

	public String updateInfo() {
		// validate if the user if the owner
		if (!user.equals(getAuthenticatedUser())) {
			return LOGIN;
		}

		// validate the input.
		errMsg.clear();
		String password = user.getPassword();
		if (password != null && password.length() > 0) {
			if (password.length() > 18 || password.length() < 6) {
				errMsg.put("user.password", "密码为6到18位数字和字母组成！");
			}
			if (confirmPassword == null || confirmPassword.length() == 0
					|| !password.equals(confirmPassword)) {
				errMsg.put("confirmPassword", "两次密码输入不符");
			}
		}
		if (!user.getEmail().matches(
				"^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-]+)+")) {
			errMsg.put("user.email", "邮箱格式不对!");
		}

		if (errMsg.size() > 0) {
			User u = user;
			user = userManager.get(u.getId());
			user.setEmail(u.getEmail());
			user.setPassword(u.getPassword());
			return INPUT;
		} else {
			user = userManager.createUser(user);
			errMsg.clear();
			errMsg.put("user.update.scucess", "更新成功！");
		}
		return SUCCESS;
	}

	private boolean signupValidate() {
		if (user.getNickname() == null || user.getNickname().length() == 0) {
			errMsg.put("user.nickname", "用户名不能为空！");
		}
		String password = user.getPassword();
		if (password.length() > 18 || password.length() < 6) {
			errMsg.put("user.password", "密码为6到18位数字和字母组成！");
		}
		if (confirmPassword == null || confirmPassword.length() == 0
				|| !password.equals(confirmPassword)) {
			errMsg.put("confirmPassword", "两次密码输入不符");
		}
		if (!user.getEmail().matches(
				"^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-]+)+")) {
			errMsg.put("user.email", "邮箱格式不对!");
		}
		if (!CaptchaUtil.validateSimpleCaptcha(ServletActionContext
				.getRequest())) {
			errMsg.put("captcha", "验证码不对！");
		}

		return errMsg.size() != 0;
	}

	public String getTopAsker() {
		users = userManager.getTopAsker(new Date(0),
				SystemConstant.MAX_RESULTS_TEN);
		return SUCCESS;
	}

	public String getTopReplier() {
		users = userManager.getTopReplier(new Date(0),
				SystemConstant.MAX_RESULTS_TEN);
		return SUCCESS;
	}

	public String get() {
		user = userManager.get(u);
		return SUCCESS;
	}

	public String ucIndex() {
		if (u == null || u.trim().length() == 0) {
			user = getAuthenticatedUser();
			if (user != null) {
				u = user.getId();
			}
		}
		if (u == null || u.trim().length() == 0) {
			return NONE;
		}
		user = userManager.get(u);
		if (activity == null) {
			activity = new TreeMap<Date, Question>();
		}
		for (Answer a : userManager.getAnswersByUser(user, null, 0, 3)) {
			activity.put(a.getCreatedAt(), a.getQuestion());
		}
		for (Question q : userManager
				.getQuestionsByUser(user, null, null, 0, 3)) {
			activity.put(q.getCreatedAt(), q);
		}
		return SUCCESS;
	}

	public String getQuestionsByUser() {
		user = userManager.get(u);
		questions = userManager.getQuestionsByUser(user, status, recommended,
				first, max);
		return SUCCESS;
	}

	public String getAnswersByUser() {
		user = userManager.get(u);
		answers = userManager.getAnswersByUser(user, status, first, max);
		return SUCCESS;
	}

	public String getUsersOrder() {
		user = userManager.get(u);
		users = userManager.getTopUsers(first, SystemConstant.MAX_RESULTS_TEN);
		max = userManager.getUserOrder(user, first);
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isCaptcha() {
		return captcha;
	}

	public void setCaptcha(boolean captcha) {
		this.captcha = captcha;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@JSON(name = "nicknameExisted")
	public boolean isNicknameExisted() {
		String s = user.getNickname().trim();
		if (s.length() > 0) {
			return userManager.checkNicknameExistence(s);
		} else {
			return true;
		}
	}

	@JSON(name = "captchaAnswerCorrected")
	public boolean isCaptchaAnswerCorrected() {
		return CaptchaUtil.validateSimpleCaptcha(getRequest(), captchaAnswer);
	}

	public String getCaptchaAnswer() {
		return captchaAnswer;
	}

	public void setCaptchaAnswer(String captchaAnswer) {
		this.captchaAnswer = captchaAnswer;
	}

	public List<User> getUsers() {
		return users;
	}

	public String getU() {
		return u;
	}

	public void setU(String u) {
		this.u = u;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Boolean getRecommended() {
		return recommended;
	}

	public void setRecommended(Boolean recommended) {
		this.recommended = recommended;
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

	public List<Question> getQuestions() {
		return questions;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public Map<Date, Question> getActivity() {
		return activity;
	}
}
