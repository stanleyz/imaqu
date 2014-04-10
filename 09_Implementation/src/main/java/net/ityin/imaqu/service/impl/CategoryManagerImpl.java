/**
 * (c)Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.ityin.imaqu.dao.CategoryDao;
import net.ityin.imaqu.model.Category;
import net.ityin.imaqu.service.CategoryManager;
import net.ityin.imaqu.util.SystemConstant;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class ...
 * 
 * @author <a href="mailto:phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
@Service(value = "categoryManager")
public class CategoryManagerImpl extends GenericManagerImpl<Category, String>
		implements CategoryManager {
	private CategoryDao categoryDao;

	@Autowired
	public CategoryManagerImpl(CategoryDao categoryDao) {
		super(categoryDao);
		this.categoryDao = categoryDao;
	}

	public List<Category> getChildrenById(String id) {
		return categoryDao.getChildrenById(id);
	}

	public Category getD() {
		return categoryDao.getD();
	}

	public List<Category> getTopPopularCategories(int maxResults) {
		return categoryDao.getTopPopularCategories(maxResults);
	}

	public List<String> findChildrenIds(String id) {
		List<String> categoryIds = new ArrayList<String>();
		categoryIds.add(id);
		for (Category category : getChildrenById(id)) {
			categoryIds.addAll(findChildrenIds(category.getId()));
		}
		return categoryIds;
	}

	public List<Category> listCategory(String cid) {
		List<Category> categories = getChildrenById(cid);
		if (categories.size() == 0) {
			Category c = get(cid);
			categories = getChildrenById(c.getParentId());
		}
		return categories;
	}

	public List<Category> getAncestors(String cid) {
		List<Category> categories = new ArrayList<Category>();
		if (cid == null || cid.equals("")) {
			return categories;
		}
		Category c = categoryDao.get(cid);
		String[] ancestorsId = StringUtils.split(c.getAncestorsId(),
				SystemConstant.DELIMITOR_COMMA);
		for (String id : ancestorsId) {
			categories.add(categoryDao.get(id));
		}
		categories.add(c);
		return categories;
	}

	@Override
	public Category get(String id) {
		return categoryDao.get(id);
	}
}
