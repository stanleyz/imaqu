/**
 * Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.action;

import java.util.Locale;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.interceptor.I18nInterceptor;

/**
 * @author <a href="phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
@Controller
public class LocaleAction extends GenericAction {
	private static final long serialVersionUID = 7056137917143692428L;
	private String lang;
	private String r;
	private String strutsSessionAttr = I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE;

	public String setLocale() {
		Locale l = Locale.SIMPLIFIED_CHINESE;
		if (lang.equals("zh_TW")) {
			l = Locale.TRADITIONAL_CHINESE;
		} else if (lang.equals("en")) {
			l = Locale.ENGLISH;
		}

		getSession().setAttribute(strutsSessionAttr, l);

		// Test the java.net.URLDecoder and
		// org.apache.commons.codec.net.URLCodec to decode the url, not succeed.
		// Looks like freemarker doesn't really encode the URL properly.
		// But it is fine to use the following line to decode for now, will keep
		// eyes on this.
		r = r.replace("&amp;", "&");
		return SUCCESS;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}
}
