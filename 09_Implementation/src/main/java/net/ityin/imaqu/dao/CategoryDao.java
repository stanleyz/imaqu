/**
 * (c)Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.dao;

import java.util.List;

import net.ityin.imaqu.model.Category;

/**
 * This class ...
 * 
 * @author <a href="mailto:phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
public interface CategoryDao extends GenericDao<Category, String> {
	public List<Category> getChildrenById(String id);

	public Category getD();

	public List<Category> getTopPopularCategories(int maxResults);
}
