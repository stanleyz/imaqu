/**
 * (c)Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.service;

import java.util.List;

import net.ityin.imaqu.model.Category;

/**
 * This class ...
 * 
 * @author <a href="mailto:phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
public interface CategoryManager extends GenericManager<Category, String> {
	public List<Category> getChildrenById(String id);

	public List<String> findChildrenIds(String id);

	public Category getD();

	public List<Category> getTopPopularCategories(int maxResults);

	public List<Category> listCategory(String cid);

	public List<Category> getAncestors(String cid);
}
