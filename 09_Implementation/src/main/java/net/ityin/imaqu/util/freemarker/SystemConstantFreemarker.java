/**
 * (c)Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.util.freemarker;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * This class ...
 * 
 * @author <a href="mailto:phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
public class SystemConstantFreemarker implements TemplateHashModel {
	public TemplateModel get(String key) throws TemplateModelException {
		TemplateHashModel staticModels = BeansWrapper.getDefaultInstance()
				.getStaticModels();
		TemplateHashModel constants = (TemplateHashModel) staticModels
				.get("net.ityin.imaqu.util.SystemConstant");
		return constants.get(key);
	}

	public boolean isEmpty() throws TemplateModelException {
		return false;
	}
}
