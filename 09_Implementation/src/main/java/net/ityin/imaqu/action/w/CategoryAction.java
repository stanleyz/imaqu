/**
 * (c)Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.action.w;

import java.util.List;

import net.ityin.imaqu.action.GenericAction;
import net.ityin.imaqu.model.Category;
import net.ityin.imaqu.service.CategoryManager;
import net.ityin.imaqu.util.SystemConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * This class ...
 * 
 * @author <a href="mailto:phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
@Controller
@Scope("prototype")
public class CategoryAction extends GenericAction {
	private static final long serialVersionUID = 4881822568264846830L;
	private static final int MAX_RESULTS = SystemConstant.MAX_RESULTS_TEN;

	@Autowired
	private CategoryManager categoryManager;
	private String cid;
	private Category category;
	private List<Category> categories;
	private List<Category> ancestorCategories;
	private String fname;

	public String getChildrenById() {
		this.categories = categoryManager.getChildrenById(cid);
		return SUCCESS;
	}

	public String listCategory() {
		category = categoryManager.get(cid);
		ancestorCategories = categoryManager.getAncestors(cid);
		categories = categoryManager.listCategory(cid);
		if (categories.indexOf(category) >= 0) {
			category = ancestorCategories.get(ancestorCategories.size() - 2);
		}
		return SUCCESS;
	}

	public String getTopPopular() {
		categories = categoryManager.getTopPopularCategories(MAX_RESULTS);
		return SUCCESS;
	}

	public String get() {
		category = categoryManager.get(cid);
		return SUCCESS;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public List<Category> getAncestorCategories() {
		return ancestorCategories;
	}

	public Category getCategory() {
		return category;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
}
