/**
 * (c)Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.util.spring;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.StringUtils;

/**
 * This class is used only Sring Security 3.0.x. Since 3.1.x,
 * CookieClearingLogoutHandler and 'delete-cookies' attribute to the 'logout'
 * namespace element, please use that if you are using 3.1.x.
 * 
 * @author <a href="mailto:phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
public class UrlLogoutCookieDeletedSuccessHandler extends
		AbstractAuthenticationTargetUrlRequestHandler implements
		LogoutSuccessHandler {
	private String deletedCookies = null;

	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		cancelCookie(request, response);
		super.handle(request, response, authentication);
	}

	/**
	 * Sets a "cancel cookie" (with maxAge = 0) on the response to disable
	 * persistent logins.
	 * 
	 * @param request
	 * @param response
	 */
	protected void cancelCookie(HttpServletRequest request,
			HttpServletResponse response) {
		if (StringUtils.hasText(deletedCookies)) {
			String[] cookieNames = StringUtils
					.commaDelimitedListToStringArray(deletedCookies);
			for (String cookieName : cookieNames) {
				this.deleteCookie(request, response, cookieName);
			}
		}
	}

	protected void deleteCookie(HttpServletRequest request,
			HttpServletResponse response, String cookieName) {
		logger.debug("Cancelling cookie");
		Cookie cookie = new Cookie(cookieName, null);
		cookie.setMaxAge(0);
		cookie.setPath(getCookiePath(request));

		response.addCookie(cookie);
	}

	private String getCookiePath(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		return contextPath.length() > 0 ? contextPath : "/";
	}

	public void setDeletedCookies(String deletedCookies) {
		this.deletedCookies = deletedCookies;
	}
}
