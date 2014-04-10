/**
 * (c)Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.dao.hibernate;

import java.util.List;

import net.ityin.imaqu.dao.CategoryDao;
import net.ityin.imaqu.model.Category;
import net.ityin.imaqu.util.SystemConstant;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

/**
 * This class ...
 * 
 * @author <a href="mailto:phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
@SuppressWarnings("unchecked")
@Repository(value = "categoryDao")
public class CategoryDaoHibernate extends GenericDaoHibernate<Category, String>
		implements CategoryDao {
	private static final String rootId = SystemConstant.ROOT_CATEGORY_ID;
	private static final String rootName = SystemConstant.ROOT_CATEGORY_NAME;

	public CategoryDaoHibernate() {
		super(Category.class);
	}

	public List<Category> getChildrenById(String id) {
		if (id == null || id.equals("")) {
			id = rootId;
		}
		Object[] params = new Object[] { id };
		return this.findByNamedQuery("getChildrenById", params);
	}

	public Category getD() {
		return (Category) this
				.findByNamedQuery("getDCategory", (Object[]) null).get(0);
	}

	public List<Category> getTopPopularCategories(int maxResults) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Category.class)
				.addOrder(Order.desc("questionsCount"));
		return findByCriteria(criteria, 0, maxResults);
	}

	@Override
	public Category get(String id) {
		Category c;
		if (id == null || id.equals("") || id.equals(rootId)) {
			c = new Category();
			c.setId(rootId);
			c.setName(rootName);
			c.setAncestorsId("");
		} else {
			c = super.get(id);
		}

		return c;
	}
}
