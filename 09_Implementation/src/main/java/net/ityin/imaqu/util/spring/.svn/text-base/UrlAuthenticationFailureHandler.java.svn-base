/**
 * Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.util.spring;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ityin.imaqu.util.SystemConstant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

/**
 * @author <a href="phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
public class UrlAuthenticationFailureHandler implements
		AuthenticationFailureHandler, InitializingBean {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private String failureUrl;
	private boolean forwardToDestination = false;
	private boolean allowSessionCreation = true;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		saveException(request, exception);

		if (forwardToDestination) {
			logger.debug("Forwarding to " + failureUrl);

			request.getRequestDispatcher(failureUrl).forward(request, response);
		} else {
			logger.debug("Redirecting to " + failureUrl);
			redirectStrategy.sendRedirect(request, response, failureUrl);
		}
	}

	/**
	 * Caches the {@code AuthenticationException} for use in view rendering.
	 * <p>
	 * If {@code forwardToDestination} is set to true, request scope will be
	 * used, otherwise it will attempt to store the exception in the session. If
	 * there is no session and {@code allowSessionCreation} is {@code true} a
	 * session will be created. Otherwise the exception will not be stored.
	 */
	protected final void saveException(HttpServletRequest request,
			AuthenticationException exception) {
		HttpSession session = request.getSession(allowSessionCreation);

		if (forwardToDestination) {
			request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
					exception);
		} else {
			session.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
					exception);
		}

		/*
		 * Add/update the LOGIN_ERROR and LOGIN_ERROR_TIMES attribute in
		 * session. <br /> This will be deleted on AuthenticationSucessHandler.
		 * 
		 * The LOGIN_ERROR attribute will be changed on the action so that it
		 * can be used to differentiate the refresh and error login actions.
		 */
		session.setAttribute(SystemConstant.LOGIN_ERROR, true);
		int i = 1;
		if (session.getAttribute(SystemConstant.LOGIN_ERROR_TIMES) != null) {
			i = (Integer) session
					.getAttribute(SystemConstant.LOGIN_ERROR_TIMES);
			session.setAttribute(SystemConstant.LOGIN_ERROR_TIMES, ++i);
		} else {
			session.setAttribute(SystemConstant.LOGIN_ERROR_TIMES, i);
		}
	}

	public String getFailureUrl() {
		return failureUrl;
	}

	public void setFailureUrl(String failureUrl) {
		this.failureUrl = failureUrl;
	}

	public boolean isForwardToDestination() {
		return forwardToDestination;
	}

	public void setForwardToDestination(boolean forwardToDestination) {
		this.forwardToDestination = forwardToDestination;
	}

	public boolean isAllowSessionCreation() {
		return allowSessionCreation;
	}

	public void setAllowSessionCreation(boolean allowSessionCreation) {
		this.allowSessionCreation = allowSessionCreation;
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	public void afterPropertiesSet() throws Exception {
		Assert.isTrue(UrlUtils.isValidRedirectUrl(failureUrl), "'" + failureUrl
				+ "' is not a valid redirect URL");
	}

}
