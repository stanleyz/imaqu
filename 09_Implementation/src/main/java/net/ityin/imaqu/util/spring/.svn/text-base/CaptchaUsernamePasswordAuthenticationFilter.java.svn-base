/**
 * (c)Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.util.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ityin.imaqu.util.captcha.CaptchaUtil;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class is used add verification code check code.
 * 
 * @author <a href="mailto:phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
public class CaptchaUsernamePasswordAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {
	private String captchaParameter = "captcha";

	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		if (!CaptchaUtil.validateSimpleCaptcha(request)) {
			throw new CaptchaErrorException("The captcha is wrong!");
		}
	
		return super.attemptAuthentication(request, response);
	}

	public String getCaptchaParameter() {
		return captchaParameter;
	}

	public void setCaptchaParameter(String captchaParameter) {
		this.captchaParameter = captchaParameter;
	}
}
