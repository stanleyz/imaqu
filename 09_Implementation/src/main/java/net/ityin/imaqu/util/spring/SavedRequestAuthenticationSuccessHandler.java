/**
 * Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.util.spring;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ityin.imaqu.model.User;
import net.ityin.imaqu.model.proxy.UserProxy;
import net.ityin.imaqu.service.UserManager;
import net.ityin.imaqu.util.SystemConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * @author <a href="phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
public class SavedRequestAuthenticationSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {
	@Autowired
	private UserManager userManager;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute(SystemConstant.LOGIN_ERROR);
		session.removeAttribute(SystemConstant.LOGIN_ERROR_TIMES);

		/*
		 * Update the login info in database.
		 */
		User user = userManager.get(((UserProxy) authentication.getPrincipal())
				.getUser().getId());
		Calendar now = Calendar.getInstance();
		Calendar lastLogin = Calendar.getInstance();
		lastLogin.setTime(user.getLastLogin());
		/*
		 * lastLogin.add(Calendar.DAY_OF_YEAR, 1);
		 * if(now.get(Calendar.DAY_OF_YEAR) ==
		 * lastLogin.get(Calendar.DAY_OF_YEAR)) {
		 */
		if ((now.get(Calendar.DAY_OF_YEAR) != lastLogin
				.get(Calendar.DAY_OF_YEAR))
				|| (now.get(Calendar.YEAR) != lastLogin.get(Calendar.YEAR))) {
			user.setScore(user.getScore() + SystemConstant.USER_SCORE_FIVE);
		}
		user.setLastLogin(now.getTime());
		user.setLastIP(request.getRemoteAddr());
		userManager.save(user);
		userManager.setAuthentication(user);

		super.onAuthenticationSuccess(request, response, authentication);
	}
}
