/**
 * (c)Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.util.freemarker;

import java.util.Date;
import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * This class ...
 * 
 * @author <a href="mailto:phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
public class NowMethod implements TemplateMethodModel {

	/*
	 * (non-Javadoc)
	 * 
	 * @see freemarker.template.TemplateMethodModel#exec(java.util.List)
	 */
	@SuppressWarnings("rawtypes")
	public Object exec(List arguments) throws TemplateModelException {
		return new Date();
	}
}
