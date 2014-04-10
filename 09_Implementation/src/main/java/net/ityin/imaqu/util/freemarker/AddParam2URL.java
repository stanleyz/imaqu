/**
 * Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.util.freemarker;

import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * @author <a href="phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
public class AddParam2URL implements TemplateMethodModel {

	/**
	 * The sequence of argments are: <br>
	 * 1. url: source URL<br>
	 * 2. param: the params needs to be added to url.<br>
	 * 3. removedValues: the param will not be added to url if the value equals
	 * to correpsonding removedValue, for instance, if param is "first=0" and
	 * corresponding removedValue is "0", then the original url are returned.
	 * (Not implenment succefully) <br>
	 * <br>
	 * The second argment "param" and third argument "removedValues" are strings
	 * delimited by "&" and correspond to each other respectively like two
	 * arrays.
	 */
	@SuppressWarnings("rawtypes")
	public Object exec(List arguments) throws TemplateModelException {
		if (arguments.size() != 3)
			return null;
		StringBuilder url = new StringBuilder(arguments.get(0).toString().replaceAll("&amp;", "&"));
		String[] params = arguments.get(1).toString().split("&");
		String[] removedValues = arguments.get(2).toString().split("&");

		int i = 0, j = -1;
		String s = "";
		for (String param : params) {
			j++;
			i = param.indexOf("=");
			String leftPart = param.substring(0, i + 1);

			// Match the arguments like ("test?a=c","a=b","b") and
			// ("test?b=b&a=c","a=b","b")
			if (removedValues[j] == null
					|| removedValues[j].equals(param.substring(i + 1))) {
				if (url.indexOf(leftPart) > 0) {
					s = url.toString().replaceAll(leftPart + "[^&]+[&$]?", "");
					if (s.endsWith("?") || s.endsWith("&")) {
						s = s.substring(0, s.length() - 1);
					}
					url.replace(0, url.length(), s);
					continue;
				} else {
					continue;
				}
			}

			// Match the arguments like ("test?a=a","a=b")
			if (url.indexOf(leftPart) > 0) {
				s = url.toString().replaceAll(leftPart + "[^&]+", param);
				url.replace(0, url.length(), s);
				continue;
			}

			// Match the argments like ("test?b=b","c=c")
			if (url.indexOf("?") > 0) {
				url.append("&");
			} else {
				url.append("?");
			}
			url.append(param);
		}

		return url.toString();
	}
}
