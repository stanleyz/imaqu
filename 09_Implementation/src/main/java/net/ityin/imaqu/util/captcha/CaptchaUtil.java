/**
 * (c)Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.util.captcha;

import javax.servlet.http.HttpServletRequest;

import net.ityin.imaqu.util.SystemConstant;
import nl.captcha.Captcha;

/**
 * This class ...
 * 
 * @author <a href="mailto:phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
public class CaptchaUtil {
	public static boolean validateSimpleCaptcha(HttpServletRequest request) {
		return validateSimpleCaptcha(request,
				request.getParameter(SystemConstant.CAPTCHA_PARAMETER));
	}

	public static boolean validateSimpleCaptcha(HttpServletRequest request,
			String answer) {
		Captcha captcha = (Captcha) request.getSession().getAttribute(
				Captcha.NAME);
		if (captcha == null || answer == null) {
			return true;
		}
		if (captcha.isCorrect(answer)) {
			return true;
		} else {
			return false;
		}
	}
}
