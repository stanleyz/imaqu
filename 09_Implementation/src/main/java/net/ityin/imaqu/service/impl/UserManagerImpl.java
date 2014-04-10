/**
 * Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.ityin.imaqu.dao.UserDao;
import net.ityin.imaqu.model.Answer;
import net.ityin.imaqu.model.Question;
import net.ityin.imaqu.model.Role;
import net.ityin.imaqu.model.User;
import net.ityin.imaqu.model.proxy.UserProxy;
import net.ityin.imaqu.service.AnswerManager;
import net.ityin.imaqu.service.QuestionManager;
import net.ityin.imaqu.service.RoleManager;
import net.ityin.imaqu.service.UserManager;
import net.ityin.imaqu.util.SystemConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

/**
 * @author <a href="phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
@Service(value = "userManager")
public class UserManagerImpl extends GenericManagerImpl<User, String> implements
		UserManager {
	private UserDao userDao;
	@Autowired
	private RoleManager roleManager;
	@Autowired
	private PasswordEncoder md5PasswordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private SessionRegistry sessionRegistry;
	@Autowired
	private QuestionManager questionManager;
	@Autowired
	private AnswerManager answerManager;

	@Autowired
	public UserManagerImpl(UserDao userDao) {
		super(userDao);
		this.userDao = userDao;
	}

	public boolean checkNicknameExistence(String nickname) {
		return userDao.findByNickname(nickname) != null;
	}

	public User createUser(User user) {
		// update a user's info
		if (user.getId() != null) {
			User u = userDao.get(user.getId());
			boolean updated = false;
			if (!user.getEmail().equals(u.getEmail())) {
				u.setEmail(user.getEmail());
				updated = true;
			}
			if (user.getPassword() != null && user.getPassword().length() > 0) {
				String pass = encodePassword(user);
				if (!pass.equals(u.getPassword())) {
					u.setPassword(pass);
					updated = true;
				}
			}
			if (updated) {
				return userDao.save(u);
			}
			return u;
		}

		// create a new user.
		user.setRole(roleManager.getGeneralRole());
		user.setCreatedAt(new Date());
		/*
		 * Set the encoded password
		 */
		user.setPassword(encodePassword(user));

		return userDao.save(user);
	}

	/**
	 * This method is used by {@code}createUser{@code} to sign in the user
	 * automatically.
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public User autoLogin(String username, String password,
			HttpServletRequest request) {
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					username, password);
			Authentication authentication = authenticationManager
					.authenticate(token);

			HttpSession session = request.getSession();
			session.removeAttribute(SystemConstant.LOGIN_ERROR);
			session.removeAttribute(SystemConstant.LOGIN_ERROR_TIMES);

			/*
			 * Update the login info in database.
			 */
			User user = userDao.get(((UserProxy) authentication.getPrincipal())
					.getUser().getId(), true);
			user.setScore(SystemConstant.USER_SCORE_TEN);
			user.setLastLogin(new Date());
			user.setLastIP(request.getRemoteAddr());
			SecurityContextHolder.getContext()
					.setAuthentication(authentication);
			setAuthentication(user);
			userDao.save(user);

			return user;
		} catch (Exception e) {
			SecurityContextHolder.getContext().setAuthentication(null);
			return null;
		}
	}

	private String encodePassword(User user) {
		return md5PasswordEncoder.encodePassword(user.getPassword(),
				user.getNickname());
	}

	public List<User> getTopAsker(Date date, int maxResults) {
		return userDao.getTopAsker(date, maxResults);
	}

	public List<User> getTopReplier(Date date, int maxResults) {
		return userDao.getTopReplier(date, maxResults);
	}

	public void setAuthentication(User user) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (authentication != null) {
			((UserProxy) authentication.getPrincipal()).setUser(user);
			SecurityContextHolder.getContext()
					.setAuthentication(authentication);
		}
	}

	public List<Question> getQuestionsByUser(User user, Byte status,
			Boolean recommended, int firstResult, int maxResults) {
		return userDao.getQuestionsByUser(user, status, recommended,
				firstResult, maxResults);
	}

	public List<Answer> getAnswersByUser(User user, Byte status,
			int firstResult, int maxResults) {
		return userDao.getAnswersByUser(user, status, firstResult, maxResults);
	}

	public List<User> getTopUsers(int orderProperty, int maxResults) {
		return userDao.getTopUsers(orderProperty, maxResults);
	}

	public int getUserOrder(User u, int orderProperty) {
		return userDao.getUserOrder(u, orderProperty);
	}

	public List<User> getUsersByCondition(User user, String order,
			int firstResult, int maxResults) {
		return userDao
				.getUsersByCondition(user, order, firstResult, maxResults);
	}

	public void switchUserState(String[] ids, Date time, boolean active) {
		User u;

		for (String id : ids) {
			u = userDao.get(id);
			userDao.switchUserState(u, time, active);
		}

		if (!active) {
			kickUsers(ids);
		}
	}

	public void kickUsers(String[] userIds) {
		List<String> ids = Arrays.asList(userIds);
		List<SessionInformation> sis;
		List<Object> principals = sessionRegistry.getAllPrincipals();
		if (principals != null && principals.size() > 0) {
			for (Object principal : principals) {
				if (ids.contains(((UserProxy) principal).getUser().getId())) {
					sis = sessionRegistry.getAllSessions(principal, false);
					if (sis != null && sis.size() > 0) {
						for (SessionInformation si : sis) {
							si.expireNow();
						}
					}
				}
			}
		}
	}

	public void deleteUsers(String[] ids, boolean cascadeQ, boolean cascadeA) {
		synchronized (ids) {
			if (!cascadeQ) {
				questionManager.removeOwner(ids);
			}
			if (!cascadeA) {
				answerManager.removeOwner(ids);
			}

			kickUsers(ids);
			for (String id : ids) {
				userDao.remove(id);
			}
		}
	}

	public void switchRole(String[] ids) {
		User u;
		Role generalRole = roleManager.getGeneralRole();
		Role adminRole = roleManager.getRoleAdmin();
		for (String id : ids) {
			u = userDao.get(id);
			if (u.getRole().equals(generalRole)) {
				u.setRole(adminRole);
			} else {
				kickUsers(new String[] { id });
				u.setRole(generalRole);
			}

			userDao.save(u);
		}
	}
}
